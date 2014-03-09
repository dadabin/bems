/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cdu.edu.TQC.bems.db.dao.UrlRoleDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.UrlRoleDaoImpl;
import cn.cdu.edu.TQC.bems.service.UrlService;

/**
 * @ClassName: UrlFilter 说明： url过滤，只允许登陆后才能进入后台页面
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-5 下午9:38:34
 * 
 */
public class UrlFilter implements Filter {

    private String loginPage = "login.html";
    private FilterConfig filterConfig;

    @Override
    public void destroy() {
	// TODO Auto-generated method stu
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	// 获取当前页面请求\
	HttpServletRequest req = (HttpServletRequest) request;
	HttpServletResponse res = (HttpServletResponse) response;
	String uri = req.getRequestURL().toString();

	// 判断ssesion中是否有admin参数,以此来判断用户是否登录
	HttpSession session = req.getSession();
	// 访问登录页面或者已经登录
	if (uri.endsWith(loginPage) || uri.endsWith("login")
		|| session.getAttribute("user") != null) {

		System.out.println("power:"+session.getAttribute("power").toString());
		if(isUseByRoleId(session.getAttribute("power").toString(),uri)){
		}else{
		 // 重定向
		    res.sendRedirect("../" + loginPage);
		}



//	    if ( isUseByRoleId(session.getAttribute("power").toString(), uri) ) {
//
//		System.out.println("------" + session.getAttribute("power"));
//		chain.doFilter(req, res);
//
//	    } else {
//
//		res.sendRedirect("login.html");
//	    }

//		if(isUseByRoleId(session.getAttribute("power").toString(),uri)){
//			
//		}else{
//			
//			 res.sendRedirect("http://localhost:8080/bems_v4/login.html");
//		}
		


	    chain.doFilter(req, res);
	    
	    return;

	} else {
	    // 重定向
	    res.sendRedirect("../" + loginPage);
	}
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
	// 获取过滤器配置参数
	filterConfig = config;
	if (filterConfig.getInitParameter("loginPage") != null) {
	    loginPage = filterConfig.getInitParameter("loginPage");
	}
    }

    /**
     * 判断是否有权限访问该url路径，有权限放回true否则返回false
     * Administrator
     * @param roleid  登录的角色ID
     * @param uri  访问的html下的url路径
     * @return
     */

    public boolean isUseByRoleId(String roleid,String uri){
    	System.out.println(uri);
    	UrlService urlservice=new UrlService();
    	
    	UrlRoleDao urlroleDao=new UrlRoleDaoImpl();
		List<String> list=urlroleDao.getUrlByRoleId(Integer.parseInt(roleid));
		boolean re=false;
    	for(String url:list){
    		if(uri.endsWith(url)){
    			
    			re=true;
    			break;
    		}
    	}
    	return re;


    	

    }

}