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
	private int type;
	private Integer state;
	private String account;
	private Date createDate;
	private Date startDate;//开始时间，查询时使用
	private Date endDate;//结束时间，查询时使用
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
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "KeywordInfo [id=" + id + ", keyword=" + keyword + ", type="
				+ type + ", state=" + state + ", account=" + account
				+ ", createDate=" + createDate + "]";
	}
}
