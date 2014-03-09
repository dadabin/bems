/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.service;

import java.util.ArrayList;
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
import javax.ws.rs.core.Response.ResponseBuilder;

import sun.net.www.MeteredStream;

import cn.cdu.edu.TQC.bems.db.bean.Meter;
import cn.cdu.edu.TQC.bems.db.dao.MeterDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.MeterDaoImpl;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: MeterService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-8 下午10:11:55
 *
 */

@Path("/meterService")
@Singleton
public class MeterService {
    private MeterDao dao = null;
    
    public MeterService(){
	dao = new MeterDaoImpl();
    }
    
    @GET
    @Path("getMeters")
    @Produces("application/xml")
    public List<Meter> getMeters(){
	List<Meter> list = new ArrayList<Meter>();
	try {
	    list = dao.getMeters();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return list;
    }
    
    @GET
    @Path("/{mid}")
    @Produces("application/xml")
    public Meter getMeter(@PathParam("mid") Integer mid){
	Meter meter = null;
	try {
	    System.out.println("@---------"+mid);
	    meter = dao.getMeterById(mid);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return meter;
    }
    
    @POST
    @Path("createMeter")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/xml")
    public Response createMeter(
	    @FormParam("meterid") String meterid,
	    @FormParam("type") String type,
	    @FormParam("location") String location,
	    @FormParam("floorid") Integer floorid){
	Meter meter = new Meter(meterid, type, floorid, location);
	if(!dao.isRepeat(meter)){
		
	dao.addMeter(meter);
	Response res = null;
	res = Response.ok(meter).type("application/xml").build();
	return res;
	
	}else{
		
		String xmlStr="<entity><message>表编号已经存在</message></entity>";
		Response res=null;
		 ResponseBuilder builder = Response.status(Status.CREATED);
		 builder.type("application/xml");
		 builder.entity(xmlStr);
		 res=builder.build();
		return res;
		
	 }
    }
    
}
