package lk.ijse.backend.controller;

import jakarta.validation.Valid;
import lk.ijse.backend.dto.ProfileDTO;
import lk.ijse.backend.dto.ResponseDTO;
import lk.ijse.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile")
@CrossOrigin
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
    public ResponseEntity<ProfileDTO> saveProfile(@RequestBody ProfileDTO profileDTO){
        profileService.saveProfile(profileDTO);
        return ResponseEntity.ok(profileDTO);
    }

    @GetMapping(value = "/get")
    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
    public ResponseEntity<List<ProfileDTO>> getProfile(@RequestBody ProfileDTO profileDTO) {
        List<ProfileDTO> profile = profileService.getProfile();
        return ResponseEntity.ok(profile);
    }
    @PutMapping(value = "/update")
    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
    public ResponseEntity<ProfileDTO> updateUser(@RequestBody @Valid ProfileDTO profileDTO) {
        profileService.updateProfile(profileDTO);
        return ResponseEntity.ok(profileDTO);
    }
}
