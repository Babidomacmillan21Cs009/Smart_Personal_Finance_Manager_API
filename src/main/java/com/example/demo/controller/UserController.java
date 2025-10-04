package com.example.demo.controller;

import com.example.demo.model.UserPrincipal;
import com.example.demo.model.Users;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user){
        Users exist_user = service.getUserByEmail(user.getEmail());

        if(exist_user != null)
            return new ResponseEntity<>("User already Exist",HttpStatus.NOT_ACCEPTABLE);
        else {
            Users curr_user = service.register(user);
            return new ResponseEntity<>(curr_user,HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return service.verify(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id){
        Users user = service.getUserById(id);

        if(user != null)
            return new ResponseEntity<>(user,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserByid(@PathVariable int id){
        Users exist_user = service.getUserById(id);

        if(exist_user != null) {
            service.deleteUserByid(id);
            return new ResponseEntity<>("Deleted Successfull",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/profile")
    public ResponseEntity<Users> getProfile(@AuthenticationPrincipal UserPrincipal userPrincipal){
        if(userPrincipal == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Users user = service.getUserByUsername(userPrincipal.getUsername());

        if(user != null)
            return new ResponseEntity<>(user,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/user/update-profile")
    public ResponseEntity<Users> updateUser(@RequestBody Users user){
        Users updated_user = service.updateUser(user);
        if(updated_user != null)
            return new ResponseEntity<>(updated_user,HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
















