package com.in.dto;

import com.in.entity.Role;

public record RegisterDto(String username, String password,Role role) {
    
}
