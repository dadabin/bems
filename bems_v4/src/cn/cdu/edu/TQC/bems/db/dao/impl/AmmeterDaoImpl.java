/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.Ammeter;
import cn.cdu.edu.TQC.bems.db.dao.AmmeterDao;

/**
 * @ClassName: AmmeterDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午7:41:27
 *
 */
public class AmmeterDaoImpl implements AmmeterDao {

	
	@Override
	public List<Ammeter> getAmmeters() {
		String sql="SELECT AMMETERID,TYPE,CIRCUITTYPE,FLOORID,LOCATION FROM ammeter";
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		List<Ammeter> ammeters=new ArrayList<Ammeter>();
		for(Map map:list){
			Ammeter ammeter=new Ammeter(
					map.get("AMMETERID").toString(),
					map.get("TYPE").toString(),
					map.get("CIRCUITTYPE").toString(),
					Integer.parseInt(map.get("FLOORID").toString()),
					map.get("LOCATION").toString()
					);
			
			ammeters.add(ammeter);
		}
		return ammeters;
	}

	/**
	 * 添加一行记录
	 */
	public boolean addAmmeter(Ammeter ammeter) {
		
		DBManager db=new DBManager();
		String sql="insert into ammeter(TYPE,CIRCUITTYPE,FLOORID,LOCATION) values(?,?,?,?)";
		Object[] params=new Object[4];
		params[0]=ammeter.getType();
		params[1]=ammeter.getCircuittype();
		params[2]=ammeter.getFloorId();
		params[3]=ammeter.getLocation();
		
		if(1==db.ExecuteNonQuery(sql,params)){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 根据Id得到对象
	 */
	public Ammeter getAmmeterById(Integer ammeterId) {
		String sql="SELECT AMMETERID,TYPE,CIRCUITTYPE,FLOORID,LOCATION FROM ammeter where AMMETERID=?";
		Object[] params=new Object[1];
		params[0]=ammeterId;
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql, params);
		Ammeter ammeter = null;
		for(Map map:list){
			 ammeter=new Ammeter(
					 map.get("AMMETERID").toString(),
					 map.get("TYPE").toString(),
					 map.get("CIRCUITTYPE").toString(),
					Integer.parseInt(map.get("FLOORID").toString()),
					map.get("LOCATION").toString()
					);
		}
		
	
		return ammeter;
	}

	public boolean updateAmmeter(Ammeter ammeter) {
		
		return false;
	}
	
	
	
	public List<String> GetAmmeterIdByFloorId(String floorid){
		String sql="select ammeterid from ammeter where floorid="+floorid;
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		List<String> reList=new ArrayList<String>();
		
		for(Map map:list){
			reList.add(map.get("ammeterid").toString());
		}
		return reList;
	}

	@Override
	
	public List<String> getAmmeterNumberByFloorId(String floorid) {
		//String sql="select a.ammeterid from ammeter a where a.floorid in (select floorid from building where buildingid='"+floorid+"')";
		String sql="select a.ammeterid from ammeter a where a.floorid="+floorid;
		List<String> reList=new ArrayList<String>();
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		for(Map map:list){
			reList.add(map.get("ammeterid").toString());
		}
		return reList;
	}

	@Override
	public List<HashMap<String, String>> getTypeAndAmmeterNumberByFloorId(
			String floorid) {
		String sql="select a.ammeterid,a.circuittype from ammeter a where a.floorid="+floorid;
		DBManager db=new DBManager();
		List<HashMap<String,String>> reList=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		for(Map map:list){
			HashMap<String,String> h=new HashMap<String,String>();
			h.put("number", map.get("ammeterid").toString());
			h.put("type", map.get("circuittype").toString());
			reList.add(h);
		}
		return reList;
	}

	@Override
	public String getAmmeterNumberByFloorIdAndType(String floorid, String type) {


		String sql="select a.ammeterid from ammeter a where a.floorid="+floorid+" and a.circuittype='"+type+"'";
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		String reStr="";
		for(Map map:list){
			reStr=map.get("ammeterid").toString();
		}
		return reStr;
	}

	@Override
	public List<String> getAmmeterNumberByBuildingId(String buildingid) {
		String sql="select a.ammeterid from ammeter a where a.floorid in (select floorid from building where buildingid="+buildingid+")";
				//String sql="select a.ammeterid from ammeter a where a.floorid="+floorid;
				List<String> reList=new ArrayList<String>();
				DBManager db=new DBManager();
				List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
				for(Map map:list){
					reList.add(map.get("ammeterid").toString());
				}
				return reList;
	}

	@Override
	public List<String> getAmmeterNumberByBuildingIdAndType(String buildingid,
			String type) {
		String sql="select a.ammeterid from ammeter a where a.floorid in (select floorid from building where buildingid="+buildingid+") and a.circuittype='"+type+"'";
		//String sql="select a.ammeterid from ammeter a where a.floorid="+floorid;
		List<String> reList=new ArrayList<String>();
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		for(Map map:list){
			reList.add(map.get("ammeterid").toString());
		}
		return reList;
	}

}
