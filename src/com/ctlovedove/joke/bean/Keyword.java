package com.ctlovedove.joke.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 禁词实体类
 * @author chenting
 *
 */
public class Keyword implements Serializable {

	private static final long serialVersionUID = 192281433342704883L;
	private int id;
	private String keyword;
	private String type;
	private int state;
	private String account;
	private Date createDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "KeywordInfo [id=" + id + ", keyword=" + keyword + ", type="
				+ type + ", state=" + state + ", account=" + account
				+ ", createDate=" + createDate + "]";
	}
}
