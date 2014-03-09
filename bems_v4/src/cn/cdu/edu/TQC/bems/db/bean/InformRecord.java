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
 * @ClassName: InformRecord
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:19:18
 *
 */

@XmlRootElement(name="inform")
@XmlAccessorType(XmlAccessType.FIELD)
public class InformRecord {
	
	private int informRecordId;
	private String informPerson;
	private String acceptPerson;
	private Date sendTime;
	private int type;
	private String content;
	private String peplay;
	private Date peplayTime;
	private int isDeal;
	private String tittle;
	
	
	public InformRecord(){
		
	}
	public InformRecord(int informRecordId,
			String informPerson,
			String acceptPerson,
			Date sendTime,
			int type,
			String content,
			String peplay,
			Date peplayTime,
			int isDeal,
			String tittle){
		this.informRecordId = informRecordId;
		this.informPerson = informPerson;
		this.acceptPerson = acceptPerson;
		this.sendTime = sendTime;
		this.type = type;
		this.content = content;
		this.peplay = peplay;
		this.peplayTime = peplayTime;
		this.tittle = tittle;
		
	}
	
	public int getInformRecordId() {
		return informRecordId;
	}
	public void setInformRecordId(int informRecordId) {
		this.informRecordId = informRecordId;
	}
	public String getInformPerson() {
		return informPerson;
	}
	public void setInformPerson(String informPerson) {
		this.informPerson = informPerson;
	}
	public String getAcceptPerson() {
		return acceptPerson;
	}
	public void setAcceptPerson(String acceptPerson) {
		this.acceptPerson = acceptPerson;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPeplay() {
		return peplay;
	}
	public void setPeplay(String peplay) {
		this.peplay = peplay;
	}
	public Date getPeplayTime() {
		return peplayTime;
	}
	public void setPeplayTime(Date peplayTime) {
		this.peplayTime = peplayTime;
	}
	public int getIsDeal() {
		return isDeal;
	}
	public void setIsDeal(int isDeal) {
		this.isDeal = isDeal;
	}
	public String getTittle() {
	    return tittle;
	}
	public void setTittle(String tittle) {
	    this.tittle = tittle;
	}
	
	
	
	

}
