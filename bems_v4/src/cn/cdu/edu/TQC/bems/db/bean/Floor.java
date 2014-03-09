/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: Floor
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午3:48:33
 *
 */

@XmlRootElement(name="floor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Floor {
	private int floorId;
	private int userNum;
	private String graphics;
	private int floorNum;
	private String intro;
	private int buildingId;
	
	public Floor(){
		
	}
	
	public Floor(
			int floorId,
			int userNum,
			String graphics,
			int floorNum,
			String intro,
			int buildingId){
		this.floorId=floorId;
		this.userNum=userNum;
		this.graphics=graphics;
		this.floorNum=floorNum;
		this.intro=intro;
		this.buildingId=buildingId;
	}
	
	public Floor(
		int userNum,
		String graphics,
		int floorNum,
		String intro,
		int buildingId){
	this.userNum=userNum;
	this.graphics=graphics;
	this.floorNum=floorNum;
	this.intro=intro;
	this.buildingId=buildingId;
}
	
	public int getFloorId() {
		return floorId;
	}
	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getGraphics() {
		return graphics;
	}
	public void setGraphics(String graphics) {
		this.graphics = graphics;
	}
	public int getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}
	
	

}
