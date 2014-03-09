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

import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.MeterData;
import cn.cdu.edu.TQC.bems.db.dao.MeterDataDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.MeterDataDaoImpl;

import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: MeterDataService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-9 上午9:27:22
 *
 */

@Path("/meterDataService")
@Singleton
public class MeterDataService {
    private MeterDataDao dao = null;
    
    public MeterDataService(){
	dao = new MeterDataDaoImpl();
    }
    
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    @GET
    @Produces("application/xml")
    public List<MeterData> getMeterDatas(){
	List<MeterData> datas = new ArrayList<MeterData>();
	try {
	    datas = dao.getMeterDatas();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return datas;
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/xml")
    public List<MeterData> getData(@PathParam("id") Integer id){
	List<MeterData> meterData = null;
	try {
	    meterData = dao.getMeterDatasByMeterID(id);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return meterData;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/xml")
    public Response createAmmeterData(
	    @FormParam("METERID") String meterId,
	    @FormParam("METERDATAID") Integer meterDataId,
	    @FormParam("time") Date time,
	    @FormParam("data") Double data){
	MeterData meterData = new MeterData(meterDataId, meterId, data, time);
	dao.addMeterData(meterData);
	Response res = null;
	res = Response.ok(meterData).type("application/xml").build();
	return res;
    } 
    
}
