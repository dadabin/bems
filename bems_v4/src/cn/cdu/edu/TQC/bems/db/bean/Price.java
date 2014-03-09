/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;
/**
 * @ClassName: Price
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:49:25
 *
 */
public class Price {
	private int priceId;
	private double meterFee;
	private double ammeterFee;
	
	public Price(){
		
	}
	
	public Price(int priceId,
			double meterFee,
			double ammeterFee){
		this.priceId = priceId;
		this.meterFee = meterFee;
		this.ammeterFee = ammeterFee;
	}
	
	public int getPriceId() {
		return priceId;
	}
	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}
	public double getMeterFee() {
		return meterFee;
	}
	public void setMeterFee(double meterFee) {
		this.meterFee = meterFee;
	}
	public double getAmmeterFee() {
		return ammeterFee;
	}
	public void setAmmeterFee(double ammeterFee) {
		this.ammeterFee = ammeterFee;
	}
	
	
	

}
