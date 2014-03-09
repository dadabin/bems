/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: Utils 说明： TODO(Tell the reader such role.)
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-5 下午8:27:02
 * 
 */
public class Utils {

	public static String getTime() {
		Date date = new Date();
		String formate = "yyyy年MM月dd HH:mm";
		SimpleDateFormat simple = new SimpleDateFormat(formate);
		return simple.format(date);
	}

	public static void main(String[] args) {
//		System.out.println("------------------>" + getTime());
//		System.out.println("--------->>" + new Date("2012/1/1"));
		Utils u = new Utils();
		// u.increaseTime(1);
		try {
//			String formate = "yyyy";
//			SimpleDateFormat simple = new SimpleDateFormat(formate);
//			System.out.println("{}" + simple.format(new Date("2012/1/30")));
//			
//			SimpleDateFormat Year = new SimpleDateFormat("yyyy");
//			year = Year.format(date);
			
			Date a = u.addTime(new Date(), "day", 7);
			System.out.println("==="+a);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * sun 2012-7-10 添加时间封装
	 * 
	 * @param date
	 *            原时间
	 * @param type
	 *            : year、mouth、day、hour、minute、second 分别表示按年、月、日、小时、分钟、秒钟
	 * @param count
	 *            加入的数据
	 * @return
	 * @throws ParseException
	 */
	public Date addTime(Date date, String type, Integer count)
			throws ParseException {
		Date endDate = null;
		HashMap<String, String> t = getStringDate(date);

		Integer year = Integer.parseInt(t.get("year")), mouth = Integer
				.parseInt(t.get("mouth")), day = Integer.parseInt(t.get("day")), hour = Integer
				.parseInt(t.get("hour")), minute = Integer.parseInt(t
				.get("minute")), second = Integer.parseInt(t.get("second"));
		System.out.println(year);
		if (type == "year") {
			year += count;
		} else if (type == "mouth") {
			List<Integer> list5 = addMouth(mouth, count);
			mouth = list5.get(1);
			year += list5.get(0);
			System.out.println(mouth);
			// mouth+=count;
			System.out.println(mouth);
		} else if (type == "day") {
			List<Integer> list4 = addDay(day, count, mouth, year);
			day = list4.get(1);
			List<Integer> list5 = addMouth(mouth, list4.get(0));
			mouth = list5.get(1);
			year += list5.get(0);
			// day+=count;
		} else if (type == "hour") {
			List<Integer> list3 = addHour(hour, count);
			hour = list3.get(1);
			List<Integer> list4 = addDay(day, list3.get(0), mouth, year);
			day = list4.get(1);
			List<Integer> list5 = addMouth(mouth, list4.get(0));
			mouth = list5.get(1);
			year += list5.get(0);

			// hour+=count;
		} else if (type == "minute") {
			List<Integer> list2 = addMinute(minute, count);
			minute = list2.get(1);
			List<Integer> list3 = addHour(hour, list2.get(0));
			hour = list3.get(1);
			List<Integer> list4 = addDay(day, list3.get(0), mouth, year);
			day = list4.get(1);
			List<Integer> list5 = addMouth(mouth, list4.get(0));
			mouth = list5.get(1);
			year += list5.get(0);
			// minute+=count;
		} else if (type == "second") {
			// second=addSecond(second, count).get(1);
			List<Integer> list1 = addSecond(second, count);
			second = list1.get(1);
			List<Integer> list2 = addMinute(minute, list1.get(0));
			minute = list2.get(1);
			List<Integer> list3 = addHour(hour, list2.get(0));
			hour = list3.get(1);
			List<Integer> list4 = addDay(day, list3.get(0), mouth, year);

			day = list4.get(1);

			List<Integer> list5 = addMouth(mouth, list4.get(0));
			mouth = list5.get(1);
			year += list5.get(0);
		}

		String yearStr, mouthStr, dayStr, hourStr, minuteStr, secondStr;
		yearStr = year.toString();
		if (mouth < 10) {
			mouthStr = "0" + mouth;
		} else {
			mouthStr = mouth.toString();
		}
		if (day < 10) {

			dayStr = "0" + day;

		} else {
			dayStr = day.toString();
		}
		if (minute < 10) {
			minuteStr = "0" + minute;
		} else {
			minuteStr = minute.toString();
		}
		if (hour < 10) {
			hourStr = "0" + hour;
		} else {
			hourStr = hour.toString();
		}
		if (minute < 10) {
			minuteStr = "0" + minute;
		} else {
			minuteStr = minute.toString();
		}
		if (second < 10) {
			secondStr = "0" + second;
		} else {
			secondStr = second.toString();
		}
		// String endDateStr=String.format("%s-%s-%s %s:%s:%s",
		// yearStr,mouthStr,dayStr,hourStr,minuteStr,secondStr);
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = stringToDate(yearStr + mouthStr + dayStr + hourStr + minuteStr
				+ secondStr);
		System.out.println("=================" + yearStr + mouthStr + dayStr
				+ hourStr + minuteStr + secondStr);
		System.out.println(d + "========================");
		endDate = d;
		return endDate;
	}

	/**
	 * sun 2012-7-10 传入时间分别得到年、月、日、小时（24时制）、分、秒
	 * 
	 * @param date
	 * @return
	 */
	public HashMap<String, String> getStringDate(Date date) {
		String year = "", mouth = "", day = "", hour = "", minute = "", second = "";
		// 得到传入时间的年份
		SimpleDateFormat Year = new SimpleDateFormat("yyyy");
		year = Year.format(date);
		// 得到传入时间的月份
		SimpleDateFormat Mouth = new SimpleDateFormat("MM");
		mouth = Mouth.format(date);
		// 得到传入时间的天数
		SimpleDateFormat Day = new SimpleDateFormat("dd");
		day = Day.format(date);
		// 得到传入时间的小时
		SimpleDateFormat Hour = new SimpleDateFormat("HH");
		hour = Hour.format(date);
		// 得到传入时间的分钟
		SimpleDateFormat Minute = new SimpleDateFormat("mm");
		minute = Minute.format(date);
		// 得到传入时间的秒钟
		SimpleDateFormat Second = new SimpleDateFormat("ss");
		second = Second.format(date);
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("year", year);
		hashMap.put("mouth", mouth);
		hashMap.put("day", day);
		hashMap.put("hour", hour);
		hashMap.put("minute", minute);
		hashMap.put("second", second);

		return hashMap;
	}

	/**
	 * sun 2012-7-10 增加count个月
	 * 
	 * @param mouth
	 * @param count
	 * @return
	 */
	public List<Integer> addMouth(Integer mouth, Integer count) {

		mouth += count;
		Integer year = 0;
		year = mouth / 13;
		mouth = mouth % 13;
		List<Integer> list = new ArrayList<Integer>();
		list.add(year);
		list.add(mouth);

		return list;
	}

	/**
	 * sun 2012-7-10 增加 count个天
	 * 
	 * @param day
	 * @param count
	 * @param mouth
	 * @param year
	 * @return
	 */
	public List<Integer> addDay(Integer day, Integer count, Integer mouth,
			Integer year) {

		Integer remouth = 0;
		Integer Leapyear = 28;
		// 能被4整除的是闰年，否则是平年
		// 闰年2月是29天；平年2月是28天
		if (year % 4 == 0) {
			Leapyear = 29;
		}
		day += count;
		int len = 0;
		switch (mouth) {
		case 1:
			len = 32;
			break;
		case 2:
			len = Leapyear + 1;
			break;
		case 3:
			len = 32;
			break;
		case 5:
			len = 32;
			break;
		case 7:
			len = 32;
			break;
		case 8:
			len = 32;
			break;
		case 10:
			len = 32;
			break;
		case 12:
			len = 32;
			break;
		default:
			len = 31;
			break;

		}

		for (;;) {
			if (day < len)
				break;

			switch (mouth + remouth) {
			case 1:
				if (day / 32 >= 1)
					day = day - 31;
				break;
			case 2:
				if (day / (Leapyear + 1) >= 1)
					day = day - Leapyear;
				break;
			case 3:
				if (day / 32 >= 1)
					day = day - 31;
				break;
			case 5:
				if (day / 32 >= 1)
					day = day - 31;
				break;
			case 7:
				if (day / 32 >= 1)
					day = day - 31;
				break;
			case 8:
				if (day / 32 >= 1)
					day = day - 31;
				break;
			case 10:
				if (day / 32 >= 1)
					day = day - 31;
				break;
			case 12:
				if (day / 32 >= 1)
					day = day - 31;
				break;
			default:
				if (day / 31 >= 1)
					day = day - 30;
				break;
			}
			remouth += 1;
			switch (remouth) {
			case 1:
				len = 32;
				break;
			case 2:
				len = Leapyear + 1;
				break;
			case 3:
				len = 32;
				break;
			case 5:
				len = 32;
				break;
			case 7:
				len = 32;
				break;
			case 8:
				len = 32;
				break;
			case 10:
				len = 32;
				break;
			case 12:
				len = 32;
				break;
			default:
				len = 31;
				break;
			}

		}
		switch (mouth) {
		case 1:
			remouth += day / 32;
			day = day % 32;
			break;
		case 2:
			remouth += day / (Leapyear + 1);
			day = day % (Leapyear + 1);
			break;
		case 3:
			remouth += day / 32;
			day = day % 32;
			break;
		case 5:
			remouth += day / 32;
			day = day % 32;
			break;
		case 7:
			remouth += day / 32;
			day = day % 32;
			break;
		case 8:
			remouth += day / 32;
			day = day % 32;
			break;
		case 10:
			remouth += day / 32;
			day = day % 32;
			break;
		case 12:
			remouth += day / 32;
			day = day % 32;
			break;
		default:
			remouth += day / 31;
			day = day % 31;
			break;
		}

		List<Integer> list = new ArrayList<Integer>();
		list.add(remouth);
		list.add(day);

		return list;
	}

	/**
	 * sun 2012-7-10 增加count个小时
	 * 
	 * @param hour
	 * @param count
	 * @return
	 */
	public List<Integer> addHour(Integer hour, Integer count) {
		hour += count;
		int day = hour / 24;
		hour = hour % 24;
		List<Integer> list = new ArrayList<Integer>();
		list.add(day);
		list.add(hour);
		return list;
	}

	/**
	 * sun 2012-7-10 增加 count个分钟
	 * 
	 * @param minute
	 * @param count
	 * @return
	 */
	public List<Integer> addMinute(Integer minute, Integer count) {
		minute += count;
		int hour = minute / 60;
		minute = minute % 60;
		List<Integer> list = new ArrayList<Integer>();
		list.add(hour);
		list.add(minute);
		return list;
	}

	/**
	 * sun 2012-7-10 增加count个秒钟
	 * 
	 * @param second
	 * @param count
	 * @return
	 */
	public List<Integer> addSecond(Integer second, Integer count) {
		second += count;
		Integer minute = 0;
		minute = second / 60;
		second = second % 60;
		List<Integer> list = new ArrayList<Integer>();
		if (minute != null && second != null) {
			list.add(minute);
			list.add(second);
		}
		return list;
	}

	/**
	 * sun 2012-7-10 字符串转换成Date格式
	 * 
	 * @param string
	 * @return
	 */
	public Date stringToDate(String string) {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String dateString = string;
		try {
			date = df.parse(dateString);
			System.out.println(df.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return date;
	}

	/**
	 * sun 2012-7-11 字符串转换成时间类型
	 * 
	 * @param string
	 * @return
	 */
	public Date stringToDateTwo(String string) {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateString = string;
		try {
			date = df.parse(dateString);
			// System.out.println(df.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return date;
	}

	/**
	 * 根据原来的时间（Date）获得相对偏移 N 月的时间（Date）
	 * 
	 * @param protoDate
	 *            原来的时间（java.util.Date）
	 * 
	 * @param dateOffset
	 *            （向前移正数，向后移负数）
	 * 
	 * @return 时间（java.util.Date）
	 */
	public static Date getOffsetMonthDate(Date protoDate, int monthOffset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(protoDate);
		// cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - monthOffset);
		// //这种写法是错误的，这种偏移以30天为标准
		cal.add(Calendar.MONTH, -monthOffset); // 正确写法
		System.out.println(cal.get(Calendar.MONTH));
		return cal.getTime();
	}

	public static Date getOffsetYearDate(Date protoDate, int dayOffset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(protoDate);
		// cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - monthOffset);
		// //这种写法是错误的，这种偏移以30天为标准
		cal.add(Calendar.DATE, dayOffset); // 正确写法
		System.out.println(cal.get(Calendar.MONTH));
		return cal.getTime();
	}

	/**
	 * sun 2012-7-16 删除图片文件
	 * 
	 * @param imagePath
	 * @return
	 */
	public boolean deleteImage(String imagePath) {

		File file = new File(imagePath);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}
	
	/***
	 * 四舍五入，保留两位有效数字
	 * 返回一个double型数据 
	 * @param data
	 * 
	 * @return
	 */
	public Double getRounding(double data){
	    BigDecimal b = new BigDecimal(data); 
	    double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
	    return f1;
	}

}
