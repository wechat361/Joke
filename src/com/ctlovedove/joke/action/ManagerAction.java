package com.ctlovedove.joke.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.ctlovedove.joke.bean.Manager;
import com.ctlovedove.joke.service.ManagerService;
import com.ctlovedove.util.Pager;
import com.ctlovedove.util.StringUtil;
/**
 * 管理员controller
 * @author chenting
 *
 */
@Controller("managerAction")
@RequestMapping("/admin/manager")
public class ManagerAction {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private ManagerService managerService;

	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	
	/**
	 * 列表页
	 * @param manager
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(@RequestParam(value="manager",required=false) Manager manager, 
					   @RequestParam(value="pageIndex",required=false, defaultValue="1") int pageIndex, 
					   @RequestParam(value="pageSize",required=false, defaultValue="10") int pageSize,	Model model){
		if(pageIndex < 1){
			pageIndex = 1;
		}
		if(pageSize < 1){
			pageSize = 10;
		}
		int totalCount = managerService.getTotalCountByPager(manager);
		List<Manager> managerList = managerService.queryManagerListByPager(manager, pageIndex, pageSize);
		Pager pager = new Pager();
		pager.setPageIndex(pageIndex);
		pager.setPageSize(pageSize);
		pager.setTotalCount(totalCount);
		
		model.addAttribute("managerList", managerList);
		model.addAttribute("pager", pager);
		return "admin/managerList";
	}
	
	/**
	 * 进入添加账号页面
	 * @return
	 */
	@RequestMapping("add")
	public String add(){
		return "admin/managerAdd";
	}
	
	/**
	 * 添加新账号
	 * @param manager
	 * @param attributes
	 * @return
	 */
	@RequestMapping("save")
	public String save(@Valid Manager manager, /*Model model, */RedirectAttributesModelMap attributes){
		
		String accountName = manager.getAccountName();
		String password = manager.getPassword();
		if(StringUtil.isNull(accountName) || StringUtil.isNull(password)){
			//model.addAttribute("errorMsg", "用户名或密码不得为空");
			attributes.addFlashAttribute("errorMsg", "用户名或密码不得为空");
			return "redirect:list.do";
		}

		Manager oldManager = managerService.queryByName(accountName);
		
		if(oldManager != null) {
			//model.addAttribute("errorMsg", "该用户名已存在");
			attributes.addFlashAttribute("errorMsg", "该用户名已存在");
			return "redirect:list.do";
		}
		
		manager.setRegisterTime(new Date());
		try {
			managerService.save(manager);
			//model.addAttribute("errorMsg", "添加成功");
			attributes.addFlashAttribute("errorMsg", "添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			//model.addAttribute("errorMsg", "添加失败");
			attributes.addFlashAttribute("errorMsg", "添加失败");
		}
		
		return "redirect:list.do";
	}
	
	/**
	 * 删除账号
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(@RequestParam(value="id", required=true) Integer id, RedirectAttributes redirectAttributes){
		if(id == null){
			redirectAttributes.addFlashAttribute("errorMsg", "请选择要删除的内容");
			return "redirect:list.do";
		}
		try {
			managerService.deleteById(id);
			redirectAttributes.addFlashAttribute("errorMsg", "删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "删除失败");
		}
		return "redirect:list.do";
	}
	
	/**
	 * 进入更新页面
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("toUpdate")
	public String toUpdate(int id, Model model, RedirectAttributes redirectAttributes){
		Manager manager = managerService.queryById(id);
		if(manager == null){
			redirectAttributes.addFlashAttribute("errorMsg", "ID错误");
			return "redirect:list.do";
		}
		model.addAttribute("manager", manager);
		return "admin/managerEdit";
	}
	
	/**
	 * 更新
	 * @param manager
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("update")
	public String update(Manager manager, RedirectAttributes redirectAttributes){
		if(manager == null){
			redirectAttributes.addFlashAttribute("errorMsg", "修改失败");
			return "redirect:list.do";
		}
		
		try {
			logger.info("------"+manager.getAddress());
			manager.setUpdateTime(new Date());
			managerService.update(manager);
			redirectAttributes.addFlashAttribute("errorMsg", "修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "修改失败");
		}
		
		return "redirect:list.do";
	}
	
	/**
	 * 进入密码重置页面
	 * @return
	 */
	@RequestMapping("toResetPwd")
	public String toResetPwd(){
		
		return "admin/managerPwdResets";
	}
	
	/**
	 * 密码重置
	 * @param accountName
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping("resetPassword")
	public String resetPassword(String accountName, String oldPassword, 
			String newPassword, Model model, RedirectAttributes attributes){
		Manager manager = null;
		Manager newManager = null;
		if(StringUtil.isNull(accountName)){
			model.addAttribute("errorMsg", "用户名不能为空");
			return "admin/managerPwdResets";
		}
		if(StringUtil.isNull(oldPassword)){
			model.addAttribute("errorMsg", "旧密码不能为空");
			model.addAttribute("accountName", accountName);
			return "admin/managerPwdResets";
		}
		if(StringUtil.isNull(oldPassword)){
			model.addAttribute("errorMsg", "新密码不能为空");
			model.addAttribute("accountName", accountName);
			return "admin/managerPwdResets";
		}
		manager = managerService.queryManagerByNameAndPwd(accountName, oldPassword);
		if(manager == null){
			model.addAttribute("errorMsg", "账号或密码不存在");
			model.addAttribute("accountName", accountName);
			return "admin/managerPwdResets";
		}
		try {
			newManager = new Manager();
			newManager.setAccountId(manager.getAccountId());
			newManager.setPassword(newPassword);
			managerService.update(manager);
			attributes.addFlashAttribute("errorMsg", "密码修改成功");
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			attributes.addFlashAttribute("errorMsg", "密码修改失败，请稍后重试");
		}
		return "redirect:list.do";
	}
}
