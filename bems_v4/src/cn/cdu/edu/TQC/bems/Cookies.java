/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: Cookies 说明： TODO(Tell the reader such role.)
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-5 下午9:01:50
 * 
 */
public class Cookies {
    public int maxage = 7 *  24 * 60 * 60; // 即是：一周 。设置该cookie的有效期,单位为秒
    public String path = "/tqc/bems"; // cookie路径
    Cookie[] cookie_get = {};

    public Cookies() {
	maxage = -1;
	path = "/";
    }

    /**
     * 发送cookie到客户端
     * 
     * @param response
     * @param name
     * @param value
     */
    public void putcookie(HttpServletResponse response, String name,
	    String value) {
	try {
	    Cookie cookie = new Cookie(name, encode(value));
	    cookie.setMaxAge(maxage);
	    cookie.setPath(path);
	    response.addCookie(cookie);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 从客户端浏览器回去到cookie
     * @param request
     * @param name
     * @return
     */
    public String getcookie(HttpServletRequest request, String name) {
	if (cookie_get == null || cookie_get.length == 0) {
	    cookie_get = request.getCookies();
	}
	String returnstr;
	returnstr = null;
	try {
	    for (int i = 0; cookie_get != null && i < cookie_get.length; i++) {
		cookie_get[i].setPath(path);
		if (cookie_get[i].getName().equals(name)) {
		    cookie_get[i].setMaxAge(-1);
		    returnstr += cookie_get[i].getValue().toString();
		    break;
		}
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return encode(returnstr);
    }

    /**
     * 清除cookie
     * 
     * @param response
     *            httpservletresponse
     * @param name
     *            String
     */
    public void removecookie(HttpServletResponse response, String name) {
	putcookie(response, name, null);
    }

    /**
     * 对给定字符进行 url 编码
     * 
     * @param value
     *            String
     * @return String
     */
    private static String encode(String value) {
	String result = "";
	if (!isempty(value)) {
	    try {
		java.net.URLDecoder.decode(value, "gbk");
	    } catch (UnsupportedEncodingException ex) {
		ex.printStackTrace();
	    }
	}
	return result;
    }

    /**
     * 判断是否为空，为空返回true
     * 
     * @param value
     *            String
     * @return boolean
     */
    private static boolean isempty(String value) {
	if (value == null || value.trim().equals(""))
	    return true;
	else
	    return false;
    }

    /**
     * 查检二个数据是否为空，如果为空返回true
     * 
     * @param value1
     * @param value2
     * @return
     */
    public boolean isempty(String value1, String value2) {
	if (null == value1 || null == value2 || "".equals(value1)
		|| "".equals(value2))
	    return true;
	else
	    return false;
    }
}
