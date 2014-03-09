/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tempuri.LinkWS;
import org.tempuri.LinkWSSoap;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.cdu.edu.TQC.bems.Analasis;
import cn.cdu.edu.TQC.bems.Utils;
import cn.cdu.edu.TQC.bems.Algorithm.TimeSerial;
import cn.cdu.edu.TQC.bems.action.LoginAction;
import cn.cdu.edu.TQC.bems.db.bean.MeterData;
import cn.cdu.edu.TQC.bems.db.bean.Template;
import cn.cdu.edu.TQC.bems.db.dao.TemplateDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.MeterDataDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.TemplateDaoImpl;

/**
 * @ClassName: Test
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-5 下午8:42:17
 *
 */
public class Test {

    public static void main(String[] args) {
//	List<HashMap<String, Object>> all = new Analasis().getComsumData("meter", "20000001", new Date("2012/1/10"),new Date("2012/2/27"));
//	Date begin = new Utils().stringToDate(2012+"0101000000");
//	Date end = new Utils().stringToDate(2012+"1230000000");
//	double da = new Analasis().getAmongData("ammeter", "20120005", begin,end);
//	System.out.println("******"+da);	
//	
//	System.out.println(new Utils().getStringDate(new Date()).get("year"));
	
	//短信接口测试
//	LinkWSSoap soap = new LinkWS().getLinkWSSoap(); 
//	
//	//发送时间
//	Date date = new Date();
//	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//	System.out.println("------"+format.format(date));
//	
//	//向群组发送一天短信
//	
//	int result = soap.batchSend("LKSDK0002822","342293","13408063229",
//		"（3）《TQC@CDU.EDU》智能楼宇能耗管理系统，短信测试功能完善（3）！收到任务请回复1，任务不明请回复2。任务重新分配请回复3！谢谢合作。注工作愉快！", "","");
//	
//	System.out.println("---result---"+result);
	
	
	List<Double> data1 = new Analasis().getDeviceByMonth("ammeter", "20120001", 2009);
	List<Double> data2 = new Analasis().getDeviceByMonth("ammeter", "20120001", 2010);
	List<Double> data3 = new Analasis().getDeviceByMonth("ammeter", "20120001", 2011);
	
	ArrayList seasonSample = new ArrayList();
	
	
	for(int i=0;i<data1.size();i++){
	    HashMap temp = new HashMap();
	    temp.put("PERIOD","2009");
		temp.put("SEASON","Y"+(i+1));
		temp.put("VALUE",""+data1.get(i));
		seasonSample.add(i,temp);
	}
	for(int i=0;i<data1.size();i++){
	    HashMap temp = new HashMap();
	    temp.put("PERIOD","2010");
		temp.put("SEASON","Y"+(i+1));
		temp.put("VALUE",""+data2.get(i));
		seasonSample.add(12+i,temp);
	}
	for(int i=0;i<data1.size();i++){
	    HashMap temp = new HashMap();
	    temp.put("PERIOD","2011");
		temp.put("SEASON","Y"+(i+1));
		temp.put("VALUE",""+data3.get(i));
		seasonSample.add(24+i,temp);
	}
	
	
	
	ArrayList SeasonList = new ArrayList();
	for(int i=0;i<12;i++){
	    HashMap temp = new HashMap();
	    temp = new HashMap();
		temp.put("SEASON","Y"+(i+1));
		temp.put("SEASON_INDEX","1");
		SeasonList.add(i,temp);
	}
	
	double res = TimeSerial.seasonTimeSerialForecast(seasonSample,SeasonList,"2012","Y2");
	double result[] = new double[SeasonList.size()];
	for(int j = 0;j<result.length;j++){
	    result[j] = TimeSerial.seasonTimeSerialForecast(seasonSample,SeasonList,"2012","Y"+(j+1));
	   
	}
	
	int i=1;
	for(double re:result){
	    System.out.println(i+"=========="+re);
	    i++;
	}
	//====================
	
	
    }
    
   

}
