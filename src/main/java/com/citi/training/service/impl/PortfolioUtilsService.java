package com.citi.training.service.impl;

import com.citi.training.model.Portfolio;
import com.citi.training.model.PortfolioPosition;
import com.citi.training.service.IEquityHoldService;
import com.citi.training.service.IEquityInfoService;
import com.citi.training.service.ITraderService;
import com.citi.training.utils.YahooFetchRealTimeData;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("portfolioutil")
public class PortfolioUtilsService {

    @Resource
    private IEquityInfoService equityService = null;

    @Resource
    private ITraderService traderService = null;

    @Resource
    private IEquityHoldService equityHoldService = null;

    public Map<String, Portfolio> PortfolioUtil() {
        Map<String, Portfolio> portfolioLookup = new HashMap<>();
        List<Map<String, String>> users = traderService.getAllTrader();
        for (Map<String, String> user : users) {
            System.out.println("--------------------orderhold-----test-------------------------");
            System.out.println(user.get("name"));
            List<Map<String, Object>> orderholds = equityHoldService.getSharesAllHold(Long.parseLong(user.get("id").toString()));

            YahooFetchRealTimeData yahooFetchRealTimeData = new YahooFetchRealTimeData();
            Map<String, Object> realtimeData = yahooFetchRealTimeData.getRealtimeData();

            Portfolio portfolio = new Portfolio();

            for (Map<String, Object> orderhold : orderholds) {
                JSONObject portfoDatalioJObj = JSONObject.fromObject(realtimeData.get(orderhold.get("symbol")));
                //TODO 验证是否为空
                portfolio.addPosition(new PortfolioPosition(orderhold.get("symbol").toString(), orderhold.get("symbol").toString(),
                        Double.parseDouble(portfoDatalioJObj.get("l1").toString()),
                        Integer.parseInt(portfoDatalioJObj.get("shares").toString())));
            }

            portfolioLookup.put(user.get("name"), portfolio);
        }
        return portfolioLookup;
    }
}
