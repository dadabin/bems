/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.resource.Singleton;

import cn.cdu.edu.TQC.bems.AmmeterTypeFloorAndId;
import cn.cdu.edu.TQC.bems.Analasis;
import cn.cdu.edu.TQC.bems.DownLoadUtil;
import cn.cdu.edu.TQC.bems.createExcel;
import cn.cdu.edu.TQC.bems.db.bean.AmmeterData;
import cn.cdu.edu.TQC.bems.db.bean.AmmeterDevice;
import cn.cdu.edu.TQC.bems.db.bean.Device;
import cn.cdu.edu.TQC.bems.db.dao.AmmeterDao;
import cn.cdu.edu.TQC.bems.db.dao.AmmeterDataDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterAnalysisDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterDataDaoImpl;

/**
 * 
 * @ClassName: AmmeterAnalysisService 说明：电表分析 TODO(Tell the reader such role.)
 * @Author SUN 【email:1096490965@qq.com】
 * @Version V1.0 2012-7-11 下午3:18:48
 * 
 */
@Path("/ammeterAnalysisService")
@Singleton
public class AmmeterAnalysisService {
	private AmmeterDao ammeterDao = null;
	private AmmeterDataDao ammeterDataDao = null;
	AmmeterAnalysisDaoImpl ammeterAnalysis = null;
	
	public AmmeterAnalysisService() {
		ammeterDao = new AmmeterDaoImpl();
		ammeterDataDao = new AmmeterDataDaoImpl();
		ammeterAnalysis = new AmmeterAnalysisDaoImpl();
	}
	
	@POST
	@Path("period")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response period(@FormParam("ammetertype") String ammeterType,
			@FormParam("starttime") String startTime,
			@FormParam("endtime") String endTime,
			@FormParam("interval") String interval) {

		// System.out.println(startTime+endTime+interval+ammeterType);
		String xmlString = "{\"ammeter\":"
				+ ammeterDataDao.comparisonofAClassOfFloors("", startTime,
						endTime, interval, ammeterType) + "}";

		Response res;
		ResponseBuilder builder = Response.status(Status.CREATED);

		builder.type("application/json");
		builder.entity(xmlString);
		res = builder.build();
		System.out.println(xmlString);
		return res;
	}

	// 1.楼层间相同电路比较
	@POST
	@Path("analysisfloorandtype")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> analysisFloorAndType(@FormParam("type") String type,
			@FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime, @FormParam("furl") String furl) {

		char[] floorc = furl.toCharArray();
		String[] c = new String[floorc.length];
		for (int i = 0; i < c.length; i++) {
			c[i] = floorc[i] + "";
			if (c[i] == "0") {
				c[i] = "-1";
			}
		}

		List<Device> devices = ammeterAnalysis.analysisFloorAndType(startTime,
				endTime, type, c);
		return devices;

	}

	// 2.不同楼层的用电趋势图
	@POST
	@Path("analysisfloorall")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> analysisFloorAll(@FormParam("furl") String floors,
			@FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime

	) {

		char[] floorc = floors.toCharArray();
		String[] c = new String[floorc.length];
		for (int i = 0; i < c.length; i++) {
			c[i] = floorc[i] + "";
			if (c[i] == "0") {
				c[i] = "-1";
			}
		}
		List<Device> devices = ammeterAnalysis.analysisFloorAll(startTime,
				endTime, c);
		return devices;
	}

	// 3.整栋楼不同电路比较
	@POST
	@Path("analysisbuildingtype")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> analysisBuildingType(
			@FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime) {
		List<Device> devices = ammeterAnalysis.analysisBuildingType(startTime
				+ " 00:00:00", endTime + " 00:00:00", new String[] { "空调电路",
				"设备电路", "照明电路" });
		return devices;
	}

	// 4.整栋楼不同电路的用电量
	@POST
	@Path("analysistype")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> analysisType(@FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime,
			@FormParam("floorNumber") String floorNumber

	) {
		List<Device> devices = ammeterAnalysis.analysisType(startTime
				+ " 00:00:00", endTime + " 00:00:00", floorNumber,
				new String[] { "空调电路", "设备电路", "照明电路" });
		return devices;
	}
	//////////////////////======================导出Excel
	
	//1.楼层间相同电路比较 
	public Response analysisFloorAndTypeToExcel(){
		
		
		
		return Response.ok().build();
	}
	
	
	
	
	// 2.不同楼层的用电趋势图导出Excel
	/**
	 * 
	 * @param servletResponse
	 * @param servletRequest
	 * @return
	 * @throws IOException 
	 */
	@GET
	@Path("/analysisflooralltoexcel")
	@Produces("application/json")
	public Response analysisFloorAllToExcel(
			@Context HttpServletResponse servletResponse,
		    @Context HttpServletRequest servletRequest) throws IOException{
		 String fullfilepath=servletRequest.getSession().getServletContext().getRealPath("/")+"upload\\";
		 createExcel ce=new createExcel();
		String excelName=ce.createBarChartToExcel(fullfilepath,  "2012.xls").get("excelName");
		String fileType="xls";
		if ("pdf".equals(fileType)) {
			servletResponse.setContentType("application/pdf;charset=utf-8");
		} else if ("xls".equals(fileType)) {
			servletResponse.setContentType("application/msexcel;charset=utf-8");
		} else if ("doc".equals(fileType)) {
			servletResponse.setContentType("application/msword;charset=utf-8");
		}
		//文件名
		servletResponse.setHeader("Content-Disposition", "attachment;filename=\""
				+ new String(excelName.getBytes(), "ISO8859-1") + "\"");
		File file=new File(fullfilepath+excelName);
		servletResponse.setContentLength((int)file.length());
		byte[] buffer=new byte[4096];//缓冲区
		BufferedOutputStream output=null;
		BufferedInputStream input =null;
		try{
			 output = new BufferedOutputStream(servletResponse.getOutputStream());
			    input = new BufferedInputStream(new FileInputStream(file));
			    int n = -1;
			    // 遍历，开始下载
			    while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			    }
			    output.write(buffer,0,n);
			    servletResponse.flushBuffer();
			
		}catch(Exception e){
			
		}finally{
			//关闭流
			if(input!=null)
				input.close();
			if (output != null)
				output.close();
			
		}
		return Response.ok().build();
		
	}

	//// 3.整栋楼不同电路比较导出Excel
	/**
	 * 下载Excel
	 * @return
	 * @throws Exception 
	 */
	@GET
	@Path("/analysisBuildingTypeToExcel")
	@Produces("application/json")
	public Response analysisBuildingTypeToExcel(
			@Context HttpServletResponse servletResponse,
		    @Context HttpServletRequest servletRequest
			) throws Exception{
	     String fullfilepath=servletRequest.getSession().getServletContext().getRealPath("/")+"upload\\"; 
	     createExcel ce=new createExcel();
	     ce.gettoExcel(fullfilepath,servletResponse);
		return Response.ok().build();
	}
	//4.整栋楼不同电路比较导出 成 Excel
	/**
	 * 
	 * @return
	 */
	public Response  analysisType(){
		return Response.ok().build();
	}
	//=====================================比较修改===================================================//
	
	/**
	 * =====================================各楼层间用电总量对比分析
	 * Administrator
	 * @param floorids  一个楼层编号字符串
	 * @param begintime
	 * @param endtime
	 * @param timetype(1,2,3,4)分别表示：1 按周查询 2 按月查询 3 按季度查询 4 按年查询
	 * @return
	 */
	
	@POST
	@Path("getByFloorid")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<AmmeterDevice> getByFloorid(
			@FormParam("floorids") String floorids,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype){
		List<AmmeterDevice> ammeterdevices=new ArrayList<AmmeterDevice>();
		String[] floors=floorids.split(",");
		
		AmmeterTypeFloorAndId ammetertypefloorandid=new AmmeterTypeFloorAndId();
		
		Analasis analasis=new Analasis();
		
		switch(timetype){
		
		case 1://按周计算
			
			
			break;
		case 2://按月计算
			for(String floor:floors){
				AmmeterDevice e=new AmmeterDevice();
				List<String> ammeters=ammetertypefloorandid.getAmmeterNumberByFloorId(floor);//得到一个楼层中的所有的电表的标号
				Double[] data=new Double[12];
				for(int i=0;i<ammeters.size();i++ ){
					List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
					for( int j=0;j< list.size();j++){
						data[j]+=list.get(j);
					}
				 }
				e.setData(data);
				e.setName("");
				e.setDatetime(new String[]{"一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"});
				ammeterdevices.add(e);
				}
			break;
		case 3://按季度计算
			for(String floor:floors){
				AmmeterDevice e=new AmmeterDevice();
				List<String> ammeters=ammetertypefloorandid.getAmmeterNumberByFloorId(floor);//得到一个楼层中的所有的电表的标号
				Double[] data=new Double[4];
				for(int i=0;i<ammeters.size();i++ ){
					List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
					for( int j=0;j< list.size();j++){
						if(j<4){
							data[0]+=list.get(j);
						}else if(4<=j&&j<7){
							data[1]+=list.get(j);
						}else if(7<=j&&j<10){
							data[2]+=list.get(j);
						}else {
							data[3]+=list.get(j);
						}
					}
				 }
				e.setData(data);
				e.setName("");
				e.setDatetime(new String[]{"第一季度","第二季度","第三季度","第四季度"});
				ammeterdevices.add(e);
				}
			break;
		case 4://按年份计算
			for(String floor:floors){
				AmmeterDevice e=new AmmeterDevice();
				List<String> ammeters=ammetertypefloorandid.getAmmeterNumberByFloorId(floor);//得到一个楼层中的所有的电表的标号
				Double[] data=new Double[12];
				for(int i=0;i<ammeters.size();i++ ){
					List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
					for( int j=0;j< list.size();j++){
						data[j]+=list.get(j);
					}
				 }
				e.setData(data);
				e.setName("");
				e.setDatetime(new String[]{"一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"});
				ammeterdevices.add(e);
				}
			

			break;
		default:
			
				break;
		
		}
		return ammeterdevices;
	}
	
	
	/**
	 * =========================================根据楼层不同电路对比使用
	 * Administrator
	 * @param floorid 一个楼层编号
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("byTypeAndFloorId")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<AmmeterDevice> byTypeAndFloorId(
			@FormParam("floorid") String floorid,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
   List<AmmeterDevice> ammeterdevices=new ArrayList<AmmeterDevice>();
    AmmeterTypeFloorAndId ammetertypefloorandid=new AmmeterTypeFloorAndId();
    Analasis analasis=new Analasis();
		switch(timetype){
		case 1://按周查询
			
			
			
			break;
		case 2://按月份查询
			AmmeterDevice e=new AmmeterDevice();
			List<HashMap<String,String>> ammeters=ammetertypefloorandid.getTypeAndAmmeterNumberByFloorId(floorid);//得到一个楼层中的所有的电表的标号
			Double[] data=new Double[12];
			for(int i=0;i<ammeters.size();i++ ){
				List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
				for( int j=0;j< list.size();j++){
					data[j]+=list.get(j);
				}
			 }
			e.setData(data);
			e.setName("");
			e.setDatetime(new String[]{"一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"});
			ammeterdevices.add(e);
			break;
		case 3://按季度查询
			AmmeterDevice e3=new AmmeterDevice();
			List<String> ammeters3=ammetertypefloorandid.getAmmeterNumberByFloorId(floorid);//得到一个楼层中的所有的电表的标号
			Double[] data3=new Double[4];
			for(int i=0;i<ammeters3.size();i++ ){
				List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters3.get(i).toString(), Integer.parseInt(begintime));
				for( int j=0;j< list.size();j++){
					if(j<4){
						data3[0]+=list.get(j);
					}else if(4<=j&&j<7){
						data3[1]+=list.get(j);
					}else if(7<=j&&j<10){
						data3[2]+=list.get(j);
					}else {
						data3[3]+=list.get(j);
					}
				}
			 }
			e3.setData(data3);
			e3.setName("");
			e3.setDatetime(new String[]{"第一季度","第二季度","第三季度","第四季度"});
			ammeterdevices.add(e3);
			
			break;
		case 4://按年份比较
			
			
			
			break;
		default:
			
				break;
		
		}
		return ammeterdevices;
	}
	/**
	 *  ================================楼层相同电路对比分析使用
	 * Administrator
	 * @param type  电路类型
	 * @param floorids 楼层编号字符串
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("getBySameTypeAndFloorId")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> getBySameTypeAndFloorId(
			@FormParam("type") String type,
			@FormParam("floorids") String floorids,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
		List<Device> devices=new ArrayList<Device>(
				);
				
				switch(timetype){
				
				case 1:
					
					
					break;
				case 2:
					
					
					break;
				case 3:
					
					break;
				case 4:
					
					
					break;
				default:
					
						break;
				
				}
				return devices;
	}
	
	/**
	 * =====================================整栋楼不同电路的对比分析
	 * Administrator
	 * @param buildingid
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("getByBuildingIdAndType")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> getByBuildingIdAndType(
			@FormParam("buildingid") String buildingid,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
		List<Device> devices=new ArrayList<Device>(
				);
				
				switch(timetype){
				
				case 1:
					
					
					break;
				case 2:
					
					
					break;
				case 3:
					
					break;
				case 4:
					
					
					break;
				default:
					
						break;
				
				}
				return devices;
	}
	/**
	 * =====================================整栋楼的总用电量
	 * Administrator
	 * @param buildingid
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("getByBuildingId")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> getByBuildingId(
			@FormParam("buildingid") String buildingid,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
		List<Device> devices=new ArrayList<Device>(
				);
				
				switch(timetype){
				
				case 1:
					
					
					break;
				case 2:
					
					
					break;
				case 3:
					
					break;
				case 4:
					
					
					break;
				default:
						break;
				}
				return devices;
	}
	
	
	//============================================================下载Excel文件===========================//
	
	/**
	 * =====================================各楼层间用电总量对比分析=分析报告保存
	 * Administrator
	 * @param floorids  一个楼层编号字符串
	 * @param begintime
	 * @param endtime
	 * @param timetype(1,2,3,4)分别表示：1 按周查询 2 按月查询 3 按季度查询 4 按年查询
	 * @return
	 */
	
	@POST
	@Path("getByFlooridExcel")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<AmmeterDevice> getByFlooridExcel(
			@FormParam("floorids") String floorids,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype){
		List<AmmeterDevice> ammeterdevices=new ArrayList<AmmeterDevice>();
		String[] floors=floorids.split(",");
		
		AmmeterTypeFloorAndId ammetertypefloorandid=new AmmeterTypeFloorAndId();
		
		Analasis analasis=new Analasis();
		
		switch(timetype){
		
		case 1://按周计算
			
			
			break;
		case 2://按月计算
			for(String floor:floors){
				AmmeterDevice e=new AmmeterDevice();
				List<String> ammeters=ammetertypefloorandid.getAmmeterNumberByFloorId(floor);//得到一个楼层中的所有的电表的标号
				Double[] data=new Double[12];
				for(int i=0;i<ammeters.size();i++ ){
					List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
					for( int j=0;j< list.size();j++){
						data[j]+=list.get(j);
					}
				 }
				e.setData(data);
				e.setName("");
				e.setDatetime(new String[]{"一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"});
				ammeterdevices.add(e);
				}
			break;
		case 3://按季度计算
			for(String floor:floors){
				AmmeterDevice e=new AmmeterDevice();
				List<String> ammeters=ammetertypefloorandid.getAmmeterNumberByFloorId(floor);//得到一个楼层中的所有的电表的标号
				Double[] data=new Double[4];
				for(int i=0;i<ammeters.size();i++ ){
					List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
					for( int j=0;j< list.size();j++){
						if(j<4){
							data[0]+=list.get(j);
						}else if(4<=j&&j<7){
							data[1]+=list.get(j);
						}else if(7<=j&&j<10){
							data[2]+=list.get(j);
						}else {
							data[3]+=list.get(j);
						}
					}
				 }
				e.setData(data);
				e.setName("");
				e.setDatetime(new String[]{"第一季度","第二季度","第三季度","第四季度"});
				ammeterdevices.add(e);
				}
			break;
		case 4://按年份计算
			for(String floor:floors){
				AmmeterDevice e=new AmmeterDevice();
				List<String> ammeters=ammetertypefloorandid.getAmmeterNumberByFloorId(floor);//得到一个楼层中的所有的电表的标号
				Double[] data=new Double[12];
				for(int i=0;i<ammeters.size();i++ ){
					List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
					for( int j=0;j< list.size();j++){
						data[j]+=list.get(j);
					}
				 }
				e.setData(data);
				e.setName("");
				e.setDatetime(new String[]{"一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"});
				ammeterdevices.add(e);
				}
			

			break;
		default:
			
				break;
		
		}
		return ammeterdevices;
	}
	
	
	/**
	 * =========================================根据楼层不同电路对比使用
	 * Administrator
	 * @param floorid 一个楼层编号
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("byTypeAndFloorIdExcel")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<AmmeterDevice> byTypeAndFloorIdExcel(
			@FormParam("floorid") String floorid,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
   List<AmmeterDevice> ammeterdevices=new ArrayList<AmmeterDevice>();
    AmmeterTypeFloorAndId ammetertypefloorandid=new AmmeterTypeFloorAndId();
    Analasis analasis=new Analasis();
		switch(timetype){
		case 1://按周查询
			
			
			
			break;
		case 2://按月份查询
			AmmeterDevice e=new AmmeterDevice();
			List<HashMap<String,String>> ammeters=ammetertypefloorandid.getTypeAndAmmeterNumberByFloorId(floorid);//得到一个楼层中的所有的电表的标号
			Double[] data=new Double[12];
			for(int i=0;i<ammeters.size();i++ ){
				List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
				for( int j=0;j< list.size();j++){
					data[j]+=list.get(j);
				}
			 }
			e.setData(data);
			e.setName("");
			e.setDatetime(new String[]{"一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"});
			ammeterdevices.add(e);
			break;
		case 3://按季度查询
			AmmeterDevice e3=new AmmeterDevice();
			List<String> ammeters3=ammetertypefloorandid.getAmmeterNumberByFloorId(floorid);//得到一个楼层中的所有的电表的标号
			Double[] data3=new Double[4];
			for(int i=0;i<ammeters3.size();i++ ){
				List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters3.get(i).toString(), Integer.parseInt(begintime));
				for( int j=0;j< list.size();j++){
					if(j<4){
						data3[0]+=list.get(j);
					}else if(4<=j&&j<7){
						data3[1]+=list.get(j);
					}else if(7<=j&&j<10){
						data3[2]+=list.get(j);
					}else {
						data3[3]+=list.get(j);
					}
				}
			 }
			e3.setData(data3);
			e3.setName("");
			e3.setDatetime(new String[]{"第一季度","第二季度","第三季度","第四季度"});
			ammeterdevices.add(e3);
			
			break;
		case 4://按年份比较
			
			
			
			break;
		default:
			
				break;
		
		}
		return ammeterdevices;
	}
	/**
	 *  ================================楼层相同电路对比分析使用
	 * Administrator
	 * @param type  电路类型
	 * @param floorids 楼层编号字符串
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("getBySameTypeAndFloorIdExcel")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> getBySameTypeAndFloorIdExcel(
			@FormParam("type") String type,
			@FormParam("floorids") String floorids,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
		List<Device> devices=new ArrayList<Device>(
				);
				
				switch(timetype){
				
				case 1:
					
					
					break;
				case 2:
					
					
					break;
				case 3:
					
					break;
				case 4:
					
					
					break;
				default:
					
						break;
				
				}
				return devices;
	}
	
	/**
	 * =====================================整栋楼不同电路的对比分析
	 * Administrator
	 * @param buildingid
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("getByBuildingIdAndTypeExcel")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> getByBuildingIdAndTypeExcel(
			@FormParam("buildingid") String buildingid,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
		List<Device> devices=new ArrayList<Device>(
				);
				
				switch(timetype){
				
				case 1:
					
					
					break;
				case 2:
					
					
					break;
				case 3:
					
					break;
				case 4:
					
					
					break;
				default:
					
						break;
				
				}
				return devices;
	}
	/**
	 * =====================================整栋楼的总用电量
	 * Administrator
	 * @param buildingid
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("getByBuildingIdExcel")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> getByBuildingIdExcel(
			@FormParam("buildingid") String buildingid,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
		List<Device> devices=new ArrayList<Device>(
				);
				
				switch(timetype){
				
				case 1:
					
					
					break;
				case 2:
					
					
					break;
				case 3:
					
					break;
				case 4:
					
					
					break;
				default:
						break;
				}
				return devices;
	}
	
	
	
	//============================================================加载时间列表=================================//
	
	
	
	//=============================================================保存分析报告===================================//
	/**
	 * =====================================各楼层间用电总量对比分析=分析报告保存
	 * Administrator
	 * @param floorids  一个楼层编号字符串
	 * @param begintime
	 * @param endtime
	 * @param timetype(1,2,3,4)分别表示：1 按周查询 2 按月查询 3 按季度查询 4 按年查询
	 * @return
	 */
	
	@POST
	@Path("getByFlooridReport")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<AmmeterDevice> getByFlooridReport(
			@FormParam("floorids") String floorids,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype){
		List<AmmeterDevice> ammeterdevices=new ArrayList<AmmeterDevice>();
		String[] floors=floorids.split(",");
		
		AmmeterTypeFloorAndId ammetertypefloorandid=new AmmeterTypeFloorAndId();
		
		Analasis analasis=new Analasis();
		
		switch(timetype){
		
		case 1://按周计算
			
			
			break;
		case 2://按月计算
			for(String floor:floors){
				AmmeterDevice e=new AmmeterDevice();
				List<String> ammeters=ammetertypefloorandid.getAmmeterNumberByFloorId(floor);//得到一个楼层中的所有的电表的标号
				Double[] data=new Double[12];
				for(int i=0;i<ammeters.size();i++ ){
					List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
					for( int j=0;j< list.size();j++){
						data[j]+=list.get(j);
					}
				 }
				e.setData(data);
				e.setName("");
				e.setDatetime(new String[]{"一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"});
				ammeterdevices.add(e);
				}
			break;
		case 3://按季度计算
			for(String floor:floors){
				AmmeterDevice e=new AmmeterDevice();
				List<String> ammeters=ammetertypefloorandid.getAmmeterNumberByFloorId(floor);//得到一个楼层中的所有的电表的标号
				Double[] data=new Double[4];
				for(int i=0;i<ammeters.size();i++ ){
					List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
					for( int j=0;j< list.size();j++){
						if(j<4){
							data[0]+=list.get(j);
						}else if(4<=j&&j<7){
							data[1]+=list.get(j);
						}else if(7<=j&&j<10){
							data[2]+=list.get(j);
						}else {
							data[3]+=list.get(j);
						}
					}
				 }
				e.setData(data);
				e.setName("");
				e.setDatetime(new String[]{"第一季度","第二季度","第三季度","第四季度"});
				ammeterdevices.add(e);
				}
			break;
		case 4://按年份计算
			for(String floor:floors){
				AmmeterDevice e=new AmmeterDevice();
				List<String> ammeters=ammetertypefloorandid.getAmmeterNumberByFloorId(floor);//得到一个楼层中的所有的电表的标号
				Double[] data=new Double[12];
				for(int i=0;i<ammeters.size();i++ ){
					List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
					for( int j=0;j< list.size();j++){
						data[j]+=list.get(j);
					}
				 }
				e.setData(data);
				e.setName("");
				e.setDatetime(new String[]{"一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"});
				ammeterdevices.add(e);
				}
			

			break;
		default:
			
				break;
		
		}
		return ammeterdevices;
	}
	
	
	/**
	 * =========================================根据楼层不同电路对比使用
	 * Administrator
	 * @param floorid 一个楼层编号
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("byTypeAndFloorIdReport")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<AmmeterDevice> byTypeAndFloorIdReport(
			@FormParam("floorid") String floorid,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
   List<AmmeterDevice> ammeterdevices=new ArrayList<AmmeterDevice>();
    AmmeterTypeFloorAndId ammetertypefloorandid=new AmmeterTypeFloorAndId();
    Analasis analasis=new Analasis();
		switch(timetype){
		case 1://按周查询
			
			
			
			break;
		case 2://按月份查询
			AmmeterDevice e=new AmmeterDevice();
			List<HashMap<String,String>> ammeters=ammetertypefloorandid.getTypeAndAmmeterNumberByFloorId(floorid);//得到一个楼层中的所有的电表的标号
			Double[] data=new Double[12];
			for(int i=0;i<ammeters.size();i++ ){
				List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters.get(i).toString(), Integer.parseInt(begintime));
				for( int j=0;j< list.size();j++){
					data[j]+=list.get(j);
				}
			 }
			e.setData(data);
			e.setName("");
			e.setDatetime(new String[]{"一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"});
			ammeterdevices.add(e);
			break;
		case 3://按季度查询
			AmmeterDevice e3=new AmmeterDevice();
			List<String> ammeters3=ammetertypefloorandid.getAmmeterNumberByFloorId(floorid);//得到一个楼层中的所有的电表的标号
			Double[] data3=new Double[4];
			for(int i=0;i<ammeters3.size();i++ ){
				List<Double> list=analasis.getDeviceByMonth("ammeter", ammeters3.get(i).toString(), Integer.parseInt(begintime));
				for( int j=0;j< list.size();j++){
					if(j<4){
						data3[0]+=list.get(j);
					}else if(4<=j&&j<7){
						data3[1]+=list.get(j);
					}else if(7<=j&&j<10){
						data3[2]+=list.get(j);
					}else {
						data3[3]+=list.get(j);
					}
				}
			 }
			e3.setData(data3);
			e3.setName("");
			e3.setDatetime(new String[]{"第一季度","第二季度","第三季度","第四季度"});
			ammeterdevices.add(e3);
			
			break;
		case 4://按年份比较
			
			
			
			break;
		default:
			
				break;
		
		}
		return ammeterdevices;
	}
	/**
	 *  ================================楼层相同电路对比分析使用
	 * Administrator
	 * @param type  电路类型
	 * @param floorids 楼层编号字符串
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("getBySameTypeAndFloorIdReport")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> getBySameTypeAndFloorIdReport(
			@FormParam("type") String type,
			@FormParam("floorids") String floorids,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
		List<Device> devices=new ArrayList<Device>(
				);
				
				switch(timetype){
				
				case 1:
					
					
					break;
				case 2:
					
					
					break;
				case 3:
					
					break;
				case 4:
					
					
					break;
				default:
					
						break;
				
				}
				return devices;
	}
	
	/**
	 * =====================================整栋楼不同电路的对比分析
	 * Administrator
	 * @param buildingid
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("getByBuildingIdAndTypeReport")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> getByBuildingIdAndTypeReport(
			@FormParam("buildingid") String buildingid,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
		List<Device> devices=new ArrayList<Device>(
				);
				
				switch(timetype){
				
				case 1:
					
					
					break;
				case 2:
					
					
					break;
				case 3:
					
					break;
				case 4:
					
					
					break;
				default:
					
						break;
				
				}
				return devices;
	}
	/**
	 * =====================================整栋楼的总用电量
	 * Administrator
	 * @param buildingid
	 * @param begintime
	 * @param endtime
	 * @param timetype
	 * @return
	 */
	@POST
	@Path("getByBuildingIdReport")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public List<Device> getByBuildingIdReport(
			@FormParam("buildingid") String buildingid,
			@FormParam("begintime") String begintime,
			@FormParam("endtime") String endtime,
			@FormParam("timetype") Integer timetype
			){
		List<Device> devices=new ArrayList<Device>(
				);
				
				switch(timetype){
				
				case 1:
					
					
					break;
				case 2:
					
					
					break;
				case 3:
					
					break;
				case 4:
					
					
					break;
				default:
						break;
				}
				return devices;
	}
	
	


}
