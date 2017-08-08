package com.citi.training.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;



public class YahooFetcheHistoricalDataService {
	private final static String baseURL = "https://query1.finance.yahoo.com/v8/finance/chart/";
	private final static String middle = "?range=";
	//private final static String period = "1mo";
	private final static String postfix = "&includePrePost=false&interval=30m&corsDomain=finance.yahoo.com&.tsrc=finance";
	@SuppressWarnings("serial")
	private final Map<String, String>  period= new HashMap<String, String>() {{
		put("5d", "5d");
		put("1mo", "1mo");
		put("3mo", "3mo");
		put("6mo", "6mo");
		put("1y", "1y");
		put("ytd", "ytd");
		put("2y", "2y");
		put("5y", "5y");
		put("10y", "10y");
		put("max", "max");
		
	}};
    
	public String get5DayDataFromAPI(String symbol) {
		URL url = null;

		try {
			url = new URL(baseURL + symbol+ middle + period.get("5d") + postfix);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return processURL(url);
	}
	public String get1moDataFromAPI(String symbol) {
		URL url = null;

		try {
			url = new URL(baseURL + symbol+ middle + period.get("1mo") + postfix);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return processURL(url);
	}
	public String get3moDataFromAPI(String symbol) {
		URL url = null;

		try {
			url = new URL(baseURL + symbol+ middle + period.get("3mo") + postfix);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return processURL(url);
	}
	public String get6moDataFromAPI(String symbol) {
		URL url = null;

		try {
			url = new URL(baseURL + symbol+ middle + period.get("6mo") + postfix);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return processURL(url);
	}
	public String get1yDataFromAPI(String symbol) {
		URL url = null;

		try {
			url = new URL(baseURL + symbol+ middle + period.get("1y") + postfix);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return processURL(url);
	}
	public String get2yDataFromAPI(String symbol) {
		URL url = null;

		try {
			url = new URL(baseURL + symbol+ middle + period.get("2y") + postfix);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return processURL(url);
	}
	public String get5yDataFromAPI(String symbol) {
		URL url = null;

		try {
			url = new URL(baseURL + symbol+ middle + period.get("5y") + postfix);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return processURL(url);
	}
	public String get10yDataFromAPI(String symbol) {
		URL url = null;

		try {
			url = new URL(baseURL + symbol+ middle + period.get("10y") + postfix);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return processURL(url);
	}
	public String getYtdDataFromAPI(String symbol) {
		URL url = null;

		try {
			url = new URL(baseURL + symbol+ middle + period.get("ytd") + postfix);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return processURL(url);
	}
	public String getMaxDataFromAPI(String symbol) {
		URL url = null;

		try {
			url = new URL(baseURL + symbol+ middle + period.get("max") + postfix);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return processURL(url);
	}
	
	
	
	
	private String processURL(URL url) {
		InputStream response = null;
		String processedData = null;
        String resultData=null;
		try {
			response = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(response));

			for (String line; (line = reader.readLine()) != null;) {

				if (processedData == null) {
					processedData = line + "\n";
				} else {
					processedData = processedData + line + "\n";
				}
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("Error: Unknown error from Yahoo!\n");
		}
		JSONObject processedJsonData=JSONObject.fromObject(processedData);
		
		resultData=JSONObject.fromObject(processedJsonData.getString("chart")).getString("result");
		return resultData;
	}

}
