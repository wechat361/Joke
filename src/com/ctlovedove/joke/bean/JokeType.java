package com.ctlovedove.joke.bean;

import java.io.Serializable;
/**
 * 笑话分类
 * @author chenting
 *
 */
public class JokeType implements Serializable{

	private static final long serialVersionUID = -2360457001667447406L;
	private int typeId;
	private String typeName;
	private String description;
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
