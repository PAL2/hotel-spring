package com.hotel.security;

import com.hotel.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Wrapper-class on User
 * Added additional information about user
 * Created by: khudnitsky
 * Date: 02.03.2016
 * Time: 12:18
 */
public class UserDetail extends org.springframework.security.core.userdetails.User {
    private User user;

    public UserDetail(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getLogin(), user.getPassword(), authorities);
        this.user = user;
    }

    public UserDetail(User user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(user.getLogin(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public String getUserFirstName() {
        return user.getFirstName();
    }

    public String getUserLastName() {
        return user.getLastName();
    }

    public String getUserLogin() {
        return user.getLogin();
    }
}
