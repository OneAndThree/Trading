package com.citi.tradingtest;

import org.junit.Test;

import com.citi.training.utils.YahooFetchRealTimeData;

import java.util.Map;

public class FetchRealTimeDataTest {

	@Test
	public void test() {
		YahooFetchRealTimeData yahooFetchRealTimeData=new YahooFetchRealTimeData();
		System.out.println(yahooFetchRealTimeData.getRealtimeData());
	}
	@Test
	public void test1() {
		YahooFetchRealTimeData yahooFetchRealTimeData = new YahooFetchRealTimeData();
		Map<String, Object> realtimeData = yahooFetchRealTimeData.getRealtimeData();
		for (String key : realtimeData.keySet()) {
			System.out.println(key+":"+realtimeData.get(key));
		}
	}

}
