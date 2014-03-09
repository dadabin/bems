/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @ClassName: Email_Autherticator 说明： TODO(Tell the reader such role.)
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-7 下午2:37:35
 * 
 */
public class Email_Autherticator extends Authenticator {
    public static String username = "bitium10";
    public static String password = "ling195891";

    public Email_Autherticator() {
	super();
    }

    public Email_Autherticator(String user, String pwd) {
	super();
	username = user;
	password = pwd;
    }

    public PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(username, password);
    }
}
