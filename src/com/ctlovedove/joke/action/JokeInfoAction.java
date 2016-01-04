package com.ctlovedove.joke.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ctlovedove.joke.bean.JokeInfo;
import com.ctlovedove.joke.bean.JokeType;
import com.ctlovedove.joke.service.JokeInfoService;
import com.ctlovedove.joke.service.JokeTypeService;
import com.ctlovedove.util.IpUtil;
import com.ctlovedove.util.Pager;
import com.ctlovedove.util.StringUtil;

/**
 * 笑话信息ACTION
 * @author chenting
 *
 */
@Controller("jokeInfoAction")
@RequestMapping("/admin/jokeInfo")
public class JokeInfoAction {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private JokeInfoService jokeInfoService;
	@Resource
	private JokeTypeService jokeTypeService;
	
	public JokeInfoService getJokeInfoService() {
		return jokeInfoService;
	}

	public void setJokeInfoService(JokeInfoService jokeInfoService) {
		this.jokeInfoService = jokeInfoService;
	}
	
	public JokeTypeService getJokeTypeService() {
		return jokeTypeService;
	}

	public void setJokeTypeService(JokeTypeService jokeTypeService) {
		this.jokeTypeService = jokeTypeService;
	}

	/**
	 * 列表页
	 * @param jokeInfo
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(JokeInfo jokeInfo,
					   @RequestParam(value="pageIndex", required=false, defaultValue="1")int pageIndex, 
					   @RequestParam(value="pageSize", required=false, defaultValue="10")int pageSize, Model model){
		if(pageIndex < 1){
			pageIndex = 1;
		}
		
		if(pageSize < 1){
			pageSize = 10;
		}
		logger.info(jokeInfo.toString());
		int totalCount = jokeInfoService.getTotalCountByPager(jokeInfo);
		List<JokeInfo> jokeInfoList = jokeInfoService.queryJokeInfoListByPager(jokeInfo, pageIndex, pageSize);
		logger.info(jokeInfoList.size());
		Pager pager = new Pager();
		pager.setPageIndex(pageIndex);
		pager.setPageSize(pageSize);
		pager.setTotalCount(totalCount);
		model.addAttribute("pager", pager);
		model.addAttribute("jokeInfo", jokeInfo);
		model.addAttribute("jokeInfoList", jokeInfoList);
		return "admin/jokeInfoList";
	}
	
	@RequestMapping("toAdd")
	public String toAdd(HttpServletRequest request){
		List<JokeType> jokeTypeList = jokeTypeService.queryJokeTypeAll();
		request.setAttribute("jokeTypeList", jokeTypeList);
		return "admin/jokeInfoAdd";
	}
	
	@RequestMapping("save")
	public String save(JokeInfo jokeInfo, RedirectAttributes attributes, HttpServletRequest request, HttpSession session){
		logger.info("新增笑话--jokeinfo--"+jokeInfo);
		try {
			if(jokeInfo == null){
				attributes.addFlashAttribute("errorMsg", "新增笑话失败");
				return "redirect:list.do";
			}
			if(StringUtil.isNull(jokeInfo.getSource())){
				jokeInfo.setSource(session.getAttribute("currentManager")+"");
			}
			jokeInfo.setSourceIp(IpUtil.getClientIp(request));
			jokeInfo.setPubDate(new Date());
			jokeInfoService.save(jokeInfo);
			attributes.addFlashAttribute("errorMsg", "新增成功");
		} catch (Exception e) {
			logger.error("保存新笑话失败", e);
			attributes.addFlashAttribute("errorMsg", "新增笑话失败");
		}
		return "redirect:list.do";
	}
	
	@RequestMapping("delete")
	public String deleteJokeInfos(HttpServletRequest request, RedirectAttributes attributes){
		String errorMsg = "删除成功";
		String[] ids = request.getParameterValues("id");
		if(ids == null || ids.length == 0){
			attributes.addFlashAttribute("errorMsg", "没有选择要删除的内容");
			logger.error("deleteJokeInfos----没有选择要删除的内容");
			return "redirect:list.do";
		}
		try {
			jokeInfoService.deleteByIds(ids);
		} catch (Exception e) {
			errorMsg = "删除失败";
			logger.error("deleteJokeInfos----删除失败", e);
		}
		attributes.addFlashAttribute("errorMsg", errorMsg);
		return "redirect:list.do";
	}
	
	/**
	 * 查看笑话详情
	 * @return
	 */
	@RequestMapping("viewJoke")
	public String viewJokeInfo(@RequestParam("id") int id, RedirectAttributes attributes, HttpServletRequest request){
		JokeInfo jokeInfo = jokeInfoService.queryById(id);
		if(jokeInfo == null){
			attributes.addFlashAttribute("errorMsg", "该内容已不存在");
			return "redirect:list.do";
		}
		request.setAttribute("jokeInfo", jokeInfo);
		return "admin/jokeInfoView";
	}
	
	/**
	 * 进入笑话编辑页面
	 * @return
	 */
	@RequestMapping("toUpdate")
	public String editorJokeInfo(@RequestParam("id") int id, RedirectAttributes attributes, HttpServletRequest request){
		JokeInfo jokeInfo = jokeInfoService.queryById(id);
		if(jokeInfo == null){
			attributes.addFlashAttribute("errorMsg", "该内容已不存在");
			return "redirect:list.do";
		}
		List<JokeType> jokeTypeList = jokeTypeService.queryJokeTypeAll();
		request.setAttribute("jokeTypeList", jokeTypeList);
		request.setAttribute("jokeInfo", jokeInfo);
		return "admin/jokeInfoEditor";
	}
	
	/**
	 * 进入笑话编辑页面
	 * @return
	 */
	@RequestMapping("update")
	public String updateJokeInfo(JokeInfo jokeInfo, RedirectAttributes attributes, HttpServletRequest request){
		logger.info(jokeInfo);
		if(jokeInfo == null){
			attributes.addFlashAttribute("errorMsg", "该内容已不存在");
			return "redirect:list.do";
		}
		try {
			jokeInfoService.update(jokeInfo);
			attributes.addFlashAttribute("errorMsg", "修改成功");
		} catch (Exception e) {
			attributes.addFlashAttribute("errorMsg", "修改失败");
			logger.error("修改失败", e);
		}
		
		return "redirect:list.do";
	}
	
	/**
	 * 审核笑话
	 * @param request
	 * @param attributes
	 * @return
	 */
	@RequestMapping("checkPass")
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
			jokeInfoService.updateJokeInfoState(ids);
		} catch (Exception e) {
			errorMsg = "操作失败";
			logger.error("checkPass----审核失败", e);
		}
		attributes.addFlashAttribute("errorMsg", errorMsg);
		return returnView;
	}
}
