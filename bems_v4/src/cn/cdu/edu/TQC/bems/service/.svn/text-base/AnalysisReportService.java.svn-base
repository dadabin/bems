/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import cn.cdu.edu.TQC.bems.db.bean.AnalysisReport;
import cn.cdu.edu.TQC.bems.db.bean.Staff;
import cn.cdu.edu.TQC.bems.db.dao.AnalysisReportDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.AnalysisReportDaoImpl;

import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: AnalysisReportService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-17 下午2:16:05
 *
 */

@Path("/analysisReportService")
@Singleton
public class AnalysisReportService {

	private AnalysisReportDao dao=null;
	public AnalysisReportService(){
		dao=new AnalysisReportDaoImpl();
	}
	
	@GET
	@Path("getanalysisreport")
	@Produces("application/xml")
	public List<AnalysisReport> getAnalysisReport(){
		List<AnalysisReport> list=null;
		try{
			list =dao.getAnalysisReports();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/xml")
	public AnalysisReport getAnalysisReportById(
		@PathParam("id") Integer id){
		AnalysisReport analysisReport=new AnalysisReport();
		try{
			analysisReport=dao.getAnalysisReposrtById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return analysisReport;
	}
	
	@POST
	@Path("/add")
	@Produces("application/json")
	public String addAnalysis(
		@QueryParam("content") String content,
		@Context HttpServletResponse servletResponse,
		@Context HttpServletRequest servletRequest) throws Exception{
	    servletRequest.setCharacterEncoding("utf-8");
	    String conn  = new String(content.getBytes(), "utf-8");
	    Staff user = (Staff) servletRequest.getSession().getAttribute("user");
	    Date date = new Date();
		AnalysisReport analysisReport=new AnalysisReport();
		try{
		    analysisReport.setContent(conn);
		    analysisReport.setStaffId(user.getStaffId());
		    analysisReport.setTime(date);
		    dao.addAnalysisReport(analysisReport);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "{\"result\":\"ok\"}";
	}
	
}
