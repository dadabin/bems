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
 * @ClassName: AmmeterData
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午3:57:50
 *
 */

@XmlRootElement(name="ammeterData")
@XmlAccessorType(XmlAccessType.FIELD)
public class AmmeterData {
	private int ammeterDataId;
	private double data;
	private Date time;
	private String ammeterId;
	
	public AmmeterData(){
		
	}
	public AmmeterData(int ammeterDataId,
			double data,
			Date time,
			String ammeterId){
		this.ammeterDataId=ammeterDataId;
		this.data=data;
		this.time=time;
		this.ammeterId=ammeterId;
	}
	
	public AmmeterData(
		double data,
		Date time,
		String ammeterId){
	this.data=data;
	this.time=time;
	this.ammeterId=ammeterId;
}
	
	
	public int getAmmeterDataId() {
		return ammeterDataId;
	}
	public void setAmmeterDataId(int ammeterDataId) {
		this.ammeterDataId = ammeterDataId;
	}
	public double getData() {
		return data;
	}
	public void setData(double data) {
		this.data = data;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getAmmeterId() {
		return ammeterId;
	}
	public void setAmmeterId(String ammeterId) {
		this.ammeterId = ammeterId;
	}
	
	

}
