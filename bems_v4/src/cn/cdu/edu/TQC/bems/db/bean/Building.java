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

@XmlRootElement(name="building")
@XmlAccessorType(XmlAccessType.FIELD)
public class Building {
	private int buildingId;
	private int userNum;
	private String graphics;
	private int floorNum;
	private double area;
	private int undergroundNumber;
	private String description;
	
	public Building()
	{
		
	}
	public Building(int buildingId,
			int userNum,
			String graphics,
			int floorNum,
			double area,
			int undergroundNumber,
			String description){
		this.buildingId=buildingId;
		this.userNum=userNum;
		this.graphics=graphics;
		this.floorNum=floorNum;
		this.area=area;
		this.undergroundNumber=undergroundNumber;
		this.description=description;
	}
	
	public int getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
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
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public int getUndergroundNumber() {
		return undergroundNumber;
	}
	public void setUndergroundNumber(int undergroundNumber) {
		this.undergroundNumber = undergroundNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
