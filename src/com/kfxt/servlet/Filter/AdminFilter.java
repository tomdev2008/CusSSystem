package com.kfxt.servlet.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kfxt.Service.AdminService;
import com.kfxt.bean.AdminBean;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/kfxt/admin/*")
public class AdminFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AdminFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 类型转换
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		// 1、得到用户请求路径
		String servletPath = req.getServletPath();
		// 2、不需要过滤的页面
		String login = "/kfxt/admin/Login.jsp";// 登录页面
		String loginservlet = "/kfxt/admin/Login";// 判断servlet
		if (login.equals(servletPath) || loginservlet.equals(servletPath)) {
			chain.doFilter(req, response);
			return;
		} else {
			// 从session中获取用户并判断用户是否登录过，如果没有登录过则重定向到登录界面
			Object user = req.getSession().getAttribute("adminlogin");
			if (user == null) {
				Cookie[] cookies = null;
				cookies = req.getCookies();
				String userpass = null;
				String username = null;
				if (cookies != null) {
					Cookie usercookie = getCookieByName(cookies, "user");
					Cookie passcookie = getCookieByName(cookies, "pass");
					if (usercookie != null && passcookie != null) {
						username = usercookie.getValue();
						userpass = passcookie.getValue();
					}
					if (username != null && userpass != null) {// cookie不等于null
						// 验证cookie正确性
						AdminService ads = new AdminService();
						AdminBean u = ads.contrast(username, userpass);
						if (u != null) {
							// 保存用户登录状态--把用户存入到session
							req.getSession().setAttribute("adminlogin", u);
							chain.doFilter(req, rep);
							return;
						} else {
							System.out.println("密码不正确");
							rep.sendRedirect(req.getContextPath() + "/kfxt/admin/Login.jsp");
							return;
						}
					}
					rep.sendRedirect(req.getContextPath() + "/kfxt/admin/Login.jsp");
					return;
				}
			} else {
				chain.doFilter(req, rep);
			}
		}
	}

	public Cookie getCookieByName(Cookie[] cs, String name) {
		for (Cookie c : cs) {
			if (name.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
