package com.citi.training.dao;

import com.citi.training.model.OrderExecution;

public interface OrderExecutionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderExecution record);

    int insertSelective(OrderExecution record);

    OrderExecution selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderExecution record);

    int updateByPrimaryKey(OrderExecution record);
}