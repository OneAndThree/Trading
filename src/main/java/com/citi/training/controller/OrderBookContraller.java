package com.citi.training.controller;

import com.citi.training.model.HistoryEquityData;
import com.citi.training.service.IHistoryEquityDataService;
import com.citi.training.service.IOrderdetailService;
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

	@ResponseBody
	@RequestMapping(value = "/orderBookBid", method = RequestMethod.POST)
	public List<Map<String, String>> getBitListData(String symbol, String period) {
		return orderdetailService.getBitList(symbol,1);

	}

	@ResponseBody
	@RequestMapping(value = "/orderBookAsk", method = RequestMethod.POST)
	public List<Map<String, String>> getAskListData(String symbol, String period) {
		return orderdetailService.getOfferList(symbol,1);

	}

}
