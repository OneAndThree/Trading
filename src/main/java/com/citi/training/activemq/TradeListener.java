package com.citi.training.activemq;

/**
 * Created by xtf on 2017/8/7.
 */
public class TradeListener {
    public void handleMessage(String message){
        System.out.println(message);
    }

}
