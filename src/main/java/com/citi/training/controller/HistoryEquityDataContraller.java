package com.citi.training.controller;

import com.citi.training.model.HistoryEquityData;
import com.citi.training.service.IHistoryEquityDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HistoryEquityDataContraller {
	@Resource
	IHistoryEquityDataService historyEquityDataService;
	


	@ResponseBody
	@RequestMapping(value = "/historyDate", method = RequestMethod.POST)
	public Map<String, Object> getHistoryData(String symbol,String period) {
		HistoryEquityData historyEquityData=historyEquityDataService.getEquityData(symbol, period);
		Map<String, Object> result = new HashMap<>();
		result.put("result", historyEquityData.getData());
		return result;
	}
}
