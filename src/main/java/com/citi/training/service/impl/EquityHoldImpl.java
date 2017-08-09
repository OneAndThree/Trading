package com.citi.training.service.impl;

import com.citi.training.dao.EquityHholdMapper;
import com.citi.training.dao.OrderdetailMapper;
import com.citi.training.model.EquityHhold;
import com.citi.training.model.Orderdetail;
import com.citi.training.service.IEquityHoldService;
import com.citi.training.service.IOrderdetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("orderholdfortrader")
public class EquityHoldImpl implements IEquityHoldService {

    @Resource
    EquityHholdMapper equityhold=null;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(EquityHhold record) {
        return 0;
    }

    @Override
    public int insertSelective(EquityHhold record) {
        return 0;
    }

    @Override
    public EquityHhold selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(EquityHhold record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(EquityHhold record) {
        return 0;
    }

    @Override
    public List<Map<String, String>> getSharesHold(Long traderid, String symbol) {
        EquityHhold record=new EquityHhold();
        record.setTraderId(traderid);
        record.setSymbol(symbol);
        return equityhold.getSharesHold(record);
    }

    @Override
    public List<Map<String, Object>> getSharesAllHold(Long traderid) {
        EquityHhold record=new EquityHhold();
        record.setTraderId(traderid);
        return equityhold.getSharesAllHold(record);
    }

}
