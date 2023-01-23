package com.project.taskit.config;

import com.project.taskit.models.User;
import com.project.taskit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {

//    @Autowired
    private  UserRepository users;

    @Autowired
    public UserDetailsLoader(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);

//        user.setPassword("password");
//        user.setUsername("anthony");

        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }
        System.out.println("load user hit");
        System.out.println("user password: "+user.getPassword());

        return new UserWithRoles(user);
    }
}
