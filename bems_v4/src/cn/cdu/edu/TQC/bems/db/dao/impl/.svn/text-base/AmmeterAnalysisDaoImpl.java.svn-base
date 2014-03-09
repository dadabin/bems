/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.db.dao.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cdu.edu.TQC.bems.Analasis;
import cn.cdu.edu.TQC.bems.Utils;
import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.Device;
import cn.cdu.edu.TQC.bems.db.dao.AmmeterDao;

/**
 * @ClassName: AmmeterAnalysisDaoImpl 说明： TODO(Tell the reader such role.)
 * @Author SUN 【email:1096490965@qq.com】
 * @Version V1.0 2012-7-13 下午4:40:56
 * 
 */

public class AmmeterAnalysisDaoImpl {
	/**
	 * sun 2012-7-14
	 * 
	 * （1） 楼层间相同电路的对比分析
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param type
	 *            电路类型
	 * @param floorIds
	 *            楼层编号数组
	 */
	public List<Device> analysisFloorAndType(String startTime, String endTime,
			String type, String[] floorIds) {
		List<Device> devices = new ArrayList<Device>();
		// 循环楼层根据楼层和电路类型 得到
		for (String floorId : floorIds) {
			List<HashMap<String, Object>> list = electricityByFloorIdAndType(
					startTime, endTime, floorId, type);
			// System.out.println(list);
			devices.add(listToDivice(list, "楼层" + floorId));
		}

		return devices;

	}

	/**
	 * (2)楼层间电路总量的比较
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param floorIds
	 *            楼层数组
	 */
	public List<Device> analysisFloorAll(String startTime, String endTime,
			String[] floorIds) {
		List<Device> devices = new ArrayList<Device>();
		// 循环楼层得到楼层总用电量
		for (String floorId : floorIds) {

			List<HashMap<String, Object>> list = floorsOfElectricityConsumption(
					startTime, endTime, floorId);
			// System.out.println(list);
			devices.add(listToDivice(list, "楼层" + floorId));
		}
		return devices;

	}

	/**
	 * sun 2012-7-14
	 * 
	 * (3) 整栋楼不同电路用电量的比较
	 * 
	 * @param startTime
	 * @param endTime
	 * @param type
	 */
	public List<Device> analysisBuildingType(String startTime, String endTime,
			String[] types) {
		List<Device> devices = new ArrayList<Device>();
		// 循环电路类型
		for (String type : types) {
			List<HashMap<String, Object>> list = buildingElectricityConsumption(
					startTime, endTime, type);
			// System.out.println(list);
			devices.add(listToDivice(list, type));
		}
		return devices;

	}

	/**
	 * （4）、楼层内不同电路的比较
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            终止时间
	 * @param floorId
	 *            楼层ID
	 * @param types
	 *            电路类型
	 * 
	 */

	public List<Device> analysisType(String startTime, String endTime,
			String floorId, String[] types) {
		List<Device> devices = new ArrayList<Device>();
		// 循环不同类型
		for (String type : types) {
			List<HashMap<String, Object>> list = electricityByFloorIdAndType(
					startTime, endTime, floorId, type);

			devices.add(listToDivice(list, type));
		}
		return devices;
	}

	// ====================================一下为封装得到List<HashMap<String,Object>>======

	/**
	 * sun 2012-7-14
	 * 
	 * 1.整栋楼的一类电路的用电量
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param type
	 *            电路类型
	 * @return
	 */
	private List<HashMap<String, Object>> buildingElectricityConsumption(
			String startTime, String endTime, String type) {
		// String sql =
		// "select * from ammeter_data d1 where ammeterid='20120001' order by time asc  ";
		String sql = "select a.CIRCUITTYPE,SUM(d.`DATA`) DATA,d.TIME from ammeter a ,ammeter_data d   where  d.TIME between '"
				+ startTime
				+ "' and '"
				+ endTime
				+ "' and  a.ammeterid=d.ammeterid and a.CIRCUITTYPE='"
				+ type
				+ "' group by d.TIME";
		System.out.println("sql:"+sql);
		DBManager db = new DBManager();
		List<HashMap<String, Object>> list = db.ExecuteQuery(sql);
		// less(list,);
		// System.out.println("||"+less(list));
		return less(list);
	}

	/**
	 * sun 2012-7-14
	 * 
	 * 2.楼层间总的用电量 ：根据楼层的ID号得到 用电趋势
	 * 
	 * @param startTime
	 * @param endTime
	 * @param floorId
	 * @return
	 */
	private List<HashMap<String, Object>> floorsOfElectricityConsumption(
			String startTime, String endTime, String floorId) {
		// String sql =
		// "select sum(`DATA`) as data,ad.TIME,a.FLOORID from ammeter_data ad,ammeter a where  and  a.ammeterid=ad.ammeterid and a.FLOORNUM ="
		// + floorId +
		// "  and  ad.TIME BETWEEN '"+startTime+"' AND '"+endTime+"'    group by ad.TIME";
		String sql = "select sum( d.`DATA`) as DATA ,d.TIME ,f.FLOORID,f.GRAPHICS,f.INTRO from ammeter a,ammeter_data d,floor f where f.FLOORID=a.FLOORID and f.FLOORNUM="
				+ floorId
				+ " and a.AMMETERID=d.AMMETERID and d.TIME BETWEEN '"
				+ startTime + "' AND '" + endTime + "'   group by d.TIME";
		DBManager db = new DBManager();
		List<HashMap<String, Object>> list = db.ExecuteQuery(sql);
		System.out.println(list);
		return less(list);
	}

	/**
	 * sun 2012-7-14
	 * 
	 * 3.根据楼层id和电路的类型得到用电趋势图
	 * 
	 * @param startTime
	 * @param endTime
	 * @param floorId
	 * @param type
	 * @return
	 */

	private List<HashMap<String, Object>> electricityByFloorIdAndType(
			String startTime, String endTime, String floorId, String type) {
		// sql="select f.FLOORNUM,f.FLOORID,f.INTRO,a.CIRCUITTYPE,a.AMMETERID,a.TYPE,d.`DATA`,d.TIME  from ammeter a,ammeter_data d,floor f  where f.FLOORID=a.FLOORID and a.AMMETERID=d.AMMETERID and a.CIRCUITTYPE='"+type+"' and f.FLOORNUM="+floorId+"  and  d.TIME between '"+startTime+"' and '"+endTime+"' order by d.TIME asc";
		String sql = "select f.FLOORNUM,f.FLOORID,f.INTRO,a.CIRCUITTYPE,a.AMMETERID,a.TYPE,d.`DATA`,d.TIME  from ammeter "
				+ "a,ammeter_data d,floor f  where f.FLOORID=a.FLOORID "
				+ "and a.AMMETERID=d.AMMETERID and a.CIRCUITTYPE='"
				+ type
				+ "' and f.FLOORNUM="
				+ floorId
				+ "  "
				+ " and  d.TIME BETWEEN '"
				+ startTime
				+ "' AND '"
				+ endTime
				+ "'  order by d.TIME asc";

		// System.out.println(sql);
		DBManager db = new DBManager();
		List<HashMap<String, Object>> list = db.ExecuteQuery(sql);
		return less(list);
	}

	// ===========================================下面为封装的方法

	/**
	 * sun 2012-7-14
	 * 
	 * 将List<HashMap<String,Object>> 转换成Device对象
	 * 
	 * @param list
	 * @param name
	 * @return
	 */
	private Device listToDivice(List<HashMap<String, Object>> list, String name) {

		Utils utils = new Utils();

		if (list != null) {
			Double[] data = new Double[+list.size()];
			Date[] times = new Date[+list.size()];
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).get("DATA"));
				System.out.println(list.get(i).get("endTime"));
				data[i] = (Double) list.get(i).get("DATA");
				times[i] = utils.stringToDateTwo(list.get(i).get("endTime")
						.toString());
				System.out.println(data[i]);
			}
			Device d = new Device();
			d.setData(data);
			d.setTimes(times);
			d.setName(name);
			return d;

		} else {
			return null;
		}

	}

	/**
	 * sun 2012-7-14
	 * 
	 * 下一行减去上一行
	 * 
	 * @param list
	 */
	private List<HashMap<String, Object>> less(
			List<HashMap<String, Object>> list) {
		// System.out.println(list);
		List<HashMap<String, Object>> reList = new ArrayList<HashMap<String, Object>>();
		if (list.size() >= 0) {
			// list.get(0).size();
			System.out.println(list.size());
			for (int i = 0; i < list.size() - 1; i++) {
				HashMap<String, Object> h = new HashMap<String, Object>();
				h.put("startTime", list.get(i).get("TIME"));
				h.put("endTime", list.get(i + 1).get("TIME"));
				h.put("DATA",
						Double.parseDouble(list.get(i + 1).get("DATA")
								.toString())
								- Double.parseDouble(list.get(i).get("DATA")
										.toString()));
				reList.add(h);
			}
		}
		return reList;
	}

	/**
	 * sun 2012-7-15 最后一行减去第一行
	 * 
	 * @param list
	 * @return
	 */
	private List<HashMap<String, Object>> lessLast(
			List<HashMap<String, Object>> list) {
		List<HashMap<String, Object>> reList = new ArrayList<HashMap<String, Object>>();
		if (list.size() > 0) {
			// list.get(0).size();
			// System.out.println(list.size());
			{
				HashMap<String, Object> h = new HashMap<String, Object>();
				h.put("startTime", list.get(0).get("TIME"));
				h.put("endTime", list.get(list.size() - 1).get("TIME"));
				h.put("DATA",
						Double.parseDouble(list.get(list.size() - 1)
								.get("DATA").toString())
								- Double.parseDouble(list.get(0).get("DATA")
										.toString()));
				reList.add(h);
			}
		}
		return reList;

	}

	// =============================================通用分析方法=====
	/**
	 * 
	 * 
	 * @param startTime
	 * @param endTime
	 * @param timeType
	 * @param ammeterId
	 * @return
	 * 
	 *         Y：年 M：月 Q：季度 D：天
	 * 
	 */
	public List<HashMap<String, Object>> select(String startTime,
			String endTime, char timeType, String ammeterId) {

		String sql = "select AMMETERDATAID,DATA,TIME,AMMETERID,YEAR(TIME) as Y,MONTH(TIME) as M,DAY(TIME) as D from ammeter_data where AMMETERID='"
				+ ammeterId + "'  ";
		Utils u = new Utils();
		HashMap<String, String> starttime = new HashMap<String, String>();
		HashMap<String, String> endtime = new HashMap<String, String>();
		starttime = u.getStringDate(u.stringToDateTwo(startTime));
		endtime = u.getStringDate(u.stringToDateTwo(endTime));
		DBManager db = new DBManager();
		List<HashMap<String, Object>> list = null;
		List<HashMap<String, Object>> reList = null;
		switch (timeType) {
		case 'Y':
			sql += " and TIME between " + startTime + " and  " + endTime
					+ "  order by TIME asc";
			list = db.ExecuteQuery(sql);
			reList = lessByTypeY("Y", list);
			break;
		case 'Q':

			sql += " and TIME between " + starttime.get("year") + " and  "
					+ endtime.get("year") + "  order by TIME asc";
			System.out.println(sql);
			list = db.ExecuteQuery(sql);
			reList = lessByTypeQ("M", list);

			break;
		case 'M':
			sql += " and TIME between " + startTime + " and  " + endTime
					+ "  order by TIME asc";
			System.out.println(sql);
			list = db.ExecuteQuery(sql);
			reList = lessByTypeM("M", list);
			break;
		default:
			break;
		}
		return reList;

	}

	/**
	 * 按季度查询
	 * 
	 * @param typeStr
	 * @param list
	 * @return
	 */
	public List<HashMap<String, Object>> lessByTypeQ(String typeStr,
			List<HashMap<String, Object>> list) {
		List<HashMap<String, Object>> reList = new ArrayList<HashMap<String, Object>>();
		String time = null;
		double upData = -1;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (upData == -1 && time == null) {// 如果upData=-1则给upData赋值
					upData = Double.parseDouble(list.get(i).get("DATA")
							.toString());
					time = list.get(i).get(typeStr).toString();
				}
				// System.out.println(upData);
				if (!time.equals(list.get(i).get(typeStr).toString())
						|| (i == list.size() - 1)) {
					// System.out.println(list.get(i).get(typeStr).toString());
					double d = Double.parseDouble(list.get(i).get("DATA")
							.toString())
							- upData;
					HashMap<String, Object> h = new HashMap<String, Object>();
					h.put("startTime", time);
					h.put("endTime", list.get(i).get("TIME"));
					h.put("data", d);
					Utils u = new Utils();
					HashMap<String, String> t = u
							.getStringDate(u.stringToDateTwo(list.get(i)
									.get("TIME").toString()));
					h.put("Y", t.get("year"));
					reList.add(h);
					upData = Double.parseDouble(list.get(i).get("DATA")
							.toString());
					time = list.get(i).get(typeStr).toString();
				}
			}
		}
		// System.out.println(")))))"+reList);
		return reList;
	}

	/**
	 * 按年查询
	 * @param typeStr
	 * @param list
	 * @return
	 */
	public List<HashMap<String, Object>> lessByTypeY(String typeStr,
			List<HashMap<String, Object>> list) {
		List<HashMap<String, Object>> reList = new ArrayList<HashMap<String, Object>>();
		String time = null;
		double upData = -1;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (upData == -1 && time == null) {// 如果upData=-1则给upData赋值
					upData = Double.parseDouble(list.get(i).get("DATA")
							.toString());
					time = list.get(i).get(typeStr).toString();
				}
				// System.out.println(upData);
				if (!time.equals(list.get(i).get(typeStr).toString())
						|| (i == list.size() - 1)) {
					// System.out.println(list.get(i).get(typeStr).toString());
					double d = Double.parseDouble(list.get(i).get("DATA")
							.toString())
							- upData;
					HashMap<String, Object> h = new HashMap<String, Object>();
					h.put("startTime", time);
					h.put("endTime", list.get(i).get("TIME"));
					h.put("data", d);
					Utils u = new Utils();
					HashMap<String, String> t = u
							.getStringDate(u.stringToDateTwo(list.get(i)
									.get("TIME").toString()));
					h.put("Y", t.get("year"));
					reList.add(h);
					upData = Double.parseDouble(list.get(i).get("DATA")
							.toString());
					time = list.get(i).get(typeStr).toString();
				}
			}
		}

		// System.out.println(")))))"+reList);

		return reList;
	}

	/**
	 * 按月份查询
	 * 
	 * @param typeStr
	 * @param list
	 * @return
	 */
	public List<HashMap<String, Object>> lessByTypeM(String typeStr,
			List<HashMap<String, Object>> list) {
		List<HashMap<String, Object>> reList = new ArrayList<HashMap<String, Object>>();
		String time = null;
		double upData = -1;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (upData == -1 && time == null) {// 如果upData=-1则给upData赋值
					upData = Double.parseDouble(list.get(i).get("DATA")
							.toString());
					time = list.get(i).get(typeStr).toString();
				}
				// System.out.println(upData);
				if (!time.equals(list.get(i).get(typeStr).toString())
						|| (i == list.size() - 1)) {
					// System.out.println(list.get(i).get(typeStr).toString());
					double d = Double.parseDouble(list.get(i).get("DATA")
							.toString())
							- upData;
					HashMap<String, Object> h = new HashMap<String, Object>();
					h.put("startTime", time);
					h.put("endTime", list.get(i).get("TIME"));
					h.put("data", d);
					Utils u = new Utils();
					HashMap<String, String> t = u
							.getStringDate(u.stringToDateTwo(list.get(i)
									.get("TIME").toString()));
					h.put("M", t.get("mouth"));
					reList.add(h);
					upData = Double.parseDouble(list.get(i).get("DATA")
							.toString());
					time = list.get(i).get(typeStr).toString();
				}
			}

		}
		System.out.println(")))))" + reList);

		return reList;
	}
	
	///////////////////////////////====最终封装-=======提取
//	public static void main(String[] args) {
//		AmmeterAnalysisDaoImpl a = new AmmeterAnalysisDaoImpl();
//		String ammeterId="20120020";
//		List<HashMap<String, Object>> list= a .selectByYear( "2013", "20120020");
//		HashMap<String,double[]> m=new HashMap<String,double[]>();
//		double[] data=new double[12];
//		int i=0;
//		for(Map map:list){
//			data[i]=Double.parseDouble(map.get("data").toString());
//			i++;
//		}
//		m.put(ammeterId, data);
//		System.out.println(data[0]);
//	}
	//========================================================
	/**
	 * 得到一个仪表一年中每一月用到的电量
	 * @param year
	 * @param ammeterId
	 * @return
	 */
	public HashMap<String,double[]> getMonthByAmmeterIdAndYear(String year,String ammeterId){
		List<HashMap<String, Object>> list=selectByYear( year, ammeterId);
		Analasis a=new Analasis();
		List<Double> datas=a.getDeviceByMonth("ammeter",ammeterId,Integer.parseInt(year));
		HashMap<String,double[]> m=new HashMap<String,double[]>();
		double[] data=new double[12];
		int i=0;
		for(double d:datas){
			data[i]=d;
			i++;
		}
		m.put(ammeterId, data);
		return m;
	}
	/**
	 * 根据电表ID得到季度用量
	 * @param year
	 * @param ammeterId
	 * @return
	 */
	public HashMap<String,double[]> getQByAmmeterIdAndYear(String year,String ammeterId){
		HashMap<String,double[]> hashMap=getMonthByAmmeterIdAndYear( year, ammeterId);
		 HashMap<String,double[]> rehashMap=new HashMap<String,double[]>();
		 double[] data=hashMap.get(ammeterId);
		   
		 rehashMap.put(ammeterId, new double[]{
				 data[0]+data[1]+data[2],
				 data[3]+ data[4]+data[5],
				 data[6]+data[7]+data[8],
				 data[9]+data[10]+data[11]+data[12]});
		 
		 
		return rehashMap;
		
	}
	//====================================================
	

	// /////=========================================根据年份查询用量
	/**
	 * 一个仪表
	 * 
	 * @param type
	 * @param year
	 * @param ammeterId
	 * @return
	 */
	public List<HashMap<String, Object>> selectByYear(String year,
			String ammeterId) {
		//
		String sql = "select AMMETERDATAID,DATA,TIME,AMMETERID,YEAR(TIME) as Y,MONTH(TIME) as M,DAY(TIME) as D from ammeter_data where ammeterId=? and year(TIME) = ?";
		Object[] params = new Object[2];
		params[0] = ammeterId;
		params[1] = year;
		DBManager db = new DBManager();
		List<HashMap<String, Object>> list = db.ExecuteQuery(sql, params);
		List<HashMap<String, Object>> reList = selectByYearType( list);// 根据类型返回一个
		return reList;
	}
	// 按月份计算
	/**
	 * 
	 * @param list
	 * @return
	 */
	public List<HashMap<String, Object>> selectByYearType(
			List<HashMap<String, Object>> list) {
			List<HashMap<String, Object>> reList = new ArrayList<HashMap<String, Object>>();
			String time = null;
			double upData = -1;
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (upData == -1 && time == null) {// 如果upData=-1则给upData赋值
						upData = Double.parseDouble(list.get(i).get("DATA")
								.toString());
						time = list.get(i).get("M").toString();
					}
					// System.out.println(upData);
					if (!time.equals(list.get(i).get("M").toString())
							|| (i == list.size() - 1)) {
						// System.out.println(list.get(i).get(typeStr).toString());
						double d = Double.parseDouble(list.get(i).get("DATA")
								.toString())
								- upData;
						HashMap<String, Object> h = new HashMap<String, Object>();
						h.put("startTime", time);
						h.put("endTime", list.get(i).get("TIME"));
						h.put("data", d);
						Utils u = new Utils();
						HashMap<String, String> t = u.getStringDate(u
								.stringToDateTwo(list.get(i).get("TIME")
										.toString()));
						h.put("M", t.get("mouth"));
						reList.add(h);
						upData = Double.parseDouble(list.get(i).get("DATA")
								.toString());
						time = list.get(i).get("M").toString();
					}
				}
			}
			return reList;
	}
	
	////////////////////////////////////////////////////////////////
	/**
	 * 根据楼层号得到月用电量
	 * @return
	 */
	public HashMap<String,double[]> getDataByFloorId(String year,String floorId){
		
		HashMap<String,double[]> hashMap=new HashMap<String,double[]>();
		AmmeterDao ammeterDao=new AmmeterDaoImpl();
		List<String> list=ammeterDao.GetAmmeterIdByFloorId(floorId);
		double[] reDatas=new double[12];
		int i=0;
		for(String ammeterid:list){
			if(i==12){
				i=0;
			}
			reDatas[i]+=getMonthByAmmeterIdAndYear(year,ammeterid).get(ammeterid)[i];
			i++;
		}
		hashMap.put(floorId, reDatas);
		return hashMap;
	}
	public static void main(String[] args) {
		AmmeterAnalysisDaoImpl a = new AmmeterAnalysisDaoImpl();
		 HashMap<String,double[]> hashMap=a.getDataByFloorId("2010","1");
		 System.out.println(hashMap.get("1")[0]);
		 System.out.println(hashMap.get("1").length);
	}
	

}



