/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import cn.cdu.edu.TQC.bems.DownLoadUtil;
import cn.cdu.edu.TQC.bems.db.bean.Template;
import cn.cdu.edu.TQC.bems.db.dao.TemplateDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.TemplateDaoImpl;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: StaffService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-5 下午8:39:55
 *
 */

@Path("/templates")
@Singleton
public class TemplateService {
    
    private TemplateDao dao = null;
    
    public TemplateService(){
	dao = new TemplateDaoImpl();
    }
    
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    @GET
    @Produces("application/xml")
    public List<Template> getStaffs(){
	List<Template> templates = null;
	try {
	    String sql = "select * from TEMPLATE"; 
	    templates = dao.getTemplates(sql); 
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return templates;
    }
    
    @GET
    @Path("/{uid}")
    @Produces("application/xml")
    public Template getTemplate(@PathParam("templateid") Integer tid){
	Template template = null;
	try {
	    String sql = "select * from TEMPLATE WHERE templateid = "+ tid;
	    template = dao.getTemplate(sql);
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return template;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/xml")
    public Response createTemplate(
	    @FormParam("a") Integer a,
	    @FormParam("b") String b,
	    @FormParam("c") Long c){
	String sql = "";
	dao.upDate(sql);
	Response res = null;
	res = Response.ok().type("application/xml").build();
	return res;
    }
    
    @DELETE
    @Path("/{id}")
    public String deleteTemplate(@PathParam("id") Integer tid){
	try {
	    String sql = "";
	    dao.upDate(sql);
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return "succes";
    }
    
    @GET
    @Path("/ammeter")
    @Produces("application/xml")
    public Response dowloadTest(
	    		@QueryParam("type") String type,
	    		@QueryParam("id") Integer id,
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) throws Exception{
	
	new DownLoadUtil().exportAmmeterDataList(servletResponse, "xls",type, id);
	
	Response res;   
        ResponseBuilder builder = Response.status(Status.CREATED);
        builder.type("application/xml");
        builder.entity("ok");
        res = builder.build();
        return res;
    }
    @GET
    @Path("/phone")
    @Produces("application/xml")
    public Response dowloadTest(
	    		@QueryParam("year") Integer year,
	    		@QueryParam("month") Integer month,
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) throws Exception{
	
	new DownLoadUtil().exportPhoneFeeList(servletResponse, "xls", year, month);
	Response res;   
        ResponseBuilder builder = Response.status(Status.CREATED);
        builder.type("application/xml");
        builder.entity("ok");
        res = builder.build();
        return res;
    }
    
    @GET
    @Path("/meter")
    @Produces("application/xml")
    public Response dowloadTest(
	    		@QueryParam("id") Integer id,
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) throws Exception{
	
	new DownLoadUtil().exportMeterDataList(servletResponse, "xls", id);
	Response res;   
        ResponseBuilder builder = Response.status(Status.CREATED);
        builder.type("application/xml");
        builder.entity("ok");
        res = builder.build();
        return res;
    }
    
}




