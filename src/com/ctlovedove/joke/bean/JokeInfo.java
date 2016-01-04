package com.ctlovedove.joke.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 笑话实体类
 * @author chenting
 *
 */
public class JokeInfo implements Serializable {

	private static final long serialVersionUID = 2998530960498357302L;
	
	private int id;//id
	private int typeId;//分类ID
	private String title;//标题
	private String source;//来源
	private String sourceIp;//来源Ip
	private String content;//内容
	private String image;//图片地址
	private Date pubDate;//发布时间
	private int state;//状态：0-未审核；1-已审核
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "JokeInfo [id=" + id + ", typeId=" + typeId + ", title=" + title
				+ ", source=" + source + ", sourceIp=" + sourceIp
				+ ", content=" + content + ", image=" + image + ", pubDate="
				+ pubDate + ", state=" + state + "]";
	}
}
