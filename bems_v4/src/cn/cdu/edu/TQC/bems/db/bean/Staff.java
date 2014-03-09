/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: Staff
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:59:10
 *
 */
@XmlRootElement(name="staff")
@XmlAccessorType(XmlAccessType.FIELD)
public class Staff {
	
	private String staffId;
	private String name;
	private String password;
	private String phoneNumber;
	private String email;
	private String role;
	private String photo;
	
	public Staff(){
		
	}
	
	public Staff(String staffId,
			String name,
			String password,
			String phoneNumber,
			String email,
			String role,
			String photo){
		this.staffId = staffId;
		this.name = name;
		this.password =password;
		this.phoneNumber = phoneNumber;
		this.email =email;
		this.role = role;
		this.photo = photo;
		
	}
	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
