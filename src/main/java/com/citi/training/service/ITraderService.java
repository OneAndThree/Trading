package com.citi.training.service;


import com.citi.training.model.Trader;

public interface ITraderService {
	
	public Trader getTraderById(Long traderId);
	
	public int deleteTraderByIdy(Long id);

    public int insertTrader(Trader record);

    public int insertSelective(Trader record);

    public int updateTraderSelective(Trader record);

    public int updateTrader(Trader record);

    public Trader selectByName(String name);

	

}
