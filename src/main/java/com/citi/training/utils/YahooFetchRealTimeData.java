package com.citi.training.utils;


public class YahooFetchRealTimeData {
	private final String[] symbols = { "ABT", "ABBV", "ACN", "ACE", "ADBE", "ADT", "AAP", "AES", "AET", "AFL", "AMG",
			"A", "GAS", "APD", "ARG", "AKAM", "AA", "AGN", "ALXN", "ALLE", "ADS", "ALL", "ALTR", "MO", "AMZN", "AEE",
			"AAL", "AEP", "AXP", "AIG", "AMT", "AMP", "ABC", "AME", "AMGN", "APH", "APC", "ADI", "AON", "APA", "AIV",
			"AMAT", "ADM", "AIZ", "T", "ADSK", "ADP", "AN", "AZO", "AVGO", "AVB", "AVY", "BHI", "BLL", "BAC", "BK",
			"BCR", "BXLT", "BAX", "BBT", "BDX", "BBBY", "BBY", "BLX", "HRB", "BA", "BWA", "BXP", "BSK", "BMY", "BRCM",
			"CHRW", "CA", "CVC", "COG", "CAM", "CPB", "COF", "CAH", "HSIC", "KMX", "CCL", "CAT", "CBG", "CBS", "CELG",
			"CNP", "CTL", "CERN", "CF", "SCHW", "CHK", "CVX", "CMG", "CB", "CI", "XEC", "CINF", "CTAS", "CSCO", "C",
			"CTXS", "CLX", "CME", "CMS", "COH", "KO", "CCE", "CTSH", "CL", "CMCSA", "CMA", "CSC", "CAG", "COP", "CNX",
			"ED", "STZ", "GLW", "COST", "CCI", "CSX", "CMI", "CVS", "DHI", "DHR", "DRI", "DVA", "DE", "DLPH", "DAL",
			"XRAY", "DVN", "DO", "DTV", "DFS", "DISCA", "DISCK", "DG", "DLTR", "D", "DOV", "DOW", "DPS", "DTE", "DD",
			"DUK", "DNB", "ETFC", "EMN", "ETN", "EBAY", "ECL", "EIX", "EW", "EA", "EMC", "EMR", "ENDP", "ESV", "ETR",
			"EOG", "EQT", "EFX", "EQIX", "EQR", "ESS", "EL", "ES", "EXC", "EXPE", "EXPD", "ESRX", "XOM", "FFIV", "FB",
			"FAST", "FDX", "FIS", "FITB", "FSLR", "FE", "FSIV", "FLIR", "FLS", "FLR", "FMC", "FTI", "F", "FOSL", "BEN",
			"FCX", "FTR", "GME", "GPS", "GRMN", "GD", "GE", "GGP", "GIS", "GM", "GPC", "GNW", "GILD", "GS", "GT",
			"GOOGL", "GOOG", "GWW", "HAL", "HBI", "HOG", "HAR", "HRS", "HIG", "HAS", "HCA", "HCP", "HCN", "HP", "HES",
			"HPQ", "HD", "HON", "HRL", "HSP", "HST", "HCBK", "HUM", "HBAN", "ITW", "IR", "INTC", "ICE", "IBM", "IP",
			"IPG", "IFF", "INTU", "ISRG", "IVZ", "IRM", "JEC", "JBHT", "JNJ", "JCI", "JOY", "JPM", "JNPR", "KSU", "K",
			"KEY", "GMCR", "KMB", "KIM", "KMI", "KLAC", "KSS", "KRFT", "KR", "LB", "LLL", "LH", "LRCX", "LM", "LEG",
			"LEN", "LVLT", "LUK", "LLY", "LNC", "LLTC", "LMT", "L", "LOW", "LYB", "MTB", "MAC", "M", "MNK", "MRO",
			"MPC", "MAR", "MMC", "MLM", "MAS", "MA", "MAT", "MKC", "MCD", "MHFI", "MCK", "MJN", "MMV", "MDT", "MRK",
			"MET", "KORS", "MCHP", "MU", "MSFT", "MHK", "TAP", "MDLZ", "MON", "MNST", "MCO", "MS", "MOS", "MSI", "MUR",
			"MYL", "NDAQ", "NOV", "NAVI", "NTAP", "NFLX", "NWL", "NFX", "NEM", "NWSA", "NEE", "NLSN", "NKE", "NI", "NE",
			"NBL", "JWN", "NSC", "NTRS", "NOC", "NRG", "NUE", "NVDA", "ORLY", "OXY", "OMC", "OKE", "ORCL", "OI", "PCAR",
			"PLL", "PH", "PDCO", "PAYX", "PNR", "PBCT", "POM", "PEP", "PKI", "PRGO", "PFE", "PCG", "PM", "PSX", "PNW",
			"PXD", "PBI", "PCL", "PNC", "RL", "PPG", "PPL", "PX", "PCP", "PCLN", "PFG", "PG", "PGR", "PLD", "PRU",
			"PEG", "PSA", "PHM", "PVH", "QRVO", "PWR", "QCOM", "DGX", "RRC", "RTN", "O", "RHT", "REGN", "RF", "RSG",
			"RAI", "RHI", "ROK", "COL", "ROP", "ROST", "RLC", "R", "CRM", "SNDK", "SCG", "SLB", "SNI", "STX", "SEE",
			"SRE", "SHW", "SIAL", "SPG", "SWKS", "SLG", "SJM", "SNA", "SO", "LUV", "SWN", "SE", "STJ", "SWK", "SPLS",
			"SBUX", "HOT", "STT", "SRCL", "SYK", "STI", "SYMC", "SYY", "TROW", "TGT", "TEL", "TE", "TGNA", "THC", "TDC",
			"TSO", "TXN", "TXT", "HSY", "TRV", "TMO", "TIF", "TWX", "TWC", "TJK", "TMK", "TSS", "TSCO", "RIG", "TRIP",
			"FOXA", "TSN", "TYC", "UA", "UNP", "UNH", "UPS", "URI", "UTX", "UHS", "UNM", "URBN", "VFC", "VLO", "VAR",
			"VTR", "VRSN", "VZ", "VRTX", "VIAB", "V", "VNO", "VMC", "WMT", "WBA", "DIS", "WM", "WAT", "ANTM", "WFC",
			"WDC", "WU", "WY", "WHR", "WFM", "WMB", "WEC", "WYN", "WYNN", "XEL", "XRX", "XLNX", "XL", "XYL", "YHOO",
			"YUM", "ZBH", "ZION", "ZTS" };
	private final String[] tags = { 
			"s", // Symbol
			"p", // Previous Close
			"a", // Ask
			"a2", // Average Daily Volume
			"b", // Bid
			"b2", // Ask (Real-time)
			"b3", // Bid (Real-time)
			"o", // Open
			"m", // Day’s Range
			"m2", // Day’s Range (Real-time)
			"w", // 52-week Range
			"a2", // Average Daily Volume
			"v", // Volume
			"j1", // Market Capitalization
			"j3", // Market Cap (Real-time)
			"r", // P/E Ratio
			"r2", // P/E Ratio (Real-time)
			"r1", // Dividend Pay Date
			"y", // Dividend Yield
			"q", // Ex-Dividend Date
			"t8", // 1 yr Target Price			
			"l2",// High Limit l3 Low Limit
			"k1",//  Last Trade (Real-time) With Time
			"k2",//  Change Percent (Real-time)     
			"k3",//  Last Trade Size
			"l",//     Last Trade (With Time)
			"l1",//   Last Trade (Price Only)
			"i5",//Order Book (Real-time)
	};

	public String getRealtimeData() {
		YahooFetcher yahooFetcher = new YahooFetcher();
		String fetcthData=yahooFetcher.getMultipleSymbolData(symbols, tags);
		String[] Equities = fetcthData.split("\n");
		for (String EquityString : Equities) {
			String [] EquityItem=EquityString.split(",");
		}
		
		return yahooFetcher.getMultipleSymbolData(symbols, tags);
	}

}
