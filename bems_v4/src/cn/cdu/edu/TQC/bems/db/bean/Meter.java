/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: Meter
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:29:14
 *
 */

@XmlRootElement(name="meter")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meter {
	
	private String meterId;
	private String type;
	private int floorId;
	private String location;
	
	public Meter(){
		
	}
	
	public Meter(String meterId,
			String type,
			int floorId,
			String location){
		this.meterId = meterId;
		this.type = type;
		this.floorId = floorId;
		this.location = location;
		
	}
	
	
	public String getMeterId() {
		return meterId;
	}
	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
