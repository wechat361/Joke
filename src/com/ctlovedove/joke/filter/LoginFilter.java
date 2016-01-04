package com.ctlovedove.joke.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ctlovedove.joke.bean.Manager;
/**
 * 
 * @author chenting
 *
 */
public class LoginFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		Manager manager = (Manager) request.getSession().getAttribute("currentManager");
		if (manager == null) {
			if (isAjaxRequest(request)) {

			} else {
				HttpServletResponse response = (HttpServletResponse) servletResponse;
				response.sendRedirect(request.getContextPath());
			}
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}

	}

	public boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
		return isAjax;
	}

	@Override
	public void destroy() {

	}
}
