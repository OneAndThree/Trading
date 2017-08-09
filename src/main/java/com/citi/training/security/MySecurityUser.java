package com.citi.training.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.HashSet;

public class MySecurityUser extends User {

    private long userId;

    public MySecurityUser(long userId, String username, String password) {
        super(username, password, new HashSet<GrantedAuthority>());
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
