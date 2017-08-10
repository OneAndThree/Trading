package com.citi.training.dao;

import com.citi.training.model.Orderdetail;

import java.util.List;
import java.util.Map;

public interface OrderdetailMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Orderdetail record);

    int insertSelective(Orderdetail record);

    Orderdetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Orderdetail record);

    int updateByPrimaryKey(Orderdetail record);

    List<Map<String,String>> getTraderBitOrOfferList(Orderdetail record);

    List<Map<String,String>> getTraderBitOrOfferActiveList(Orderdetail record);

    List<Map<String,String>> getBitOrOfferList(Orderdetail record);

    List<Map<String,String>> getBitOrOfferActiveList(Orderdetail record);

    List<Map<String,String>> getBitList(Orderdetail record);

    List<Map<String,String>> getOfferList(Orderdetail record);


}