package com.citi.tradingtest;

import com.citi.training.dao.EquityInfoMapper;
import com.citi.training.model.Trader;
import com.citi.training.model.User;
import com.citi.training.service.IEquityInfoService;
import com.citi.training.service.ITraderService;
import com.citi.training.service.IUserService;
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
public class EquityInfoTest {

	private static Logger logger = Logger.getLogger(EquityInfoTest.class);

    @Resource
    private IEquityInfoService equityService=null;

    @Test  
    public void test() {
        List<Map<String,String>> euitiesInfo= equityService.getAllEquityInfo();
        for (Map<String,String> euityInfo:euitiesInfo) {
            //euityInfo.get("symbol");
            System.out.println( euityInfo.get("symbol"));
        }
        System.out.println(equityService.getAllEquityInfo());
        logger.info("值："+equityService.getAllEquityInfo());
        //logger.info(JSON.toJSONString(user));  
    }
}
