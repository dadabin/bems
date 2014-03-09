/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.listener;

import java.util.Date;
import java.util.Enumeration;


import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;



import cn.cdu.edu.TQC.bems.db.bean.Staff;

/**
 * @ClassName: SessionListener
 * 说明：
 * 监听事件，用于处理系统日志
 * 记录登陆者信息
 * 记录session创建
 * 
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-5 下午8:33:02
 *
 */
public class SessionListener implements HttpSessionAttributeListener,HttpSessionListener {
    
    private static Logger logger = Logger.getLogger(SessionListener.class);
    

    @Override
    public void attributeAdded(HttpSessionBindingEvent e) {
	Staff staff = (Staff) e.getSession().getAttribute("user");
	if(staff != null){
	    String name = staff.getName();
	    //用户登录成功
	    logger.info(name+"在"+new Date()+"以"+staff.getRole()+"权限登陆系统！");
	}

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent e) {
	Staff staff = (Staff) e.getSession().getAttribute("user");
	if(staff != null){
	    String name = staff.getName();
	    logger.info(name+"在"+new Date()+"退出系统！");
	}
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent e) {
	
    }


    @Override
    public void sessionCreated(HttpSessionEvent e) {
	Enumeration names =  e.getSession().getAttributeNames();
	while (names.hasMoreElements()) {
	    String name = (String) names.nextElement();
	    logger.info("在"+new Date()+"系统添加了属性为："+name +"的会话"+e.getSession().getAttribute(name));
	}
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
	Enumeration names =  e.getSession().getAttributeNames();
	while (names.hasMoreElements()) {
	    String name = (String) names.nextElement();
	    logger.info("在"+new Date()+"系统添加了属性为："+name +"与系统的会话结束");
	}
	
    }

}
