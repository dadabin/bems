/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: EmergncyGroup
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:09:10
 *
 */

@XmlRootElement(name="emergncyGroup")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmergncyGroup {
	private int emergncyGroupId;
	private String groupName;
	private String description;
	
	public EmergncyGroup(){
		
	}
	public EmergncyGroup(int emergncyGroupId,
			String groupName,
			String description){
		
		this.emergncyGroupId = emergncyGroupId;
		this.groupName = groupName;
		this.description = description;
		
	}
	public int getEmergncyGroupId() {
		return emergncyGroupId;
	}
	public void setEmergncyGroupId(int emergncyGroupId) {
		this.emergncyGroupId = emergncyGroupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
