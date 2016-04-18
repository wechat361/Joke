package com.ctlovedove.joke.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ctlovedove.joke.bean.Keyword;
import com.ctlovedove.joke.bean.Manager;
import com.ctlovedove.joke.service.KeywordService;
import com.ctlovedove.log.annotation.SystemControllerLog;
import com.ctlovedove.util.Pager;
import com.ctlovedove.util.StringUtil;

/**
 * 关键字ACTION
 * @author chenting
 *
 */
@Controller("keywordAction")
@RequestMapping("/admin/keyword")
public class KeywordAction {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private KeywordService keywordService;
	
	public KeywordService getKeywordService() {
		return keywordService;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	/**
	 * 列表页
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(Keyword keyword,
					   @RequestParam(value="pageIndex", required=false, defaultValue="1")int pageIndex, 
					   @RequestParam(value="pageSize", required=false, defaultValue="10")int pageSize, Model model){
		if(pageIndex < 1){
			pageIndex = 1;
		}
		
		if(pageSize < 1){
			pageSize = 10;
		}
		logger.info("Keyword查询条件"+keyword.toString());
		int totalCount = keywordService.getTotalCountByPager(keyword);
		List<Keyword> keywordList = keywordService.queryKeywordListByPager(keyword, pageIndex, pageSize);
		logger.info("Keyword查询结果"+keywordList.size());
		Pager pager = new Pager();
		pager.setPageIndex(pageIndex);
		pager.setPageSize(pageSize);
		pager.setTotalCount(totalCount);
		model.addAttribute("pager", pager);
		model.addAttribute("keyword", keyword);
		model.addAttribute("keywordList", keywordList);
		return "admin/keywordList";
	}
	
	@RequestMapping("toAdd")
	public String toAdd(HttpServletRequest request){
		return "admin/keywordAdd";
	}
	
	@RequestMapping("save")
	@SystemControllerLog(description="新增禁词")
	public String save(Keyword keyword,@RequestParam(value="keywords") String keywords,  
			RedirectAttributes attributes, HttpServletRequest request, HttpSession session){
		if(StringUtil.isNull(keywords)){
			request.setAttribute("errorMsg", "禁词不能为空哦！");
			logger.error("禁词不能为空");
			return "admin/keywordAdd";
		}
		Manager m = (Manager)session.getAttribute("currentManager");
		if(m == null){
			request.setAttribute("errorMsg", "您的登陆已失效，请重新登陆！");
			logger.error("用户登陆失效");
			return "login";
		}
		keyword.setAccount(m.getAccountName());
		keyword.setCreateDate(new Date());
		keyword.setState(1);
		String keywordss[] = keywords.split("\r\n");
		int length = keywordss == null ? 0 : keywordss.length;
		List<Keyword> keywordList = new ArrayList<Keyword>();
		try {
			for (int i = 0; i < length; i++) {
				String ke = keywordss[i];
				if(StringUtil.isNull(ke)){
					continue;
				}
				Keyword newKeyword = new Keyword();
				BeanUtils.copyProperties(keyword, newKeyword);
				newKeyword.setKeyword(ke);
				keywordList.add(newKeyword);
			}
			keywordService.addBatch(keywordList);
			attributes.addFlashAttribute("errorMsg", "添加成功！");
		} catch(DuplicateKeyException e){
			logger.error("含有重复禁词", e);
			request.setAttribute("errorMsg", "添加失败，含有重复禁词！");
			return "admin/keywordAdd";
		} catch (Exception e) {
			logger.error("添加禁词失败", e);
			request.setAttribute("errorMsg", "添加失败！");
			return "admin/keywordAdd";
		}
		return "redirect:list.do";
	}
	
	@RequestMapping("delete")
	@SystemControllerLog(description="删除禁词")
	public String deleteKeywords(HttpServletRequest request, RedirectAttributes attributes){
		String errorMsg = "删除成功";
		String[] ids = request.getParameterValues("id");
		if(ids == null || ids.length == 0){
			attributes.addFlashAttribute("errorMsg", "没有选择要删除的内容");
			logger.error("deleteKeywords----没有选择要删除的内容");
			return "redirect:list.do";
		}
		try {
			keywordService.deleteByIds(ids);
		} catch (Exception e) {
			errorMsg = "删除失败";
			logger.error("deleteKeywords----删除失败", e);
		}
		attributes.addFlashAttribute("errorMsg", errorMsg);
		return "redirect:list.do";
	}
	
	/**
	 * 进入禁词更新页面
	 * @return
	 */
	@RequestMapping("toUpdate")
	public String editorKeyword(@RequestParam("id") int id, RedirectAttributes attributes, HttpServletRequest request){
		Keyword keyword = keywordService.queryById(id);
		if(keyword == null){
			attributes.addFlashAttribute("errorMsg", "该内容已不存在");
			return "redirect:list.do";
		}
		request.setAttribute("keyword", keyword);
		return "admin/keywordEdit";
	}
	
	/**
	 * 更新禁词
	 * @return
	 */
	@RequestMapping("update")
	@SystemControllerLog(description="修改禁词")
	public String updateKeyword(Keyword keyword, RedirectAttributes attributes, HttpServletRequest request){
		logger.info(keyword);
		if(keyword == null){
			attributes.addFlashAttribute("errorMsg", "该内容已不存在");
			return "redirect:list.do";
		}
		try {
			keywordService.update(keyword);
			attributes.addFlashAttribute("errorMsg", "修改成功");
		} catch (Exception e) {
			attributes.addFlashAttribute("errorMsg", "修改失败");
			logger.error("修改失败", e);
		}
		
		return "redirect:list.do";
	}
	
	/**
	 * 审核禁词
	 * @param request
	 * @param attributes
	 * @return
	 */
	@RequestMapping("checkPass")
	@SystemControllerLog(description="审核禁词")
	public String checkPass(HttpServletRequest request, RedirectAttributes attributes){
		String errorMsg = "操作成功";
		String returnView = "redirect:list.do";
		String from = request.getParameter("from");
		String[] ids = request.getParameterValues("id");
		
		if("view".equals(from) && ids != null && ids.length > 0){
			returnView = "viewJoke.do?id="+ids[0];
		}
		
		if(ids == null || ids.length == 0){
			attributes.addFlashAttribute("errorMsg", "没有选择要审核的内容");
			logger.error("checkPass----没有选择要审核的内容");
			return returnView;
		}
		try {
			keywordService.updateKeywordState(ids);
		} catch (Exception e) {
			errorMsg = "操作失败";
			logger.error("checkPass----审核失败", e);
		}
		attributes.addFlashAttribute("errorMsg", errorMsg);
		return returnView;
	}
}
