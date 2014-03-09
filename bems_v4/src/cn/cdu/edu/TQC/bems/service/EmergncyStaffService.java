/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import cn.cdu.edu.TQC.bems.db.bean.EmergncyStaff;
import cn.cdu.edu.TQC.bems.db.bean.Staff;
import cn.cdu.edu.TQC.bems.db.dao.EmergncyStaffDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.EmergncyGroupDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.EmergncyStaffDaoImpl;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: EmergncyStaffService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-17 下午1:03:58
 *
 */

@Path("/emergncyStaffService")
@Singleton
public class EmergncyStaffService {
	
	private EmergncyStaffDao dao=null;
	public EmergncyStaffService(){
		dao=new EmergncyStaffDaoImpl();
	}
	/**
	 * 添加单个小组成员
	 * Administrator
	 * @param staffId
	 * @param poistion
	 * @param emergncyGroupId
	 * @return
	 */
	@POST
	@Path("createemergncystaff")
	@Produces("application/xml")
	public Response createEmergncyStaff(
			@FormParam("staffId") String staffId,
			@FormParam("position") String poistion,
			@FormParam("emergncyGroupId") Integer emergncyGroupId
			){
		String xmlString="添加失败";
		EmergncyStaff emergncyStaff=new EmergncyStaff();
		try{
			
			if(dao.addEmergncyStaff(emergncyStaff)){
				xmlString="添加成功";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Response res;
		ResponseBuilder builder =Response.status(Status.CREATED);
		builder.type("application/xml");
		builder.entity("<message>"+xmlString+"</message>");
		res=builder.build();
		return res;
	}
	
	
	/**
	 * 批量添加应急小组成员
	 * Administrator
	 * @param staffs
	 * @return
	 */
	
	@POST
	@Path("createemergncystaffs")
	@Produces("application/xml")
	public Response createEmergncyStaffs(
			@FormParam("staffs") String staffs,
			@FormParam("emergncyid") Integer emergncyid
			){
		String[] staffid=staffs.split(",");
		String sql[]= new String[staffid.length+1];
		 sql[0]="delete from emergncy_staff where emergncygroupid="+emergncyid;
	    for(int i=0;i<staffid.length;i++){
	    	sql[i+1]="insert into emergncy_staff (staffid,position,emergncygroupid) values ('"+staffid[i]+"',2,"+emergncyid+")";
	    }
	    String xmlString="添加失败";
		
		try{
			if(dao.addEmergncyStaffs(sql))
			{
				xmlString="添加成功";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Response res;
		ResponseBuilder builder =Response.status(Status.CREATED);
		builder.type("application/xml");
		builder.entity("<message>"+xmlString+"</message>");
		res=builder.build();
		return res;
		
	}
	
	
	
	@GET
	@Path("del/{id}")
	@Produces("application/xml")
	public Response delEmergncyStaffById(
			@PathParam("id") Integer id){
		String xmlString="";
		try{
			if(dao.deleteEmergncyStaff(id)){
				xmlString="删除成功";
			}else{
				xmlString="删除失败";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Response res; 
        ResponseBuilder builder = Response.status(Status.CREATED);
        builder.type("application/xml");
        builder.entity("<message>"+xmlString+"</message>");
        res = builder.build();
        return res;
		
	}
	
	@GET
	@Path("delbystaffid/{id}")
	@Produces("application/xml")
	public Response delEmergncyStaffByStaffId(
			@PathParam("id") String id){
		String xmlString="";
		try{
			if(dao.deleteEmergncyStaffByStaffId(id)){
				xmlString="删除成功";
			}else{
				xmlString="删除失败";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Response res; 
        ResponseBuilder builder = Response.status(Status.CREATED);
        builder.type("application/xml");
        builder.entity("<message>"+xmlString+"</message>");
        res = builder.build();
        return res;
	}
	@GET
	@Path("/getstaffbyermergncygroupid/{id}")
	@Produces("application/json")
	public List<Staff> getStaffByEmergncyGroupId(@PathParam("id") Integer id){
		List<Staff> staffs=null;
		try{
			staffs= dao.getStaffByEmergncyGroupId(id);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return staffs;
		
	}
	
	
	
	

}
