package com.citi.training.controller;

import com.citi.training.model.HistoryEquityData;
import com.citi.training.service.IHistoryEquityDataService;
import com.citi.training.service.IOrderdetailService;
import com.citi.training.service.ITraderService;
import com.citi.training.utils.YahooFetchRealTimeData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderBookContraller {
	@Resource
	IOrderdetailService orderdetailService=null;

	@Resource
	ITraderService trader=null;


	@ResponseBody
	@RequestMapping(value = "/orderBookBid", method = RequestMethod.POST)
	public List<Map<String, String>> getBitListData(String symbol) {
		return orderdetailService.getBitList(symbol,1);

	}

	@ResponseBody
	@RequestMapping(value = "/orderBookAsk", method = RequestMethod.POST)
	public List<Map<String, String>> getAskListData(String symbol) {
		return orderdetailService.getOfferList(symbol,1);

	}

	@ResponseBody
	@RequestMapping(value = "/myorder", method = RequestMethod.POST)
	public List<Map<String, String>> getMyOrderData(String userName,String symbol) {
		return orderdetailService.getOrderByTraderId(trader.selectByName(userName).getId());

	}


}
