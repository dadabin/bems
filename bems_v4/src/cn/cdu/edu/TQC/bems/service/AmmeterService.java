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

import cn.cdu.edu.TQC.bems.db.bean.Ammeter;
import cn.cdu.edu.TQC.bems.db.dao.AmmeterDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterDaoImpl;

import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: AmmeterService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-9 上午9:27:59
 *
 */

@Path("/ammeterService")
@Singleton
public class AmmeterService {
	private AmmeterDao dao=null;
	public AmmeterService(){
		dao=new AmmeterDaoImpl();
	}
	
	@GET
	@Produces("application/xml")
	public List<Ammeter> getAmmeters(){
		List<Ammeter> list=null;
		try{
			list=dao.getAmmeters();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	
	@GET
	@Path("/{aid}")
	@Produces("application/xml")
	public Ammeter getAmmeterById(@PathParam("aid") Integer aid){
		Ammeter ammeter=null;
		try{
			ammeter=dao.getAmmeterById(aid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ammeter;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/xml")
	public Response createAmmeter(
			@FormParam("ammeterId") String ammeterId,
			@FormParam("type") String type,
			@FormParam("circuittype") String circuittype,
			@FormParam("floorid") Integer floorid,
			@FormParam("location") String location
			){
		Ammeter ammeter=new Ammeter(
				ammeterId,
				type,
				circuittype,
				floorid,
				location
				);
		
		dao.addAmmeter(ammeter);
		Response res = null;
		res = Response.ok(ammeter).type("application/xml").build();
		return res;
		
	}
	
	
	

}
