package com.citi.training.model;

public class OrderExecution {
	private Integer executionsId;
	private Fill fill;
	private Integer rejection;
	private Integer active;
	OrderDetail orderDetail;

	public Integer getExecutionsId() {
		return executionsId;
	}

	public void setExecutionsId(Integer executionsId) {
		this.executionsId = executionsId;
	}

	public Fill getFills() {
		return fill;
	}

	public void setFills(Fill fills) {
		this.fill = fill;
	}

	public Integer getRejection() {
		return rejection;
	}

	public void setRejections(Integer rejections) {
		this.rejection = rejection;
	}

	public Integer getActive() {
		return active;
	}

	public void setActives(Integer actives) {
		this.active = active;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetails(OrderDetail orderDetails) {
		this.orderDetail = orderDetail;
	}

	@Override
	public String toString() {
		return "OrderExecution [executionsId=" + executionsId + ", fill=" + fill + ", rejection=" + rejection
				+ ", active=" + active + ", orderDetail=" + orderDetail + "]";
	}

}
