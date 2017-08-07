package com.citi.training.model;

public class Trader {
	private Integer traderId;
	private String name;
	private String password;
	private String email;

	public Integer getTraderId() {
		return traderId;
	}

	public void setTraderId(Integer traderId) {
		this.traderId = traderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Trader [traderId=" + traderId + ", name=" + name + ", password=" + password + ", email=" + email + "]";
	}

}
