package com.project.MedicalAPI.controller;

import com.project.MedicalAPI.domain.user.User;
import com.project.MedicalAPI.domain.user.UserAuthenticationData;
import com.project.MedicalAPI.infra.security.JWTTokenData;
import com.project.MedicalAPI.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid UserAuthenticationData userAuthenticationData) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userAuthenticationData.login(),
                userAuthenticationData.password());
        var authUser = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(JWTtoken));
    }
}
