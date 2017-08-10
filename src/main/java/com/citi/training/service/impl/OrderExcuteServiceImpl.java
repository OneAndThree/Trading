package com.citi.training.service.impl;

import com.citi.training.dao.OrderExecutionMapper;
import com.citi.training.model.OrderExecution;
import com.citi.training.service.IOrderExcuteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("orderExcuteService")
public class OrderExcuteServiceImpl implements IOrderExcuteService{

    @Resource
    OrderExecutionMapper orderExecutionMapper=null;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public Long insert(OrderExecution record) {
        return orderExecutionMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderExecution record) {
        return 0;
    }

    @Override
    public OrderExecution selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(OrderExecution record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(OrderExecution record) {
        return 0;
    }

    @Override
    public OrderExecution selectByOrderId(Long orderId) {
        return orderExecutionMapper.selectByOrderId(orderId);
    }
}
