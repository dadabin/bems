/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import sun.util.logging.resources.logging;

/**
 * @ClassName: Device
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-13 下午1:19:07
 *
 */

@XmlRootElement(name="serize")
@XmlAccessorType(XmlAccessType.FIELD)
public class Device implements Serializable{
    private final static long seralizID = 128190382190231L; 
    
    private String name;
    private Double[] data;
    private Date[] times;
    
    public Device(){
	
    }
    public Device(String name,Double[] data,Date[] time){
	this.name = name;
	this.data = data;
	this.times = time;
    }
    

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double[] getData() {
        return data;
    }
    public void setData(Double[] data) {
        this.data = data;
    }
    public Date[] getTimes() {
        return times;
    }
    public void setTimes(Date[] times) {
        this.times = times;
    }
    
    
}
