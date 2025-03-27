package lk.ijse.backend.service.imple;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.transaction.Transactional;
import lk.ijse.backend.dto.ProfileDTO;
import lk.ijse.backend.dto.ProfileDataDTO;
import lk.ijse.backend.dto.UserDTO;
import lk.ijse.backend.entity.Profile;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repository.ProfileRepo;
import lk.ijse.backend.repository.UserRepo;
import lk.ijse.backend.service.ProfileService;
import lk.ijse.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    private static final String DEFAULT_DIRECTORY = "/home/dilini/Documents/IJSE-Institute of Software Engineering/2nd Semester/AAD/Spring/Guppy-Zone-Aquarium-Project-New/Back-End/src/main/resources/static";
    private static final String DEFAULT_IMAGE_DIRECTORY =  "/home/dilini/Documents/IJSE-Institute of Software Engineering/2nd Semester/AAD/Spring/Guppy-Zone-Aquarium-Project-New/Back-End/src/main/resources/static/images";
    private static final String ITEM_UPLOAD_DIR = DEFAULT_IMAGE_DIRECTORY + "/items";
    private static final String PROFILE_UPLOAD_DIR = DEFAULT_IMAGE_DIRECTORY + "/profileimages";

    static {
        createIfNotExistDirectory(DEFAULT_DIRECTORY);
        createIfNotExistDirectory(DEFAULT_IMAGE_DIRECTORY);
        createIfNotExistDirectory(ITEM_UPLOAD_DIR);
        createIfNotExistDirectory(PROFILE_UPLOAD_DIR);
    }


    @Override
    public String saveItemImage(MultipartFile image) {
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(ITEM_UPLOAD_DIR + fileName);

        try {
            image.transferTo(filePath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath.toString();
    }

    @Override
    public ProfileDTO saveProfile(UserDTO userDTO) {
//      User user = new User();
//      user.setUid(userDTO.get());
//      user.setUsername(userDTO.getUsername());
//      user.setPassword(userDTO.getPassword());
//      user.setEmail(userDTO.getEmail());
//      user.setRole(userDTO.getRole());
//      user.setImage(userDTO.getImage());
//      user.setAddress(userDTO.getAddress());
//      user.setContact(userDTO.getContact());
//      userRepo.save(user);

        return null;
    }

//    @Override
//    public ProfileDTO saveProfile(ProfileDataDTO profileDataDTO) {
//        // Validate image
//        if (profileDataDTO.getImage() == null || profileDataDTO.getImage().isEmpty()) {
//            throw new IllegalArgumentException("Profile image is required");
//        }
//
//        // Save image
//        String imagePath = null;
//        try {
//            imagePath = saveProfileImage(profileDataDTO.getImage());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Map and save profile
//        Profile profile = new Profile();
//        profile.setFirstName(profileDataDTO.getFirstName());
//        profile.setLastName(profileDataDTO.getLastName());
//        profile.setContact(profileDataDTO.getContact());
//        profile.setAddress(profileDataDTO.getAddress());
//        profile.setImage(imagePath);
//        System.out.println("Email  ; "+ profileDataDTO.getEmail() );
//        // Set user (assuming you have this relationship)
//        UserDTO user = userService.getCurrentUser(profileDataDTO.getEmail()); // Or fetch by ID/email
//        System.out.println("user : "+user);
//        profile.setUser(modelMapper.map(user, User.class));
//        System.out.println("profile : "+ profile);
//        // Save to database
//        Profile savedProfile = profileRepo.save(profile);
//
//        // Convert to DTO
//        ProfileDTO profileDTO = new ProfileDTO();
//        profileDTO.setName(savedProfile.getFirstName() + " " + savedProfile.getLastName());
//        profileDTO.setEmail(savedProfile.getUser().getEmail());
//        profileDTO.setContact(savedProfile.getContact());
//        profileDTO.setAddress(savedProfile.getAddress());
//        profileDTO.setImage(savedProfile.getImage());
//
//        return profileDTO;
//    }

//    private String saveProfileImage(MultipartFile image) throws IOException {
//        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
//        Path uploadPath = Paths.get(PROFILE_UPLOAD_DIR).toAbsolutePath().normalize();
//        Path filePath = uploadPath.resolve(fileName);
//
//        // Create directories if they don't exist
//        Files.createDirectories(uploadPath);
//
//        // Save file
//        try (InputStream inputStream = image.getInputStream()) {
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        }
//
//        return "/profile-images/" + fileName; // Return relative path
//    }

    static void createIfNotExistDirectory(String directory) {
        File uploadDir = new File(directory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // ✅ Create directory if it doesn’t exist
        }
    }



}
