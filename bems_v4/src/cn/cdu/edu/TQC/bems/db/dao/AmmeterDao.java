/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao;

import java.util.HashMap;
import java.util.List;

import cn.cdu.edu.TQC.bems.db.bean.Ammeter;

/**
 * @ClassName: AmmeterDao
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午7:38:49
 *
 */
public interface AmmeterDao {
	public List<Ammeter> getAmmeters();
	public boolean addAmmeter(Ammeter ammeter);
	public Ammeter getAmmeterById(Integer ammeterId);
	public boolean updateAmmeter(Ammeter ammeter);
	public List<String> GetAmmeterIdByFloorId(String floorid);
	public List<String> getAmmeterNumberByFloorId(String floorid);
	public  List<HashMap<String,String>> getTypeAndAmmeterNumberByFloorId(String floorid);
	public String getAmmeterNumberByFloorIdAndType(String floorid,String type);
	public List<String> getAmmeterNumberByBuildingIdAndType(String buildingid,String type);
	public List<String> getAmmeterNumberByBuildingId(String buildingid);
	
}
