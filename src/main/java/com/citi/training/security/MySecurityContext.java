package com.citi.training.security;

import com.citi.training.model.Trader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Collection;


public class MySecurityContext {
    public static MySecurityUser getSecurityUser() {
        if(getAuthentication() == null)
            return null;
        MySecurityUser user;
        try {
           user = (MySecurityUser)getAuthentication()
                   .getPrincipal();
           return user;
        }
        catch (ClassCastException e)
        {
           return null;
        }
    }


    public static Object getCurrentPrincipal(){
        if(getAuthentication() == null)
            return null;
        return getAuthentication().getPrincipal();
    }



    public static Trader getCurrentUser() {
        MySecurityUser securityUser = getSecurityUser();
        if(securityUser == null)
            return null;

        Trader user = new Trader();
        user.setName(securityUser.getUsername());
        user.setId(securityUser.getUserId());
        return user;
    }


    public static long getCurrentUserId() {
        MySecurityUser user = getSecurityUser();
        if(user == null)
            return -1;
        return user.getUserId();
    }




    public static String getCurrentUsername() {
        Authentication authentication = getAuthentication();

        if ((authentication == null) || (authentication.getPrincipal() == null)) {
            return "";
        }
        return authentication.getName();
    }



    public static String getCurrentSessionId() {
        Authentication authentication = getAuthentication();
        return getSessionIdByAuthentication(authentication);
    }


    public static String getCurrentUserIp() {
        Authentication authentication = getAuthentication();
        return getUserIpByAuthentication(authentication);
    }


    public static String getUserIpByAuthentication(Authentication authentication) {

        if (authentication == null) {
            return "";
        }
        Object details = authentication.getDetails();

        if (!(details instanceof WebAuthenticationDetails)) {
            return "";
        }
        WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
        return webDetails.getRemoteAddress();
    }


    public static String getSessionIdByAuthentication(Authentication authentication) {

        if (authentication == null) {
            return "";
        }
        Object details = authentication.getDetails();

        if (!(details instanceof WebAuthenticationDetails)) {
            return "";
        }
        WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
        return webDetails.getSessionId();
    }

    public static Collection<GrantedAuthority> getCurrentUserAuthorities(){
        MySecurityUser user = getSecurityUser();
        if(user == null)
            return null;

        return user.getAuthorities();
    }



    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }


}
