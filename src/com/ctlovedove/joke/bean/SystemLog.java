package com.ctlovedove.joke.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 系统日志实体类
 * @author chenting
 *
 */
public class SystemLog implements Serializable {

	private static final long serialVersionUID = 6736184584588358478L;
	private int id;
	private String description;
	private String method;
	private int type;
	private String ip;
	private String exceptionCode;
	private String exceptionDetail;
	private String params;
	private String createUser;
	private Date createDate;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionDetail() {
		return exceptionDetail;
	}
	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "LogInfo [id=" + id + ", description=" + description
				+ ", method=" + method + ", type=" + type + ", ip=" + ip
				+ ", exceptionCode=" + exceptionCode + ", exceptionDetail="
				+ exceptionDetail + ", params=" + params + ", createUser="
				+ createUser + ", createDate=" + createDate + ", remark="
				+ remark + "]";
	}
}
