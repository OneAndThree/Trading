package com.citi.training.service.impl;

import com.citi.training.dao.OrderdetailMapper;
import com.citi.training.model.Orderdetail;
import com.citi.training.service.IOrderdetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("orderdetailfortrader")
public class OrderdetailServiceImpl implements IOrderdetailService {

    @Resource
    private OrderdetailMapper orderdetailMapper=null;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public Long insert(Orderdetail record) {
        return orderdetailMapper.insert(record);
    }

    @Override
    public int insertSelective(Orderdetail record) {
        return 0;
    }

    @Override
    public Orderdetail selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Orderdetail record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Orderdetail record) {
        return 0;
    }

    @Override
    public List<Map<String, String>> getTraderBitOrOfferList(Long traderId,String side,String symbol) {

        Orderdetail record =new Orderdetail();
        record.setTraderId(traderId);
        record.setSide(side);
        record.setSymbol(symbol);
        return orderdetailMapper.getTraderBitOrOfferList(record);
    }

    @Override
    public List<Map<String, String>> getTraderBitOrOfferActiveList(Long traderId, String side, String symbol, int active) {
        Orderdetail record =new Orderdetail();
        record.setTraderId(traderId);
        record.setSide(side);
        record.setSymbol(symbol);
        record.setActive(active);

        return orderdetailMapper.getTraderBitOrOfferActiveList(record);
    }

    @Override
    public List<Map<String, String>> getBitOrOfferActiveList(String side, String symbol, int active) {
        Orderdetail record =new Orderdetail();
        record.setSide(side);
        record.setSymbol(symbol);
        record.setActive(active);
        return null;
    }

    @Override
    public List<Map<String, String>> getBitOrOfferList(String side,String symbol) {
        Orderdetail record =new Orderdetail();
        record.setSide(side);
        record.setSymbol(symbol);
        return orderdetailMapper.getBitOrOfferList(record);
    }

    @Override
    public List<Map<String, String>> getBitList(String symbol, int actived) {
        Orderdetail record =new Orderdetail();
        record.setSymbol(symbol);
        record.setActive(actived);
        return orderdetailMapper.getBitList(record);
    }

    @Override
    public List<Map<String, String>> getOfferList(String symbol, int actived) {
        Orderdetail record =new Orderdetail();
        record.setSymbol(symbol);
        record.setActive(actived);
        return orderdetailMapper.getOfferList(record);
    }

    @Override
    public List<Map<String, String>> getOrderByTraderId(Long traderId) {
        return orderdetailMapper.getOrderByTraderId(traderId);
    }



}
