package com.citi.training.service.impl;

import javax.annotation.Resource;

import com.citi.training.dao.TraderMapper;
import com.citi.training.model.Trader;
import com.citi.training.service.ITraderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("traderService")
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

	@Override
	public Trader selectByName(String name) {
		return this.traderDao.getByName(name);
	}

	@Override
	public List<Map<String, String>> getAllTrader() {
		return this.traderDao.getAllTrader();
	}


}
