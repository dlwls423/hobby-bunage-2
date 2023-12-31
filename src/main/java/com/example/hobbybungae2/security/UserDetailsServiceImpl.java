package com.example.hobbybungae2.security;

import com.example.hobbybungae2.domain.user.entity.User;
import com.example.hobbybungae2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String idName) throws UsernameNotFoundException {
        User user = userRepository.findByIdName(idName)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + idName));

        return new UserDetailsImpl(user);
    }
}
