/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems;

import java.util.Random;

/**
 * @ClassName: StringUtills 说明： TODO(Tell the reader such role.)
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-25 下午9:38:56
 * 
 */
public class StringUtills {
    public String[] getGroupStringFromString(String base, String oneChar) {
	
	int count = getCharPopTimes(base,",");
	String[] group = new String[count+1];
	int begin = 0;
	int end=0;
	for(int i=0;i<count;i++){
	    end = base.indexOf(",", begin);
	    System.out.println("***"+end);
	    System.out.println("----"+begin);
	    group[i] = base.substring(begin, end);
	    begin += group[i].length()+1;
	}
	group[count] =  base.substring(begin);//最后一个字符，特殊处理
		
	return group;
    }

    public int getCharPopTimes(String base, String oneChar) {
	int cnt = 0, start = 0;
	while (start != base.length()) {
	    int i = base.indexOf(oneChar, start);
	    if (i != -1) {
		cnt++;
		start = i + 1;
	    } else{
		break;
	    }  
	}
	
	return cnt;
    }
    
    public int productIntegerAmong(int max,int min){
    	Random rand = new Random();
    	int randNumber = rand.nextInt(max - min + 1) + min;
    	return randNumber;
    }
    
    public static void main(String[] args){
	System.out.println("--"+new StringUtills().getCharPopTimes("213,1231,123", ","));
	System.out.println("====="+"213,1231,123".indexOf(",", 0));
//	System.out.println("+++++"+"csacas".substring(0, 2));
	String[] aa= new StringUtills().getGroupStringFromString("213,1231,123", ",");
	for(String a:aa){
	    System.out.println("@@@@@@@@@@@@@"+a);
	}
	
	
    }
}
