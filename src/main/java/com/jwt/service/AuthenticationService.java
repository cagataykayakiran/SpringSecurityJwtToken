package com.jwt.service;

import com.jwt.dto.UserLoginRequest;
import com.jwt.dto.UserRequest;
import com.jwt.dto.UserResponse;
import com.jwt.user.Role;
import com.jwt.user.User;
import com.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserRequest userRequest) {
        User user = User.builder()
                .username(userRequest.getUsername())
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }

    public UserResponse auth(UserLoginRequest userRequest) {
        authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken
                        (userRequest.getUsername(), userRequest.getPassword()));
        User user = userRepository.findByUsername(userRequest.getUsername()).orElse(null);
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}
