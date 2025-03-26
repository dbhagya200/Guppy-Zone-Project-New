package lk.ijse.backend.service;


import lk.ijse.backend.dto.UserDTO;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO getCurrentUser(String email);

    int updateUser(UserDTO userDTO);

}
