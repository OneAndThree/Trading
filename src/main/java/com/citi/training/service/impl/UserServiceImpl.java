package com.citi.training.service.impl;

import javax.annotation.Resource;  

import org.springframework.stereotype.Service;

import com.citi.training.dao.IUserDao;
import com.citi.training.model.User;
import com.citi.training.service.IUserService;  
  
@Service("userService")  
public class UserServiceImpl implements IUserService {  
    @Resource  
    private IUserDao userDao;  
    @Override  
    public User getUserById(int userId) {  
        // TODO Auto-generated method stub  
        return this.userDao.selectByPrimaryKey(userId);  
    }  
  
}  