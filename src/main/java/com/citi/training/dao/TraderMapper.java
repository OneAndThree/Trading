package com.citi.training.dao;

import com.citi.training.model.EquityHhold;
import com.citi.training.model.Trader;

import java.util.List;
import java.util.Map;

public interface TraderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Trader record);

    int insertSelective(Trader record);

    Trader selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Trader record);

    int updateByPrimaryKey(Trader record);

    Trader getByName(String name);

    List<Map<String,String>> getAllTrader();
}