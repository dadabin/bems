/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;

import java.util.Date;

/**
 * @ClassName: PlaneRecord
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:45:39
 *
 */
public class PlaneRecord {
	private int planeRecordId;
	private int analysisReportId;
	private String staffId;
	private String content;
	private Date time;
	
	public PlaneRecord(){
		
	}
	
	public PlaneRecord(int planeRecordId,
			int analysisReportId,
			String staffId,
			String content,
			Date time){
		this.planeRecordId = planeRecordId;
		this.analysisReportId = analysisReportId;
		this.staffId = staffId;
		this.content = content;
		this.time = time;
	}
	
	
	public int getPlaneRecordId() {
		return planeRecordId;
	}
	public void setPlaneRecordId(int planeRecordId) {
		this.planeRecordId = planeRecordId;
	}
	public int getAnalysisReportId() {
		return analysisReportId;
	}
	public void setAnalysisReportId(int analysisReportId) {
		this.analysisReportId = analysisReportId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	

}
