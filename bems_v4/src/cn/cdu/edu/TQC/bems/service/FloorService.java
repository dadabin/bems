/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.service;

import java.io.File;
import java.util.Iterator;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import cn.cdu.edu.TQC.bems.db.bean.Floor;
import cn.cdu.edu.TQC.bems.db.dao.FloorDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.FloorDaoImpl;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: FloorService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-9 上午11:14:09
 *
 */

@Path("/floorService")
@Singleton
public class FloorService {
	private FloorDao dao=null;
	
	public FloorService(){
		dao=new FloorDaoImpl();
	}
	
	@GET
	@Produces("application/xml")
	public List<Floor> getFloors(){
		List<Floor> list=null;
		try{
			list=dao.getFloors();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	@GET
	@Path("/{fid}")
	@Produces("application/xml")
	public Floor getFloorById(@PathParam("fid") Integer fid){
		Floor floor = null;
		try{
			floor=dao.getFloorById(fid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return floor;
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/xml")
	public Response createFloor(
			@FormParam("usernum") Integer usernum,
			@FormParam("graphics") String graphics,
			@FormParam("floornum") Integer floornum,
			@FormParam("intro") String intro,
			@FormParam("buildingid") Integer buildingid
			){
		Floor floor=new Floor(
				usernum,
				graphics,
				floornum,
				intro,
				buildingid
				);
		dao.addFloor(floor);
		Response res = null;
		res = Response.ok(floor).type("application/xml").build();
		return res;
	}
	
	
	 @POST
	 @Path("/uploadImage")
	 @Consumes("multipart/form-data")
	 @Produces("application/xml")
	public Response uploadImage(
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) throws Exception{

		String fullfilepath=servletRequest.getSession().getServletContext().getRealPath("/")+"upload\\";
		String fileName="";
		String floorId="";
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		List items=null;
		try{
			items=upload.parseRequest(servletRequest);
			
		}catch(Throwable e){
			e.printStackTrace();
		}
		
		Iterator itr=items.iterator();
		while(itr.hasNext()){
			FileItem item = (FileItem) itr.next();
			if(item.isFormField()){
				String name=item.getFieldName();
				String value=item.getString();
				if(name.equalsIgnoreCase("floorid")){
					floorId=value;
				}
			}else{
				String fieldName = item.getFieldName();
				fileName = item.getName();
				int i2 = fileName.lastIndexOf("\\");
				if(i2>-1) 
				    fileName = fileName.substring(i2+1);
				File dirs=new File(fullfilepath);
				File uploadedFile=new File(dirs,fileName);
				item.write(uploadedFile);
				
				
			}
		}
		//数据库中插入
		boolean flag = dao.updateImage(Integer.parseInt(floorId),fileName);
	     String  xmlString = "<result>";
	     if(flag){
		 xmlString += "<entity>";
	                xmlString += "<bid>"+ floorId + "</bid>";
	                xmlString += "<filename>"+ "upload/"+ fileName+"</filename>";
	                xmlString += "</entity>";
	                xmlString += "</result>"; 
	     }else {
		xmlString +="更新图片失败</result>";
	    }
	     	Response res;   
	        ResponseBuilder builder = Response.status(Status.CREATED);
	        builder.type("application/xml");
	        builder.entity(xmlString);
	        res = builder.build();
	        return res;
	}
	
	 @POST
	 @Path("/addFloor")
	 @Consumes("multipart/form-data")
	 @Produces("application/xml")
	 public Response addFloor(@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) throws Exception{
	     Floor floor = new Floor();
	     String fullfilepath=servletRequest.getSession().getServletContext().getRealPath("/")+"upload\\";
		String fileName="";
		String floorId="";
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		List items=null;
		try{
			items=upload.parseRequest(servletRequest);
			
		}catch(Throwable e){
			e.printStackTrace();
		}
		
		Iterator itr=items.iterator();
		while(itr.hasNext()){
			FileItem item = (FileItem) itr.next();
			if(item.isFormField()){
				String name=item.getFieldName();
				String value=item.getString();
				
				if(name.equalsIgnoreCase("floorNo")){
					floorId=value;
					floor.setFloorNum(Integer.parseInt(value));
				}else if(name.equalsIgnoreCase("buildingid")){
				    floor.setBuildingId(Integer.parseInt(value));
				}else if(name.equalsIgnoreCase("intro")){
				    floor.setIntro(value);
				}else if(name.equalsIgnoreCase("usersnum")){
				    floor.setUserNum(Integer.parseInt(value));
				}
				
			}else{
				String fieldName = item.getFieldName();
				fileName = item.getName();
				int i2 = fileName.lastIndexOf("\\");
				if(i2>-1) 
				    fileName = fileName.substring(i2+1);
				File dirs=new File(fullfilepath);
				File uploadedFile=new File(dirs,fileName);
				item.write(uploadedFile);
				
				floor.setGraphics(fileName);
			}
		}
		//数据库中插入
		System.out.println("-----"+floor.getBuildingId() + "=="+floor.getFloorNum() + "**"+floor.getGraphics());
		boolean flag = dao.addFloor(floor);
		
	     String  xmlString = "<result>";
	     if(flag){
		 xmlString += "<entity>";
	                xmlString += "<bid>"+ floorId + "</bid>";
	                xmlString += "<filename>"+ "upload/"+ fileName+"</filename>";
	                xmlString += "</entity>";
	                xmlString += "</result>"; 
	     }else {
		xmlString +="更新图片失败</result>";
	    }
	     	Response res;   
	        ResponseBuilder builder = Response.status(Status.CREATED);
	        builder.type("application/xml");
	        builder.entity(xmlString);
	        res = builder.build();
	        return res;
	 }
	

}
