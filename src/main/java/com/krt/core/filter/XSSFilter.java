package com.krt.core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**  
 * @Description: XSS攻击过滤器
 * @author 殷帅
 * @date 2016年7月29日
 * @version 1.0
 */
public class XSSFilter implements Filter {


	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
	}

	public void destroy() {

	}
}
