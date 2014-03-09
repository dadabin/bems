/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.AnalysisReport;
import cn.cdu.edu.TQC.bems.db.dao.AnalysisReportDao;

/**
 * @ClassName: AnalysisReportDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:21:23
 *
 */
public class AnalysisReportDaoImpl implements AnalysisReportDao{

	@Override
	public boolean addAnalysisReport(AnalysisReport analysisReport) {
		String sql="INSERT INTO analysis_report(STAFFID,CONTENT,TIME) VALUES (?,?,?)";
		Object[] params=new Object[3];
		params[0]=analysisReport.getStaffId();
		params[1]=analysisReport.getContent();
		params[2]=analysisReport.getTime();
		DBManager db=new DBManager();
		if(1==db.ExecuteNonQuery(sql,params)){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public boolean updateAnalysisReport(AnalysisReport analysisReport) {

		return false;
	}

	
	public AnalysisReport getAnalysisReposrtById(Integer analysisReportId) {
            String sql="SELECT ANALYSISREPORTID,STAFFID,CONTENT,TIME FROM analysis_report WHERE ANALYSISREPORTID=?";
            Object[] params=new Object[1];
            params[0]=analysisReportId;
            
            DBManager db=new DBManager();
            List<HashMap<String,Object>> list=db.ExecuteQuery(sql,params);
            AnalysisReport analysisReport=new AnalysisReport(
            		Integer.parseInt(list.get(0).get("ANALYSISREPORTID").toString()),
            		list.get(0).get("STAFFID").toString(),
            		list.get(0).get("CONTENT").toString(),
            		Date.valueOf(list.get(0).get("TIME").toString())
            		
            		);
            

		
		return analysisReport;
	}

	@Override
	public List<AnalysisReport> getAnalysisReports() {
		// TODO Auto-generated method stub
		return null;
	}

}
