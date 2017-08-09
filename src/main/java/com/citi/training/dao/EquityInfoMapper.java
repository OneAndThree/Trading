package com.citi.training.dao;

import com.citi.training.model.EquityInfo;

import java.util.List;
import java.util.Map;

public interface EquityInfoMapper {
    int deleteByPrimaryKey(String symbol);

    int insert(EquityInfo record);

    int insertSelective(EquityInfo record);

    List<Map<String,String>> getAllEquityInfo();
}