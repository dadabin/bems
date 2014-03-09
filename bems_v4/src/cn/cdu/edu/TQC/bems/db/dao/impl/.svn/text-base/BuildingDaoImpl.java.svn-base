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
import cn.cdu.edu.TQC.bems.db.bean.Building;
import cn.cdu.edu.TQC.bems.db.dao.BuildingDao;

/**
 * @ClassName: BuildingDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:27:53
 *
 */
public class BuildingDaoImpl implements BuildingDao{

	/**
	 * 
	 */
	public boolean addBuilding(Building building) {


		String sql="INSERT INTO building(USERNUM,GRAPHICS,FLOORNUM,AREA,UNDERGROUNDNUMBER,DESCRIPTION) VALUES (?,?,?,?,?,?)";
		Object[] params=new Object[6];
		params[0]=building.getUserNum();
		params[1]=building.getGraphics();
		params[2]=building.getFloorNum();
		params[3]=building.getArea();
		params[4]=building.getUndergroundNumber();
		params[5]=building.getDescription();
		
		DBManager db=new DBManager();
		if(1==db.ExecuteNonQuery(sql,params)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 */
	public boolean updateBuilding(Building building) {
		String sql="UPDATE building SET USERNUM=?,GRAPHICS=?,FLOORNUM=?,AREA=?,UNDERGROUNDNUMBER=?,DESCRIPTION=? WHERE BUILDINGID=?";
		Object[] params=new Object[7];
		params[0]=building.getUserNum();
		params[1]=building.getGraphics();
		params[2]=building.getFloorNum();
		params[3]=building.getArea();
		params[4]=building.getUndergroundNumber();
		params[5]=building.getDescription();
		params[6]=building.getBuildingId();
		DBManager db=new DBManager();
		if(1==db.ExecuteNonQuery(sql,params)){
			return true;
		}else{
			return false;
		}
			
		
		
	}

	/**
	 * 
	 */
	public Building getBuildingById(int buildingId) {

		String sql="SELECT BUILDINGID,USERNUM,GRAPHICS,FLOORNUM,AREA,UNDERGROUNDNUMBER,DESCRIPTION FROM building WHERE BUILDINGID=?";
	   Object[] params=new Object[1];
	   params[0]=buildingId;
	   DBManager db=new DBManager();
	   List<HashMap<String,Object>> list=db.ExecuteQuery(sql,params);
	   Building building=new Building(
			   Integer.parseInt(list.get(0).get("BUILDINGID").toString()),
			   Integer.parseInt(list.get(0).get("USERNUM").toString()),
				list.get(0).get("GRAPHICS").toString(),
				Integer.parseInt(list.get(0).get("FLOORNUM").toString()),
				Double.parseDouble(list.get(0).get("AREA").toString()),
				Integer.parseInt(list.get(0).get("UNDERGROUNDNUMBER").toString()),
				list.get(0).get("DESCRIPTION").toString());
		return building;
	}

	/**
	 * 
	 */
	public List<Building> getBuildings() {
		// TODO Auto-generated method stub
		String sql="SELECT BUILDINGID,USERNUM,GRAPHICS,FLOORNUM,AREA,UNDERGROUNDNUMBER,DESCRIPTION FROM building";
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		List<Building> buildings=new ArrayList<Building>();
		for(Map map : list){
			Building building=new Building(
					Integer.parseInt(map.get("BUILDINGID").toString()),
					   Integer.parseInt(map.get("USERNUM").toString()),
					   map.get("GRAPHICS").toString(),
						Integer.parseInt(map.get("FLOORNUM").toString()),
						Double.parseDouble(map.get("AREA").toString()),
						Integer.parseInt(map.get("UNDERGROUNDNUMBER").toString()),
						map.get("DESCRIPTION").toString()
					);
			buildings.add(building);
			
			
		}
		return buildings;
	}

	
	@Override
	public boolean updateImage(Integer buildingID,String image) {
	    boolean flag = false;
	    String sql = "UPDATE building SET GRAPHICS = '"+image+"' WHERE BUILDINGID = "+buildingID;
	    DBManager db=new DBManager();
	    int i = db.ExecuteNonQuery(sql);
	    if(i > 0){
		flag = true;
	    }
	    return flag;
	}

}
