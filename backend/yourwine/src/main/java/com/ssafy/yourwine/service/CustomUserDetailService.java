package com.ssafy.yourwine.service;

import com.ssafy.yourwine.exception.ErrorCode;
import com.ssafy.yourwine.exception.UserException;
import com.ssafy.yourwine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(String.valueOf(id)).orElseThrow(() -> new UserException(ErrorCode.UNEXPECTED_USER));
    }
}
