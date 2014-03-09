/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: Building
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-6 下午12:10:58
 *
 */

@XmlRootElement(name="usage")
@XmlAccessorType(XmlAccessType.FIELD)
public class Template implements Serializable{
    private static long serialVersionUID = 32131312341L;
    
    private Integer 	proOne;
    private String 	proTwo;
    private Long 	proThr;
    
    /***************************
     *       constructor	*
     ***************************/
    
    public Template(){
	
    }
    public Template(int one,String two,long three){
	proOne = one;
	proTwo = two;
	proThr = three;
    }

    /***************************
     *  setter and getter      *
     ***************************/
    
    public Integer getProOne() {
        return proOne;
    }

    public void setProOne(Integer proOne) {
        this.proOne = proOne;
    }
  
    public String getProTwo() {
        return proTwo;
    }

    public void setProTwo(String proTwo) {
        this.proTwo = proTwo;
    }

    public Long getProThr() {
        return proThr;
    }
    
    public void setProThr(Long proThr) {
        this.proThr = proThr;
    }
   
}
