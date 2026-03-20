package com.in.service;

import com.in.dto.LoginDto;
import com.in.dto.RegisterDto;
import com.in.entity.User;
import com.in.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterDto registerDto) {
        log.info("User Registering");
        User user = new User();
        user.setUsername(registerDto.username());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setRole(registerDto.role());
        userRepository.save(user);
        return "User Registration Successful";
    }

    @Override
    public String login(LoginDto loginDto) {
        var authenticated = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()))
                .isAuthenticated();
        if (authenticated) {
            return jwtUtil.generateToken(loginDto.username());
          
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

}
