/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.citi.training.model;


import java.util.Date;

public class TradeOrderDetail {

	private String ticker;// symbol

	private int shares;

	private TradeAction action;

	private String username;

	private double respectPrice;

	private String strategetype;

	private Date deadline;

	private int submitStutes;

	public int getSubmitStutes() {
		return submitStutes;
	}

	public void setSubmitStutes(int submitStutes) {
		this.submitStutes = submitStutes;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	private int quantity;

	public double getRespectPrice() {
		return respectPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStrategetype() {
		return strategetype;
	}

	public void setStrategetype(String strategetype) {
		this.strategetype = strategetype;
	}

	public void setRespectPrice(double respectPrice) {
		this.respectPrice = respectPrice;
	}

	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public int getShares() {
		return this.shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public TradeAction getAction() {
		return this.action;
	}

	public void setAction(TradeAction action) {
		this.action = action;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "[ticker=" + this.ticker + ", shares=" + this.shares
				+ ", action=" + this.action + ", username=" + this.username + "]";
	}


	public enum TradeAction {
		Buy, Sell;
	}

}
