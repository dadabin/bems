/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems;

import org.tempuri.LinkWS;
import org.tempuri.LinkWSSoap;

/**
 * @ClassName: SendEMS
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-8-10 上午9:38:38
 *
 */
public class SendEMS {
    private LinkWSSoap soap;
    private String enterprise_No;
    private String enterprise_pwd;
    
    
    public SendEMS(){
	soap = new LinkWS().getLinkWSSoap();
	enterprise_No = Constant.Enterprise_No;
	enterprise_pwd = Constant.Enterprise_pwd;
    }
    
    /***
     * 
     * @param phoneNo
     * @param content
     * @param snedTime
     * @return
     * 0，发送成功进入审核阶段；
     * 1、直接发送成功；
     * -1、帐号尚未注册；
     * -2、其他错误；
     * -3、帐号或则密码错误；
     * -4、一次提交信息不能超过600个手机号码；
     * -5、企业号帐户余额不足，请先充值再提交短信息！；
     * -6、定时发送时间不是有效的时间格式；
     * -8、发送内容需在3到500个字之间；
     * -9、 发送号码为空
     */
    public int sned(String[] phoneNo,String content,String snedTime){
	
	StringBuffer sb = new StringBuffer();
	if(phoneNo.length > 0){
	    for(int i=0;i<phoneNo.length;i++){
		    String str ;
		    if(i == phoneNo.length - 1){
			str = phoneNo[i];
		    }else {
			str = phoneNo[i] + ",";
		    }
		    sb.append(str);
		}
	}
	
	int result = soap.batchSend(enterprise_No,enterprise_pwd,sb.toString(),content, "",snedTime);
	
	return result;
    }
}
