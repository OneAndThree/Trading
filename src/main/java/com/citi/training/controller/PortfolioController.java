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

import com.citi.training.model.TradeOrderDetail;
import com.citi.training.service.impl.PortfolioUtilsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import com.citi.training.model.Portfolio;
import com.citi.training.model.PortfolioPosition;
import com.citi.training.service.PortfolioService;
import com.citi.training.service.TradeService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


@Controller
public class PortfolioController {

	private static final Log logger = LogFactory.getLog(PortfolioController.class);

	private final PortfolioService portfolioService;

	private final TradeService tradeService;


	@Resource
	PortfolioUtilsService portfolioUtilsService=null;

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
		Map<String, Portfolio> portfolioLookup = portfolioUtilsService.PortfolioUtil();
		Portfolio portfolio = portfolioLookup.get(principal.getName());
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
