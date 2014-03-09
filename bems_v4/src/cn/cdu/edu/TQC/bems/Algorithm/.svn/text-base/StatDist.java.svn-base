package cn.cdu.edu.TQC.bems.Algorithm;

import java.util.*;
import java.lang.*;


public class StatDist {
	public StatDist() {

	}
	/*
	*定义常量E
	*/
	public static final double E = 2.7182818284590452354;

	/*
	*定义常量PI
	*/
	public static final double PI = 3.14159265358979323846;

	/*
	 * Desc: The probability density function
	*正态分布某点的概率密度
	*输入参数：
	*     @param double avg 正态分布的数学期望
	*     @param double stddev 正态分布的标准方差
	*     @param double point 检测点
	*输出参数：
	*/
	public static double norminc(double point, double avg, double stddev) {
		if (stddev == 0) {
			return 1;
		} else {
			double a = (point - avg) / stddev;
			a = a * a / 2.0;
			return Math.exp(-a) / Math.sqrt(2.0 * PI * stddev);
		}
	}

	/*
	*正态分布函数反函数，根据分布概率值获得区间点，本质是解积分方程，
	*注：任何一个正态分布函数的反函数等于avg+stddev * stdnorminv(double x),
	*avg为平均值，
	*stddev 为方差，
	*stdnorminv(double x)为标准正态分布函数的反函数，
	*其对照值写死在本函数中，本函数只精确到小数点后两位，穷举了100个点
	*/
	public static double norminv(double integral, double avg, double stddev) {
		/*
		*如果分布值是0.5,那么分布点肯定是avg，本函数只精确到小数点后2位
		*/
		double POSITIVE_INFINITY = 1.0 / 0.0;
		double NEGATIVE_INFINITY = -1.0 / 0.0;
		double point;
		//根据3倍偏差原则，如果为0，则返回avg-3*stddev,如果为1，则返回avg+3*stddev
		if (integral == 0) {
			point = avg - 3 * stddev;
			//System.out.println("The parameter is unormal ");
		} else if (integral == 1) {
			point = avg + 3 * stddev;
			//System.out.println("The parameter is unormal ");
		} else {
			double stdpoint = 0;
			Double temp = new Double(integral * 100);
			int iswith = temp.intValue();
			switch (iswith) {
				case 1 :
					stdpoint = -2.326347000;
					break;
				case 2 :
					stdpoint = -2.053747519;
					break;
				case 3 :
					stdpoint = -1.880792650;
					break;
				case 4 :
					stdpoint = -1.750685567;
					break;
				case 5 :
					stdpoint = -1.644853476;
					break;
				case 6 :
					stdpoint = -1.554773691;
					break;
				case 7 :
					stdpoint = -1.475791283;
					break;
				case 8 :
					stdpoint = -1.405071903;
					break;
				case 9 :
					stdpoint = -1.340755412;
					break;
				case 10 :
					stdpoint = -1.281551939;
					break;
				case 11 :
					stdpoint = -1.226528461;
					break;
				case 12 :
					stdpoint = -1.174987081;
					break;
				case 13 :
					stdpoint = -1.126391354;
					break;
				case 14 :
					stdpoint = -1.080319496;
					break;
				case 15 :
					stdpoint = -1.036433474;
					break;
				case 16 :
					stdpoint = -0.994457898;
					break;
				case 17 :
					stdpoint = -0.954165204;
					break;
				case 18 :
					stdpoint = -0.915364982;
					break;
				case 19 :
					stdpoint = -0.877896141;
					break;
				case 20 :
					stdpoint = -0.841621042;
					break;
				case 21 :
					stdpoint = -0.806421028;
					break;
				case 22 :
					stdpoint = -0.772192978;
					break;
				case 23 :
					stdpoint = -0.738846607;
					break;
				case 24 :
					stdpoint = -0.706302325;
					break;
				case 25 :
					stdpoint = -0.674489526;
					break;
				case 26 :
					stdpoint = -0.643345203;
					break;
				case 27 :
					stdpoint = -0.612812818;
					break;
				case 28 :
					stdpoint = -0.58284137;
					break;
				case 29 :
					stdpoint = -0.553384622;
					break;
				case 30 :
					stdpoint = -0.524400458;
					break;
				case 31 :
					stdpoint = -0.495850336;
					break;
				case 32 :
					stdpoint = -0.467698831;
					break;
				case 33 :
					stdpoint = -0.439913238;
					break;
				case 34 :
					stdpoint = -0.412463237;
					break;
				case 35 :
					stdpoint = -0.385320604;
					break;
				case 36 :
					stdpoint = -0.358458952;
					break;
				case 37 :
					stdpoint = -0.331853516;
					break;
				case 38 :
					stdpoint = -0.305480959;
					break;
				case 39 :
					stdpoint = -0.279319195;
					break;
				case 40 :
					stdpoint = -0.253347241;
					break;

				case 41 :
					stdpoint = -0.227545081;
					break;
				case 42 :
					stdpoint = -0.20189354;
					break;
				case 43 :
					stdpoint = -0.176374174;
					break;
				case 44 :
					stdpoint = -0.150969169;
					break;
				case 45 :
					stdpoint = -0.125661246;
					break;
				case 46 :
					stdpoint = -0.100433574;
					break;
				case 47 :
					stdpoint = -0.075269689;
					break;
				case 48 :
					stdpoint = -0.050153414;
					break;
				case 49 :
					stdpoint = -0.02506879;
					break;
				case 50 :
					stdpoint = 0.00000000;
					break;
				case 51 :
					stdpoint = 0.02506879;
					break;
				case 52 :
					stdpoint = 0.050153414;
					break;
				case 53 :
					stdpoint = 0.075269689;
					break;
				case 54 :
					stdpoint = 0.100433574;
					break;
				case 55 :
					stdpoint = 0.125661246;
					break;
				case 56 :
					stdpoint = 0.150969169;
					break;
				case 57 :
					stdpoint = 0.176374174;
					break;
				case 58 :
					stdpoint = 0.20189354;
					break;
				case 59 :
					stdpoint = 0.227545081;
					break;
				case 60 :
					stdpoint = 0.253347241;
					break;
				case 61 :
					stdpoint = 0.279319195;
					break;
				case 62 :
					stdpoint = 0.305480959;
					break;
				case 63 :
					stdpoint = 0.331853516;
					break;
				case 64 :
					stdpoint = 0.358458952;
					break;
				case 65 :
					stdpoint = 0.385320604;
					break;
				case 66 :
					stdpoint = 0.412463237;
					break;
				case 67 :
					stdpoint = 0.439913238;
					break;
				case 68 :
					stdpoint = 0.467698831;
					break;
				case 69 :
					stdpoint = 0.495850336;
					break;
				case 70 :
					stdpoint = 0.524400458;
					break;
				case 80 :
					stdpoint = 0.841621042;
					break;
				case 79 :
					stdpoint = 0.806421028;
					break;
				case 78 :
					stdpoint = 0.772192978;
					break;
				case 77 :
					stdpoint = 0.738846607;
					break;
				case 76 :
					stdpoint = 0.706302325;
					break;
				case 75 :
					stdpoint = 0.674489526;
					break;
				case 74 :
					stdpoint = 0.643345203;
					break;
				case 73 :
					stdpoint = 0.612812818;
					break;
				case 72 :
					stdpoint = 0.58284137;
					break;
				case 71 :
					stdpoint = 0.553384622;
					break;
				case 90 :
					stdpoint = 1.281551939;
					break;
				case 89 :
					stdpoint = 1.226528461;
					break;
				case 88 :
					stdpoint = 1.174987081;
					break;
				case 87 :
					stdpoint = 1.126391354;
					break;
				case 86 :
					stdpoint = 1.080319496;
					break;
				case 85 :
					stdpoint = 1.036433474;
					break;
				case 84 :
					stdpoint = 0.994457898;
					break;
				case 83 :
					stdpoint = 0.954165204;
					break;
				case 82 :
					stdpoint = 0.915364982;
					break;
				case 81 :
					stdpoint = 0.877896141;
					break;
				case 99 :
					stdpoint = 2.326347000;
					break;
				case 98 :
					stdpoint = 2.053747519;
					break;
				case 97 :
					stdpoint = 1.880792650;
					break;
				case 96 :
					stdpoint = 1.750685567;
					break;
				case 95 :
					stdpoint = 1.644853476;
					break;
				case 94 :
					stdpoint = 1.554773691;
					break;
				case 93 :
					stdpoint = 1.475791283;
					break;
				case 92 :
					stdpoint = 1.405071903;
					break;
				case 91 :
					stdpoint = 1.340755412;
					break;
			}
			point = avg + stddev * stdpoint;
		}
		return point;
	}

	public static void main(String[] args) 
	{  
		ArrayList seasonSample = new ArrayList();
		HashMap temp = new HashMap();
		temp.put("PERIOD","2002");
		temp.put("SEASON","Y1");
		temp.put("VALUE","150");
		seasonSample.add(0,temp);
		temp = new HashMap();
		temp.put("PERIOD","2002");
		temp.put("SEASON","Y2");
		temp.put("VALUE","140");
		seasonSample.add(1,temp);
		temp = new HashMap();
		temp.put("PERIOD","2003");
		temp.put("SEASON","Y1");
		temp.put("VALUE","160");
		seasonSample.add(2,temp);
		temp = new HashMap();
		temp.put("PERIOD","2003");
		temp.put("SEASON","Y2");
		temp.put("VALUE","150");
		seasonSample.add(3,temp);
		temp = new HashMap();
		temp.put("PERIOD","2004");
		temp.put("SEASON","Y1");
		temp.put("VALUE","170");
		seasonSample.add(4,temp);

		ArrayList SeasonList = new ArrayList();
		temp = new HashMap();
		temp.put("SEASON","Y1");
		temp.put("SEASON_INDEX","1");
		SeasonList.add(0,temp);
		temp = new HashMap();
		temp.put("SEASON","Y2");
		temp.put("SEASON_INDEX","1");
		SeasonList.add(1,temp);
		

		double res = TimeSerial.seasonTimeSerialForecast(seasonSample,SeasonList,"2004","Y2");
		System.out.println("THe forecast result is=========="+res);
		double[]  test = new double[5];
		test[0]=3.0;
		test[1]=3.0;
		res = Base.min(test);
		System.out.println("The res is ---"+res);
	}



}
