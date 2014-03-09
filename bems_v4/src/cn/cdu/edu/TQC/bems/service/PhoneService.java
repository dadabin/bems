/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cn.cdu.edu.TQC.bems.db.bean.Phone;
import cn.cdu.edu.TQC.bems.db.dao.PhoneDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.PhoneDaoImpl;

import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: PhoneService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-9 上午9:28:36
 *
 */

@Path("/phoneService")
@Singleton
public class PhoneService {
	private PhoneDao dao=null;
	public PhoneService(){
		dao=new PhoneDaoImpl();
	}
	
	
	@GET
	@Produces("application/xml")
	public List<Phone> getPhones(){
		List<Phone> phones=null;
		try{
			phones=dao.getPhones();
		}catch(Exception e){
			e.printStackTrace();
		}
		return phones;
		
	}
	
	@GET
	@Path("/{pid}")
	@Produces("application/xml")
	public Phone getPhoneById(@PathParam("pid") Integer pid){
		Phone phone=null;
		try{
			phone=dao.getPhoneById(pid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return phone;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/xml")
	public Response createPhone(
			
			@FormParam("phonenumber") String phonenumber,
			@FormParam("floorid") Integer floorid,
			@FormParam("housenumber") String housenumber,
			@FormParam("phonepower") String phonepower,
			@FormParam("personsresponesiblefor") String personsresponesiblefor
			){
		Phone phone=new Phone(
				0,
				phonenumber,
				floorid,
				housenumber,
				phonepower,
				personsresponesiblefor
				);
		Response res = null;
		res = Response.ok(phone).type("application/xml").build();
		return res;
	}
	
	
	
	

}
