/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao;

import java.util.List;

import cn.cdu.edu.TQC.bems.db.bean.PlaneRecord;

/**
 * @ClassName: PlaneRecordDao
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:16:38
 *
 */
public interface PlaneRecordDao {
	
	public boolean addPlaneRecord(PlaneRecord planeRecord);
	
	public boolean updatePlaneRecord(PlaneRecord planeRecord);
	
	public PlaneRecord getPlaneRecordById(Integer planeRecordId);
	
	public List<PlaneRecord> getPlaneRecords();
	

}
