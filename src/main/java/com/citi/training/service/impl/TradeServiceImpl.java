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
package com.citi.training.service.impl;

import com.citi.training.model.TradeOrderDetail;
import com.citi.training.service.PortfolioService;
import com.citi.training.service.TradeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import com.citi.training.model.Portfolio;
import com.citi.training.model.PortfolioPosition;
import com.citi.training.model.TradeOrderDetail.TradeAction;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class TradeServiceImpl implements TradeService {

	private static final Log logger = LogFactory.getLog(TradeServiceImpl.class);

	private final SimpMessageSendingOperations messagingTemplate;

	private final PortfolioService portfolioService;

	private final List<TradeResult> tradeResults = new CopyOnWriteArrayList<>();


	@Autowired
	public TradeServiceImpl(SimpMessageSendingOperations messagingTemplate, PortfolioService portfolioService) {
		this.messagingTemplate = messagingTemplate;
		this.portfolioService = portfolioService;
	}

	/**
	 * In real application a tradeOrderDetail is probably executed in an external system, i.e. asynchronously.
	 */
	public void executeTrade(TradeOrderDetail tradeOrderDetail) {

		Portfolio portfolio = this.portfolioService.findPortfolio(tradeOrderDetail.getUsername());
		String ticker = tradeOrderDetail.getTicker();
		int sharesToTrade = tradeOrderDetail.getShares();

		PortfolioPosition newPosition = (tradeOrderDetail.getAction() == TradeAction.Buy) ?
				portfolio.buy(ticker, sharesToTrade) : portfolio.sell(ticker, sharesToTrade);

		if (newPosition == null) {
			String payload = "Rejected tradeOrderDetail " + tradeOrderDetail;
			this.messagingTemplate.convertAndSendToUser(tradeOrderDetail.getUsername(), "/queue/errors", payload);
			return;
		}

		this.tradeResults.add(new TradeResult(tradeOrderDetail.getUsername(), newPosition));
	}

	//@Scheduled(fixedDelay=1500)
	public void sendTradeNotifications() {

		Map<String, Object> map = new HashMap<>();
		map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);

		for (TradeResult result : this.tradeResults) {
			if (System.currentTimeMillis() >= (result.timestamp + 1500)) {
				logger.debug("Sending position update: " + result.position);
				this.messagingTemplate.convertAndSendToUser(result.user, "/queue/position-updates", result.position, map);
				this.tradeResults.remove(result);
			}
		}
	}


	private static class TradeResult {

		private final String user;
		private final PortfolioPosition position;
		private final long timestamp;

		public TradeResult(String user, PortfolioPosition position) {
			this.user = user;
			this.position = position;
			this.timestamp = System.currentTimeMillis();
		}
	}

}