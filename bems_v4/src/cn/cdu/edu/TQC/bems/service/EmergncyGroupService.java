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

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

import cn.cdu.edu.TQC.bems.db.bean.EmergncyGroup;
import cn.cdu.edu.TQC.bems.db.dao.EmergncyGroupDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.EmergncyGroupDaoImpl;

/**
 * @ClassName: EmergbcyGroupService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-17 上午10:59:47
 *
 */

@Path("/emergncyGroupService")
@Singleton
public class EmergncyGroupService {
	private EmergncyGroupDao dao=null;
	public EmergncyGroupService(){
		dao=new EmergncyGroupDaoImpl();
	}
	
	@GET
	@Path("getemergncygroup")
	@Produces("application/xml")
	public List<EmergncyGroup> getEmergncyGroup(){
		
		List<EmergncyGroup> list=null;
		try{
			list=dao.getEmergncyGroups();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	@POST
	@Path("createemergncygroup")
	@Produces("application/xml")
	public Response createEmergncyGroup(
			@FormParam("emergency_name") String groupName,
			@FormParam("emergency_description") String description){
		EmergncyGroup emergncyGroup=new EmergncyGroup();
		emergncyGroup.setGroupName(groupName.trim());
		emergncyGroup.setDescription(description.trim());
		String xmlString="";
		if(dao.addEmergncyGroup(emergncyGroup)){
			xmlString="添加成功";
		}else{
			xmlString="添加失败";
		}
		Response res; 
        ResponseBuilder builder = Response.status(Status.CREATED);
        builder.type("application/xml");
        builder.entity("<message>"+xmlString+"</message>");
        res = builder.build();
        return res;
	}
	/**
	 * 查看应急小组成员
	 * Administrator
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public EmergncyGroup getEmergncyGroupById(@PathParam("id") Integer id){
		EmergncyGroup emergncyGroup=new EmergncyGroup();
		try{
			emergncyGroup=dao.getEmergncyGroupById(id);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return emergncyGroup;
	}
	
	
	@GET
	@Path("del/{id}")
	@Produces("application/xml")
	public Response delEmergncyGroupById(
			@PathParam("id") Integer id){
		String xmlString="";
		try{
			if(dao.deleteEmergncyGroupByid(id)){
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
	
}
