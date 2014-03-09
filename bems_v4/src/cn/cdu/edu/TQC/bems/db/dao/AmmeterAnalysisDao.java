/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao;

import java.util.HashMap;
import java.util.List;

import cn.cdu.edu.TQC.bems.db.bean.Device;

/**
 * @ClassName: AmmeterAnalysisDao
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-13 下午4:57:53
 *
 */
public interface AmmeterAnalysisDao {

	public List<Device> analysisFloorAndType(String startTime, String endTime,
			String type, String[] floorIds);
	
	public List<Device> analysisFloorAll(String startTime, String endTime,
			String[] floorIds);
	
	public List<Device> analysisBuildingType(String startTime, String endTime,
			String[] types);
	
	public List<Device> analysisType(String startTime, String endTime, String floorId,
			String[] types);
	
	
	
	
	
	
}
