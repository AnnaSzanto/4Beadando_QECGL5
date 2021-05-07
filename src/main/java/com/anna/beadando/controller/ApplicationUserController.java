package com.anna.beadando.controller;


import com.anna.beadando.entity.ApplicationUser;
import com.anna.beadando.model.UserCredential;
import com.anna.beadando.repository.ApplicationUserRepository;
import com.anna.beadando.security.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Service
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserRepository userRepository;

    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> registry(@Valid @RequestBody UserCredential user) throws Exception {
        if(!user.isUsernameValid()){
            return ResponseEntity.ok("Username should be longer then 6 characters!");
        }
        if(!user.isPasswordValid()){
            return ResponseEntity.ok("Password should be longer then 8 characters and should contains letters and numbers!");
        }

        if(userRepository.findByUsername(user.getUsername()) == null) {
            ApplicationUser applicationUser = ApplicationUser.builder()
                    .username(user.getUsername())
                    .password(Password.getSaltedHash(user.getPassword()))
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .build();
            userRepository.save(applicationUser);
        } else {
            return ResponseEntity.ok("Used email");
        }
        return ResponseEntity.ok("OK");
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> login(@RequestBody UserCredential user) throws Exception {
        ApplicationUser userByUsername = userRepository.findByUsername(user.getUsername());
        boolean loggedIn = false;
        if(userByUsername != null) {
            loggedIn = Password.check(user.getPassword(), userByUsername.getPassword());
            if (loggedIn) {
                return ResponseEntity.ok("You logged in");
            } else {
                return ResponseEntity.ok("Wrong username or password");
            }
        } else {
            return ResponseEntity.ok("No user with this password or username");
        }

    }
}
