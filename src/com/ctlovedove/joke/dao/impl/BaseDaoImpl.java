package com.ctlovedove.joke.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public class BaseDaoImpl{

	@Resource//自动注入
	public SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
