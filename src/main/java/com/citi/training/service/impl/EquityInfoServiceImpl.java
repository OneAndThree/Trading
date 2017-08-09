package com.citi.training.service.impl;


import com.citi.training.dao.EquityInfoMapper;
import com.citi.training.model.EquityInfo;
import com.citi.training.service.IEquityInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("equityInfo")
public class EquityInfoServiceImpl implements IEquityInfoService{

    @Resource
    private EquityInfoMapper EquityInfo;

    @Override
    public int deleteByPrimaryKey(String symbol) {
        return 0;
    }

    @Override
    public int insert(EquityInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(EquityInfo record) {
        return 0;
    }

    @Override
    public List<Map<String,String>> getAllEquityInfo() {
        return EquityInfo.getAllEquityInfo();
    }
}
