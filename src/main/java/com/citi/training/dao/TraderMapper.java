package com.citi.training.dao;

import com.citi.training.model.Trader;

public interface TraderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Trader record);

    int insertSelective(Trader record);

    Trader selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Trader record);

    int updateByPrimaryKey(Trader record);
}