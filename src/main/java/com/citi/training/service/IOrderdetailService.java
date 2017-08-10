package com.citi.training.service;

import com.citi.training.model.Orderdetail;

import java.util.List;
import java.util.Map;

public interface IOrderdetailService {
    public int deleteByPrimaryKey(Long id);

    public Long insert(Orderdetail record);

    public int insertSelective(Orderdetail record);

    public Orderdetail selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(Orderdetail record);

    public int updateByPrimaryKey(Orderdetail record);

    public List<Map<String,String>> getTraderBitOrOfferList(Long traderId,String side,String symbol);

    public List<Map<String,String>> getTraderBitOrOfferActiveList(Long traderId,String side,String symbol, int active);

    public List<Map<String,String>> getBitOrOfferActiveList(String side,String symbol, int active);

    public List<Map<String,String>> getBitOrOfferList(String side,String symbol);

    public List<Map<String,String>> getBitList(String symbol, int active);

    public List<Map<String,String>> getOfferList(String symbol, int active);


}
