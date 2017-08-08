package com.citi.training.service.impl;

import javax.annotation.Resource;

import com.citi.training.dao.TraderMapper;
import com.citi.training.model.Trader;
import com.citi.training.service.ITraderService;

public class TraderServiceImpl implements ITraderService{

	@Resource
	private TraderMapper traderDao;
	
	@Override
	public Trader getTraderById(Long traderId) {		
		return this.traderDao.selectByPrimaryKey(traderId);
	}

	@Override
	public int deleteTraderByIdy(Long id) {
		return this.traderDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertTrader(Trader record) {
		return this.traderDao.insert(record);
	}

	@Override
	public int insertSelective(Trader record) {
		return this.traderDao.insertSelective(record);
	}

	@Override
	public int updateTraderSelective(Trader record) {
		return this.traderDao.updateByPrimaryKey(record);
	}

	@Override
	public int updateTrader(Trader record) {
		return this.traderDao.updateByPrimaryKeySelective(record);
	}

}
