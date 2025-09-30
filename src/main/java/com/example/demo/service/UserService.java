package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    public List<Users> getAllUsers() {
        return repo.findAll();
    }

    public Users register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public Users getUserById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Users getUserByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    public String verify(Users user) {
        Authentication authentication = authManager.
                authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "Fail";
    }

    public Users updateUser(Users user) {
        Users curr_user = getUserById(user.getId());

        if(curr_user != null){
            curr_user.setUsername(user.getUsername());
            curr_user.setPassword(passwordEncoder.encode(user.getPassword()));
            curr_user.setEmail(user.getEmail());
            return repo.save(curr_user);
        }

        return null;
    }

    public Users getUserByUsername(String username) {
        return repo.findByUsername(username);
    }

    public void deleteUserByid(int id) {
        repo.deleteById(id);
    }
}















