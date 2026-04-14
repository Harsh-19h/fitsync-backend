package com.project.fitness.service;

import com.project.fitness.model.User;
import com.project.fitness.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.project.fitness.dto.UserDTO;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO saveUser(User user){
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
}
