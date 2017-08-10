package com.citi.training.service.impl;

import com.citi.training.dao.FillMapper;
import com.citi.training.model.Fill;
import com.citi.training.service.IFillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("fillService")
public class FillServiceImpl implements IFillService{
    @Resource
    FillMapper fillMapper=null;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Fill record) {

        return fillMapper.insert(record);
    }

    @Override
    public int insertSelective(Fill record) {
        return fillMapper.insertSelective(record);
    }

    @Override
    public Fill selectByPrimaryKey(Integer id) {
        return fillMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Fill record) {
        return fillMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Fill record) {
        return fillMapper.updateByPrimaryKey(record);
    }

    @Override
    public Fill getByExcudeId(Long orderexcutionId) {
        return fillMapper.getByExcudeId(orderexcutionId);
    }
}
