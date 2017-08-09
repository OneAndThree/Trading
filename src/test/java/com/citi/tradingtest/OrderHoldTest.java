package com.citi.tradingtest;

import com.citi.training.model.EquityHhold;
import com.citi.training.model.Orderdetail;
import com.citi.training.service.IEquityHoldService;
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
public class OrderHoldTest {

	private static Logger logger = Logger.getLogger(OrderHoldTest.class);

    @Resource
    private IEquityHoldService equityHoldService=null;

    @Test  
    public void test() {

        List<Map<String,String>> orderholds= equityHoldService.getSharesHold(1L,"A");
        for (Map<String,String> orderhold:orderholds) {
            //euityInfo.get("symbol");
            System.out.println( orderhold.get("symbol"));
        }
        System.out.println(orderholds);
        logger.info("值："+orderholds);
        //logger.info(JSON.toJSONString(user));  
    }

    @Test
    public void test2() {


        List<Map<String,Object>> orderholds= equityHoldService.getSharesAllHold(Long.parseLong(("1").toString()));
        for (Map<String,Object> orderhold:orderholds) {
            //euityInfo.get("symbol");
            System.out.println( orderhold.get("symbol"));
        }
        System.out.println(orderholds);
        logger.info("值："+orderholds);
        //logger.info(JSON.toJSONString(user));
    }
}
