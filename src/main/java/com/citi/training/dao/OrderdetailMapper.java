package com.citi.training.dao;

import com.citi.training.model.Orderdetail;

public interface OrderdetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Orderdetail record);

    int insertSelective(Orderdetail record);

    Orderdetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Orderdetail record);

    int updateByPrimaryKey(Orderdetail record);
}