package com.citi.training.controller;

import com.citi.training.model.HistoryEquityData;
import com.citi.training.service.IHistoryEquityDataService;
import com.citi.training.utils.YahooFetchRealTimeData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EquityDataFetchContraller {
	@Resource
	IHistoryEquityDataService historyEquityDataService;

	@ResponseBody
	@RequestMapping(value = "/historyData", method = RequestMethod.POST)
	public Map<String, Object> getHistoryData(String symbol,String period) {
		HistoryEquityData historyEquityData=historyEquityDataService.getEquityData(symbol, period);
		Map<String, Object> result = new HashMap<>();
		if(historyEquityData!=null){
			result.put("result", historyEquityData.getData());
		}else{
			result=null;
		}
		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/realtimeData", method = RequestMethod.POST)
	public Map<String, Object> getrealtimeData() {
		Map<String, Object> result = new HashMap<>();
		YahooFetchRealTimeData yahooFetchRealTimeData=new YahooFetchRealTimeData();
		result.put("result", yahooFetchRealTimeData.getRealtimeData());
		return result;
	}

}
