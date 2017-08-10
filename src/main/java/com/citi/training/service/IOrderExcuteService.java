package com.citi.training.service;

import com.citi.training.model.OrderExecution;

public interface IOrderExcuteService {
    public int deleteByPrimaryKey(Long id);

    public Long insert(OrderExecution record);

    public int insertSelective(OrderExecution record);

    public OrderExecution selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(OrderExecution record);

    public int updateByPrimaryKey(OrderExecution record);

    public OrderExecution selectByOrderId(Long id);
}
