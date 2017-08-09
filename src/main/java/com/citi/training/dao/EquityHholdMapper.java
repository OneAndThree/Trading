package com.citi.training.dao;

import com.citi.training.model.EquityHhold;

import java.util.List;
import java.util.Map;

public interface EquityHholdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EquityHhold record);

    int insertSelective(EquityHhold record);

    EquityHhold selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EquityHhold record);

    int updateByPrimaryKey(EquityHhold record);

    List<Map<String,String>>  getSharesHold(EquityHhold record);

    List<Map<String,Object>>  getSharesAllHold(EquityHhold record);
}