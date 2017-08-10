package com.citi.training.service;

import com.citi.training.model.Fill;

public interface IFillService {
    public int deleteByPrimaryKey(Integer id);

    public int insert(Fill record);

    public int insertSelective(Fill record);

    public Fill selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Fill record);

    public int updateByPrimaryKey(Fill record);

    public Fill getByExcudeId(Long orderexcutionId);

}
