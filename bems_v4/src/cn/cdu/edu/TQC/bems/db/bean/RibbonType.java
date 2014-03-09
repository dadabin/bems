/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;
/**
 * @ClassName: RibbonType
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午4:55:46
 *
 */
public class RibbonType {
	
	private int ribbonTypeId;
	private String typeName;
	private String color;
	private String intro;
	
	public RibbonType(){
		
	}
	public RibbonType(int ribbonTypeId,
			String typeName,
			String  color,
			String intro){
		this.ribbonTypeId = ribbonTypeId;
		this.typeName = typeName;
		this.color = color;
		this.intro = intro;
	}
	
	public int getRibbonTypeId() {
		return ribbonTypeId;
	}
	public void setRibbonTypeId(int ribbonTypeId) {
		this.ribbonTypeId = ribbonTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}


}
