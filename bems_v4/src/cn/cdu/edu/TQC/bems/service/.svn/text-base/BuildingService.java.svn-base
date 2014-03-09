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

import cn.cdu.edu.TQC.bems.db.bean.Building;
import cn.cdu.edu.TQC.bems.db.dao.BuildingDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.BuildingDaoImpl;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: BuildingService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-9 上午10:22:40
 *
 */

@Path("/bulidingService")
@Singleton
public class BuildingService {
	
	private BuildingDao dao = null;
	public BuildingService(){
	dao=new BuildingDaoImpl();
	}
	
	@GET
	@Produces("application/xml")
	public List<Building> getBuildings(){
		List<Building> list=null;
		try{
			list=dao.getBuildings();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@GET
	@Path("/{bid}")
	@Produces("application/xml")
	public Building getBuildingById(@PathParam("bid") Integer bid){
		Building building=new Building();
		try{
			System.out.println("@--------"+bid);
			building=dao.getBuildingById(bid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return building;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/xml")
	public Response createBuilding(
			@FormParam("buildingid") Integer buildingid,
			@FormParam("usernum") Integer usernum,
			@FormParam("graphics") String graphics,
			@FormParam("floornum") Integer floornum,
			@FormParam("area") double area,
			@FormParam("undergroundnumber") Integer undergroundnumber,
			@FormParam("description") String description
			){
		Building building =new Building(
				buildingid,
				usernum,
				graphics,
				floornum,
				area,
				undergroundnumber,
				description
				);
		dao.addBuilding(building);
		Response res = null;
		res = Response.ok(building).type("application/xml").build();
		return res;
	}
	
	 @POST
	 @Path("/uploadImage")
	 @Consumes("multipart/form-data")
	 @Produces("application/xml")
	public Response uplaodImage(@Context HttpServletResponse servletResponse,
                		    @Context HttpServletRequest servletRequest)throws Exception{
	     System.out.println("Is Multipart");
	    
	     //String fullfilepath = servletRequest.getServletPath()+"\\upload\\";//servletRequest.getServletContext().getRealPath("/") +"\\WebContent\\upload"+System.getProperty("file.separator");
	     String fullfilepath=servletRequest.getSession().getServletContext().getRealPath("/")+"upload\\";  
	     System.out.println(fullfilepath+"=======");
	     String fileName = "";
	     String buildingid = "";
	     FileItemFactory factory = new DiskFileItemFactory();
	     ServletFileUpload upload = new ServletFileUpload(factory);
	     List items = null;
	     try {
	          items = upload.parseRequest(servletRequest);
	          System.out.println("items: "+items);
	     } catch (Throwable e) {
	          e.printStackTrace();
	     }
	     
	     
	     Iterator itr = items.iterator();        
	     while (itr.hasNext()) {
		    FileItem item = (FileItem) itr.next();
		   
		    if (item.isFormField()) {
			String name = item.getFieldName();
		            System.out.println("name: "+name);
		            String value = item.getString();
		            System.out.println("value: "+value);
		            
		            if (name.equalsIgnoreCase("buildingid")){
		                buildingid = value;
		            }
		            
		    } else {
		        //processUploadedFile
				String fieldName = item.getFieldName();
				fileName = item.getName();
				int i2 = fileName.lastIndexOf("\\");
				if(i2>-1) 
				    fileName = fileName.substring(i2+1);
				
				System.out.println("-------------"+fileName);
				File dirs = new File(fullfilepath);
				
				File uploadedFile = new File(dirs,fileName);
				item.write(uploadedFile);
				
		    }
		 }
	     
	     //向数据库中插入图片信息的数据
	     boolean flag = dao.updateImage(Integer.parseInt(buildingid),fileName);
	     String  xmlString = "<result>";
	     if(flag){
		 xmlString += "<entity>";
	                xmlString += "<bid>"+ buildingid + "</bid>";
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
