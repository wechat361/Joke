package com.ctlovedove.joke.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctlovedove.joke.bean.SystemLog;

/**
 * 系统日志管理Service
 * @author chenting
 *
 */
public interface SystemLogService {
	/**
	 * 新增
	 * @param systemLog
	 * @throws Exception 
	 */
	public void save(SystemLog systemLog) throws Exception;
	/**
	 * 修改
	 * @param systemLog
	 */
	public void update(SystemLog systemLog);
	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(int id);

	/**
	 * 分页查找
	 * @param systemLog
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SystemLog> queryListByPager(SystemLog systemLog, int pageIndex, int pageSize);
	/**
	 * 获取总记录数
	 * @param systemLog
	 * @return
	 */
	public int getTotalCountByPager(SystemLog systemLog);
	
	/**
	 * 根据ID获取信息
	 * @param id
	 * @return
	 */
	public SystemLog queryById(@Param("id") int id);
}
