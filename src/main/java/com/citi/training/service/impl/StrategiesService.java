package com.citi.training.service.impl;

import com.citi.training.service.IOrderdetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service("strategisService")
public class StrategiesService {


   @Resource
   IOrderdetailService orderdetailService=null;

    public enum StrategeName{
        M,IOC,FOK,GTC
    }
    public void chooseStrateges(String type, String symbol, int quantity, double price, LocalDate deadline,int submitted){

        if (type.equals(StrategeName.M)) {
            stratesgeOfM(symbol,quantity,submitted);
        }else if(type.equals(StrategeName.IOC)){
            stratesgeOfIOC(symbol,quantity,submitted);
        }else if(type.equals(StrategeName.FOK)){
            stratesgeOfFOK(symbol,quantity,price,submitted);
        }else if(type.equals(StrategeName.GTC)){
            stratesgeOfGTC(symbol,quantity,price,deadline,submitted);
        }

    }
    public void init(){

    }

    private void stratesgeOfGTC(String symbol, int quantity, double price, LocalDate deadline,int submitted) {
        //TODO


    }

    private void stratesgeOfFOK(String symbol, int quantity, double price,int submitted) {
        //TODO
    }

    private void stratesgeOfIOC(String symbol, int quantity,int submitted) {
        //TODO
    }

    private void stratesgeOfM(String symbol, int quantity,int submitted) {
        //TODO
        List<Map<String, String>> allBidBook=orderdetailService.getBitOrOfferList("B",symbol);
        List<Map<String, String>> allOfferBook=orderdetailService.getBitOrOfferList("O",symbol);

    }

}
