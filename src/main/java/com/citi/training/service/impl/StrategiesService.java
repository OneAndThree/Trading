package com.citi.training.service.impl;

import com.citi.training.model.EquityHhold;
import com.citi.training.model.OrderExecution;
import com.citi.training.model.Orderdetail;
import com.citi.training.service.*;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("strategisService")
public class StrategiesService {


    @Resource
    IOrderdetailService orderdetailService = null;

    @Resource
    IOrderExcuteService orderExcuteService = null;

    @Resource
    IFillService fillService = null;

    @Resource
    ITraderService traderService=null;

    @Resource
    IEquityHoldService equityHoldService = null;

    public enum StrategeName {
        M, IOC, FOK, GTC
    }

    public enum BitOrOffer {
        B, O
    }

    public void chooseStrateges(String traderName, String tradeType, String strategetype, String symbol, int quantity, double price, Date deadline, int submitted) {

        if (strategetype.equals(StrategeName.M)) {

            stratesgeOfM(traderName, tradeType, symbol, quantity, submitted);

        } else if (strategetype.equals(StrategeName.IOC)) {

            stratesgeOfIOC(traderName, tradeType, symbol, quantity, submitted);

        } else if (strategetype.equals(StrategeName.FOK)) {

            stratesgeOfFOK(traderName, tradeType, symbol, quantity, price, submitted);

        } else if (strategetype.equals(StrategeName.GTC)) {

            stratesgeOfGTC(traderName, tradeType, symbol, quantity, price, deadline, submitted);

        } else {
            stratesgeOfM(traderName, tradeType, symbol, quantity, submitted);
        }

    }


    public void init() {
        Map<String, String> map = new HashMap<>();

    }


    public void stratesgeOfGTC(String traderName, String tradeType, String symbol, int quantity, double price, Date deadline, int submitted) {
        buyOrSell(traderName, tradeType, symbol, quantity);

    }

    public void stratesgeOfFOK(String traderName, String tradeType, String symbol, int quantity, double price, int submitted) {
        buyOrSell(traderName, tradeType, symbol, quantity);
    }

    public void stratesgeOfIOC(String traderName, String tradeType, String symbol, int quantity, int submitted) {
        buyOrSell(traderName, tradeType, symbol, quantity);
    }
    public void stratesgeOfM(String traderName, String tradeType, String symbol, int quantity, int submitted){
        buyOrSell(traderName, tradeType, symbol, quantity);
    }

    public void stratesgeOfMM(String traderName, String tradeType, String symbol, int quantity, int submitted) {

        if (tradeType.equals(String.valueOf(BitOrOffer.B))) {
            List<Map<String, String>> allOfferBook = orderdetailService.getOfferList(symbol, 1);

            int qty = 0;
            if (allOfferBook.size() > 0) {
                for (Map<String, String> offerRecord : allOfferBook) {
                    qty = qty + Integer.valueOf(String.valueOf(offerRecord.get("quantity")));
                }
                if (quantity <= qty) {
                    int temp = quantity;
                    for (Map<String, String> bitRecord : allOfferBook) {
                        int quan = Integer.valueOf(String.valueOf(bitRecord.get("quantity")));

                        if (quan >= temp) {
                            Orderdetail orderdetail = new Orderdetail();
                            orderdetail.setSide("B");
                            orderdetail.setActive(0);
                            orderdetail.setDate(new Date());
                            orderdetail.setSymbol(symbol);
                            orderdetail.setPrice(Double.valueOf(String.valueOf(bitRecord.get("price"))));
                            Long traderId = traderService.selectByName(traderName).getId();
                            orderdetail.setTraderId(traderId);
                            orderdetail.setQuantity(temp);
                            orderdetail.setType(tradeType);
                            orderdetail.setSubmit(1);
                            Long orderId = orderdetailService.insert(orderdetail);

                            OrderExecution orderExecution = new OrderExecution();
                            orderExecution.setOrderId(orderId);
                            orderExecution.setFills(temp);
                            orderExecution.setRejections(0);
                            orderExcuteService.insert(orderExecution);

                            EquityHhold equityHhold = new EquityHhold();
                            equityHhold.setSymbol(symbol);
                            equityHhold.setShares(equityHoldService.getSharesHold(traderId, symbol).getShares() + temp);
                            equityHhold.setTraderId(traderId);
                            equityHhold.setId(equityHoldService.getSharesHold(traderId, symbol).getId());
                            equityHoldService.updateByPrimaryKeySelective(equityHhold);

                            Orderdetail updateOr = new Orderdetail();
                            updateOr.setId(Long.valueOf(String.valueOf(bitRecord.get("id"))));
                            updateOr.setActive(0);
                            updateOr.setQuantity(quan - temp);
                            orderdetailService.updateByPrimaryKeySelective(updateOr);

                            return;
                        } else {
                            temp = temp - quan;
                            Orderdetail orderdetail = new Orderdetail();
                            orderdetail.setSide("B");
                            orderdetail.setActive(0);
                            orderdetail.setDate(new Date());
                            orderdetail.setSymbol(symbol);
                            orderdetail.setPrice(Double.valueOf(String.valueOf(bitRecord.get("price"))));
                            Long traderId = traderService.selectByName(traderName).getId();
                            orderdetail.setTraderId(traderId);
                            orderdetail.setQuantity(quan);
                            orderdetail.setType(tradeType);
                            orderdetail.setSubmit(1);
                            Long orderId = orderdetailService.insert(orderdetail);

                            OrderExecution orderExecution = new OrderExecution();
                            orderExecution.setOrderId(orderId);
                            orderExecution.setFills(quan);
                            orderExecution.setRejections(0);
                            orderExcuteService.insert(orderExecution);

                            EquityHhold equityHhold = new EquityHhold();
                            equityHhold.setSymbol(symbol);
                            equityHhold.setShares(equityHoldService.getSharesHold(traderId, symbol).getShares() + quan);
                            equityHhold.setTraderId(traderId);
                            equityHhold.setId(equityHoldService.getSharesHold(traderId, symbol).getId());
                            equityHoldService.updateByPrimaryKeySelective(equityHhold);

                            Orderdetail updateOr = new Orderdetail();
                            updateOr.setId(Long.valueOf(String.valueOf(bitRecord.get("id"))));
                            updateOr.setActive(0);
                            updateOr.setQuantity(0);
                            orderdetailService.updateByPrimaryKeySelective(updateOr);
                        }
                    }
                }

            } else {
                Orderdetail orderdetail = new Orderdetail();
                orderdetail.setSide("B");
                orderdetail.setActive(0);
                orderdetail.setDate(new Date());
                orderdetail.setSymbol(symbol);
                Long traderId = traderService.selectByName(traderName).getId();
                orderdetail.setTraderId(traderId);
                orderdetail.setQuantity(quantity);
                orderdetail.setType(tradeType);
                orderdetail.setSubmit(1);
                Long orderId = orderdetailService.insert(orderdetail);
                OrderExecution orderExecution = new OrderExecution();
                orderExecution.setOrderId(orderId);
                orderExecution.setFills(0);
                orderExecution.setRejections(quantity);
                orderExcuteService.insert(orderExecution);
            }
        } else if (tradeType.equals(String.valueOf(BitOrOffer.O))) {
            List<Map<String, String>> allBitBook = orderdetailService.getBitList(symbol, 1);
            int qty = 0;

            if (allBitBook.size() > 0) {
                for (Map<String, String> bitRecord : allBitBook) {
                    qty = qty + Integer.valueOf(String.valueOf(bitRecord.get("quantity")));
                }
                if (quantity <= qty) {
                    int temp = quantity;
                    for (Map<String, String> bitRecord : allBitBook) {
                        int quan = Integer.valueOf(String.valueOf(bitRecord.get("quantity")));

                        if (quan >= temp) {
                            Orderdetail orderdetail = new Orderdetail();
                            orderdetail.setSide("O");
                            orderdetail.setActive(0);
                            orderdetail.setDate(new Date());
                            orderdetail.setSymbol(symbol);
                            orderdetail.setPrice(Double.valueOf(String.valueOf(bitRecord.get("price"))));
                            Long traderId = traderService.selectByName(traderName).getId();
                            orderdetail.setTraderId(traderId);
                            orderdetail.setQuantity(temp);
                            orderdetail.setType(tradeType);
                            orderdetail.setSubmit(1);
                            Long orderId = orderdetailService.insert(orderdetail);

                            OrderExecution orderExecution = new OrderExecution();
                            orderExecution.setOrderId(orderId);
                            orderExecution.setFills(temp);
                            orderExecution.setRejections(0);
                            orderExcuteService.insert(orderExecution);

                            EquityHhold equityHhold = new EquityHhold();
                            equityHhold.setSymbol(symbol);
                            equityHhold.setShares(equityHoldService.getSharesHold(traderId, symbol).getShares() + temp);
                            equityHhold.setTraderId(traderId);
                            equityHhold.setId(equityHoldService.getSharesHold(traderId, symbol).getId());
                            equityHoldService.updateByPrimaryKeySelective(equityHhold);

                            Orderdetail updateOr = new Orderdetail();
                            updateOr.setId(Long.valueOf(String.valueOf(bitRecord.get("id"))));
                            updateOr.setActive(0);
                            updateOr.setQuantity(quan - temp);
                            orderdetailService.updateByPrimaryKeySelective(updateOr);

                            return;
                        } else {
                            temp = temp - quan;
                            Orderdetail orderdetail = new Orderdetail();
                            orderdetail.setSide("B");
                            orderdetail.setActive(0);
                            orderdetail.setDate(new Date());
                            orderdetail.setSymbol(symbol);
                            orderdetail.setPrice(Double.valueOf(String.valueOf(bitRecord.get("price"))));
                            Long traderId = traderService.selectByName(traderName).getId();
                            orderdetail.setTraderId(traderId);
                            orderdetail.setQuantity(quan);
                            orderdetail.setType(tradeType);
                            orderdetail.setSubmit(1);
                            Long orderId = orderdetailService.insert(orderdetail);

                            OrderExecution orderExecution = new OrderExecution();
                            orderExecution.setOrderId(orderId);
                            orderExecution.setFills(quan);
                            orderExecution.setRejections(0);
                            orderExcuteService.insert(orderExecution);

                            EquityHhold equityHhold = new EquityHhold();
                            equityHhold.setSymbol(symbol);
                            equityHhold.setShares(equityHoldService.getSharesHold(traderId, symbol).getShares() + quan);
                            equityHhold.setTraderId(traderId);
                            equityHhold.setId(equityHoldService.getSharesHold(traderId, symbol).getId());
                            equityHoldService.updateByPrimaryKeySelective(equityHhold);

                            Orderdetail updateOr = new Orderdetail();
                            updateOr.setId(Long.valueOf(String.valueOf(bitRecord.get("id"))));
                            updateOr.setActive(0);
                            updateOr.setQuantity(0);
                            orderdetailService.updateByPrimaryKeySelective(updateOr);
                        }
                    }
                }
            } else {
                Orderdetail orderdetail = new Orderdetail();
                orderdetail.setSide("O");
                orderdetail.setActive(0);
                orderdetail.setDate(new Date());
                orderdetail.setSymbol(symbol);
                Long traderId = traderService.selectByName(traderName).getId();
                orderdetail.setTraderId(traderId);
                orderdetail.setQuantity(quantity);
                orderdetail.setType(tradeType);
                orderdetail.setSubmit(1);
                Long orderId = orderdetailService.insert(orderdetail);
                OrderExecution orderExecution = new OrderExecution();
                orderExecution.setOrderId(orderId);
                orderExecution.setFills(0);
                orderExecution.setRejections(quantity);
                orderExcuteService.insert(orderExecution);
            }
        }
    }


    public List<Map<String, String>> getOfferList(String symbol){
        return orderdetailService.getOfferList(symbol, 1);
    }
    public List<Map<String, String>> getBitList(String symbol){
        return orderdetailService.getBitList(symbol, 1);
    }

    public boolean buyOrSell(String traderName,String tradeType,String symbol,int quantity){

        Long traderId=traderService.selectByName(traderName).getId();
        EquityHhold equityHhold = new EquityHhold();
        equityHhold.setSymbol(symbol);
        equityHhold.setTraderId(traderId);
        equityHhold.setId(equityHoldService.getSharesHold(traderId, symbol).getId());
        if(tradeType.equals(String.valueOf(BitOrOffer.B))){
            equityHhold.setShares(equityHoldService.getSharesHold(traderId, symbol).getShares()+quantity);
            int rel=equityHoldService.updateByPrimaryKeySelective(equityHhold);
            System.out.println(rel);
        }else if(tradeType.equals(String.valueOf(BitOrOffer.O))){
            if(equityHoldService.getSharesHold(traderId, symbol).getShares()>=quantity){
                equityHhold.setShares(equityHoldService.getSharesHold(traderId, symbol).getShares()-quantity);
                int rel=equityHoldService.updateByPrimaryKeySelective(equityHhold);
                System.out.println(rel);
            }else{
                return false;
            }
        }
        return true;
    }

}
