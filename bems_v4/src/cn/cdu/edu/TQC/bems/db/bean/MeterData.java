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
 * @ClassName: MeterData
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:34:29
 *
 */

@XmlRootElement(name="meterData")
@XmlAccessorType(XmlAccessType.FIELD)
public class MeterData {
	
	
	private int meterDataId;
	private String meterId;
	private double data;
	private Date time;
	
	
	public MeterData(){
		
	}
	public MeterData(int meterDataId,
			String meterId,
			double data,
			Date time){
		this.meterDataId=meterDataId;
		this.meterId=meterId;
		this.data=data;
		this.time=time;
		
		
	}

	public int getMeterDataId() {
		return meterDataId;
	}
	public void setMeterDataId(int meterDataId) {
		this.meterDataId = meterDataId;
	}
	public String getMeterId() {
		return meterId;
	}
	public void setMeterId(String meterId) {
		this.meterId = meterId;
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
	
	
	

}
