/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.citi.training.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.citi.training.model.*;
import com.citi.training.service.*;
import com.citi.training.service.impl.PortfolioUtilsService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


@Controller
public class PortfolioController {

	private static final Log logger = LogFactory.getLog(PortfolioController.class);

	private final PortfolioService portfolioService;

	private final TradeService tradeService;


//	@Resource
//	PortfolioUtilsService portfolioUtilsService=null;

	@Resource
	private ITraderService traderService = null;
	@Resource
	private IEquityHoldService equityHoldService = null;
	@Resource
	IHistoryEquityDataService historyEquityDataService = null;

	@Autowired
	public PortfolioController(PortfolioService portfolioService, TradeService tradeService) {
		this.portfolioService = portfolioService;
		this.tradeService = tradeService;
	}

//	@SubscribeMapping("/positions")
//	public List<PortfolioPosition> getPositions(Principal principal) throws Exception {
//		logger.debug("Positions for " + principal.getName());
//		Portfolio portfolio = this.portfolioService.findPortfolio(principal.getName());
//		return portfolio.getPositions();
//	}
	@SubscribeMapping("/positions")
	public List<PortfolioPosition> getPositions(Principal principal) throws Exception {
		logger.debug("Positions for " + principal.getName());
		//Map<String, Portfolio> portfolioLookup = portfolioUtilsService.PortfolioUtil();
		//Portfolio portfolio = portfolioLookup.get(principal.getName());
		Trader trader=traderService.selectByName(principal.getName());
		List<Map<String, Object>> orderholds = equityHoldService.getSharesAllHold(trader.getId());
		Portfolio portfolio = new Portfolio();
		double price=0.0;
		for (Map<String, Object> orderhold : orderholds) {

			HistoryEquityData historyEquityData = historyEquityDataService.getEquityData(String.valueOf(orderhold.get("symbol")),"1d");

			JSONArray datas=JSONArray.fromObject(historyEquityData.getData());
			JSONObject historyDataJson=JSONObject.fromObject(datas.get(0));
			if (historyDataJson!=null){
				JSONObject indicators=JSONObject.fromObject(historyDataJson.get("indicators"));
				if(indicators!=null){
					JSONObject quote= JSONObject.fromObject(indicators.get("quote"));
					if(quote!=null){
						JSONArray prices=JSONArray.fromObject(quote.get("close"));
						price=Double.valueOf(String.valueOf(prices.get(prices.size())));
					}
				}
			}
			//TODO 验证是否为空
			portfolio.addPosition(new PortfolioPosition(orderhold.get("symbol").toString(),
					orderhold.get("symbol").toString(), price,
					Integer.parseInt(String.valueOf(orderhold.get("shares")))));
		}
		return portfolio.getPositions();
	}

	@MessageMapping("/tradeOrderDetail")
	public void executeTrade(TradeOrderDetail tradeOrderDetail, Principal principal) {
		tradeOrderDetail.setUsername(principal.getName());
		logger.debug("TradeOrderDetail: " + tradeOrderDetail);
		this.tradeService.executeTrade(tradeOrderDetail);
	}

	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}

}
