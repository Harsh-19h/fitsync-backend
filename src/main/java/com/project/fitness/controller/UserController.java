package com.project.fitness.controller;

import com.project.fitness.dto.AuthResponse;
import com.project.fitness.dto.LoginRequest;
import com.project.fitness.model.User;
import com.project.fitness.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.project.fitness.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserDTO createUser( @Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return userService.loginUser(request.getEmail(),request.getPassword());
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/me")
    public UserDTO getLoggedInUser(HttpServletRequest request){
        String email = (String) request.getAttribute("email");
        return userService.getUserByEmail(email);
    }
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
