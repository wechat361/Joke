package com.ctlovedove.joke.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 管理员类
 * @author chenting
 *
 */
public class Manager implements Serializable{

	private static final long serialVersionUID = -2933914527847993583L;
	
	private int accountId;
	private String accountName;
	private String password;
	private String phone;
	private String email;
	private String address;
	private Date registerTime;
	private Date updateTime;
	private int state;
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Manager [accountId=" + accountId + ", accountName="
				+ accountName + ", password=" + password + ", phone=" + phone
				+ ", email=" + email + ", address=" + address
				+ ", registerTime=" + registerTime + ", updateTime="
				+ updateTime + ", state=" + state + "]";
	}
}
