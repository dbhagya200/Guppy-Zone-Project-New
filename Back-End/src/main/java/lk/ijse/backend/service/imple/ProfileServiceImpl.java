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
import org.springframework.context.annotation.Lazy;
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
    @Lazy
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;


}
