package cn.cdu.edu.TQC.bems;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.org.apache.bcel.internal.generic.I2F;

import cn.cdu.edu.TQC.bems.db.bean.Phone;
import cn.cdu.edu.TQC.bems.db.bean.PhoneFeeData;

/**
 * @ClassName: ReadExcel
 * 
 * @Description: TODO(Tell the reader such role.)
 * 
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-19 下午7:37:08
 * 
 * @Version V1.0
 * 
 */
public class ReadExcel {

	public List<PhoneFeeData> readExcel(String path) {
		List<PhoneFeeData> all = new ArrayList<PhoneFeeData>();
		Date times = new Date();
		String staff = "";

		// 创建excel文本模版
		Workbook workbook = null;
		try {
			// Excel2003
			workbook = new HSSFWorkbook(new FileInputStream(path));

		} catch (Exception e) {
			// Excel2007级以上版本
			try {
				workbook = new XSSFWorkbook(path);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		// 循环Sheet
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			// 取得当前sheet
			Sheet sheet = workbook.getSheetAt(i);
			// 取得总的行数
			int rows = sheet.getPhysicalNumberOfRows();
			// 取得头部信息

			Row row0 = (Row) sheet.getRow(0);
			Row row1 = (Row) sheet.getRow(1);
			String timeStr = row0.getCell(1).getDateCellValue()+"";// new
			// Utils().stringToDateTwo(row0.getCell(1).getNumericCellValue()+"");
			times = new Date(timeStr+"");
			staff = row1.getCell(1).getStringCellValue();

			// 从第三行开始，循环话费
			for (int m = 3; m < rows; m++) {
				// 取得当前行
				Row row = (Row) sheet.getRow(m);

				if (row != null) {
					// 新建一个实体
					PhoneFeeData data = new PhoneFeeData();
					data.setStaffId(staff);
					data.setTime(times);// 现在时间
					// 取得总的单元数
					int cells = row.getPhysicalNumberOfCells();
					// 循环所有单元
					for (int n = 0; n < cells; n++) {
						// 获取单元
						Cell cell = row.getCell(n);
						String value = "";
						if (cell != null) {
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_BLANK:
								value = null;
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								value += cell.getBooleanCellValue();
								break;
							case Cell.CELL_TYPE_ERROR:
								value += cell.getErrorCellValue();
								break;
							case Cell.CELL_TYPE_FORMULA:
								value += cell.getCellFormula();
								break;
							case Cell.CELL_TYPE_NUMERIC:
								value += cell.getNumericCellValue();

								break;
							case Cell.CELL_TYPE_STRING:
								value += cell.getStringCellValue();
								break;
							default:
								System.out.println("-----找不到指定类型-----");
								break;
							}
						}
						if (value != null) {
							if (n == 0) {

								data.setPhoneId(Integer.parseInt(value));
							}
							if (n == 1) {
								data.setPhoneNum(value);
							}
							if (n == 4) {
								data.setFee(Double.parseDouble(value));
							}

						}

					}
					if (data.getFee() != 0) {
						all.add(data);
					}
				}
			}
		}
		return all;
	}

	/***
	 * 读取电话基本信息excel
	 * 
	 * @param filepath
	 * @return
	 */
	public List<Phone> readPhoneExcel(String path) {
		List<Phone> all = new ArrayList<Phone>();
		Date times = new Date();
		String staff = "";

		// 创建excel文本模版
		Workbook workbook = null;
		try {
			// Excel2003
			workbook = new HSSFWorkbook(new FileInputStream(path));

		} catch (Exception e) {
			// Excel2007级以上版本
			try {
				workbook = new XSSFWorkbook(path);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		// 循环Sheet
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			// 取得当前sheet
			Sheet sheet = workbook.getSheetAt(i);
			// 取得总的行数
			int rows = sheet.getPhysicalNumberOfRows();

			// 从第三行开始，循环话费
			for (int m = 3; m < rows; m++) {
				// 取得当前行
				Row row = (Row) sheet.getRow(m);

				if (row != null) {
					System.out.println("row--------" + m);
					// 新建一个实体
					Phone phone = new Phone();
					// 取得总的单元数
					int cells = row.getPhysicalNumberOfCells();
					// 循环所有单元
					for (int n = 0; n < cells; n++) {
						// 获取单元
						Cell cell = row.getCell(n);
						String value = "";
						if (cell != null) {
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_BLANK:
								value = null;
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								value += cell.getBooleanCellValue();
								break;
							case Cell.CELL_TYPE_ERROR:
								value += cell.getErrorCellValue();
								break;
							case Cell.CELL_TYPE_FORMULA:
								value += cell.getCellFormula();
								break;
							case Cell.CELL_TYPE_NUMERIC:
								value += cell.getNumericCellValue();

								break;
							case Cell.CELL_TYPE_STRING:
								value += cell.getStringCellValue();
								break;
							default:
								System.out.println("-----找不到指定类型-----");
								break;
							}
						}
						if (value != null && !value.equals("")) {
							if (n == 0) {
								DecimalFormat format = new DecimalFormat(
										"#################");
								Double d = Double.parseDouble(value);
								phone.setPhoneNumber(format.format(d));
							}
							if (n == 1) {
								DecimalFormat format = new DecimalFormat("###");
								Double d = Double.parseDouble(value);
								phone.setFloorId(Integer.parseInt(format
										.format(d)));
							}
							if (n == 2) {
								phone.setHouseNumber(value + "");
							}
							if (n == 3) {
								phone.setPhonePower(value + "");
							}
							if (n == 4) {
								phone.setPersonsresPoneSibleFor(value + "");
							}

						}

					}
					System.out.println("-----" + phone.getPhoneNumber());
					if (phone.getHouseNumber() != null
							&& !phone.getPhoneNumber().equals("")) {
						all.add(phone);
					}
				}
			}
		}

		return all;
	}

	public static void main(String[] args) {
		// List<PhoneFeeData> datas = new
		// ReadExcel().readExcel("D:/apache-tomcat-7.0.26/webapps/wtpwebapps/bems_v2/upload/phone.xls");
		// System.out.println("编号==========电话号码=========费用");
		// System.out.println("采集时间："+datas.get(0).getStaffId());
		// System.out.println("采集时间："+datas.get(0).getTime());
		// for(PhoneFeeData d:datas){
		// System.out.println(d.getPhoneId()+"=========="+d.getPhoneNum()+"========="+d.getFee());
		// }

		List<PhoneFeeData> datas = new ReadExcel().readExcel("F:/phonedata.xls");
		for (PhoneFeeData data : datas) {
			System.out.println(data.getPhoneNum()+"--" + data.getFee()+"======"+data.getTime());
		}
	}
}
