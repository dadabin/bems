/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import cn.cdu.edu.TQC.bems.db.bean.MeterData;
import cn.cdu.edu.TQC.bems.db.bean.PhoneFeeData;
import cn.cdu.edu.TQC.bems.db.dao.MeterDataDao;
import cn.cdu.edu.TQC.bems.db.dao.PhoneFeeDataDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.MeterDataDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.PhoneFeeDataDaoImpl;

import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: PhoneDataService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-9 上午9:28:59
 *
 */

@Path("/phoneDataService")
@Singleton
public class PhoneDataService {
    private PhoneFeeDataDao dao = null;
    public PhoneDataService(){
	this.dao = new PhoneFeeDataDaoImpl();
    }
    
    
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    @GET
    @Produces("application/xml")
    public List<PhoneFeeData> getFeeDatas(){
	List<PhoneFeeData> feeDatas = null;
	try {
	    feeDatas = dao.getPhoneFeeDatas();
	    System.out.println("---------->?"+feeDatas.size());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return feeDatas;
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/xml")
    public List<PhoneFeeData> getPhoneFeeData(@PathParam("id") Integer id){
	List<PhoneFeeData> data = null;
	data = dao.getPhoneFeeDatasByPid(id);
	return data;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/xml")
    public Response createPhoneFeeData(
	    @FormParam("PHONEFEEDATAID") Integer PHONEFEEDATAID,
	    @FormParam("PHONEID") Integer PHONEID,
	    @FormParam("time") Date time,
	    @FormParam("FEE") Double FEE,
	    @FormParam("STAFFID") String STAFFID){
	PhoneFeeData d = new PhoneFeeData(PHONEFEEDATAID, PHONEID, time, FEE, STAFFID);
	dao.addPhoneFeeData(d);
	Response res = null;
	res = Response.ok(d).type("application/xml").build();
	return res;
    } 
    
}
