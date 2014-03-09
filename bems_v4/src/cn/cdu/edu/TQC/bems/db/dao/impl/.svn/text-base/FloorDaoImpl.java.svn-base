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
import cn.cdu.edu.TQC.bems.db.bean.Floor;
import cn.cdu.edu.TQC.bems.db.dao.FloorDao;

/**
 * @ClassName: FloorDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:34:59
 *
 */
public class FloorDaoImpl implements FloorDao{

	
	public boolean addFloor(Floor floor) {
		String sql="INSERT INTO floor (USERNUM,GRAPHICS,FLOORNUM,INTRO,BUILDINGID) values (?,?,?,?,?) ";
		Object[] params=new Object[5];
		params[0]=floor.getUserNum();
		params[1]=floor.getGraphics();
		params[2]=floor.getFloorNum();
		params[3]=floor.getIntro();
		params[4]=floor.getBuildingId();
		
		DBManager db=new DBManager();
		if(0 < db.ExecuteNonQuery(sql,params)){
			return true;
		}else{
			return false;
		}
		
	}

	
	public boolean updateFloor(Floor floor) {
		String sql="UPDATE floor SET USERNUM=?,GRAPHICS=?,FLOORNUM=?,INTRO=?,BUILDINGID=? WHERE FLOORID=?";
		Object[] params=new Object[6];
		params[0]=floor.getUserNum();
		params[1]=floor.getGraphics();
		params[2]=floor.getFloorNum();
		params[3]=floor.getIntro();
		params[4]=floor.getBuildingId();
		params[5]=floor.getFloorId();
		DBManager db=new DBManager();
		if(1==db.ExecuteNonQuery(sql,params)){
			return true;
		}else{
			return false;
		}
		
	}

	
	public Floor getFloorById(int floorId) {

		String sql="SELECT FLOORID,USERNUM,GRAPHICS,FLOORNUM,INTRO,BUILDINGID FROM floor WHERE FLOORID=?";
		Object[] params=new Object[1];
		params[0]=floorId;
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql,params);
		Floor floor=new Floor(
				Integer.parseInt(list.get(0).get("FLOORID").toString()),
				Integer.parseInt(list.get(0).get("USERNUM").toString()),
				list.get(0).get("GRAPHICS").toString(),
				Integer.parseInt(list.get(0).get("FLOORNUM").toString()),
				list.get(0).get("INTRO").toString(),
				Integer.parseInt(list.get(0).get("BUILDINGID").toString())
				);

		return floor;
	}

	@Override
	public List<Floor> getFloors() {
	
		String sql="SELECT FLOORID,USERNUM,GRAPHICS,FLOORNUM,INTRO,BUILDINGID FROM floor";
		List<Floor> floors=new ArrayList<Floor>();
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		for(Map map : list){
			Floor floor=new Floor(
					Integer.parseInt(map.get("FLOORID").toString()),
					Integer.parseInt(map.get("USERNUM").toString()),
					map.get("GRAPHICS").toString(),
					Integer.parseInt(map.get("FLOORNUM").toString()),
					map.get("INTRO").toString(),
					Integer.parseInt(map.get("BUILDINGID").toString())
					);
			floors.add(floor);
		}
		return floors;
	}


	@Override
	public boolean updateImage(int floorId, String fileName) {
		String sql="UPDATE floor GRAPHICS=? WHERE FLOORNUM=?";
		Object[] params=new Object[2];
		params[0]=fileName;
		params[1]=floorId;
		DBManager db=new DBManager();
		if(1==db.ExecuteNonQuery(sql,params)){
			return true;
		}else{
		return false;
		}
	}

}
