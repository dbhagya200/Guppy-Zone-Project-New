package lk.ijse.backend.controller;

import jakarta.servlet.annotation.MultipartConfig;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/profile")
@CrossOrigin
@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024,
        maxFileSize = 10 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024)
public class ProfileController {

    @Autowired
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @PostMapping(path = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
    public ResponseEntity<ResponseDTO> saveImage(@ModelAttribute ProfileDataDTO profileDataDTO) {

        String savedProfile = profileService.saveItemImage(profileDataDTO.getImage());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(VarList.Created, "Success", savedProfile));
    }

    @PostMapping(path = "/saveProfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
    public ResponseEntity<ResponseDTO> saveProfile(
            @Valid @ModelAttribute ProfileDataDTO profileDataDTO,
            BindingResult bindingResult) throws MethodArgumentNotValidException {

        // Validate input
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        ProfileDTO savedProfile = profileService.saveProfile(profileDataDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(VarList.Created, "Profile created successfully", savedProfile));
    }


}
