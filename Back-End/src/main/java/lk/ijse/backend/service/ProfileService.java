package lk.ijse.backend.service;

import lk.ijse.backend.dto.ProfileDTO;
import lk.ijse.backend.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProfileService {
    void updateProfile( ProfileDTO profileDTO);
    void saveProfile( ProfileDTO profileDTO);
    List<ProfileDTO> getProfile();
}
