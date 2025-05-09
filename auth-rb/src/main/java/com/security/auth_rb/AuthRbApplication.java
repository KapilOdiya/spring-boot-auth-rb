package com.security.auth_rb;

import com.security.auth_rb.enums.Role;
import com.security.auth_rb.jpa.entities.User;
import com.security.auth_rb.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//default user need to be create to start the application specific user ...
@SpringBootApplication
public class AuthRbApplication implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthRbApplication.class, args);
    }

    public void run(String...args){
        User adminAccount = userRepository.findByUserRole(Role.ADMIN);
        if(adminAccount==null){
           User adminUser  = new User();
           adminUser.setUserName("admin");
           adminUser.setEmail("admin@gmail.com");
           adminUser.setPassword(new BCryptPasswordEncoder().encode("admin@123"));
           adminUser.setUserRole(Role.ADMIN);
           userRepository.save(adminUser);
        }

    }

}
