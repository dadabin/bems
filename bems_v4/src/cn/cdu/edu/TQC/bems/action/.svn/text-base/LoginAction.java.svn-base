/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.action;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tempuri.LinkWS;
import org.tempuri.LinkWSSoap;

import cn.cdu.edu.TQC.bems.Constant;
import cn.cdu.edu.TQC.bems.db.bean.InformRecord;
import cn.cdu.edu.TQC.bems.db.bean.Staff;
import cn.cdu.edu.TQC.bems.db.dao.StaffDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.InformRecordDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.StaffDaoImpl;
import cn.cdu.edu.TQC.simulation.timer.CollectAmmeterData;

/**
 * @ClassName: LoginAction 说明： TODO(Tell the reader such role.)
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-5 下午8:29:25
 * 
 */

public class LoginAction extends HttpServlet {

    private StaffDao dao;

    public LoginAction() {
	dao = new StaffDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	// 获取请求参数
	String name = req.getParameter("username");
	String pwd = req.getParameter("password");
	String power = req.getParameter("power");// manage和staff

	// 查看是否收到回复
	// insertResponseMsg();

	// 判断是否已经登陆
	Staff staff = (Staff) req.getSession().getAttribute("user");

	if (staff != null) {
	    // 分发页面
	    doForward(staff, req, resp);

	} else if (name != null && pwd != null && power != null
		&& !name.equals("") && !pwd.equals("") && !power.equals("")) {
	    // 验证数据合法性
	    staff = dao.getStaffById(name);
	    // 用户存在，密码正确，角色合法

	    // 分发页面

	    if (staff != null && staff.getPassword().equals(pwd)
		    && staff.getRole().equals(power)) {
		
		req.getSession().setAttribute("username", name);
		req.getSession().setAttribute("power", power);
		req.getSession().setAttribute("password", pwd);
		// 分发页面
		this.doForward(staff, req, resp);
		
	    } else {
		
		// 重定向到登陆页面
		resp.sendRedirect("login.html");
		
	    }
	}
    }

    /**
     * 根据权限不同分发页面
     * 
     * @param staff
     * @param req
     * @param resp
     */
    private void doForward(Staff staff, HttpServletRequest req,
	    HttpServletResponse resp) {
	String role = staff.getRole();

	try {
	    if (role.equals("1")) {
		// 1、注册session
		req.getSession().setAttribute("user", staff);
		// 转到中层管理者页面
		req.getRequestDispatcher("html/index.html").forward(req, resp);
	    }
	    if (role.equals("2")) {
		// 1、注册session
		req.getSession().setAttribute("user", staff);
		// 转到总务人员页面
		req.getRequestDispatcher("html/index.html").forward(req, resp);
	    }
	} catch (Exception e) {
	    System.out.println("------页面分发出错了！----------");
	    System.err.println("------页面分发出错了！----------");
	}
    }

    /***
     * 
     */
    private void insertResponseMsg() {
	// 获取回复消息
	LinkWSSoap soap = new LinkWS().getLinkWSSoap();
	String content = soap.get(Constant.Enterprise_No,
		Constant.Enterprise_pwd);
	StaffDaoImpl staffDaoImpl = new StaffDaoImpl();

	// 处理回复消息
	if (content != null && !content.equals("")) {
	    Pattern pattern = Pattern.compile("[||]+");
	    String[] strs = pattern.split(content);
	    if (strs.length > 0 && strs != null) {
		for (int i = 0; i < strs.length; i++) {
		    // 一个||表示一则回复
		    InformRecord record = new InformRecord();
		    Pattern pt = Pattern.compile("[#]+");
		    String[] st = pt.split(content);

		    String phoneNumber = st[0];
		    Staff stf = staffDaoImpl.getStaffByPhoneNo(phoneNumber);
		    
		    
		    //1、回复通知人
		    
		    
		    
		    // 1、更新回复（数据库）
		    Object[] params = new Object[3];
		    params[0] = st[1];
		    params[1] = st[2];
		    params[2] = stf.getStaffId();

		    boolean f = new InformRecordDaoImpl()
			    .updateInformRecord(params);
		    if (f) {
			System.out.println("----收到一则" + stf.getStaffId()
				+ "的回复，并更新成功----");
		    }
		}
	    }
	}
    }

}
