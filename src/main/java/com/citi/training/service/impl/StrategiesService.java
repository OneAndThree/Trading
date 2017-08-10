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
    public enum bOrO{
        B,O
    }
    public void chooseStrateges(String type, String symbol, int quantity, double price, LocalDate deadline,int submit,String bitOrOffer){

        if (type.equals(StrategeName.M)) {
            stratesgeOfM(symbol,quantity,submit, bitOrOffer);
        }else if(type.equals(StrategeName.IOC)){
            stratesgeOfIOC(symbol,quantity,submit, bitOrOffer);
        }else if(type.equals(StrategeName.FOK)){
            stratesgeOfFOK(symbol,quantity,price,submit, bitOrOffer);
        }else if(type.equals(StrategeName.GTC)){
            stratesgeOfGTC(symbol,quantity,price,deadline,submit, bitOrOffer);
        }

    }

    private void stratesgeOfGTC(String symbol, int quantity, double price, LocalDate deadline, int submit, String bitOrOffer) {
        //TODO


    }

    private void stratesgeOfFOK(String symbol, int quantity, double price,int submit, String bitOrOffer) {
        //TODO
    }

    private void stratesgeOfIOC(String symbol, int quantity, int submit, String bitOrOffer) {
        //TODO
    }

    private void stratesgeOfM(String symbol, int quantity, int submit, String bitOrOffer) {
        //TODO
        List<Map<String, String>> allBidBook=orderdetailService.getBitOrOfferList("B",symbol);
        List<Map<String, String>> allOfferBook=orderdetailService.getBitOrOfferList("O",symbol);

    }

}
