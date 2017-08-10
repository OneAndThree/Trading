package com.citi.tradingtest;

import com.citi.training.model.EquityHhold;
import com.citi.training.service.IEquityHoldService;
import com.citi.training.service.impl.StrategiesService;
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
public class StrategeTest {

	private static Logger logger = Logger.getLogger(StrategeTest.class);

    @Resource
    private StrategiesService strategiesService=null;

    @Test  
    public void test() {

        //strategiesService.stratesgeOfM("Danol","B","ABT",1,1);

        strategiesService.buyOrSell("Danol","B","ABT",1);
//        EquityHhold orderhold= equityHoldService.getSharesHold(1L,"A");
//
//            System.out.println(orderhold.getSymbol());
//
//        System.out.println(orderhold);
//        logger.info("值："+orderhold);
        //logger.info(JSON.toJSONString(user));  
    }


}
