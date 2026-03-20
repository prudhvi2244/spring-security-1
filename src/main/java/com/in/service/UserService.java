package com.in.service;

import com.in.dto.LoginDto;
import com.in.dto.RegisterDto;

public interface UserService {
    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
