package cn.cdu.edu.TQC.bems;

import java.util.HashMap;
import java.util.List;

import cn.cdu.edu.TQC.bems.db.dao.AmmeterDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterDaoImpl;

/**
 * 电表的分析使用
 * @author Administrator
 *
 */
public class AmmeterTypeFloorAndId {
	private AmmeterDao dao;
	
	public AmmeterTypeFloorAndId(){
		dao=new AmmeterDaoImpl();
	}

	
	/**
	 *    对各楼层间用电总量的对比
	 * 
	 * 根据楼层号得到各个楼层的仪表
	 * Administrator
	 * @param floorid
	 * @return
	 */
	public List<String> getAmmeterNumberByFloorId(String floorid){
		return dao.getAmmeterNumberByFloorId(floorid);
	}
	
	
	/**
	 * 
	 * 
	 *  对楼层内不同电路对比有用
	 * 根据楼层得到电表的类型和电表的编号
	 * Administrator
	 * @param floorid
	 * @return
	 */
	public  List<HashMap<String,String>> getTypeAndAmmeterNumberByFloorId(String floorid){
		
		return dao.getTypeAndAmmeterNumberByFloorId(floorid);
	}
	
	
	/**
	 * 
	 *   对楼层间相同电路对比使用
	 *   
	 *   注意：地下室只有一个电表
	 * 
	 * 根据楼层编号，和电路类型得到电表的编号
	 * Administrator
	 * @param floorid
	 * @param type
	 * @return
	 */
	public String getAmmeterNumberByFloorIdAndType(String floorid,String type){ 
		return dao.getAmmeterNumberByFloorIdAndType(floorid,type);
	}
	
	
	/**
	 * 对整栋楼不同电路使用的
	 * 根据大楼编号，和电路类型得到电表的编号
	 * Administrator
	 * @param buildingid
	 * @param type
	 * @return
	 */
	public List<String> getAmmeterNumberByBuildingIdAndType(String buildingid,String type){
		return dao.getAmmeterNumberByBuildingIdAndType(buildingid,type);
	}
	
	
	/**
	 * 根据大楼编号得到电表的编号列表
	 * Administrator
	 * @param buildingid
	 * @return
	 */
	public List<String> getAmmeterNumberByBuildingId(String buildingid){
		return dao.getAmmeterNumberByBuildingId(buildingid);
	}
	
	
	
	
	
	
	
	
	
	

}
