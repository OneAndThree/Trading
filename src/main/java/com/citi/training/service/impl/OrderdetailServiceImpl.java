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
    OrderdetailMapper orderdetailMapper=null;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Orderdetail record) {
        return 0;
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
    public List<Map<String, String>> getSharesInfo(Orderdetail record) {
        return orderdetailMapper.getSharesInfo(record);
    }
}
