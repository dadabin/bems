/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import org.apache.xmlbeans.impl.inst2xsd.SalamiSliceStrategy;

import com.sun.org.apache.bcel.internal.generic.I2F;
import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.net.www.protocol.file.Handler;

import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.Ammeter;
import cn.cdu.edu.TQC.bems.db.bean.AmmeterData;
import cn.cdu.edu.TQC.bems.db.bean.Device;
import cn.cdu.edu.TQC.bems.db.bean.Meter;
import cn.cdu.edu.TQC.bems.db.bean.MeterData;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterDataDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.MeterDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.MeterDataDaoImpl;

/**
 * @ClassName: Analasis 说明： TODO(Tell the reader such role.)
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-11 下午6:54:04
 * 
 */
public class Analasis {
	private DBManager db = null;
	private Utils utils = null;

	public Analasis() {
		db = new DBManager();
		utils = new Utils();
	}

	/*******************************************
	 * 接口方法getDevicesData和getAmongData *
	 *******************************************/

	/**
	 * 获取一个时间段内如干仪表表的数据信息
	 * 
	 * @param deviceType
	 *            ------仪表类型
	 * @param deviceID
	 *            -----仪表编号
	 * @param begin
	 *            -----开始时间
	 * @param end
	 *            ------结束时间
	 * @return
	 */
	public List<Device> getDevicesData(String deviceType, String deviceID[],
			Date begin, Date end) {
		List<Device> devices = new ArrayList<Device>();
		for (int i = 0; i < deviceID.length; i++) {
			Device device = new Device();

			HashMap<String, List<Object>> map = getResult(deviceType,
					deviceID[i], begin, end);
			List<Object> usage = map.get("usage");
			List<Object> times = map.get("time");
			Double[] data = new Double[usage.size()];
			Date[] dds = new Date[times.size()];
			for (int k = 0; k < usage.size(); k++) {
				data[k] = utils.getRounding((Double) usage.get(k));
				dds[k] = (Date) times.get(k);
			}
			device.setData(data);
			device.setName(deviceID[i]);
			device.setTimes(dds);
			devices.add(device);
		}
		return devices;
	}

	/**
	 * 返回给定年份中每个月的用量情况
	 * 
	 * @param deviceType
	 * @param deviceID
	 * @param year
	 * @return
	 */
	public List<Double> getDeviceByMonth(String deviceType, String deviceID,
			int year) {
		List<Double> data = new ArrayList<Double>();

		Date begin = new Utils().stringToDate(year + "0101000000");
		Date end = new Utils().stringToDate(year + "1230000000");
		HashMap<String, List<Object>> map = getResult(deviceType, deviceID,
				begin, end);
		List<Object> times = map.get("time");
		Date endDate = (Date) times.get(times.size() - 1);
		int month = endDate.getMonth() + 1;
		for (int i = 1; i <= month; i++) {
			Date tempDate = null;
			if (begin.getMonth() + 1 == month) {
				tempDate = Utils.getOffsetYearDate(begin, 30);
			} else {
				tempDate = Utils.getOffsetMonthDate(begin, -1);
			}
			Double d = utils.getRounding( getAmongData(deviceType, deviceID, begin, tempDate) );
			data.add(d);
			begin = tempDate;
		}
		return data;
	}

	/***
	 * 獲取所有儀表在指定月分的用量
	 * 
	 * @param deviceType
	 *            ----儀錶類型
	 * @param year
	 *            --------年
	 * @param month
	 *            --------月
	 * @return
	 */
	public List<Double> getDevicesAtOneTime(String deviceType, int year,
			int month) {
		List<Double> all = null;
		Date begin = new Date(year + "/" + month + "/01");
		Date end = new Date(year + "/" + month + "/31");

		if (deviceType.endsWith("meter")) {
			all = new ArrayList<Double>();
			List<Meter> meters = new MeterDaoImpl().getMeters();
			for (Meter meter : meters) {
				if (meter != null) {
					Double d = utils.getRounding( getAmongData(deviceType, meter.getMeterId(),
							begin, end) );
					all.add(d);
				}
			}
		}
		if (deviceType.equals("ammeter")) {
			all = new ArrayList<Double>();
			List<Ammeter> ammeters = new AmmeterDaoImpl().getAmmeters();
			//System.out.println("???" + ammeters.size());
			for (int i = 0; i < ammeters.size(); i++) {
				Ammeter ammeter = ammeters.get(i);
				if (ammeter != null && ammeter.getAmmeterId() != null) {
					Double d = utils.getRounding ( getAmongData(deviceType, ammeter.getAmmeterId(),
							begin, end) );
					all.add(d);
					System.out.println("@@" + i);
				}
			}
		}
		return all;
	}

	/**
	 * 返回给定年份中每个极端的用量
	 * 
	 * @param deviceType
	 * @param deviceID
	 * @param year
	 * @return
	 */
	public List<Double> getDeviceBySeason(String deviceType, String deviceID,
			int year) {
		List<Double> all = new ArrayList<Double>();
		double temp = 0.0;
		List<Double> data = new ArrayList<Double>();
		data = getDeviceByMonth(deviceType, deviceID, year);
		for (int i = 1; i <= data.size(); i++) {
			temp += utils.getRounding ( data.get(i - 1) );
			if (i % 3 == 0) {
				all.add(temp);
				temp = 0.0;
			}
		}
		return all;
	}

	/**
	 * 获取一段时间的仪表的用量
	 * 
	 * @param deviceType
	 *            ------仪表类型
	 * @param deviceID
	 *            -----仪表编号
	 * @param begin
	 *            -----开始时间
	 * @param end
	 *            ------结束时间
	 * 
	 * @return double
	 */
	public Double getAmongData(String deviceTYpe, String deviceID, Date begin,
			Date end) {
		List<HashMap<String, Object>> all = this.getComsumData(deviceTYpe,
				deviceID, begin, end);
		Double start = 0.0;
		Double en = 0.0;
		if (all.size() > 0) {
			if (deviceTYpe.equals("phone")) {
				start = (Double) all.get(0).get("FEE");
				en = (Double) all.get(all.size() - 1).get("FEE");
			} else {
				start = (Double) all.get(0).get("DATA");
				en = (Double) all.get(all.size() - 1).get("DATA");
			}
		}

		if (en - start < 0) {
			return start - en;
		}
		return en - start;
	}

	/***
	 * @author LPM
	 * 
	 *         返回指定编号的仪表在指定时间内的明细数据---日期处理
	 * 
	 *         使用方法：
	 * 
	 *         HashMap<String, List<Object>> maps = new
	 *         Analasis().getResult("meter", "20000001", new
	 *         Date("2012/1/10"),new Date("2012/2/27")); List<Object> times =
	 *         maps.get("time");//获取的时间 List<Object> usage =
	 *         maps.get("usage");//获取的周用用 for(Object t: times){
	 *         System.out.println("---------time"+t); }
	 * 
	 * 
	 * @param deviceType
	 *            ----仪表类型
	 * @param deviceID
	 *            -----仪表编号
	 * @param begin
	 *            -----开始时间
	 * @param end
	 *            ----结束时间
	 * @return
	 */
	public HashMap<String, List<Object>> getResult(String deviceType,
			String deviceID, Date begin, Date end) {
		List<HashMap<String, Object>> all = this.getComsumData(deviceType,
				deviceID, begin, end);
		HashMap<String, List<Object>> results = this.result(all, deviceType);
		// 日期处理
		List<Object> list = results.get("atime");
		if (list.size() > 0) {
			results.remove("atime");
			list.remove(0);
			results.put("time", list);
		}
		return results;
	}

	/**
	 * 结果处理--------数据处理
	 * 
	 * @param all
	 * @param deviceType
	 * @return
	 */
	private HashMap<String, List<Object>> result(
			List<HashMap<String, Object>> all, String deviceType) {
		HashMap<String, List<Object>> list = new HashMap<String, List<Object>>();
		List<Object> timeNum = new ArrayList<Object>();
		List<Object> usage = new ArrayList<Object>();
		if (deviceType.equals("meter")) {
			for (HashMap map : all) {
				timeNum.add((Date) map.get("TIME"));
				usage.add((Double) map.get("DATA"));
			}
		}
		if (deviceType.equals("ammeter")) {
			for (HashMap map : all) {
				timeNum.add((Date) map.get("TIME"));
				usage.add((Double) map.get("DATA"));
			}
		}
		if (deviceType.equals("phone")) {
			for (HashMap map : all) {
				timeNum.add((Date) map.get("TIME"));
				usage.add((Double) map.get("FEE"));
			}
		}
		list.put("atime", timeNum);
		list.put("usage", tempSubtraction(usage));
		return list;
	}

	/**
	 * @author LPM 根据仪表类型以及仪表编号，以及始末时间， 获取查询该仪表在这段时间内的所有用量
	 * 
	 * @param deviceTYpe
	 *            仪表类型
	 * @param deviceID
	 *            仪表编号
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public List<HashMap<String, Object>> getComsumData(String deviceTYpe,
			String deviceID, Date begin, Date end) {
		List<HashMap<String, Object>> all = new ArrayList<HashMap<String, Object>>();
		String[] sql = this.builtSql(deviceTYpe, deviceID, begin, end);

		for (String str : sql) {
			List<HashMap<String, Object>> list = db.ExecuteQuery(str);
			for (HashMap<String, Object> map : list) {
				all.add(map);
			}
		}
		return all;
	}

	/**
	 * @author LPM 构造sql语句
	 * 
	 * @param deviceTYpe
	 * @param deviceID
	 * @param begin
	 * @param end
	 * @return
	 */
	private String[] builtSql(String deviceTYpe, String deviceID, Date begin,
			Date end) {
		System.out.println(begin.toLocaleString() + "====||=="
				+ end.toLocaleString());

		String[] sql = null;
		Utils u = new Utils();

		int sy = Integer.parseInt((String) u.getStringDate(begin).get("year"));
		int sm = Integer.parseInt((String) u.getStringDate(begin).get("mouth"));
		int sd = Integer.parseInt((String) u.getStringDate(begin).get("day"));// 查询到上一周的数据

		int ey = Integer.parseInt((String) u.getStringDate(end).get("year"));
		int em = Integer.parseInt((String) u.getStringDate(end).get("mouth"));
		int ed = Integer.parseInt((String) u.getStringDate(end).get("day"));// 查询到下一周的数据

		StringBuilder sb = new StringBuilder();
		StringBuilder sb0 = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		String head = "";

		if (deviceTYpe.equals("ammeter")) {
			// 查询电表语句
			head += "select AMMETERID,`DATA`,`TIME`,year(time) as y ,MONTH(time) as m ,DAY(time) as d from  ammeter_data  where";
			head += "  AMMETERID='" + deviceID + "'";
		}
		if (deviceTYpe.equals("meter")) {
			// 查询水表语句
			head += "select METERID,`DATA`,`TIME`,year(time) as y ,MONTH(time) as m ,DAY(time) as d from  meter_data  where";
			head += "  METERID='" + deviceID + "'";

		}
		if (deviceTYpe.equals("phone")) {
			// 查询电话语句
			head += "select PHONEID,`FEE`,`TIME`,year(time) as y ,MONTH(time) as m ,DAY(time) as d from  phone_fee_data  where";
			head += "  PHONEID='" + deviceID + "'";
		}

		head += " and year(time)  between  " + sy + "  and " + ey + "";

		if (sy == ey) {
			if (em - sm == 0) {
				sql = new String[1];
				sb.append(head);

				sb.append(" and  MONTH(TIME) between " + sm + " and " + em + "");
				sb.append(" and day(time) between '" + sd + "' and '" + ed
						+ "'");
				sb.append(" order by `time`");
				sql[0] = sb.toString();
			} else if (em - sm == 1) {
				sql = new String[2];
				sb.append(head);
				sb0.append(head);

				sb.append(" and  MONTH(TIME) between " + sm + " and " + sm + "");
				sb.append(" and day(time) between " + sd + " and 31");
				sb0.append(" and  MONTH(TIME) between " + em + " and " + em
						+ "");
				sb0.append(" and day(time) between 1 and " + ed + "");
				sb.append(" order by `time`");
				sb0.append(" order by `time`");
				sql[0] = sb.toString();
				sql[1] = sb0.toString();
			} else {
				sql = new String[3];
				sb.append(head);
				sb0.append(head);
				sb1.append(head);

				sb.append(" and  MONTH(TIME) between " + sm + " and " + sm + "");
				sb.append(" and day(time) between " + sd + " and 31");
				sb0.append(" and  MONTH(TIME) between " + em + " and " + em
						+ "");
				sb0.append(" and day(time) between 1 and " + ed + "");
				sb1.append(" and  MONTH(TIME) between " + (sm + 1) + " and "
						+ (em - 1) + "");
				sb1.append(" and day(time) between 1 and 31");

				sb.append(" order by `time`");
				sb0.append(" order by `time`");
				sb1.append(" order by `time`");

				sql[0] = sb.toString();
				sql[1] = sb1.toString();
				sql[2] = sb0.toString();
			}
		} else {
			sql = new String[1];
			sql[0] = "";
		}
		return sql;
	}

	/**
	 * 周用量减法
	 * 
	 * @param all
	 * @return
	 */
	private List<Object> tempSubtraction(List<Object> all) {
		double[] temp = new double[all.size()];
		List<Object> list = new ArrayList<Object>();
		int i = 0;
		for (Object object : all) {
			temp[i] = (Double) object;
			i++;
		}

		for (int j = 0; j < temp.length - 1; j++) {
			list.add(temp[j + 1] - temp[j]);
		}
		return list;
	}

	/**
	 * 测试的main方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String[] ids = { "20120001", "20120002" };
		List<Double> list = new Analasis().getDeviceByMonth("ammeter", "",2010
				);
		List<Double> list1 = new Analasis().getDeviceByMonth("meter",20120002+"" ,
			2010);
		for (Double a : list) {
			System.out.println("----" + a);
		} 

	}

}
