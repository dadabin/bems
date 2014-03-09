package cn.cdu.edu.TQC.bems.Algorithm;

import java.util.*;

/***
 * 移动平均法实现
 * 基于季节波动的时间序列模型
 */
public class TimeSerial {

	public TimeSerial() {

	}

	/*
	 * 对于一个基于季节波动的时间序列模型来说，每一个时间点由两个要素确定 
	 * 1>周期 ：由不同季节组成的一个单位
	 * 2>季节：最细的时间单位
	 * 一般在季节要素上有相对固定规律的波定性，在周期要素上有线性或其他的 发展趋势 比如我们的时间序列是一个关于半年的销售列表，
	 * 那么周期是年、一个周期
	 * 由上半年和下半年两个季节组成； 又比如时间序列是一个关于日的销售列表，并且在每个礼拜有周期性，那么
	 * 周期是周、一个周期由7个礼拜组成，这7个礼拜就是季节；
	 */

	/***************************************************************************
	 * Desc: The time serial model for forecast that based on the analyse of
	 * season waving Note:all kinds of time serial forecast can use this
	 * function,such as Month forecast,year forecast,half year forecast and even
	 * day forecast or hour forecast. 基于季节波动分析的时间序列模型预测 Inpute Parameter 
	 * @param
	 * ArrayList timeSerialList 时间序列样本 [PERIOD：周期;SEASON:季节;VALUE:序列值] 
	 * @param
	 * ArrayList seasonList 季节 [SEASON:季节；SEASON_INDEX:季节指数]返回季节指数 
	 * @param String
	 * aim_Period 预测目标周期 
	 * @param String aim_Season 预测目标季节 Outpute Parameter
	 * @return double res Logic
	 *  
	 **************************************************************************/
	public static double seasonTimeSerialForecast(ArrayList timeSerialList,
			ArrayList seasonList, String aim_Period, String aim_Season) {
		/*
		 * First step : check the parameters 
		 * 第一步：检测相关参数
		 */
		int season_len = seasonList.size();
		if (season_len <= 1) {
			System.out.println("the seasonlist is unnormal,please check it");
			return -1;
		}
		System.out.println("the season's length is-----"+season_len);

		int sample_size = timeSerialList.size();
		if (sample_size < 2 * season_len) {
			System.out.println("the sample number is too small,please check it");
			return -1;
		}
		System.out.println("the sample's length is-----"+sample_size);


		/*
		 * Second step: move average process -- two step process
		 * 第二步：移动平均处理,做两阶段移动平均
		 */
		moveAvg(timeSerialList, season_len, 2);

		/*
		 * Third step: get the season index --season index described the season
		 * waving degree, if there have no season trend ,all season's season
		 * index was close to 1.0 
		 * 第三步：求季节指数
		 */
		getSeasonIndex(timeSerialList, seasonList);

		/*
		 * Fourth step : compute the period develop index k which comes from a
		 * that was the front n entries of sample's average of the last sample
		 * and b that was the same season sample's average of the last sample,
		 * the k will made the last sample's move average value = b + k*(a-b)
		 * 
		 * 第四步：求周期趋势系数 根据时间序列最后一个样本的前season_len个样本的平均值a 和同季节的平均值b，获得最佳趋势系数k，使得
		 * MOVE_VALUE = b+k×（a-b)
		 */
		Object Season1 = ((HashMap)timeSerialList.get(sample_size - 1)).get("SEASON");
		System.out.println("\n the last season is--"+Season1.toString());
		//同季节平均
		double same_season_move = getSameSeasonMoveAvg(timeSerialList, Season1,season_len);
		System.out.println("\n the same season is--"+same_season_move);

		double[] lastlist = new double[season_len];
		for (int j = 0; j < season_len; j++) {
			lastlist[j] = Double.parseDouble(((HashMap) timeSerialList
					.get(sample_size - 2 - j)).get("MOVE_VALUE").toString());
		}
		//上期平均
		double last_move = Base.avg(lastlist);
		System.out.println("The Last season is ----"+ last_move);
		double coeff = 0.0;
		if (last_move != same_season_move) {
			System.out.println("The last move avg is --"+Double.parseDouble(((HashMap) timeSerialList
					.get(sample_size - 1)).get("MOVE_VALUE").toString()));
			coeff = (Double.parseDouble(((HashMap) timeSerialList
					.get(sample_size - 1)).get("MOVE_VALUE").toString()) - same_season_move)
					/ (last_move - same_season_move);
		}
		System.out.println("The develop coeff is ---"+coeff);

		/*
		 * Fifth step: compute the forecast value without consider the season
		 * wave Using the coeff that previous step get and the sample's average
		 * vale that have the same season to the forecast time. 
		 * 第五步：
		 * 求预测季节的同季节的平均值，并利用上述公式，算出除季节因素外的预测值
		 */
		double aim_same = getSameSeasonMoveAvg(timeSerialList, aim_Season,season_len);
		System.out.println("The aim same  is ---"+aim_same);
		
		lastlist = new double[season_len];
		for (int j = 0; j < season_len; j++) {
			lastlist[j] = Double.parseDouble(((HashMap) timeSerialList
					.get(sample_size - 1 - j)).get("MOVE_VALUE").toString());
		}
		//上期平均
		last_move = Base.avg(lastlist);
		System.out.println("the forecast last move is --"+last_move);
		double forecast = aim_same + coeff * (last_move - aim_same);
		System.out.println("The forecast result without season elements is --"+forecast);

		/*
		 * Sixth step : get the season index from SeasonList of the forecast
		 * time and get the last forecast value. 
		 * 第六步：
		 * 从SeasonList中找到被预测季节的季节指数，复原预测值
		 */
		for (int i = 0; i < season_len; i++) {
			if (((HashMap) seasonList.get(i)).get("SEASON")
					.equals(aim_Season)) {
				double sea_ind = Double.parseDouble(((HashMap) seasonList
						.get(i)).get("SEASON_INDEX").toString());
				System.out.println("The season index is --"+sea_ind);
				forecast *= sea_ind;
			}

		}
		return forecast;
	}

	/*
	 * Desc: Move average process for a time serial Note: if a time serial has
	 * no season trend or the analyser have no idea of whether the serial has
	 * season trend or not ,then can user the season_len as 1.
	 * 时间序列移动平均 
	 * Input Parameter 
	 * @param timeSerialList 时间序列样本
	 * @param season_len 季度长度（一个周期内季节的个数）
	 * @param rank 移动平均的阶数 Outpute Parameter 
	 * @return void
	 */
	public static void moveAvg(ArrayList timeSerialList, int season_len,
			int rank) {
		if (season_len < 1)
			season_len = 1;
		/*
		 * 第一步：根据season_len对序列做初处理
		 */
		int sample_len = timeSerialList.size();

		double[] serial_value = new double[sample_len];
		Object obj_temp = new Object();
		HashMap tab_temp = new HashMap();

		for (int i = 0; i < sample_len; i++) {
			obj_temp = timeSerialList.get(i);
			tab_temp = (HashMap) obj_temp;
			serial_value[i] = Double.parseDouble(tab_temp.get("VALUE")
					.toString());
			System.out.println("The sample--"+i+" value is --"+serial_value[i]);
		}

		/*
		 * 如果season_len>1，第一阶移动平均是 每一个值跟前season_len－1个值求平均
		 */
		if (season_len > 1) {

			for (int j = sample_len - 1; j >= 0; j--) {
				if (j >= season_len - 1) {
					double d_temp = 0;
					for (int k = 0; k < season_len; k++) {
						d_temp += serial_value[j - k];
					}
					System.out.println("\n the first rank moveavg--"+j+"first n sample sum is --"+d_temp);
					d_temp /= season_len;
					serial_value[j] = d_temp;
					System.out.println("\n the first rank moveavg--"+j+"deal result is --"+serial_value[j]);
				}
			}
			rank -= 1;
		}

		/*
		 * 如果移动平均的阶数>1，那么从最后往前，每一个值等于自己跟前一个值的平均 这样做rank-1次
		 */
		if (rank >= 1) {
			for (int times = rank; times > 0; times--) {
				for (int i = sample_len - 1; i > 0; i--) {
					if (i >= 1) {
						serial_value[i] = (serial_value[i] + serial_value[i - 1]) / 2;
						System.out.println("\n the move rank"+times+" sample--"+i+"deal result is --"+serial_value[i]);
					}
				}

			}
		}

		/*
		 * 把移动平均结果更新到timeSerials中去
		 */
		for (int i = 0; i < sample_len; i++) {
			obj_temp = timeSerialList.get(i);
			tab_temp = (HashMap) obj_temp;
			tab_temp.put("MOVE_VALUE", String.valueOf(serial_value[i]));
			timeSerialList.set(i, tab_temp);
		}

	}

	/*
	 * Desc: compute the season index for a time serial Note: this method will
	 * put the result direct to the inpute parameter of seasonList 
	 * 获得时间序列的季节指数
	 * Inpute Parameter 
	 * @param timeSerialList 移动平均处理后的时间序列
	 * @param seasonList 季节序列
	 * 
	 * Outpute Parameter 
	 * @return void
	 */
	public static void getSeasonIndex(ArrayList timeSerialList,
			ArrayList seasonList) {
		int season_len = seasonList.size();

		if (season_len <= 0) {
			return;
		} else if (season_len == 1) { 
		    //如果一个周期只有一个季节，那么季节指数为1
			HashMap season = (HashMap) seasonList.get(0);
			season.put("SEASON_INDEX", String.valueOf(1.0));
			return;
		}

		/*
		 * 循环每一个季节处理，计算其季节指数
		 */
		for (int i = 0; i < season_len; i++) {
			HashMap season_ha = (HashMap) seasonList.get(i);
			String Season = (season_ha.get("SEASON")).toString();
			System.out.println("The ana season is --"+Season.toString());
			int sample_num = 0;
			Object sample_tmp = new Object();
			String season_tmp = new String();
			double[][] data = new double[3][season_len];
			for (int j =  timeSerialList.size()-1; j >season_len; j--) {
				sample_tmp = timeSerialList.get(j);
				season_tmp = ((HashMap) sample_tmp).get("SEASON").toString();
				System.out.println("the --"+j+"---sample's season is --"+season_tmp.toString());
				if (season_tmp.equals(Season)) {
					data[0][sample_num] = Double.parseDouble(((HashMap) sample_tmp).get("VALUE")
									.toString());
					System.out.println("\n the sample value is --"+data[0][sample_num]);
					data[1][sample_num] = Double
							.parseDouble(((HashMap) sample_tmp).get(
									"MOVE_VALUE").toString());
					if (data[1][sample_num] != 0) {
						data[2][sample_num] = data[0][sample_num] / data[1][sample_num];
					} 
					else{
						data[2][sample_num] = 1.0;
					}

					System.out.println("\n the season index sample is --"+data[2][sample_num]);
					sample_num += 1;

				}

			}
			double season_index = 1.0;
			//如果一个符合条件的sample都没有
			if (sample_num > 0) {
				double[] coeff = new double[sample_num];
				for (int k=0;k<sample_num ;k++)
				{
					coeff[k]= data[2][k];
				}
				season_index = Base.avg(coeff);
			}
			System.out.println("the sample num is --"+sample_num);
			season_ha.put("SEASON_INDEX", String.valueOf(season_index));
			System.out.println("\n THe season_index is --"+season_index);
			seasonList.set(i, season_ha);
		}

	}

	/*
	 * Desc: get the average value of sample that has the same season
	 * 获得某季节相同季节的移动平均值 Inpute Parameter @param ArrayList timeSerialList, @param
	 * Object Season Outpute Parameter @return double avgvalue Logic
	 *  
	 */
	public static double getSameSeasonMoveAvg(ArrayList timeSerialList,
			Object Season,int season_len) {
		double[] data = new double[timeSerialList.size()];
		System.out.println("\n the season is "+Season.toString());
		int len = 0;
		for (int i = timeSerialList.size()-2; i > season_len-1; i--) {
			System.out.println("\n ..."+(((HashMap) timeSerialList.get(i)).get("SEASON")).toString());
			if ((((HashMap) timeSerialList.get(i)).get("SEASON")).toString()
					.equals(Season.toString())) {
				data[len] = Double
						.parseDouble(((HashMap) timeSerialList.get(i)).get(
								"MOVE_VALUE").toString());
	        len+=1;
			}
		}
		if(len!=0){
			double[] sampsame = new double[len];
			for(int j=0;j<len;j++)
				sampsame[j]=data[j];
			return Base.avg(sampsame);
		}else{
		
		return 1.0/0.0;
		
		}
		
		
	}

}