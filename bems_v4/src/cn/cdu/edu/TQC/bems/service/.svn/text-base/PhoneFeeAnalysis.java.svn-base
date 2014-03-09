/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import cn.cdu.edu.TQC.bems.Constant;
import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.PhoneFeeData;

import com.sun.jersey.spi.resource.Singleton;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @ClassName: PhoneFeeService
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-16 下午4:04:29
 *
 */

@Path("/phoneAnalysis")
@Singleton
public class PhoneFeeAnalysis {
    private DBManager db;
    public PhoneFeeAnalysis(){
	this.db = new DBManager();
    }
    
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    @GET
    @Path("/query")
    @Produces("application/json")
    public String topTen(
	    @QueryParam("type") String type,
	    @QueryParam("year") int year,
	    @QueryParam("month") int month){
	
	List<HashMap<String , Object>> all = new ArrayList<HashMap<String,Object>>();
	String jsonStr = "{\"serize\":[";
	int i = 0;
	
	if(Constant.PHONE_POWER_WORD.equals(type)){
	    //国际
	    all = getTopTenPhone(Constant.PHONE_POWER_WORD_CN, year, month);
	}
	if(Constant.PHONE_POWER_CONTRY.equals(type)){
	    //国内
	    all = getTopTenPhone(Constant.PHONE_POWER_CONTRY_CN, year, month);
	}
	if(Constant.PHONE_POWER_CITY.equals(type)){
	    //市话p
	    all = getTopTenPhone(Constant.PHONE_POWER_CITY_CN, year, month);
	}
	if(Constant.PHONE_POWER_INNER.equals(type)){
	    //内线
	    all = getTopTenPhone(Constant.PHONE_POWER_INNER_CN, year, month);
	}
	for(Map map : all){
	    if(i < all.size() - 1){
		jsonStr += "{\"phone\":\""+all.get(i).get("PHONENUM")+"\",\"fee\":\""+all.get(i).get("FEE")+"\",\"responsor\":\""+all.get(i).get("PERSONSRESPONESIBLEFOR")+"\"},";
	    }else {
		jsonStr += "{\"phone\":\""+all.get(i).get("PHONENUM")+"\",\"fee\":\""+all.get(i).get("FEE")+"\",\"responsor\":\""+all.get(i).get("PERSONSRESPONESIBLEFOR")+"\"}";
	    }
	    i++;
	}
	return jsonStr+"]}";
    }
    
    /***
     * 返回整栋楼每层的话费总量
     * 
     * @param year
     * @param month
     * @return
     */
    @GET
    @Path("/floors")
    @Produces("application/json")
    public String getFloors(
	    @QueryParam("year") int year,
	    @QueryParam("month") int month){
	
	String jsonStr = "{\"datas\":[";
	List<HashMap<String, Object>> all = getEveryFloors(year, month);
	for(int i=0;i<all.size();i++){
	    if(i < all.size()-1){
		jsonStr += all.get(i).get(i+"")+",";//"{\""+(i+1)+"楼\":\""+all.get(i).get(i+"")+"\"},";
	    }else {
		jsonStr += all.get(i).get(i+"");//"{\""+(i+1)+"楼\":\""+all.get(i).get(i+"")+"\"}";
	    }
	}
	return jsonStr+"]}";
    }
   
    @GET
    @Path("/avg")
    @Produces("application/json")
    public String getFloorsEVG(
	    @QueryParam("year") int year,
	    @QueryParam("month") int month){
	double[] all = getEveryFloorsEVG(year, month);
	String jsonStr = "{\"datas\":[";
	for(int i=0;i<all.length;i++){
	    if(i < all.length-1){
		jsonStr += all[i]+",";
	    }else {
		jsonStr += all[i];
	    }
	}
	return jsonStr+"]}";
    }
    
    
    
    /********************************
     * 		电话分析	            *
     ********************************/
    
    
    /**
     * 不同权限，，话费最高的前十门
     * 返回：
     * 1、电话号码---‘PHONENUM’
     * 2、电话费用----‘FEE’
     * 3、电话责任人---‘PERSONSRESPONESIBLEFOR’
     * 
     * @param power--------电话权限
     * @param year--------年月
     * @param month---------月
     * @return
     */
    private List<HashMap<String , Object>> getTopTenPhone(String power ,int year,int month){
	
	
	int returnNUM = 10;//设置返回数量，，，从高到低排序desc
	
//	String kkkkkkkkkk="select f.PHONENUM ,f.FEE,f.STAFFID,p.PERSONSRESPONESIBLEFOR from phone_fee_data AS f ,PHONE AS p where p.PHONEPOWER='国际长途' and year(f.time)=2012 and month(f.time)=7 and f.PHONEID=p.PHONEID order by f.FEE desc LIMIT 10 OFFSET 0;";
	//返回指定月份，指定权限的话费最高的前十部电话
	String sql  = "select f.PHONENUM ,f.FEE,p.PERSONSRESPONESIBLEFOR " +
			"from phone_fee_data AS f ,PHONE AS p " +
			"where p.PHONEPOWER='"+power+"' and year(f.time)="+year+" and month(f.time)="+month+" and f.PHONEID=p.PHONEID " +
					"order by f.FEE desc LIMIT "+returnNUM+" OFFSET 0;";

	List<HashMap<String , Object>> all = db.ExecuteQuery(sql);
	return all;
    }
    
    /***
     * 获取大楼内每层楼的平均电话费
     * @param year
     * @param month
     * @return
     */
    private double[] getEveryFloorsEVG(
	    int year,
	    int month){
	List<HashMap<String, Object>> all = getEveryFloors(year, month);
	double[] result = new double[all.size()];
	int[] phonnums = getEveryFloorsPhoneNum();
	for(int i=0;i<all.size();i++){
		double a = Double.parseDouble(all.get(i).get(i+"").toString());
		result[i] = a/phonnums[i];
	}
	return result;
    } 
    
    
    /****
     * 获取大楼内指定月份的各楼层电话费用情况
     * 
     * @param year
     * @param month
     * @return
     */
    private List<HashMap<String, Object>> getEveryFloors(
	    int year,
	    int month){
	//查询无重复的列，，所有的楼层号
	//select distinct  FLOORID from floor ;
	 List<HashMap<String, Object>> all = new ArrayList<HashMap<String,Object>>();
	 
	Integer[] floorsNum = new PhoneFeeAnalysis().getFloorsNum();
	for(int i=0;i<floorsNum.length;i++){
	    String sql = "select sum(f.FEE) as tottle from phone_fee_data AS f ,PHONE AS p where p.FLOORID="+floorsNum[i]+" and f.PHONEID=p.PHONEID and year(f.time)="+year+" and month(f.time)="+month;
	    List<HashMap<String, Object>> maps = db.ExecuteQuery(sql);
	    Object map = maps.get(0).get("tottle");
	    
	    if(map != null){
		HashMap<String, Object> pa = new HashMap<String, Object>();
		pa.put(i+"", map);
		 all.add(pa);
	    }
	}
	
	
	//查询指定楼层在指定月份的电话费用总和
	////select sum(f.FEE) from phone_fee_data AS f ,PHONE AS p where p.FLOORID=6 and f.PHONEID=p.PHONEID and year(f.time)=2012 and month(f.time)=7 ;
	
	return all;
    }
    
    
    /***
     * 获取每层楼的电话数 
     * @return
     */
    private int[] getEveryFloorsPhoneNum(){
	
	Integer[] floors = getFloorsNum();
	int[] all = new int[floors.length];
	for(int i=0;i<floors.length;i++){
	    String sql = "select * from phone p where p.FLOORID="+floors[i];
	    all[i] = db.ExecuteQuery(sql).size();
	}
	return all;
    }
    
    /**
     * 
     * @return
     */
    private Integer[] getFloorsNum(){
	int buildingid = 1;
	String str = "select distinct  FLOORID from floor  where BUILDINGID="+buildingid;
	List<HashMap<String, Object>> floors =  db.ExecuteQuery(str);
	Integer[] floorsNo = new Integer[floors.size()];
	int i=0;
	for(HashMap<String, Object> m:floors){
	   floorsNo[i]= (Integer) m.get("FLOORID");
	   i++;
	}
	return floorsNo;
    }
    
    
    //测试类
    public static void main(String[] args){
	//查询某栋大楼的楼层数目
	Integer[] a = new PhoneFeeAnalysis().getFloorsNum();
	System.out.println("---"+a.length);
	
	
	//查询每一楼的话费
	List<HashMap<String, Object>> all = new PhoneFeeAnalysis().getEveryFloors(2012, 7);
	System.out.println("-===="+all.size());
	for(int i=0;i<all.size();i++){
	    System.out.println(i+1+"楼-------"+all.get(i).get(i+""));
	}
	
	//查询不同权限的话费前十部
	List<HashMap<String , Object>> list = new PhoneFeeAnalysis().getTopTenPhone("国际长途", 2012, 7);
	for(Map map:list){
	    System.out.println("电话号码："+map.get("PHONENUM")+"----电话费用"+map.get("FEE")+"----责任人："+map.get("PERSONSRESPONESIBLEFOR"));
	}
	
	System.out.println("+====="+new PhoneFeeAnalysis().topTen("word", 2012, 7));
	System.out.println("--------"+new PhoneFeeAnalysis().getFloors(2012, 7));
	
	
	double[] aa = new PhoneFeeAnalysis().getEveryFloorsEVG(2012, 7);
	for(double x :aa){
	    System.out.println("楼**=========="+x);
	}
    }
}
