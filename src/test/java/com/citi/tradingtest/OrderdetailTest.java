package com.citi.tradingtest;

import com.citi.training.model.Orderdetail;
import com.citi.training.service.IEquityInfoService;
import com.citi.training.service.IOrderdetailService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class OrderdetailTest {

	private static Logger logger = Logger.getLogger(OrderdetailTest.class);

    @Resource
    private IOrderdetailService orderdetailService=null;

    @Test  
    public void test() {
        Orderdetail ord=new Orderdetail();
        ord.setTraderId(1L);
        ord.setSymbol("A");

        List<Map<String,String>> orderdetails= orderdetailService.getSharesInfo(ord);
        for (Map<String,String> orderdetail:orderdetails) {
            //euityInfo.get("symbol");
            System.out.println( orderdetail.get("symbol"));
        }
        System.out.println(orderdetails);
        logger.info("值："+orderdetails);
        //logger.info(JSON.toJSONString(user));  
    }
}
