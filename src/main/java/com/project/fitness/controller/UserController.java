package com.project.fitness.controller;

import com.project.fitness.model.User;
import com.project.fitness.service.UserService;
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

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
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
