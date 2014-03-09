/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao;

import java.util.HashMap;
import java.util.List;

import cn.cdu.edu.TQC.bems.db.bean.AmmeterData;

/**
 * @ClassName: AmmeterDataDao
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:02:53
 *
 */
public interface AmmeterDataDao {
	
	public boolean addAmmeterData(AmmeterData ammeterData);
	
	public boolean updateAmmeterData(AmmeterData ammeterData);
	
	public AmmeterData getAmmeterDataById(Integer ammeterDataid);
	
	public List<AmmeterData> getAmmeterDatas();
	
	public boolean collectionData(AmmeterData ammeterData);

	public List<HashMap<String,Object>> select(String fieldStr,String whereStr);
	
	public List<HashMap<String,Object>> select (int index,int pageSize);
	
	public List<HashMap<String,Object>> select (int index,int pageSize,String whereStr);
	
	public boolean bulkDelete(String whereStr);
	
	public List<AmmeterData> getAmmeterDatasByAmmeterID(Integer ammeterID);
	
	public String comparisonofAClassOfFloors(
			String type,
			String startTime,
			String endTime,
			String interval,
			String ammeterType
			
			);
	public List<AmmeterData> getAmmeterDatasByFloors(Integer floorID);

}
