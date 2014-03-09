/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

import cn.cdu.edu.TQC.bems.Utils;
import cn.cdu.edu.TQC.bems.db.bean.Staff;
import cn.cdu.edu.TQC.bems.db.dao.StaffDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.StaffDaoImpl;

/**
 * @ClassName: StaffService 说明： TODO(Tell the reader such role.)
 * @Author SUN 【email:1096490965@qq.com】
 * @Version V1.0 2012-7-15 下午3:59:08
 * 
 */

@Path("/staffservice")
@Singleton
public class StaffService {

	private StaffDao dao = null;

	public StaffService() {
		dao = new StaffDaoImpl();
	}

	@GET
	@Path("getStaffs")
	@Produces("application/json")
	public List<Staff> getStaffs() {
		List<Staff> staffs = null;
		try{
			staffs=dao.getStaffs();
		}catch(Exception e){
			e.printStackTrace();
		}
		return staffs;
	}
	
	@GET
	@Path("getstaffs")
	@Produces("application/xml")
	public List<Staff> getstaffs() {
		List<Staff> staffs = null;
		try{
			staffs=dao.getStaffs();
		}catch(Exception e){
			e.printStackTrace();
		}
		return staffs;
	}

	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public Staff getStaffById(@PathParam("id") String id) {
		Staff staff = new Staff();
		try {
			staff = dao.getStaffById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staff;
	}
	

	@GET
	@Path("del/{id}")
	@Produces("application/xml")
	public Response deleteById(@PathParam("id") String id) {
		String resStr;
		if (dao.deleteStaff(id)) {
			resStr = "删除成功";
		} else {
			resStr = "删除失败";
		}
		Response res;
		ResponseBuilder builder = Response.status(Status.CREATED);
		builder.type("application/xml");
		builder.entity("<entity><message>" + resStr + "</message></entity>");
		res = builder.build();
		return res;
	}
	// 批量删除
	@GET
	@Path("bulkdelete/{deleteStr}")
	@Produces("application/xml")
	public Response bulkDelete(@PathParam("deleteStr") String deleteStr) {
		String resStr;
		if (dao.bulkDelete(deleteStr)) {
			resStr = "删除成功";
		} else {
			resStr = "删除失败";
		}
		Response res;
		ResponseBuilder builder = Response.status(Status.CREATED);
		builder.type("application/xml");
		builder.entity("<entity><message>" + resStr + "</message></entity>");
		res = builder.build();
		return res;
	}

	@POST
	@Path("createstaff")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/xml")
	public Response createStaff(
			@FormParam("user_add_id")  String Staffid,
			@FormParam("user_add_name")  String name,
			@FormParam("user_add_pwd")  String password,
			@FormParam("user_add_email")  String email,
			@FormParam("user_add_role")  String role,
			@FormParam("user_add_phone") String phonenumber,
			@FormParam("user_add_pic")  String photo
			){
		String resStr=null;
		Staff staff=new Staff();
		staff.setStaffId(Staffid);
		staff.setName(name);
		staff.setPassword(password);
		staff.setEmail(email);
		staff.setRole(role);
		staff.setPhoneNumber(phonenumber);
		staff.setPhoto(photo);
		if (dao.addStaff(staff)) {
			resStr = "添加成功";
		} else {
			resStr = "添加失败";
		}
		Response res;
		ResponseBuilder builder = Response.status(Status.CREATED);
		builder.type("application/xml");
		builder.entity("<entity><message>" + resStr + "</message></entity>");
		res = builder.build();
		return res;
	}
	//添加人员
	 @POST
	 @Path("createstaffandpic")
	 @Consumes("multipart/form-data")
	 @Produces("application/xml")
	public void  createStaffAndPic(
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) throws Exception{
		 String fullfilepath=servletRequest.getSession().getServletContext().getRealPath("/")+"upload\\";
		 String fileName="";
		 Staff staff=new Staff();
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
					if(name.equalsIgnoreCase("user_add_id")){
						staff.setStaffId(value);
					}else if(name.equalsIgnoreCase("user_add_name")){
						 staff.setName(value);
					}else if(name.equalsIgnoreCase("user_add_pwd")){
						staff.setPassword(value);
					}else if(name.equalsIgnoreCase("user_add_email")){
						staff.setEmail(value);
					}else if(name.equalsIgnoreCase("user_add_role")){
						staff.setRole(value);
					}else if(name.equalsIgnoreCase("user_add_tel")){
						staff.setPhoneNumber(value);
					}
				}else{
					String fieldName = item.getFieldName();
				
					fieldName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					
					fileName = item.getName();
					int i2 = fileName.lastIndexOf(".");
					if(i2>-1) 
					    fileName =fieldName+ fileName.substring(i2);
					File dirs=new File(fullfilepath);
					File uploadedFile=new File(dirs,fileName);
					System.out.println("执行了添加文件"+fileName);
					
					item.write(uploadedFile);
				}
			}
			System.out.println(fileName);
			staff.setPhoto(fileName);
			boolean flag=dao.addStaff(staff);
			String resStr;
			if (flag) {
				resStr = "添加成功";
			} else {
				resStr = "添加失败";
			}
			Response res;
			ResponseBuilder builder = Response.status(Status.CREATED);
			builder.type("application/xml");
			builder.entity("<script>alert(\"" + resStr + "\");</script>");
			res = builder.build();
			//return res;
			servletResponse.sendRedirect("");
	 }
	 
	 //修改人员 有图片的删除方法
	 @POST
	 @Path("updatestaffandpic")
	 @Consumes("multipart/form-data")
	 @Produces("application/xml")
	 public Response  updateStaffAndPic(
				@Context HttpServletResponse servletResponse,
				@Context HttpServletRequest servletRequest) throws Exception{
		 System.out.println("修改");
			 String fullfilepath=servletRequest.getSession().getServletContext().getRealPath("/")+"upload\\";
			 String fileName="";
			 Staff staff=new Staff();
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
						if(name.equalsIgnoreCase("user_update_id")){
							staff.setStaffId(value);
						}else if(name.equalsIgnoreCase("user_update_name")){
							 staff.setName(value);
						}else if(name.equalsIgnoreCase("user_update_password")){
							staff.setPassword(value);
						}else if(name.equalsIgnoreCase("user_update_email")){
							staff.setEmail(value);
						}else if(name.equalsIgnoreCase("user_update_role")){
							staff.setRole(value);
						}else if(name.equalsIgnoreCase("user_update_tel")){
							staff.setPhoneNumber(value);
						}else if(name.equalsIgnoreCase("user_update_old_pic")){
							fileName=value;
						}
						
					}else{
						//删除图片文件
						Utils util=new Utils();
						util.deleteImage(fullfilepath+fileName);
						
						
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
			
				staff.setPhoto(fileName);
				boolean flag=dao.updateStaffAndPic(staff);
				String resStr;
				if (flag) {
					resStr = "修改成功";
				} else {
					resStr = "修改失败";
				}
				Response res;
				ResponseBuilder builder = Response.status(Status.CREATED);
				builder.type("application/xml");
				builder.entity("<script>alert(\"" + resStr + "\");</script>");
				res = builder.build();
				return res;
		 }
	 
	 //修改人员信息 无图片上传方法
	 
		@POST
		@Path("updatestaff")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces("application/xml")
		public Response updateStaff(
				@FormParam("user_update_id")  String Staffid,
				@FormParam("user_update_name")  String name,
				@FormParam("user_update_password")  String password,
				@FormParam("user_update_email")  String email,
				@FormParam("user_update_role")  String role,
				@FormParam("user_update_tel") String phonenumber
				){
			String resStr=null;
			Staff staff=new Staff();
			staff.setStaffId(Staffid);
			staff.setName(name);
			staff.setPassword(password);
			staff.setEmail(email);
			staff.setRole(role);
			staff.setPhoneNumber(phonenumber);
		
			if (dao.updateStaff(staff)) {
				resStr = "修改成功";
			} else {
				resStr = "修改失败";
			}
			Response res;
			ResponseBuilder builder = Response.status(Status.CREATED);
			builder.type("application/xml");
			builder.entity("<entity><message>" + resStr + "</message></entity>");
			res = builder.build();
			System.out.println("====================");
			return res;
		}
		
		@GET
		@Path("/currentUser")
		@Produces("application/json")
		public Staff getCurrentUserInform(
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest){
		    
		    Staff user = new Staff();
		    user = (Staff) servletRequest.getSession().getAttribute("user");
		  return user;   
		}
		
		
		@GET
		@Path("geturlbyrole/{role}")
		@Produces("application/xml")
		public String getUrlByRole(
				@PathParam("role")  int role 
				){
			
		
			return null;
			
		}
		
		@GET
		@Path("/logout")
		@Produces("application/xml")
		public String dropSession(
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest){
		    HttpSession session = servletRequest.getSession();
		    String values[] = session.getValueNames();
		    for(String value:values){
			System.out.println("---"+value);
			session.removeAttribute(value);
		    }
		    //清除session
		    session.invalidate();
		    return "<result>删除成功！</result>";
		}
}
