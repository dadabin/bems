/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: Ammeter
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午3:53:34
 *
 */

@XmlRootElement(name="ammeter")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ammeter {
	
	private String ammeterId;
	private String type;
	private String circuittype;
	private int floorId;
	private String location;
	
	public Ammeter(){
		
	}
	
	public Ammeter(String ammeterId,
			String type,
			String circuittype,
			int floorId,
			String location){
		this.ammeterId=ammeterId;
		this.type=type;
		this.circuittype=circuittype;
		this.floorId=floorId;
		this.location=location;
	}
	
	public String getAmmeterId() {
		return ammeterId;
	}
	public void setAmmeterId(String ammeterId) {
		this.ammeterId = ammeterId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCircuittype() {
		return circuittype;
	}
	public void setCircuittype(String circuittype) {
		this.circuittype = circuittype;
	}
	public int getFloorId() {
		return floorId;
	}
	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	

}
