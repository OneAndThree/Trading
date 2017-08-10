package com.citi.training.model;

public class Fill {
    private Integer id;

    private Long price;

    private Integer quantity;

    private Long orderexcutionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getOrderexcutionId() {
        return orderexcutionId;
    }

    public void setOrderexcutionId(Long orderexcutionId) {
        this.orderexcutionId = orderexcutionId;
    }
}