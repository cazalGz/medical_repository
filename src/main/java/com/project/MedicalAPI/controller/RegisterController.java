package com.project.MedicalAPI.controller;

import com.project.MedicalAPI.domain.user.User;
import com.project.MedicalAPI.domain.user.UserRegisterData;
import com.project.MedicalAPI.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserRegisterData userRegisterData) {
        if (usuarioRepository.findByLogin(userRegisterData.login()) != null) {
            return ResponseEntity.badRequest().body("User name is already in use.");
        }


        String passwordEncripted = passwordEncoder.encode(userRegisterData.password());


        User newUser = new User(null, userRegisterData.login(), passwordEncripted);
        usuarioRepository.save(newUser);

        return ResponseEntity.ok("user registered successful");
    }
}
