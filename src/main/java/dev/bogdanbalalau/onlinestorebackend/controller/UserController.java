package dev.bogdanbalalau.onlinestorebackend.controller;

import dev.bogdanbalalau.onlinestorebackend.dto.LoginDTO;
import dev.bogdanbalalau.onlinestorebackend.dto.RegisterDTO;
import dev.bogdanbalalau.onlinestorebackend.entity.Order;
import dev.bogdanbalalau.onlinestorebackend.entity.User;
import dev.bogdanbalalau.onlinestorebackend.entity.UserRole;
import dev.bogdanbalalau.onlinestorebackend.service.OrderService;
import dev.bogdanbalalau.onlinestorebackend.service.UserService;
import dev.bogdanbalalau.onlinestorebackend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    @Autowired
    private OrderService orderService;

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

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody User user, @PathVariable Integer id) {
        Optional<User> optionalUser = this.userService.findById(id);
        if (optionalUser.isPresent()) {
            User usr = optionalUser.get();
            usr.setUsername(user.getUsername());
            usr.setEmail(user.getEmail());
            usr.setPassword(user.getPassword());

            ApiResponse response = new ApiResponse.Builder()
                    .status(200)
                    .message("User updated successfully")
                    .data(userService.updateUser(usr))
                    .build();
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse.Builder()
                    .status(200)
                    .message("User not found")
                    .data(userService.updateUser(null))
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        Optional<User> user = this.userService.findById(id);
        if (user.isPresent()) {
            List<Order> orders = this.orderService.findByUser(user.get());
            this.orderService.deleteAll(orders);
            this.userService.deleteUser(user.get());
            ApiResponse response = new ApiResponse.Builder()
                    .status(200)
                    .message("User deleted successfully")
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        }
        ApiResponse response = new ApiResponse.Builder()
                .status(404)
                .message("User not found")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginDTO loginDTO) {
        Optional<User> optionalUser = this.userService.findByEmail(loginDTO.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(loginDTO.getPassword())) {
                ApiResponse response = new ApiResponse.Builder()
                        .status(200)
                        .message("User authenticated successfully")
                        .data(user)
                        .build();

                return ResponseEntity.ok(response);
            }
        }
        ApiResponse response = new ApiResponse.Builder()
                .status(404)
                .message("User not found")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
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
