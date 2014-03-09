/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cdu.edu.TQC.bems.Utils;
import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.AmmeterData;
import cn.cdu.edu.TQC.bems.db.dao.AmmeterDataDao;

/**
 * @ClassName: AmmeterDataDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:08:31
 *
 */
public class AmmeterDataDaoImpl implements AmmeterDataDao {
    	private DBManager db = null;
    	private Utils utils = null;
    	
    	public AmmeterDataDaoImpl(){
    	    this.db = new DBManager();
    	    this.utils = new Utils();
    	}

	@Override
	public boolean addAmmeterData(AmmeterData ammeterData) {
	    boolean flag = false;
		String sql = "insert into ammeter_data(AMMETERDATAID," +
				"DATA,TIME,AMMETERID) values (?,?,?,?)";
		Object[] params = new Object[4];
		params[0] = ammeterData.getAmmeterDataId();
		params[1] = ammeterData.getData();
		params[2] = ammeterData.getTime();
		params[3] = ammeterData.getAmmeterId();
		try {
		    int i = db.ExecuteNonQuery(sql, params);
		    if(i > 0){
			flag = true;
		    }
		} catch (Exception e) {
		    // TODO: handle exception
		}
		return flag;
	}

	@Override
	public boolean updateAmmeterData(AmmeterData ammeterData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AmmeterData getAmmeterDataById(Integer ammeterDataid) {
		AmmeterData ammeterData = new AmmeterData();
		String sql = "select * from ammeter_data where AMMETERDATAID = ?";
		Object[] params = new Object[1];
		params[0] = ammeterDataid;
		List<HashMap<String, Object>> list =  db.ExecuteQuery(sql, params);
		Map map = list.get(0);
		ammeterData.setAmmeterDataId((Integer)map.get("AMMETERDATAID"));
		ammeterData.setAmmeterId(map.get("AMMETERID").toString());
		ammeterData.setData(utils.getRounding( (Double)map.get("DATA") ));
		ammeterData.setTime((Date) map.get("TIME"));
		
		return ammeterData;
	}

	@Override
	public List<AmmeterData> getAmmeterDatas() {
		List<AmmeterData> all = new ArrayList<AmmeterData>();
		String sql = "select * from ammeter_data";
		List<HashMap<String, Object>> list =  db.ExecuteQuery(sql);
		for(Map map : list){
		    AmmeterData ammeterData = new AmmeterData();
		    ammeterData.setAmmeterDataId((Integer)map.get("AMMETERDATAID"));
		     ammeterData.setAmmeterId(map.get("AMMETERID").toString());
		    ammeterData.setData(utils.getRounding( (Double)map.get("DATA") ));
		    ammeterData.setTime((Date) map.get("TIME"));
		    all.add(ammeterData);	
		}
		return all;
	}
	
	/**
	 * sun  2012-7-10
	 * 功能：条件查询
	 * @param fieldStr 查询的字段
	 * @param whereStr  约束条件
	 * 
	 * @return
	 */
	public List<HashMap<String,Object>> select(String fieldStr,String whereStr){
	    return null;
	}

	    
	public boolean collectionData(AmmeterData ammeterData) {
	    boolean flag = false;
		String sql = "insert into ammeter_data("+
				"DATA,TIME,AMMETERID) values (?,?,?)";
		Object[] params = new Object[3];
		params[0] = ammeterData.getData();
		params[1] = ammeterData.getTime();
		params[2] = ammeterData.getAmmeterId();
		try {
		    int i = db.ExecuteNonQuery(sql, params);
		    if(i > 0){
			flag = true;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		System.out.println("---------->collection ammeter");
		return flag;
	}
	
	
	
	/**
	 * sun 2012-7-10
	 * 功能：无条件分页
	 * @param index
	 * @param pageSize
	 * @return
	 */
	public List<HashMap<String,Object>> select (int index,int pageSize){
		
		return null;
	}
	
	/**
	 * sun 2012-7-10 
	 * 功能：条件分页
	 * @param index
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	public List<HashMap<String,Object>> select (
			       int index,
			       int pageSize,
			       String whereStr
			       ){
	    String sql="SELECT AMMETERDATAID,DATA,TIME,AMMETERID FROM ammeter_data  %s  LIMIT  "+(index-1)*pageSize+"," +pageSize*index;
	    sql=String.format(sql, whereStr);
	    DBManager db=new DBManager();
	    return db.ExecuteQuery(sql);
		
	}
	
	/**
	 * sun 2012-7-10 
	 * 功能：批量删除
	 * @param whereStr
	 * @return
	 */
	public boolean bulkDelete(String whereStr){
	
		String sql="DELETE FROM ammeter_data where AMMETERDATAID IN ( %S )";
		sql=String.format(sql, whereStr);
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sql)==1){
			return true;
		}else{
			return false;
		}
		
	}
	//================================================下面是数据分析方面的一些方法==================//
	/**
	 * sun 2012-7-11 
	 * 某一类电表 某段时间间隔的用电量   楼层间比较分析 
	 * 
	 * 
	 * 
	 * @param type     :类型 以周为间隔单位、还是以月为间隔单位，还是以年为间隔单位
	 * @param startTime :时间格式 如：2012-7-10 ：00:00:00
	 * @param endTime   ：时间格式 如：2012-7-10 ：00:00:00
	 * @param interval  :时间间隔数字  整数
	 * @param ammeterType  :电路类型
	 * @return
	 */
	public String comparisonofAClassOfFloors(
			String type,
			String startTime,
			String endTime,
			String interval,
			String ammeterType
			){
		Utils util=new Utils();
		 HashMap<String,String> hashMapStartTime =util.getStringDate(util.stringToDateTwo(startTime));
		 HashMap<String,String> hashMapEndTime=util.getStringDate(util.stringToDateTwo(endTime));
		String startSql=
				"select ammeterdataid,`DATA`,ammeter.AMMETERID,ammeter.CIRCUITTYPE,y,m,d,"+
		       "floor.intro,floor.FLOORID  from (select ammeterid,ammeterdataid,`DATA`,year(TIME) as"+
			   " y, MONTH(TIME) as m,DAY(TIME) as d from ammeter_data) as adata,ammeter,floor  "+
		       " where floor.floorid=ammeter.FLOORID and ammeter.AMMETERID=adata.ammeterid and "+
			   "y="+Integer.parseInt(hashMapStartTime.get("year")) +"  and m="+Integer.parseInt(hashMapStartTime.get("mouth"))+" and d="+
			   Integer.parseInt(hashMapStartTime.get("day"))+
			   "  ";
		String endSql=
				"select ammeterdataid,`DATA`,ammeter.AMMETERID,ammeter.CIRCUITTYPE,y,m,d,"+
		       "floor.intro,floor.FLOORID  from (select ammeterid,ammeterdataid,`DATA`,year(TIME) as"+
			   " y, MONTH(TIME) as m,DAY(TIME) as d from ammeter_data) as adata,ammeter,floor  "+
		       " where floor.floorid=ammeter.FLOORID and ammeter.AMMETERID=adata.ammeterid and "+
			   "y="+Integer.parseInt(hashMapEndTime.get("year")) +"  and m="+Integer.parseInt(hashMapEndTime.get("mouth"))+" and d="+
			   Integer.parseInt(hashMapEndTime.get("day"))+
			   " ";
		DBManager db=new DBManager();
		List<HashMap<String,Object>> startTimeResult=db.ExecuteQuery(startSql);
		List<HashMap<String,Object>> endTimeResult=db.ExecuteQuery(endSql);
		List<HashMap<String,Object>> returnList=new ArrayList<HashMap<String,Object>>();
		//将List<HashMap<String,Object>>的数据转换成JSON字符串
		Map	startMap=null,endMap=null;
		int i=0;
		while(startTimeResult!=null&&endTimeResult!=null){
			if(i>=startTimeResult.size()||i>=endTimeResult.size())
				break;
		    startMap=startTimeResult.get(i);
		    endMap=endTimeResult.get(i);
		    HashMap<String,Object> h=new HashMap<String,Object>();
		    h.put("\"CIRCUITTYPE\"", "\""+startMap.get("CIRCUITTYPE")+"\"");
		    h.put("\"intro\"", "\""+startMap.get("intro")+"\"");
		    h.put("\"DATA\"",  "\""+(Double.parseDouble(endMap.get("DATA").toString())-Double.parseDouble(startMap.get("DATA").toString()))+"\"");
		    h.put("\"AMMETERID\"", "\""+startMap.get("AMMETERID")+"\"");
		    h.put("\"FLOORID\"", "\""+startMap.get("FLOORID")+"\"");
		    returnList.add(h);
			i++;
		}
		String res=returnList.toString().replaceAll("=", ":");

		return res;
	}
	/**
	 * sun 2012-7-12 
	 * 楼层间用电总量的对比
	 * @param startTime  起始时间
	 * @param endTime    终止时间
	 * @return
	 */
	public String theTotalPowerBetweenTheFloor(String startTime ,String endTime){
		String startSql="select f.floorid,f.usernum,f.floornum,f.intro,sum(`DATA`) as total,ad.TIME from floor f,  ammeter a,ammeter_data ad where f.floorid=a.floorid and ad.ammeterid=a.ammeterid and TIME='"+startTime+"' group by(a.floorid)";
		String endSql="select f.floorid,f.usernum,f.floornum,f.intro,sum(`DATA`) as total,ad.TIME from floor f,  ammeter a,ammeter_data ad where f.floorid=a.floorid and ad.ammeterid=a.ammeterid and TIME='"+endTime+"' group by(a.floorid)";
		
		DBManager db=new DBManager();
		List<HashMap<String,Object>> startList=db.ExecuteQuery(startSql);
		List<HashMap<String,Object>>  endList=db.ExecuteQuery(endSql);
		
		System.out.println(startList);
		System.out.println(endList);
		 String re=getJsonByListsPoor(
				 startList,
				 endList,
					"floormy",
					new String[]{"floorid","usernum","floornum","intro","total","TIME"},
					"total",
					"total"
					);
		 System.out.println(re);
		return re;
	}
	public static void main(String[] args){
		AmmeterDataDaoImpl a=new AmmeterDataDaoImpl();
		//a.theTotalPowerBetweenTheFloor("2010-01-01 00:00:00", "2010-11-05 00:00:00");
		a.theTrendOfBuildingElectricity("2010-01-01 00:00:00", "2010-11-05 00:00:00");
	}
	
	/**
	 * sun 2012-7-12
	 * 一段时间整栋大楼的总的用电
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String theTrendOfBuilding(String startTime,String endTime){
		String startSql="select sum(`DATA`) as total,TIME from ammeter_data where TIME='"+startTime+"' group by TIME ";
		String endSql="select sum(`DATA`) as total,TIME from ammeter_data where TIME='"+endTime+"' group by TIME ";
		DBManager db=new DBManager();
		List<HashMap<String,Object>> startList=db.ExecuteQuery(startSql);
		List<HashMap<String,Object>> endList=db.ExecuteQuery(endSql);
		 String re=getJsonByListsPoor(
				 startList,
				 endList,
					"building",
					new String[]{"TIME","total"},
					"total",
					"total"
					);
		 System.out.println(re);
		return re;
	}
	/**
	 * sun 2012-7-12
	 * 整栋大楼不同电路的用电路用电总量分析
	 * @param startTime
	 * @param endTime
	 * @param typeStr
	 * @return
	 */
	public String theTrendOfBuildingElectricityByType(String startTime,String endTime,String typeStr){
		String startSql="";
		String endSql="";
		DBManager db=new DBManager();
		List<HashMap<String,Object>> startList=db.ExecuteQuery(startSql);
		List<HashMap<String,Object>> endList=db.ExecuteQuery(endSql);
		return null;
	}
	
	/**
	 * sun 2012-7-12
	 * 整栋楼总用电量的趋势图(读取电表的数据)
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String theTrendOfBuildingElectricity(String startTime,String endTime){
		
		String sql="select sum(`DATA`) as total,TIME from ammeter_data where TIME BETWEEN '"+startTime+"' and '"+endTime+"' group by TIME ";
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		String rs=getJsonByList("building",new String[]{"total","TIME"},list);		
		 System.out.println(rs);
		return rs;
	}
	
	
	/**
	 * sun  2012-7-12
	 * 一层楼的总用电量的趋势
	 * @param startTime 起始时间
	 * @param endTime  终止时间
	 * @param FloorId 楼层编号
	 * @return
	 */
	public String theTrendOfFloorElectricity(String startTime,String endTime,String FloorId){
		return "";
	}
	
	
	
	
	
	
	
	
	
	
	

	//==========================================================下方是封装的方法=============================
	/**
	 * sun  2012-7-12 
	 * 封装方法  
	 * 得到一个json对象
	 * @param objectName  json对象名
	 * @param jsonTitle   json属性有哪些
	 * @param list        传入的list
	 * @return  返回json
	 */
	public String getJsonByList(String objectName,String[] jsonTitle,List<HashMap<String,Object>> list){
		int i=0;
		List<HashMap<String,Object>> resutList=new ArrayList<HashMap<String,Object>>(); 
		while(list!=null){
			if(i>=list.size())
				break;
			HashMap<String,Object> h=new HashMap<String,Object>();
			for(String name:jsonTitle){
				h.put("\""+name+"\"", "\""+list.get(i).get(name)+"\"");
				
			}
			resutList.add(h);
			i++;
		}
		String res="{\""+objectName+"\":"+resutList.toString().replaceAll("=", ":")+"}";
		return res;
	}
	
	/**
	 * sun 2012-7-12
	 * 有字段做差比较list2中的属性列减去list1中的属性列 
	 * 注意： 两个list中的属性列结构必须完全相同
	 * 
	 * @param list1  
	 * @param list2   
	 * @param objectName   ：返回的Json对象名称
	 * @param jsonTitle   :返回的json中的属性的名称的列名
	 * @param poorTitle  ：做差的属性名
	 * @param poorNewTitle : 做差后的新的属性标题
	 * @return
	 */
	public  String getJsonByListsPoor(
			List<HashMap<String,Object>> list1,
			List<HashMap<String,Object>> list2,
			String objectName,
			String[] jsonTitle,
			String poorTitle,
			String poorNewTitle
			){
		
		int i=0;
		List<HashMap<String,Object>> resutList=new ArrayList<HashMap<String,Object>>(); 
		while(list1!=null&&list2!=null){
			if(i>=list1.size()||i>=list2.size())
				break;
			HashMap<String,Object> h=new HashMap<String,Object>();
			for(String name:jsonTitle){
				if(poorTitle.equals(name)){
					 h.put("\""+poorNewTitle+"\"",  "\""+(Double.parseDouble(list2.get(i).get(name).toString())-Double.parseDouble(list1.get(i).get(name).toString()))+"\"");
				}else{
				h.put("\""+name+"\"", "\""+list1.get(i).get(name)+"\"");
				}
			}
			resutList.add(h);
			i++;
		}
		String res="{\""+objectName+"\":"+resutList.toString().replaceAll("=", ":")+"}";
		return res;
	}

	@Override
	public List<AmmeterData> getAmmeterDatasByAmmeterID(Integer ammeterID) {
		List<AmmeterData> all = new ArrayList<AmmeterData>();
		String sql = "select * from ammeter_data where AMMETERID=?";
		Object[] params = {ammeterID};
		List<HashMap<String, Object>> list =  db.ExecuteQuery(sql,params);
		for(Map map : list){
		    AmmeterData ammeterData = new AmmeterData();
		    ammeterData.setAmmeterDataId((Integer)map.get("AMMETERDATAID"));
		     ammeterData.setAmmeterId(map.get("AMMETERID").toString());
		    ammeterData.setData((Double)map.get("DATA"));
		    ammeterData.setTime((Date) map.get("TIME"));
		    all.add(ammeterData);	
		}
		return all;
	}


	@Override
	public List<AmmeterData> getAmmeterDatasByFloors(Integer floorID) {
	    List<AmmeterData> all = new ArrayList<AmmeterData>();
		String sql = "select a.AMMETERID,a.TIME,a.DATA,a.AMMETERDATAID from ammeter_data as a , ammeter as am where  am.FLOORID=? group by a.AMMETERID";
		Object[] params = {floorID};
		List<HashMap<String, Object>> list =  db.ExecuteQuery(sql,params);
		for(Map map : list){
		    AmmeterData ammeterData = new AmmeterData();
		    ammeterData.setAmmeterDataId((Integer)map.get("AMMETERDATAID"));
		     ammeterData.setAmmeterId(map.get("AMMETERID").toString());
		    ammeterData.setData((Double)map.get("DATA"));
		    ammeterData.setTime((Date) map.get("TIME"));
		    all.add(ammeterData);	
		}
		return all;
	}
	
	
}
