package com.ctlovedove.log.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONArray;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ctlovedove.joke.bean.Manager;
import com.ctlovedove.joke.bean.SystemLog;
import com.ctlovedove.joke.service.SystemLogService;
import com.ctlovedove.log.annotation.SystemControllerLog;
import com.ctlovedove.log.annotation.SystemServiceLog;
import com.ctlovedove.util.IpUtil;

/**
 * 日志管理切点类
 * @author chenting
 *
 */
@Aspect
@Component
public class SystemLogAspect {
	//注入Service用于把日志保存数据库 
	@Resource
	private SystemLogService systemLogService;
	//本地异常日志记录对象  
    private  static  final Logger logger = Logger.getLogger(SystemLogAspect.class);
    
    //controller层切入点
    @Pointcut("@annotation(com.ctlovedove.log.annotation.SystemControllerLog)")
    public void controllerAspect() {
    	System.out.println("========controllerAspect===========");
    }
    //service层切入点
    @Pointcut("@annotation(com.ctlovedove.log.annotation.SystemServiceLog)")
    public void serviceAspect() {
    	System.out.println("========serviceAspect===========");
    }
	
    /**
     * 前置通知 用于拦截Controller层操作 
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		// 获取登陆用户信息
		Manager manager = (Manager) request.getSession().getAttribute(
				"currentManager");
		// 获取请求ip
		String ip = IpUtil.getClientIp(request);
		try {
			// *========控制台输出=========*//
			System.out.println("=====前置通知开始=====");
			System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "."
							+ joinPoint.getSignature().getName() + "()"));
			System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
			System.out.println("请求人:" + manager.getAccountName());
			System.out.println("请求IP:" + ip);
			// *========数据库日志=========*//
			SystemLog log = new SystemLog();
			log.setDescription(getControllerMethodDescription(joinPoint));
			log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			log.setType(0);
			log.setIp(ip);
			log.setExceptionCode(null);
			log.setExceptionDetail(null);
			log.setParams(null);
			log.setCreateUser(manager.getAccountName());
			log.setCreateDate(new Date());
			// 保存数据库
			systemLogService.save(log);
			System.out.println("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}", e);
		}
	}
    
    /**
     * 异常通知 用于拦截service层记录异常日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut="serviceAspect()", throwing="e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		// 获取登陆用户信息
		Manager manager = (Manager) request.getSession().getAttribute(
				"currentManager");
		// 获取请求ip
		String ip = IpUtil.getClientIp(request);
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		Object[] args = joinPoint.getArgs();
		if (args != null) {
			JSONArray jsonArray = new JSONArray();
			jsonArray.put(args);
			params = jsonArray.toString();
		}
		try {
			/* ========控制台输出========= */
			System.out.println("=====异常通知开始=====");
			System.out.println("异常代码:" + e.getClass().getName());
			System.out.println("异常信息:" + e.getMessage());
			System.out.println("异常方法:"
					+ (joinPoint.getTarget().getClass().getName() + "."
							+ joinPoint.getSignature().getName() + "()"));
			System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
			System.out.println("请求人:" + manager.getAccountName());
			System.out.println("请求IP:" + ip);
			System.out.println("请求参数:" + params);
			/* ==========数据库日志========= */
			SystemLog log = new SystemLog();
			log.setDescription(getServiceMthodDescription(joinPoint));
			log.setExceptionCode(e.getClass().getName());
			log.setType(1);
			log.setExceptionDetail(e.getMessage());
			log.setMethod((joinPoint.getTarget().getClass().getName() + "."
					+ joinPoint.getSignature().getName() + "()"));
			log.setParams(params);
			log.setCreateUser(manager.getAccountName());
			log.setCreateDate(new Date());
			log.setIp(ip);
			// 保存数据库
			systemLogService.save(log);
			System.out.println("=====异常通知结束=====");
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex);
		}

	}

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception{
    	//获取目标类名
    	String targetName = joinPoint.getTarget().getClass().getName();
    	//获取方法名
    	String methodName = joinPoint.getSignature().getName();
    	//获取相关参数
    	Object[] arguments = joinPoint.getArgs();
    	//生成类对象
    	Class targetClass = Class.forName(targetName);
    	//获取该类中的方法
    	Method[] methods = targetClass.getMethods();
    	
    	String description = "";
    	
    	for(Method method : methods) {
    		if(!method.getName().equals(methodName)) {
    			continue;
    		}
    		Class[] clazzs = method.getParameterTypes();
    		if(clazzs.length != arguments.length) {//比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载哦
    			continue;
    		}
    		description = method.getAnnotation(SystemControllerLog.class).description();
    	}
    	return description;
    }
    
    /**
     * 获取注解中对方法的描述信息 用于service层注解
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception{
    	//获取目标类名
    	String targetName = joinPoint.getTarget().getClass().getName();
    	//获取方法名
    	String methodName = joinPoint.getSignature().getName();
    	//获取相关参数
    	Object[] arguments = joinPoint.getArgs();
    	//生成类对象
    	Class targetClass = Class.forName(targetName);
    	//获取该类中的方法
    	Method[] methods = targetClass.getMethods();
    	
    	String description = "";
    	
    	for(Method method : methods) {
    		if(!method.getName().equals(methodName)) {
    			continue;
    		}
    		Class[] clazzs = method.getParameterTypes();
    		if(clazzs.length != arguments.length) {//比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载哦
    			continue;
    		}
    		description = method.getAnnotation(SystemServiceLog.class).description();
    	}
    	
    	return description;
    }
}
