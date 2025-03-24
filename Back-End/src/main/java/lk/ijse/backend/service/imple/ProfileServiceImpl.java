package lk.ijse.backend.service.imple;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.transaction.Transactional;
import lk.ijse.backend.dto.ProfileDTO;
import lk.ijse.backend.dto.ProfileDataDTO;
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
        // Generate a unique filename
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(ITEM_UPLOAD_DIR + fileName);

        try {
            //save the image
            image.transferTo(filePath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath.toString();
    }

    static void createIfNotExistDirectory(String directory) {
        File uploadDir = new File(directory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // ✅ Create directory if it doesn’t exist
        }
    }
}
