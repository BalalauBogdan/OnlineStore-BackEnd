package dev.bogdanbalalau.onlinestorebackend.controller;

import dev.bogdanbalalau.onlinestorebackend.dto.RegisterDTO;
import dev.bogdanbalalau.onlinestorebackend.entity.User;
import dev.bogdanbalalau.onlinestorebackend.entity.UserRole;
import dev.bogdanbalalau.onlinestorebackend.service.UserService;
import dev.bogdanbalalau.onlinestorebackend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllUser() {
        List<User> userList = this.userService.findAll();
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("List with all registered users")
                .data(userList)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setUserRole(UserRole.USER);

        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User registered successfully")
                .data(this.userService.createUser(user))
                .build();

        return ResponseEntity.ok(response);
    }
}
