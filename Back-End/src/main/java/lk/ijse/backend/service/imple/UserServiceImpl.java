package lk.ijse.backend.service.imple;


import jakarta.servlet.annotation.MultipartConfig;
import lk.ijse.backend.dto.ProfileDTO;
import lk.ijse.backend.dto.ProfileDataDTO;
import lk.ijse.backend.dto.UserDTO;
import lk.ijse.backend.entity.Profile;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repository.ProfileRepo;
import lk.ijse.backend.repository.UserRepo;
import lk.ijse.backend.service.UserService;
import lk.ijse.backend.util.JwtUtil;
import lk.ijse.backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepo userRepository;

   @Autowired
    private ModelMapper modelMapper;
   @Autowired
   private ProfileServiceImpl profileService;
   @Autowired
   private ProfileRepo profileRepo;
   @Autowired
   private JwtUtil jwtUtil;

   private static final String FRONTEND_DIR = "static/images/";
   private static final String ITEMS = "items/";
   private static final String PROFILE = "profileImages/";
    private static final String DEFAULT_DIRECTORY = "/home/dilini/Documents/IJSE-Institute of Software Engineering/2nd Semester/AAD/Spring/Guppy-Zone-Aquarium-Project-New/Front-End/static/";
    private static final String DEFAULT_IMAGE_DIRECTORY = DEFAULT_DIRECTORY + "images/";
    private static final String ITEM_UPLOAD_DIR = DEFAULT_IMAGE_DIRECTORY + ITEMS;
    private static final String PROFILE_UPLOAD_DIR = DEFAULT_IMAGE_DIRECTORY + PROFILE;

    static {
        createIfNotExistDirectory(ITEM_UPLOAD_DIR);
        createIfNotExistDirectory(PROFILE_UPLOAD_DIR);
    }
    @Override
    public User getUserProfile(String email) {
        User user = userRepository.findByEmail(email);
        Profile profile = profileRepo.findByUser(user);
        return modelMapper.map(profile, User.class);
    }

    @Override
    public String saveItemImage(MultipartFile image) {
        String path = saveImage(image, ITEM_UPLOAD_DIR, ITEMS);
        return FRONTEND_DIR + path;
    }

    @Override
    public String saveProfileImage(MultipartFile image) {
        String path = saveImage(image, PROFILE_UPLOAD_DIR, PROFILE);
        return FRONTEND_DIR + path;
    }

    @Override
    public UserDTO getUserDTOByToken(String token) {
        String username = jwtUtil.getUsernameFromToken(token);
        return getUserByEmail(username);
    }

    public String saveImage(MultipartFile image, String savingDir, String savedFolderName) {
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(savingDir + fileName);

        try {
            image.transferTo(filePath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedFolderName + fileName;
    }
    static void createIfNotExistDirectory(String directory) {
        File uploadDir = new File(directory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//        System.out.println("user = " + user);
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                new ArrayList<>()
//        );
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found for email: " + email);
        }

        // Convert String role to GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getRole() != null && !user.getRole().isEmpty()) {
            // If role is stored as "ROLE_SELLER", remove "ROLE_" prefix
            String authority = user.getRole().startsWith("ROLE_")
                    ? user.getRole().substring(5)  // Removes "ROLE_"
                    : user.getRole();

            authorities.add(new SimpleGrantedAuthority(authority));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }
//    public ProfileDTO loadProfileDetailsByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username);
//        return modelMapper.map(user,ProfileDTO.class);
//    }

    public ProfileDTO loadProfileDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        // Create custom type mapping
        modelMapper.typeMap(User.class, ProfileDTO.class).addMappings(mapper -> {
            // Map specific fields if names don't match
            mapper.map(User::getEmail, ProfileDTO::setEmail);
            // Skip problematic fields
//            mapper.skip(ProfileDTO::setSomeCircularReferenceField);
        });

        return modelMapper.map(user, ProfileDTO.class);
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
    public UserDTO getUserEmailByToken(String token) {
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
        System.out.println("Searching user by email: " + email);

        // Find user by email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found for email: " + email);
        }

        System.out.println("User found: " + user);

        String savedPath = null;
        if (profileDataDTO.getImage() != null){
            savedPath = saveProfileImage(profileDataDTO.getImage());
            user.setImage(savedPath); // Save new image OR keep old one
        }

        // Update user details
        user.setFirstName(profileDataDTO.getFirstName());
        user.setLastName(profileDataDTO.getLastName());
        user.setAddress(profileDataDTO.getAddress());
        user.setContact(profileDataDTO.getContact());


//        user.setImage(fileName); // Save new file name (or retain old one)

        userRepository.save(user); // Save updated user

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setEmail(user.getEmail());
        profileDTO.setName(user.getFirstName() + " " + user.getLastName());
        profileDTO.setAddress(user.getAddress());
        profileDTO.setContact(user.getContact());
        profileDTO.setImage(user.getImage()); // Return filename (not MultipartFile)

        return profileDTO;
    }

    // File storage method
    private String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null; // Return null if no file is uploaded
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get("uploads/profile_images/", fileName);

        try {
            Files.createDirectories(filePath.getParent()); // Ensure directory exists
            file.transferTo(filePath.toFile()); // Save file
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + fileName, e);
        }

        return fileName; // Return only the file name, not full path
    }




}
