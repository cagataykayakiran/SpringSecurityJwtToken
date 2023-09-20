package com.jwt.controller;

import com.jwt.dto.UserLoginRequest;
import com.jwt.dto.UserRequest;
import com.jwt.dto.UserResponse;
import com.jwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(authenticationService.save(userRequest));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody UserLoginRequest userRequest) {
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }
}
