package lk.ijse.backend.service;

import lk.ijse.backend.dto.ProfileDTO;
import lk.ijse.backend.dto.ProfileDataDTO;
import lk.ijse.backend.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProfileService {
    public String saveItemImage(MultipartFile image);
//    ProfileDTO saveProfile(ProfileDataDTO profileDataDTO);
//    ProfileDTO getProfileByUserEmail(String email);
//    ProfileDTO updateProfile(String email, ProfileDataDTO profileDataDTO);
//    void deleteProfile(String email);
//    List<ProfileDTO> getAllProfiles();
}
