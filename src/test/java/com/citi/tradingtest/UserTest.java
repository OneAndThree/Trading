package com.citi.tradingtest;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.training.model.User;
import com.citi.training.service.IUserService;


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserTest {

	private static Logger logger = Logger.getLogger(UserTest.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private IUserService userService = null;  
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test() {  
        User user = userService.getUserById(1);  
        System.out.println(user.getUserName());  
        logger.info("值："+user.getUserName());  
        //logger.info(JSON.toJSONString(user));  
    }  

}
