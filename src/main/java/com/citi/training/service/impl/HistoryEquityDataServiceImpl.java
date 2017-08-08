package com.citi.training.service.impl;

import com.citi.training.dao.HistoryEquityDataMapper;
import com.citi.training.model.HistoryEquityData;
import com.citi.training.service.IHistoryEquityDataService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("historyEquityDataService")
public class HistoryEquityDataServiceImpl implements IHistoryEquityDataService{
	private static Logger logger = Logger.getLogger(HistoryEquityDataServiceImpl.class);
	@Resource
    private HistoryEquityDataMapper historyEquityDataMapper;
	@Override
	public int saveRecord(HistoryEquityData record) {	
		if(historyEquityDataMapper.selectByPrimaryKey(record.getPrefix())!=null) {
			System.out.println("Updating data");
			int flag=historyEquityDataMapper.updateByPrimaryKey(record);
			System.out.println("Updatd data end");
			return flag;			
		}else {
			System.out.println("inserting data");
			int flag=historyEquityDataMapper.insert(record);
			System.out.println("insert data end");
			return flag;
		    //return historyEquityDataMapper.insert(record);
		}
	}
	@Override
	public HistoryEquityData getEquityData(String symble, String period) {		
		return historyEquityDataMapper.selectByPrimaryKey(symble+"-"+period);
	}

}
