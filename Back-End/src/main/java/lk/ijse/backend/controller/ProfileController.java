package lk.ijse.backend.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lk.ijse.backend.dto.ProfileDTO;
import lk.ijse.backend.dto.ProfileDataDTO;
import lk.ijse.backend.dto.ResponseDTO;
import lk.ijse.backend.service.ProfileService;
import lk.ijse.backend.service.UserService;
import lk.ijse.backend.util.JwtUtil;
import lk.ijse.backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/profile")
@CrossOrigin
@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024,
        maxFileSize = 10 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024)
public class ProfileController {

    @Autowired
    private final ProfileService profileService;
    @Autowired
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public ProfileController(ProfileService profileService, UserService userService, JwtUtil jwtUtil) {
        this.profileService = profileService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping(path = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
    public ResponseEntity<ResponseDTO> saveImage(@ModelAttribute ProfileDataDTO profileDataDTO) {

        String savedProfile = userService.saveItemImage(profileDataDTO.getImage());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(VarList.Created, "Success", savedProfile));
    }

    @PutMapping(path = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProfileDTO> updateProfile(@RequestHeader("Authorization") String token,
                                                    @ModelAttribute ProfileDataDTO profileDataDTO) {

        // Extract email from JWT token
        String email = jwtUtil.getUsernameFromToken(token.substring(7));

        // Update profile
        ProfileDTO updatedProfile = userService.updateUserProfile(email, profileDataDTO);
        return ResponseEntity.ok(updatedProfile);
    }

}
