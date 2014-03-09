/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.service;

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

import cn.cdu.edu.TQC.bems.Utils;
import cn.cdu.edu.TQC.bems.db.bean.AmmeterData;
import cn.cdu.edu.TQC.bems.db.dao.AmmeterDataDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterDataDaoImpl;

import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: AmmeterDataService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-8 下午9:27:22
 *
 */

@Path("/ammeterDataService")
@Singleton
public class AmmeterDataService {
    private AmmeterDataDao dao = null;
    
    public AmmeterDataService(){
	dao = new AmmeterDataDaoImpl();
    }
    
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    @GET
    @Produces("application/xml")
    public List<AmmeterData> getAmmeterDatas(){
	List<AmmeterData> ammeterDatas = null;
	try {
	    ammeterDatas = dao.getAmmeterDatas();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return ammeterDatas;
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/xml")
    public List<AmmeterData> getAmmeterData(@PathParam("id") Integer id){
	List<AmmeterData> data = null;
	try {
	    data = dao.getAmmeterDatasByAmmeterID(id);
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return data;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/xml")
    public Response creatAmmeterData(
	    @FormParam("ammeterid") String ammeterid,
	    @FormParam("time") Date time,
	    @FormParam("data") Double data){
	time = new Date();
	AmmeterData ammeterData = new AmmeterData(data, time, ammeterid);
	dao.addAmmeterData(ammeterData);
	Response res = null;
	res = Response.ok(ammeterData).type("application/xml").build();
	return res;
    }
    
}
