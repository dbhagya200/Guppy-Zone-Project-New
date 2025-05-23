package lk.ijse.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lk.ijse.backend.dto.*;
import lk.ijse.backend.service.UserService;
import lk.ijse.backend.service.imple.MailSendServiceImpl;
import lk.ijse.backend.service.imple.UserServiceImpl;
import lk.ijse.backend.util.JwtUtil;
import lk.ijse.backend.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    private final JwtUtil jwtUtil;

    private final MailSendServiceImpl mailSendService;

    //constructor injection
    public UserController(UserService userService, UserServiceImpl userServiceImpl, JwtUtil jwtUtil, MailSendServiceImpl mailSendService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.mailSendService = mailSendService;
    }
    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            int res = userService.saveUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    mailSendService.sendRegisteredEmail(userDTO.getUsername(), userDTO.getEmail(), "Registered Successfully!");
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }


//
//    @PutMapping("/update")
//    public ResponseEntity<ProfileDTO> updateProfile(
//            @RequestHeader("Authorization") String token,
//            @ModelAttribute ProfileDataDTO profileDataDTO) {
//
//        // Extract email from JWT token
//        String email = jwtUtil.extractEmailFromToken(token.substring(7));
//
//        // Update profile
//        ProfileDTO updatedProfile = userService.updateUserProfile(email, profileDataDTO);
//
//        return ResponseEntity.ok(updatedProfile);
//    }
    @GetMapping("/get")
    public String getUserProfile(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7); // Remove "Bearer " prefix
        String username = JwtUtil.JWT_TOKEN_VALIDITY + jwtUtil.getUsernameFromToken(token);
        return "User Email: " + username;
    }

//    @PutMapping("/update")
//    public ResponseEntity<ProfileDTO> updateProfile(@RequestHeader("Authorization") String token,
//                                                     @ModelAttribute ProfileDataDTO profileDataDTO) {
//
//        // Extract email from JWT token
//        String email = jwtUtil.getUsernameFromToken(token.substring(7));
//
//        // Update profile
//        ProfileDTO updatedProfile = userService.updateUserProfile(email, profileDataDTO);
//        return ResponseEntity.ok(updatedProfile);
//    }


}
