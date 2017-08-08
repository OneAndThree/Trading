package com.citi.training.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.citi.training.model.HistoryEquityData;
import com.citi.training.service.IHistoryEquityDataService;

public class HistoryEquityDataContraller {
	@Resource
	IHistoryEquityDataService historyEquityDataService = null;
	
	private String symble;
	private String period; 
	private Map<String, Object> Result;
	@RequestMapping(value = "/historyDate", method = RequestMethod.GET)
	public Map<String, Object> getHistoryData() {
		HistoryEquityData historyEquityData=historyEquityDataService.getEquityData(symble, period);
		Result.put("result", historyEquityData.getData());
		return Result;
	}
}
