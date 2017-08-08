package com.citi.training.model;

public class OrderExecution {
    private Long id;

    private Integer fills;

    private Long orderId;

    private Integer rejections;

    private Integer actives;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFills() {
        return fills;
    }

    public void setFills(Integer fills) {
        this.fills = fills;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getRejections() {
        return rejections;
    }

    public void setRejections(Integer rejections) {
        this.rejections = rejections;
    }

    public Integer getActives() {
        return actives;
    }

    public void setActives(Integer actives) {
        this.actives = actives;
    }
}