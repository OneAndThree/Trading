package com.citi.training.service;

import com.citi.training.model.EquityHhold;
import com.citi.training.model.Orderdetail;

import java.util.List;
import java.util.Map;

public interface IEquityHoldService {
    public int deleteByPrimaryKey(Integer id);

    public int insert(EquityHhold record);

    public int insertSelective(EquityHhold record);

    public EquityHhold selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(EquityHhold record);

    public int updateByPrimaryKey(EquityHhold record);

    public List<Map<String,String>>  getSharesHold(Long traderid,String symbol);

    public List<Map<String,Object>>  getSharesAllHold(Long traderid);
}
