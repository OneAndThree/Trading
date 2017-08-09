package com.citi.training.service;

import com.citi.training.model.EquityInfo;

import java.util.List;
import java.util.Map;

public interface IEquityInfoService {

    public int deleteByPrimaryKey(String symbol);

    public int insert(EquityInfo record);

    public int insertSelective(EquityInfo record);

    public List<Map<String,String>> getAllEquityInfo();

}
