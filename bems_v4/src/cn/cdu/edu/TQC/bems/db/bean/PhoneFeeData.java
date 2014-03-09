/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: PhoneFeeData
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:42:17
 * 
 * 
 * @Version V2.0 2012-7-15
 * @author LPM 添加属性phoneNum
 *
 */

@XmlRootElement(name="phoneFeeData")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneFeeData {
	
	private int phoneFeeDataId;
	private int phoneId;
	private Date time;
	private double fee;
	private String staffId;
	private String phoneNum;
	
	public PhoneFeeData(){
		
	}
	public PhoneFeeData(int phoneFeeDataId,int phoneId,
			Date time,double fee, String staffId){
		this.phoneFeeDataId = phoneFeeDataId;
		this.phoneId = phoneId;
		this.time = time;
		this.fee = fee;
		this.staffId = staffId;
	}
	
	public int getPhoneFeeDataId() {
		return phoneFeeDataId;
	}
	public void setPhoneFeeDataId(int phoneFeeDataId) {
		this.phoneFeeDataId = phoneFeeDataId;
	}
	public int getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	
	public String getPhoneNum() {
	    return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
	    this.phoneNum = phoneNum;
	}
	

}
