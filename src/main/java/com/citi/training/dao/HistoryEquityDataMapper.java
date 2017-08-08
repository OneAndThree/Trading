package com.citi.training.dao;

import com.citi.training.model.HistoryEquityData;

public interface HistoryEquityDataMapper {
    int deleteByPrimaryKey(String prefix);

    int insert(HistoryEquityData record);

    int insertSelective(HistoryEquityData record);

    HistoryEquityData selectByPrimaryKey(String prefix);

    int updateByPrimaryKeySelective(HistoryEquityData record);

    int updateByPrimaryKey(HistoryEquityData record);
    
    //int saveOrUpdate(HistoryEquityData record);
}