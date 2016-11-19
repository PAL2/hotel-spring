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

    /*@Override
    @Transactional
    public CustomUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getUserByLogin(login);
            if (user == null) {
                throw new UsernameNotFoundException("Login " + login + " doesn't exist!");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new CustomUser(user, true, true, true, true, getGrantedAuthorities(user));
    }
*/
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getUserByLogin(login);
            if (user == null) {
                throw new UsernameNotFoundException("Login " + login + " doesn't exist!");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        org.springframework.security.core.userdetails.User userDetail =
                new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, getGrantedAuthorities(user));
        System.out.println(userDetail);
        return userDetail;
    }
        /*return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                System.out.println("XCD");
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + finalUser.getUserRole().toUpperCase().toString()));
                System.out.println(authorities);
                return authorities;
            }

            @Override
            public String getPassword() {
                return finalUser.getPassword();
            }

            @Override
            public String getUsername() {
                System.out.println("DWKMDMPWM");
                return finalUser.getLogin();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };*/


    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().toUpperCase().toString()));
        System.out.println(authorities);
        return authorities;
    }
}