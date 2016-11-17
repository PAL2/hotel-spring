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

@Service("authenticationService")
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getUserByLogin(login);
            System.out.println();
            if (user == null)
                throw new UsernameNotFoundException("Login " + login + " doesn't exist!");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new UserDetail(user, true, true, true, true, getGrantedAuthorities(user));
    }

    /* @Override
     @Transactional(readOnly = true)
     public CustomUser loadUserByUsername(String login) throws UsernameNotFoundException {
             System.out.println("lll");
                     *//*User user = null;
        try {
            user = userService.getUserByLogin(login);
            if (user == null) {
                throw new UsernameNotFoundException(WebConstants.USER_NOT_FOUND);
            }
        }
        catch (ServiceException e) {
            e.printStackTrace();
        }
        return new CustomUser(user, true, true, true, true, getGrantedAuthorities(user));*//*
        return null;
    }




    */
    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (AccessLevel access : user.getAccessLevels()) {
            authorities.add(new SimpleGrantedAuthority(WebConstants.ROLE_PREFIX + access.getAccessLevelType().toString()));
        }
        return authorities;
    }
}