package com.project.fitness.service;

import com.project.fitness.dto.AuthResponse;
import com.project.fitness.model.User;
import com.project.fitness.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.fitness.dto.UserDTO;
import java.util.stream.Collectors;
import com.project.fitness.security.JwtUtil;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserDTO saveUser(User user){

        //encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id).orElse(null);

        if(user == null) return null;

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public void deleteUser(Long id){

        userRepository.deleteById(id);
    }

    public AuthResponse loginUser(String email, String password){

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new RuntimeException("Invalid email or password");
        }
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

       String token = jwtUtil.generateToken((user.getEmail()));
        return new AuthResponse(token);
    }
}
