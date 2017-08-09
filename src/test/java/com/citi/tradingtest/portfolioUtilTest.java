package com.citi.tradingtest;

import com.citi.training.model.Portfolio;
import com.citi.training.service.IEquityHoldService;
import com.citi.training.service.impl.PortfolioUtilsService;
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
public class portfolioUtilTest {

	private static Logger logger = Logger.getLogger(portfolioUtilTest.class);


    @Resource
    private PortfolioUtilsService portfolioUtilsService=null;

    @Test  
    public void test() {

        Map<String, Portfolio> portfolioLookup =portfolioUtilsService.PortfolioUtil();

        for (String key:portfolioLookup.keySet()) {
            //euityInfo.get("symbol");
            System.out.println( key);
        }
        //System.out.println(orderholds);
        // logger.info("值："+orderholds);
        //logger.info(JSON.toJSONString(user));  
    }

}
