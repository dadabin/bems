/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao.impl;

import java.util.List;

import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.PlaneRecord;
import cn.cdu.edu.TQC.bems.db.dao.PlaneRecordDao;

/**
 * @ClassName: PlaneRecordDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:56:30
 *
 */
public class PlaneRecordDaoImpl implements PlaneRecordDao{

	
	public boolean addPlaneRecord(PlaneRecord planeRecord) {
		String sql="INSERT INTO plane_record (ANALYSISREPORTID,STAFFID,CONTENT,TIME) VALUES (?,?,?,?)";
		Object[] params=new Object[]{planeRecord.getAnalysisReportId(),planeRecord.getStaffId(),planeRecord.getContent(),planeRecord.getTime()};
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sql,params)==1){
			return true;
		}else{
		return false;
		}
	}


	public boolean updatePlaneRecord(PlaneRecord planeRecord) {
		String sql="UPDATE plane_record SET ANALYSISREPORTID=?,STAFFID=?,CONTENT=? WHERE PLANERECORDID=?";
		Object[] params=new Object[]{planeRecord.getAnalysisReportId(),planeRecord.getStaffId(),planeRecord.getContent(),planeRecord.getPlaneRecordId()};
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sql,params)==1){
			return true;
		}else{
		return false;
		}
	}

	
	public PlaneRecord getPlaneRecordById(Integer planeRecordId) {
		String sql="";
		Object[] params=new Object[] {};
		return null;
	}

	
	public List<PlaneRecord> getPlaneRecords() {
		// TODO Auto-generated method stub
		return null;
	}

}
