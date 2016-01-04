package com.ctlovedove.joke.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ctlovedove.joke.bean.Keyword;
import com.ctlovedove.joke.dao.KeywordDao;
/**
 * 关键字DAO实现类
 * @author chenting
 *
 */
@Repository("keywordDao")
public class KeywordDaoImpl extends BaseDaoImpl implements KeywordDao{

	@Override
	public void save(Keyword keyword) {
		sqlSessionTemplate.insert("saveKeyword", keyword);
	}

	@Override
	public void update(Keyword keyword) {
		sqlSessionTemplate.update("updateKeyword", keyword);
	}

	@Override
	public void deleteById(int id) {
		sqlSessionTemplate.delete("deleteKeywordById", id);
	}

	@Override
	public List<Keyword> queryByAccount(String accountName) {
		return sqlSessionTemplate.selectList("queryByAccount", accountName);
	}

	/**
	 * 分页查找
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Keyword> queryListByPager(Keyword keyword, int pageIndex, int pageSize){
		Map param = new HashMap();
		param.put("keyword", keyword);
		param.put("pageIndex", (pageIndex - 1) * pageSize);
		param.put("pageSize", pageSize);
		return sqlSessionTemplate.selectList("queryKeywordListByPager", param);
	}
	
	/**
	 * 获取总记录数
	 * @param keyword
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getTotalCountByPager(Keyword keyword){
		Map param = new HashMap();
		param.put("keyword", keyword);
		return sqlSessionTemplate.selectOne("com.ctlovedove.joke.dao.impl.KeywordDaoImpl.getTotalCountByPager", param);
	}
	
	/**
	 * 根据ID获取信息
	 * @param accountId
	 * @return
	 */
	public Keyword queryById(int accountId){
		return sqlSessionTemplate.selectOne("queryKeywordById", accountId);
	}
}
