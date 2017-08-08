package com.citi.training.service;

import com.citi.training.model.HistoryEquityData;

public interface IHistoryEquityDataService {  
    public int saveRecord(HistoryEquityData record);  
    public HistoryEquityData getEquityData(String symble,String period);
}  