package com.citi.training.model;

public class Fill {
	private Double price;
	private Integer quantity;
	private OrderExecution orderExecution;

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public OrderExecution getOrderExecution() {
		return orderExecution;
	}

	public void setOrderExecution(OrderExecution orderExecution) {
		this.orderExecution = orderExecution;
	}

	@Override
	public String toString() {
		return "Fill [price=" + price + ", quantity=" + quantity + ", orderExecution=" + orderExecution + "]";
	}

}
