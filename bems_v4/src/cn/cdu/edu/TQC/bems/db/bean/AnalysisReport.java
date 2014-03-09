/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;

import java.util.Date;

/**
 * @ClassName: AnalysisReport
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:00:48
 *
 */
public class AnalysisReport {
	
	private int analysisResportId;
	private String staffId;
	private String content;
	private Date time;
	
	public AnalysisReport(){
		
	}
	
	public AnalysisReport(int analysisResportId,
			String staffId,
			String content,
			Date time){
		this.analysisResportId=analysisResportId;
		this.staffId=staffId;
		this.content=content;
		this.time=time;
	}
	
	
	public int getAnalysisResportId() {
		return analysisResportId;
	}
	public void setAnalysisResportId(int analysisResportId) {
		this.analysisResportId = analysisResportId;
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
