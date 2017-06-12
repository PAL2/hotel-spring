package com.hotel.security;

import com.hotel.entity.User;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 16.11.2016.
 */

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.findByLogin(login);
            if (user == null) {
                throw new UsernameNotFoundException("Login " + login + " doesn't exist!");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new org.springframework.security.core.userdetails.User
                (user.getLogin(), user.getPassword(), true, true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().toUpperCase()));
        return authorities;
    }
}