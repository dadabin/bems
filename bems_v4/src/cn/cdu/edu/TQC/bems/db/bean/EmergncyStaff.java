/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;
/**
 * @ClassName: EmergncyStaff
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:12:47
 *
 */
public class EmergncyStaff {
	private int emergncyStaffId;
	private String staffId;
	private String position;
	private int emergncyGroupId;
	
	public EmergncyStaff(){
		
	}
	
	public EmergncyStaff(int emergncyStaffId,
			String staffId,
			String position,
			int emergncyGroupId){
		
		this.emergncyStaffId = emergncyStaffId;
		this.staffId = staffId;
		this.position = position;
		this.emergncyGroupId = emergncyGroupId; 
	}
	
	public int getEmergncyStaffId() {
		return emergncyStaffId;
	}
	public void setEmergncyStaffId(int emergncyStaffId) {
		this.emergncyStaffId = emergncyStaffId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getEmergncyGroupId() {
		return emergncyGroupId;
	}
	public void setEmergncyGroupId(int emergncyGroupId) {
		this.emergncyGroupId = emergncyGroupId;
	}
	

}
