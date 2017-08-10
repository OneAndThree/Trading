package com.citi.training.service.impl;

import com.citi.training.service.IOrderdetailService;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class StrategiesService {


    @Resource
    IOrderdetailService orderdetailService=null;

    public enum StrategeName{
        M,IOC,FOK,GTC
    }
    public void chooseStrateges(String type, String symbol, int quantity, double price, LocalDate deadline){

        if (type.equals(StrategeName.M)) {
            stratesgeOfM(symbol,quantity);
        }else if(type.equals(StrategeName.IOC)){
            stratesgeOfIOC(symbol,quantity);
        }else if(type.equals(StrategeName.FOK)){
            stratesgeOfFOK(symbol,quantity,price);
        }else if(type.equals(StrategeName.GTC)){
            stratesgeOfGTC(symbol,quantity,price,deadline);
        }

    }

    private void stratesgeOfGTC(String symbol, int quantity, double price, LocalDate deadline) {
        //TODO


    }

    private void stratesgeOfFOK(String symbol, int quantity, double price) {
        //TODO
    }

    private void stratesgeOfIOC(String symbol, int quantity) {
        //TODO
    }

    private void stratesgeOfM(String symbol, int quantity) {
        //TODO
        List<Map<String, String>> allBidBook=orderdetailService.getBitOrOfferList("B",symbol);
        List<Map<String, String>> allOfferBook=orderdetailService.getBitOrOfferList("O",symbol);

    }

}
