package com.citi.tradingtest;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.training.model.HistoryEquityData;
import com.citi.training.service.IHistoryEquityDataService;
import com.citi.training.service.impl.YahooFetcheHistoricalDataService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class SaveHistoryDataToDBTest {
	public String[] symbols = { "ABT", "ABBV", "ACN", "ADBE", "AAP", "AES", "AET", "AFL", "AMG", "A", "APD", "AKAM",
			"AA", "AGN", "ALXN", "ALLE", "ADS", "ALL", "MO", "AMZN", "AEE", "AAL", "AEP", "AXP", "AIG", "AMT", "AMP",
			"ABC", "AME", "AMGN", "APH", "APC", "ADI", "AON", "APA", "AIV", "AMAT", "ADM", "AIZ", "T", "ADSK", "ADP",
			"AN", "AZO", "AVGO", "AVB", "AVY", "BHI", "BLL", "BAC", "BK", "BCR", "BAX", "BBT", "BDX", "BBBY", "BBY",
			"BLX", "HRB", "BA", "BWA", "BXP", "BSK", "BMY", "CHRW", "CA", "COG", "STI", "SYMC", "SYY", "TROW", "TGT",
			"TEL", "DIS", "WM", "WAT", "ANTM", "WFC", "WDC", "WU", "WY", "WHR", "WFM", "WMB", "WEC", "WYN", "WYNN",
			"XEL", "XRX", "XLNX", "XL", "XYL", "ZBH", "ZION", "ZTS" };
	public String[] period = { "5d", "1mo", "3mo", "6mo", "1y", "2y", "5y", "10y", "ytd", "max" };
	private static Logger logger = Logger.getLogger(SaveHistoryDataToDBTest.class);
	private String flag = "-";
	@Resource
	IHistoryEquityDataService historyEquityDataService = null;
	YahooFetcheHistoricalDataService fecthData = new YahooFetcheHistoricalDataService();

	@Test
	public void test() {
		for (int i = 0; i < symbols.length; i++) {
			System.out.print(i + 1);
			String data = fecthData.get5DayDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "5d");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("5d");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);

		}
	}

	@Test
	public void test1() {
		for (int i = 0; i < symbols.length; i++) {
			System.out.print(i + 1);
			String data = fecthData.get1DayDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "1d");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("1d");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);

		}
	}
	@Test
	public void test2() {
		for (int i = 0; i < symbols.length; i++) {
			String data = fecthData.get1moDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "1mo");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("1mo");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);

		}
	}

	@Test
	public void test3() {
		for (int i = 0; i < symbols.length; i++) {
			String data = fecthData.get3moDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "3mo");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("3mo");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);
		}
	}

	@Test
	public void test4() {
		for (int i = 0; i < symbols.length; i++) {
			String data = fecthData.get6moDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "6mo");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("6mo");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);
		}
	}

	@Test
	public void test5() {
		for (int i = 0; i < symbols.length; i++) {
			String data = fecthData.get1yDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "1y");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("1y");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);
		}
	}

	@Test
	public void test6() {
		for (int i = 0; i < symbols.length; i++) {
			String data = fecthData.get2yDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "2y");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("2y");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);
		}
	}

	@Test
	public void test7() {
		for (int i = 0; i < symbols.length; i++) {
			String data = fecthData.get5yDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "5y");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("5y");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);
		}
	}

	@Test
	public void test8() {
		for (int i = 0; i < symbols.length; i++) {
			String data = fecthData.get10yDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "10y");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("10y");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);
		}
	}
	
    @Test
	public void test9() {
		for (int i = 0; i < symbols.length; i++) {
			String data = fecthData.getYtdDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "ytd");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("ytd");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);
		}
	}

	@Test
	public void test10() {
		for (int i = 0; i < symbols.length; i++) {
			String data = fecthData.getMaxDataFromAPI(symbols[i]);
			HistoryEquityData historyEquityData = new HistoryEquityData();
			historyEquityData.setPrefix(symbols[i] + flag + "max");
			historyEquityData.setSymble(symbols[i]);
			historyEquityData.setPeriod("max");
			historyEquityData.setData(data);
			historyEquityDataService.saveRecord(historyEquityData);
		}
	}

}
