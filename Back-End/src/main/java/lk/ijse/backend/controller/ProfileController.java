package lk.ijse.backend.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import lk.ijse.backend.dto.ProfileDTO;
import lk.ijse.backend.dto.ProfileDataDTO;
import lk.ijse.backend.dto.ResponseDTO;
import lk.ijse.backend.service.ProfileService;
import lk.ijse.backend.service.UserService;
import lk.ijse.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile")
@CrossOrigin
@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024,
        maxFileSize = 10 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024)
public class ProfileController {

//    @Autowired
//    private final ProfileService profileService;
//
//
//    @PostMapping(path = "save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
//    public ResponseEntity<ProfileDTO> saveProfile(@RequestBody ProfileDTO profileDTO,
//            @ModelAttribute ProfileDataDTO profileDataDTO) {
//
//        ProfileDTO savedProfile = profileService.saveItemImage(profileDataDTO);
//        return ResponseEntity.ok(savedProfile);
//    }
//
//    @GetMapping(path = "/get")
//    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
//    public ResponseEntity<List<ProfileDTO>> getProfile() {
//        List<ProfileDTO> profile = profileService.getProfile();
//        return ResponseEntity.ok(profile);
//    }
//    @PutMapping(path = "/update")
//    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
//    public ResponseEntity<ProfileDTO> updateUser(@RequestBody @Valid ProfileDTO profileDTO) {
//        profileService.updateProfile(profileDTO);
//        return ResponseEntity.ok(profileDTO);
//    }
}
