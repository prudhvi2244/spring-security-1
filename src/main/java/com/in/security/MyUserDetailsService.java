package com.in.security;

import com.in.entity.User;
import com.in.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        java.util.Optional<User> opt = userRepository.findByUsername(username);
        var user = opt.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new org.springframework.security.core
                .userdetails
                .User(user.getUsername(), user.getPassword(),
                Set.of(new SimpleGrantedAuthority(user.getRole().toString())));
    }
}
