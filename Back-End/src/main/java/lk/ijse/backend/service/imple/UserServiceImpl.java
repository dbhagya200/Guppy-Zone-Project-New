package lk.ijse.backend.service.imple;


import lk.ijse.backend.dto.ProfileDTO;
import lk.ijse.backend.dto.ProfileDataDTO;
import lk.ijse.backend.dto.UserDTO;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repository.UserRepo;
import lk.ijse.backend.service.UserService;
import lk.ijse.backend.util.JwtUtil;
import lk.ijse.backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;
@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepo userRepository;

   @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO getCurrentUser(String email) {
        if (userRepository.existsByEmail(email)) {
            User user=userRepository.findByEmail(email);
            return modelMapper.map(user,UserDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public int updateUser(UserDTO userDTO) {
        if (!userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Found;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }

    @Override
    public String getUserEmailByToken(String token) {
        return null;
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        return modelMapper.map(user, UserDTO.class);
    }


    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//            userDTO.setRole("USER");
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }

    @Override
    public ProfileDTO updateUserProfile(String email, ProfileDataDTO profileDataDTO) {
        // Find user by email
        User user = userRepository.findByEmail(email);
        System.out.println("user = " + user);

        // Convert ProfileDataDTO to ProfileDTO
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setEmail(profileDataDTO.getEmail());
        profileDTO.setImage(profileDataDTO.getImage().getOriginalFilename()); // Save image name (you can upload separately)
        profileDTO.setName(profileDataDTO.getFirstName() + " " + profileDataDTO.getLastName());
        profileDTO.setAddress(profileDataDTO.getAddress());
        profileDTO.setContact(profileDataDTO.getContact());

        // Map ProfileDTO to User entity
        modelMapper.map(profileDTO, user);

        // Save updated user
        userRepository.save(user);

        return profileDTO;
    }
}
