package lk.ijse.backend.service;


import lk.ijse.backend.dto.ProfileDTO;
import lk.ijse.backend.dto.ProfileDataDTO;
import lk.ijse.backend.dto.UserDTO;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO getCurrentUser(String email);
    UserDTO getUserByEmail(String email);

    int updateUser(UserDTO userDTO);
    String getUserEmailByToken(String token);
    ProfileDTO updateUserProfile(String email, ProfileDataDTO profileDataDTO);

}
