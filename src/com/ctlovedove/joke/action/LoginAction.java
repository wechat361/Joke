package com.ctlovedove.joke.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctlovedove.joke.bean.Manager;
import com.ctlovedove.joke.service.ManagerService;
import com.ctlovedove.util.StringUtil;

@Controller("loginAction")
//@SessionAttributes("currentManager")
public class LoginAction {
	private Logger logger = Logger.getLogger(LoginAction.class);
	@Resource(name="managerService")
	private ManagerService managerService;

	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	
	/**
	 * 登录功能
	 * @param manager
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Manager manager, Model model, HttpServletRequest request){
		String ip = request.getRemoteAddr();
		logger.info("ip-----------------"+ip);
		String username = manager.getAccountName();
		String password = manager.getPassword();
		logger.info("username-----------------"+username);
		logger.info("password-----------------"+password);
		Manager m = (Manager)request.getSession().getAttribute("currentManager");
		
		if(m == null){
			if(StringUtil.isNotNull(username) && StringUtil.isNotNull(password)){
				m = managerService.queryManagerByNameAndPwd(username, password);
			}else{
				model.addAttribute("errorMsg", "用户名或密码不得为空");
				return "login";
			}
		}
		
		if(m != null){
			List<Manager> managerList = managerService.queryManagerListByPager(new Manager(), 1, 5);
			model.addAttribute( "managerList",managerList);
			request.getSession().setAttribute("currentManager", m);
			//model.addAttribute("currentManager", m);
			return "admin/index";
		}
		model.addAttribute("errorMsg", "用户名或密码错误");
		return "login";
	}
	
	/**
	 * 退出
	 * @param manager
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("admin/logout")
	public String logout(HttpServletRequest request, HttpSession session){
		String ip = request.getRemoteAddr();
		logger.info("ip-----------------"+ip);
		Manager manager = (Manager)session.getAttribute("currentManager");
		if(manager != null){
			session.invalidate();
		}
		return "login";
	}
	
	//TODO 用于表单数据绑定，具体原理还有待研究
//	@InitBinder
//	public void initBinder(WebDataBinder binder){
//		binder.setFieldDefaultPrefix("manager.");//别名前缀，在jsp页面中，表单name值必须为“manager.password”的形式
//	}

}
