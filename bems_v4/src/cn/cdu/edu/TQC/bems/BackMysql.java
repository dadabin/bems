package cn.cdu.edu.TQC.bems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 *<p>版权所有:(c)2012-2013 sun</
 *@作者: Administrator
 *@时间: 2012-8-16 下午6:13:16
 *@描述: [BackMysql]请在此简要描述类的功能
 *
 *
 *mysql数据库的备份和还原
 *
 *
 */

public class BackMysql {
	
    public static void main(String[] args){
    	BackMysql bk=new BackMysql();
    }
    
    //backup方法是备份数据库到服务器地址
    public void backup(){
    	try{
    		String filePath=System.getProperty("user.dir")+"\\"+new Date().getYear()+
    				"-"+new Date().getMonth()+"-"+new Date().getDay()+".sql";//BakMysql.class.getResource("").toString();
    		System.out.println();
    		Runtime rt=Runtime.getRuntime();
    		//调用mysql的cmd：
    		Process child=rt.exec("D:\\Program Files\\Apache SoftwareFoundation\\xampp\\mysql\\bin\\mysqldump -uroot -p1234 114la");
    		//设置导出编码为utf-8.这里必须是utf-8
    		//注意这一句，是指允许mysqldump命令，后面跟的是登录名和登录的密码，接着后面的是指备份的数据库的名字，到此结束，
    		//以此生成一个执行的进程，取得此进程输出流到我们要备份的文件
    		//把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注意：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
    		InputStream in=child.getInputStream();//控制台的输出信息作为输入流
    		InputStreamReader xx=new InputStreamReader(in,"utf8");//设置输出流编码为utf8.这里必须是utf8，否则从流中读入的是乱码
    		String inStr;
    		StringBuffer sb=new StringBuffer("");
    		String outStr;
    		//组合控制台输出信息字符串
    		BufferedReader br=new BufferedReader(xx);
    		while((inStr=br.readLine())!=null){
    			sb.append(inStr+"\r\n");
    			
    		}
    		outStr=sb.toString();//备份出来的内容是一个字条串
    		//要用来导入用的sql目标文件：
    		FileOutputStream fout=new FileOutputStream(filePath);
    		OutputStreamWriter writer=new OutputStreamWriter(fout,"utf8");
    		writer.write(outStr);//写文件
    		//注意：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
    		writer.flush();
    		//别忘记关闭输入输出流
    		in.close();
    		xx.close();
    		writer.close();
    		fout.close();
    		System.out.println("");
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    
    public String backupMySqlToFile(){
    	String outStr="";
    	try{
    		Runtime rt=Runtime.getRuntime();
    		Process child=rt.exec("D:\\Program Files\\Apache SoftwareFoundation\\xampp\\mysql\\bin\\mysqldump -uroot -p123 114la");
    		//设置导出编码为utf-8.这里必须是utf-8在此要注意，有时会发生一个mysqldump:
    		//Got error:1045的错误，此时mysqldump必须加上你要备份的数据库的IP地址，即
    		//mysqldump -h129.168.0.1 -uroot -pmysql dbname
    		//吧进程执行中的控制台输出信息写入sql文件，即生成了备份文件
    		//注意：如果不对控制台信息进行读出，则会导致进程阻塞无法运行
    		InputStream in=child.getInputStream();//控制台的输出信息作为输入流
    		InputStreamReader xx=new InputStreamReader(in,"utf8");//设置输出流编码为utf8，这里必须是utf8，否则从流中读入的是乱码
    		String inStr;
    		StringBuffer sb=new StringBuffer("");
    		//组合控制台输出信息字符串
    		BufferedReader br=new BufferedReader(xx);
    		while((inStr=br.readLine())!=null){
    			sb.append(inStr+"\r\n");
    			
    		}
    		outStr=sb.toString();
    		in.close();
    		xx.close();
    		br.close();
    		
    		
    		
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return outStr;
    }
    
    public void load(){
    	try{
    		String fPath="c:\\test.sql";
    		Runtime rt=Runtime.getRuntime();
    		Process child=rt.exec("D:\\Program Files\\Apache SoftwareFoundation\\xampp\\mysql\\bin\\mysql -uroot -p1234 114la");
    		OutputStream out =child.getOutputStream();//控制台的输入信息作为输出流
    		String inStr;
    		StringBuffer sb=new StringBuffer("");
    		String outStr;
    		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fPath),"utf8"));
    		while((inStr=br.readLine())!=null){
    			sb.append(inStr+"\r\n");
    		}
    		outStr=sb.toString();
    		OutputStreamWriter writer=new OutputStreamWriter(out,"utf8");
    		writer.write(outStr);
    		//注意：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方式则可以避免
    		writer.flush();
    		out.close();
    		br.close();
    		writer.close();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		
    	}
    }
    
    
    //当用用户恢复数据的时候，直接传一个上传的文件个这个方法，就可以对数据进行恢复了
    public void restoreMysqlFromFile(File f){
    	try{
    		FileInputStream fis=new FileInputStream(f);
    		Runtime rt=Runtime.getRuntime();
    		Process child=rt.exec("mysql -uroot -p1234 114la");//这里执行的mysql命令，用户名，密码以及要恢复的数据库，命令执行完后会从我们上传的文件里面读取要执行的内容
    		OutputStream out=child.getOutputStream();//控制台的输入信息作为输出流
    		String inStr;
    		StringBuffer sb=new StringBuffer("");
    		String outStr;
    		BufferedReader br=new BufferedReader(new InputStreamReader(fis,"utf8"));
    		while((inStr=br.readLine())!=null){
    			sb.append(inStr+"\r\n");
    			
    		}
    		outStr=sb.toString();
    		OutputStreamWriter writer=new OutputStreamWriter(out,"utf8");
    		writer.write(outStr);
    		//注意：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
    		writer.flush();
    		out.close();
    		br.close();
    		writer.close();
    		fis.close();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

}


