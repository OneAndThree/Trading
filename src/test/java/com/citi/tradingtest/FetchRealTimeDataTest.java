package com.citi.tradingtest;

import org.junit.Test;

import com.citi.training.utils.YahooFetchRealTimeData;

public class FetchRealTimeDataTest {

	@Test
	public void test() {
		YahooFetchRealTimeData yahooFetchRealTimeData=new YahooFetchRealTimeData();
		System.out.println(yahooFetchRealTimeData.getRealtimeData());
	}

}
