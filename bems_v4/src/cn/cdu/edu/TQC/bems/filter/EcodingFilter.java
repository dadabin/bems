/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName: EcodingFilter 
 * 说明： 字符集过滤器
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-5 下午8:36:47
 * 
 */
public class EcodingFilter implements Filter {
    private String charset = "";
    
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	// 设置字符过滤
	request.setCharacterEncoding(this.charset);
	response.setCharacterEncoding(this.charset);
	chain.doFilter(request, response);
    }

    public void destroy() {
	//TODO 销毁
    }
    
    public void init(FilterConfig config) throws ServletException {
	// 获取初始化参数
	this.charset = config.getInitParameter("charset");
    }
}
