package com.ctlovedove.joke.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctlovedove.joke.bean.Keyword;
import com.ctlovedove.joke.dao.KeywordDao;
import com.ctlovedove.joke.service.KeywordService;
/**
 * 关键字service实现类
 * @author chenting
 *
 */
@Service("keywordService")
public class KeywordServiceImpl implements KeywordService {
	
	@Resource
	private KeywordDao keywordDao;

	public KeywordDao getKeywordDao() {
		return keywordDao;
	}

	public void setKeywordDao(KeywordDao keywordDao) {
		this.keywordDao = keywordDao;
	}

	@Override
	public void save(Keyword keyword) throws Exception {
		try {
			keywordDao.save(keyword);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception 
	 */
	public void deleteByIds(String[] ids) throws Exception{
		try {
			keywordDao.deleteByIds(ids);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 修改
	 * @param keyword
	 * @throws Exception 
	 */
	public void update(Keyword keyword) throws Exception{
		try {
			keywordDao.update(keyword);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Keyword> queryKeywordListByPager(Keyword keyword,
			int pageIndex, int pageSize) {
		return keywordDao.queryListByPager(keyword, pageIndex, pageSize);
	}

	@Override
	public int getTotalCountByPager(Keyword keyword) {
		return keywordDao.getTotalCountByPager(keyword);
	}

	@Override
	public Keyword queryById(int accountId) {
		return keywordDao.queryById(accountId);
	}

	/**
	 * 批量插入数据库
	 * @param keywordList
	 */
	public void addBatch(List<Keyword> keywordList) throws Exception{
		try {
			keywordDao.addBatch(keywordList);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 批量审核
	 * @param ids
	 */
	public void updateKeywordState(String[] ids) throws Exception{
		
	}
}
