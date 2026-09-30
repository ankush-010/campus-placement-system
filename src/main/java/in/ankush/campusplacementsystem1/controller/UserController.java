package in.ankush.campusplacementsystem1.controller;

import in.ankush.campusplacementsystem1.dto.LoginRequest;
import in.ankush.campusplacementsystem1.model.User;
import in.ankush.campusplacementsystem1.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import in.ankush.campusplacementsystem1.dto.LoginResponse;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    public String adminAccess(@RequestParam String role) {

        if (!role.equalsIgnoreCase("ADMIN")) {
            throw new RuntimeException("Access denied. Admin role required");
        }

        return "Welcome Admin";
    }

    @GetMapping("/student")
    public String studentAccess(@RequestParam String role) {

        if (!role.equalsIgnoreCase("STUDENT")) {
            throw new RuntimeException("Access denied. Student role required");
        }

        return "Welcome Student";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid username or password");
        }

        return new LoginResponse(
                "Login successful",
                user.getUsername(),
                user.getRole()
        );
    }
}