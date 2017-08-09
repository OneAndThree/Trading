package com.citi.training.security;

import com.citi.training.model.Trader;
import com.citi.training.service.ITraderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class MyUserDetailService implements UserDetailsService {
    protected static final Logger logger = LogManager.getLogger(MyUserDetailService.class);

    private ITraderService traderService;

    public MyUserDetailService() {}

    @Autowired
    public MyUserDetailService(ITraderService traderService)
    {
        this.traderService = traderService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("begin authorization");
        Trader user = traderService.selectByName(username);
        return transferUser(user);
    }


    public User transferUser(Trader user) {

        if (user != null) {
            MySecurityUser securityUser = new MySecurityUser(user.getId(), user.getName(), user.getPassword());//参数是正确的密码
            return securityUser;
        }


        throw new UsernameNotFoundException("Doesn't exist the username");


    }

}

