package com.citi.training.dao;

import com.citi.training.model.Fill;

public interface FillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Fill record);

    int insertSelective(Fill record);

    Fill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fill record);

    int updateByPrimaryKey(Fill record);

    Fill getByExcudeId(Long orderexcutionId);
}