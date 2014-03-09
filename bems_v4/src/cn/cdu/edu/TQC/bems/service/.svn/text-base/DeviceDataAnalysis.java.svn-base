/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import cn.cdu.edu.TQC.bems.Analasis;
import cn.cdu.edu.TQC.bems.Utils;
import cn.cdu.edu.TQC.bems.db.bean.AmmeterData;
import cn.cdu.edu.TQC.bems.db.bean.Device;
import cn.cdu.edu.TQC.bems.db.bean.MeterData;
import cn.cdu.edu.TQC.bems.db.bean.PhoneFeeData;
import cn.cdu.edu.TQC.bems.db.dao.MeterDao;
import cn.cdu.edu.TQC.bems.db.dao.MeterDataDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterDataDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.MeterDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.MeterDataDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.PhoneFeeDataDaoImpl;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: MeterAnalysis 说明： TODO(Tell the reader such role.)
 * @Author SUN 【email:1096490965@qq.com】
 * @Version V1.0 2012-7-11 下午3:15:55
 * 
 */

@Path("/deviceDataAnalysis")
@Singleton
public class DeviceDataAnalysis {
    private MeterDataDao dataDao = null;
    private MeterDao dao = null;

    public DeviceDataAnalysis() {
	dao = new MeterDaoImpl();
	dataDao = new MeterDataDaoImpl();
    }

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    /**
     * 给出起始时间，仪表号,仪表类型
     * 查询周用量
     * 
     * 例如：
     * http://localhost:8888/bems_v2/rest/meterAnalysis/query?id=20000001&id=20000002&begin=20100101000000&end=20100301000000
     * @param ids--------
     * @param begint
     * @param endt
     * @return
     */
    @GET
    @Path("/query")
    @Produces("application/json")
    public List<Device> getDeviceDatas(
	    @QueryParam("deviceTYpe") String deviceTYpe,
	    @QueryParam("id") List<String> ids,
	    @QueryParam("begin") String begint,
	    @QueryParam("end") String endt){
	Date begin = new Utils().stringToDate(begint);
	Date end = new Utils().stringToDate(endt);
	String[] des = new String[ids.size()];
	for(int i=0;i<ids.size();i++){
	    des[i] = ids.get(i);
	}
	List<Device> devices = new ArrayList<Device>();
	if(begin != end){
	    devices = new Analasis().getDevicesData(deviceTYpe, des, begin, end);
	}
	return devices;
    }

    /**
     * 根据仪表类型，仪表编号，年份获取月度能耗值
     * @param deviceType
     * @param deviceID
     * @param year
     * @return
     */
    @GET
    @Path("/querymonth")
    @Produces("application/json")
    public String getDeviceDataByMonth(
	    @QueryParam("deviceTYpe") String deviceType, 
	    @QueryParam("id") String deviceID,
	    @QueryParam("year") int year){
	List<Double> monthData = new Analasis().getDeviceByMonth(deviceType, deviceID, year);
	String strjson = "{\"month\":[";
	for(int k=0;k<monthData.size();k++){
	    if(k!=monthData.size()-1){
		strjson += monthData.get(k) + ",";
	    }else {
		strjson += monthData.get(k);
	    }
	}
	strjson += "]}";
	
	return strjson;
    }
    
    /**
     * 根据仪表类型，仪表编号，年份获取季度能耗值
     * @param deviceType
     * @param deviceID
     * @param year
     * @return
     */
    @GET
    @Path("/queryyear")
    @Produces("application/json")
    public String getDeviceDataByYear(
	    @QueryParam("deviceTYpe") String deviceType, 
	    @QueryParam("id") String deviceID,
	    @QueryParam("year") int year){
	String stryear = "{\"serize\" :[";
	int date = Integer.parseInt(new Utils().getStringDate(new Date()).get("year"));
	for(int i=year;i<=date;i++){
	    	List<Double> seasonData = new Analasis().getDeviceBySeason(deviceType, deviceID, i);
		String strjson = "{\"name\":  \"" +i+
				"年\", \"data\" :[";
		for(int k=0;k<seasonData.size();k++){
		    if(k!=seasonData.size()-1){
			strjson += seasonData.get(k) + ",";
		    }else {
			strjson += seasonData.get(k);
		    }
		}
		strjson += "]}";
	    	if(i!=date){
	    	    strjson += ",";
	    	}
		stryear += strjson;
	}
	return stryear+"]}";
    }
    
    @GET
    @Path("/queryall")
    @Produces("application/json")
    public String getDeviceDataByDeviceID(
	    @QueryParam("deviceTYpe") String deviceTYpe,
	    @QueryParam("did") Integer did){
	
	String all = "{\"data\":[";
	
	if(deviceTYpe.equals("meter")){
	    List<MeterData> datas = new MeterDataDaoImpl().getMeterDatasByMeterID(did);
	    all += datas.get(0).getData() +",";
	    for(int k=1;k<datas.size();k++){
		if(k != datas.size()-1){
			all += datas.get(k).getData() - datas.get(k-1).getData() + ",";
		    }else {
			all += datas.get(k).getData() - datas.get(k-1).getData();
		    }
	    }
	}
	if(deviceTYpe.equals("ammeter")){
	    List<AmmeterData> datas = new AmmeterDataDaoImpl().getAmmeterDatasByAmmeterID(did);
	    all += datas.get(0).getData() +",";
	    for(int k=1;k<datas.size();k++){
		if(k != datas.size()-1){
			all += datas.get(k).getData() - datas.get(k-1).getData() + ",";
		    }else {
			all += datas.get(k).getData() - datas.get(k-1).getData();
		    }
		}
	}
	if(deviceTYpe.equals("phone")){
	    List<PhoneFeeData> datas = new PhoneFeeDataDaoImpl().getPhoneFeeDatasByPid(did);
	    all += datas.get(0).getFee() +",";
	    for(int k=1;k<datas.size();k++){
		if(k != datas.size()-1){
			all += datas.get(k).getFee() - datas.get(k-1).getFee() + ",";
		    }else {
			all += datas.get(k).getFee() - datas.get(k-1).getFee();
		    }
		}
	}
	return all+"]}";
    }
    
    @GET
    @Path("/querybuilding")
    @Produces("application/json")
    public String getBuildsAtOneTimes(
	    @QueryParam("deviceTYpe") String deviceType,
	    @QueryParam("year") int year,
	    @QueryParam("month") int month){
	String all= "{\"data\":[";
	List<Double> list = new Analasis().getDevicesAtOneTime(deviceType, year, month);
	for(int i=0;i<list.size();i++){
	    if(i != list.size()-1){
		all += list.get(i)+",";
	    }else {
		all += list.get(i);
	    }
	}
	return all+"]}";
    }
    
    
    
    
    
    
    
    /** 
     * 此方法的请求的路径为： 
     *    http://localhost:8080/AdvanceJersey/resources/multiplePathService/2/4?rep=0 
     * @param arg1 
     * @param arg2 
     * @return 
     */  
    @Produces(MediaType.TEXT_PLAIN)  
    @GET  
    @Path("/{arg1}/{arg2}")
    public String getStringRep(
	    @PathParam("arg1")String arg1,   
        @PathParam("arg2")String arg2) {
	System.out.println("???");
        return "representation: StringRepresentation: arg1: "  
                        +arg1+" arg2: "+arg2+"\n\n";  
    }
    
    @GET  
    @Produces(MediaType.WILDCARD)  
    public Response get(@QueryParam("format") String format) {  
        //调用相应的相应类型  
	System.out.println("----"+format);
	
        return Response.ok("Hello World", format).build();  
    }  
}
