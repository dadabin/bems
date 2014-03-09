/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import cn.cdu.edu.TQC.bems.Analasis;
import cn.cdu.edu.TQC.bems.Utils;
import cn.cdu.edu.TQC.bems.Algorithm.TimeSerial;

import com.sun.jersey.spi.resource.Singleton;

/**
 * @ClassName: ForecastService 说明： TODO(Tell the reader such role.)
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-8-17 下午5:50:46
 * 
 */

@Path("/forecastService")
@Singleton
public class ForecastService {

    /***
     * 
     * @param deviceType
     *            ----------设备类型
     * @param deviceID
     *            ------------设备编号
     * @param forcastYear
     *            ---------预测的年份
     * 
     * @return 该年内每月的预测能耗值
     * 
     */
    @GET
    @Path("/forcastYear")
    @Produces("application/json")
    public String forcastYear(
	    @QueryParam(value = "deviceType") String deviceType,
	    @QueryParam(value = "deviceID") String deviceID,
	    @QueryParam(value = "forcastYear") String forcastYear) {

	String jsonString = "{\"result\":[";

	// 获取样本
	int year = Integer.parseInt(forcastYear);
	ArrayList seasonSample = new ArrayList();
	int k = 0;
	for (int i = 2008; i < year; i++) {
	    List<Double> data = new Analasis().getDeviceByMonth(deviceType,
		    deviceID, i);
	    for (int j = 0; j < data.size(); j++) {
		HashMap temp = new HashMap();
		temp.put("PERIOD", i);
		temp.put("SEASON", "Y" + (j + 1));
		temp.put("VALUE", "" + data.get(j));
		seasonSample.add(k * 12 + j, temp);
	    }
	    k++;
	}

	ArrayList SeasonList = new ArrayList();
	for (int i = 0; i < 12; i++) {
	    HashMap temp = new HashMap();
	    temp = new HashMap();
	    temp.put("SEASON", "Y" + (i + 1));
	    temp.put("SEASON_INDEX", "1");
	    SeasonList.add(i, temp);
	}

	for (int j = 0; j < SeasonList.size(); j++) {
	    double re = TimeSerial.seasonTimeSerialForecast(seasonSample,
		    SeasonList, forcastYear, "Y" + (j + 1));
	    double result = new Utils().getRounding(re);
	    if (j == SeasonList.size() - 1) {
		jsonString += result+"]";
	    } else {
		jsonString += result + ",";
	    }

	}
	
	jsonString += ",\"avg\":[";
	
	for (int j = 0; j < SeasonList.size(); j++) {
	    double re = TimeSerial.seasonTimeSerialForecast(seasonSample,
		    SeasonList, forcastYear, "Y" + (j + 1)) / 180;
	    double result = new Utils().getRounding(re);
	    if (j == SeasonList.size() - 1) {
		jsonString += result+"]";
	    } else {
		jsonString += result + ",";
	    }

	}
	
	return jsonString + "}";
    }

    @GET
    @Path("/forcastSeason")
    @Produces("application/json")
    public String forcastSeason(
	    @QueryParam(value = "deviceType") String deviceType,
	    @QueryParam(value = "deviceID") String deviceID,
	    @QueryParam(value = "forcastYear") String forcastYear,
	    @QueryParam(value = "forcastSeason") String forcastSeason) {
	String jsonString = "{\"result\":[{\"name\":\""+forcastYear+"（预测年份）\",\"data\":[";
	// 获取样本
	int year = Integer.parseInt(forcastYear);
	ArrayList seasonSample = new ArrayList();
	int k = 0;
	for (int i = 2008; i < year; i++) {
	    List<Double> data = new Analasis().getDeviceBySeason(deviceType,
		    deviceID, i);
	    for (int j = 0; j < data.size(); j++) {
		HashMap temp = new HashMap();
		temp.put("PERIOD", i);
		temp.put("SEASON", "Y" + (j + 1));
		temp.put("VALUE", "" + data.get(j));
		seasonSample.add(k * 4 + j, temp);
	    }
	    k++;
	}

	ArrayList SeasonList = new ArrayList();
	for (int i = 0; i < 4; i++) {
	    HashMap temp = new HashMap();
	    temp = new HashMap();
	    temp.put("SEASON", "Y" + (i + 1));
	    temp.put("SEASON_INDEX", "1");
	    SeasonList.add(i, temp);
	}

	for (int j = 0; j < SeasonList.size(); j++) {
	    double re = TimeSerial.seasonTimeSerialForecast(seasonSample,
		    SeasonList, forcastYear, "Y" + (j + 1));
	    double result = new Utils().getRounding(re);
	    if (j == SeasonList.size() - 1) {
		jsonString += result + "]}";
	    } else {
		jsonString += result + ",";
	    }

	}
	
	jsonString += ",{\"name\":\""+(year-1)+"（历史数据）\",\"data\":[";
	
	List<Double> data = new Analasis().getDeviceBySeason(deviceType,
		    deviceID, year-1);
	
	for(int i=0;i<data.size();i++){
	    double result = new Utils().getRounding(data.get(i));
	    if (i == data.size() - 1) {
		jsonString += result+"]";
	    } else {
		jsonString += result + ",";
	    }
	}
	
	return jsonString + "}]}";
    }

    @GET
    @Path("/forcastMonth")
    @Produces("application/json")
    public String forcastMonth(
	    @QueryParam(value = "deviceType") String deviceType,
	    @QueryParam(value = "deviceID") String deviceID,
	    @QueryParam(value = "forcastYear") String forcastYear,
	    @QueryParam(value = "forcastMonth") String forcastMonth) {
	String jsonString = "{\"result\":[";
	// 获取样本
	int year = Integer.parseInt(forcastYear);
	ArrayList seasonSample = new ArrayList();
	int k = 0;
	for (int i = 2008; i < year; i++) {
	    List<Double> data = new Analasis().getDeviceByMonth(deviceType,
		    deviceID, i);
	    for (int j = 0; j < data.size(); j++) {
		HashMap temp = new HashMap();
		temp.put("PERIOD", i);
		temp.put("SEASON", "Y" + (j + 1));
		temp.put("VALUE", "" + data.get(j));
		seasonSample.add(k * 12 + j, temp);
	    }
	    k++;
	}

	ArrayList SeasonList = new ArrayList();
	for (int i = 0; i < 12; i++) {
	    HashMap temp = new HashMap();
	    temp = new HashMap();
	    temp.put("SEASON", "Y" + (i + 1));
	    temp.put("SEASON_INDEX", "1");
	    SeasonList.add(i, temp);
	}

	
	    double re = TimeSerial.seasonTimeSerialForecast(seasonSample,
		    SeasonList, forcastYear, "Y" + forcastMonth);
	    double result = new Utils().getRounding(re);
	   
	return jsonString+result+"]}";
    }

}
