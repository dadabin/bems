/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;
/**
 * @ClassName: PhoneOrder
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-16 上午9:55:31
 *
 */
public class PhoneOrder {
    private String phoneID;
    private String PhoneNUM;
    private String floorID;
    private String phonePower;
    private String phoneFee;
    
    public PhoneOrder(){
	super();
    }
    public PhoneOrder(
	     String phoneID,
	     String PhoneNUM,
	     String floorID,
	     String phonePower,
	     String phoneFee){
	super();
	this.floorID = floorID;
	this.phoneFee = phoneFee;
	this.phoneID = phoneID;
	this.PhoneNUM = PhoneNUM;
	this.phonePower = phonePower;
	
    }
    /**
     * @return the phoneID
     */
    public String getPhoneID() {
        return phoneID;
    }
    /**
     * @param phoneID the phoneID to set
     */
    public void setPhoneID(String phoneID) {
        this.phoneID = phoneID;
    }
    /**
     * @return the phoneNUM
     */
    public String getPhoneNUM() {
        return PhoneNUM;
    }
    /**
     * @param phoneNUM the phoneNUM to set
     */
    public void setPhoneNUM(String phoneNUM) {
        PhoneNUM = phoneNUM;
    }
    /**
     * @return the floorID
     */
    public String getFloorID() {
        return floorID;
    }
    /**
     * @param floorID the floorID to set
     */
    public void setFloorID(String floorID) {
        this.floorID = floorID;
    }
    /**
     * @return the phonePower
     */
    public String getPhonePower() {
        return phonePower;
    }
    /**
     * @param phonePower the phonePower to set
     */
    public void setPhonePower(String phonePower) {
        this.phonePower = phonePower;
    }
    /**
     * @return the phoneFee
     */
    public String getPhoneFee() {
        return phoneFee;
    }
    /**
     * @param phoneFee the phoneFee to set
     */
    public void setPhoneFee(String phoneFee) {
        this.phoneFee = phoneFee;
    }
    
}
