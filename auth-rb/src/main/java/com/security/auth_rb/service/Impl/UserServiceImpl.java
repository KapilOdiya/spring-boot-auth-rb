package com.security.auth_rb.service.Impl;


import com.security.auth_rb.jpa.repository.UserRepository;
import com.security.auth_rb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
       return new UserDetailsService() {
           @Override
           public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               return userRepository.findByEmail(username)
                       .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
           }
       };
    }
}
