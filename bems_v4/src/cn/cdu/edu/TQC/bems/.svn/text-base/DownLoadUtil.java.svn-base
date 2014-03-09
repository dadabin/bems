/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.cdu.edu.TQC.bems.db.bean.AmmeterData;
import cn.cdu.edu.TQC.bems.db.bean.MeterData;
import cn.cdu.edu.TQC.bems.db.bean.Phone;
import cn.cdu.edu.TQC.bems.db.bean.PhoneFeeData;
import cn.cdu.edu.TQC.bems.db.bean.PhoneOrder;
import cn.cdu.edu.TQC.bems.db.dao.PhoneFeeDataDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterDataDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.MeterDataDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.PhoneDaoImpl;
import cn.cdu.edu.TQC.bems.db.dao.impl.PhoneFeeDataDaoImpl;

/**
 * @ClassName: DownLoadUtil
 * 
 *             下载文件---------pdf,excel,word 说明： TODO(Tell the reader such role.)
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-13 上午7:58:09
 * 
 */
public class DownLoadUtil {

    public static boolean downLoadFile(String type, String filePath,
	    HttpServletResponse response, String fileName, String fileType)
	    throws Exception {
	// 判断下载文件的类型1、phonedata电话费用账单2、phone电话基本信息3、commons一般文件
	if ("phonedata".equals(type)) {
	    // 生成excel
	    buildExcel(filePath);
	}
	if ("phone".equals(type)) {
	    // 生成excel
	    builPhoneExcel(filePath);
	}

	File file = new File(filePath);
	// 根据文件路径获得File文件
	// 设置文件类型(这样设置就不止是下Excel文件了，一举多得)
	if ("pdf".equals(fileType)) {
	    response.setContentType("application/pdf;charset=utf-8");
	} else if ("xls".equals(fileType)) {
	    response.setContentType("application/msexcel;charset=utf-8");
	} else if ("doc".equals(fileType)) {
	    response.setContentType("application/msword;charset=utf-8");
	}
	// 文件名
	response.setHeader("Content-Disposition", "attachment;filename=\""
		+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
	response.setContentLength((int) file.length());
	byte[] buffer = new byte[4096];// 缓冲区
	BufferedOutputStream output = null;
	BufferedInputStream input = null;
	try {
	    output = new BufferedOutputStream(response.getOutputStream());
	    input = new BufferedInputStream(new FileInputStream(file));
	    int n = -1;
	    // 遍历，开始下载
	    while ((n = input.read(buffer, 0, 4096)) > -1) {
		output.write(buffer, 0, n);
	    }
	    output.flush(); // 不可少
	    response.flushBuffer();// 不可少
	} catch (Exception e) {
	    // 异常自己捕捉
	} finally {
	    // 关闭流，不可少
	    if (input != null)
		input.close();
	    if (output != null)
		output.close();
	}
	return true;
    }
    

    private static void buildExcel(String filePath) {
	ExportExcel<PhoneOrder> ex = new ExportExcel<PhoneOrder>();
	String[] headers = { "电话编号", "电话号码", "楼层编号", "电话权限", "费用（本月电话费用）" };
	List<Phone> list = new PhoneDaoImpl().getPhones();
	List<PhoneOrder> dataset = new ArrayList<PhoneOrder>();

	for (Phone phone : list) {
	    PhoneOrder order = new PhoneOrder();
	    order.setFloorID(phone.getFloorId() + "");
	    order.setPhoneFee("");
	    order.setPhoneID(phone.getPhoneId() + "");
	    order.setPhoneNUM(phone.getPhoneNumber());
	    order.setPhonePower(phone.getPhonePower());
	    dataset.add(order);
	}
	try {
	    OutputStream out = new FileOutputStream(filePath);
	    ex.exportExcel(headers, dataset, out);
	    out.close();
	    System.out.println("------ok!");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static void builPhoneExcel(String filePath) {
	ExportExcel<Phone> exportExcel = new ExportExcel<Phone>();
	String[] headers = { "电话号码", "楼层号", "房间号", "电话权限", "电话责任人" };
	Collection<Phone> dataset = new ArrayList<Phone>();
	try {
	    OutputStream out = new FileOutputStream(filePath);
	    exportExcel.exportExcel(headers, dataset, out);
	    out.close();
	    System.out.println("------ok!");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
    
    /************************************
     *       	 详单下载			*
     ************************************/
    
    /**
     * 按年月下载电话费用账单
     * 月份
     * 年份
     * @param month
     */
    public static void exportPhoneFeeList(
	    HttpServletResponse response,
	    String fileType,
	    int year,int month) throws Exception{
	

	String fileName = "phoneDetail.xls";
	
	if ("pdf".equals(fileType)) {
	    response.setContentType("application/pdf;charset=utf-8");
	} else if ("xls".equals(fileType)) {
	    response.setContentType("application/msexcel;charset=utf-8");
	} else if ("doc".equals(fileType)) {
	    response.setContentType("application/msword;charset=utf-8");
	}
	// 文件名
	response.setHeader("Content-Disposition", "attachment;filename=\""
		+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
	
	ExportExcel<PhoneFeeData> ex = new ExportExcel<PhoneFeeData>();
	String[] headers = { "序号", "电话编号", "时间", "费用（本月电话费用）","录入人员","电话号码" };
	 List<PhoneFeeData> dataset = new PhoneFeeDataDaoImpl().getPhoneFeeDatas(year, month);
	
	try {
	    OutputStream out = new BufferedOutputStream(response.getOutputStream());
	    ex.exportExcel(headers, dataset, out);
	    out.close();
	    System.out.println("------ok!");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * 按仪表编号下载水表用量
     * @param filePath
     * @param MeterID
     * @param year
     * @param mouth
     * @throws Exception 
     */
    public static void exportMeterDataList(
	    HttpServletResponse response,
	    String fileType,Integer MeterID) throws Exception{
	
	String fileName = "merterDetail.xls";
	
	if ("pdf".equals(fileType)) {
	    response.setContentType("application/pdf;charset=utf-8");
	} else if ("xls".equals(fileType)) {
	    response.setContentType("application/msexcel;charset=utf-8");
	} else if ("doc".equals(fileType)) {
	    response.setContentType("application/msword;charset=utf-8");
	}
	// 文件名
	response.setHeader("Content-Disposition", "attachment;filename=\""
		+ new String(fileName.getBytes(), "ISO8859-1") + "\"");

	
	ExportExcel<MeterData> ex = new ExportExcel<MeterData>();
	String[] headers = { "序号", "水表编号",  "用量（吨）","时间" };
	List<MeterData> list = new MeterDataDaoImpl().getMeterDatasByMeterID(MeterID);
	try {
	    OutputStream out = new BufferedOutputStream(response.getOutputStream());
	    ex.exportExcel(headers, list, out);
	    out.close();
	    System.out.println("------ok!");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * 下载电表用量数据
     * @param filePath
     * @param ammeterid
     * @throws Exception 
     */
    public static void exportAmmeterDataList( 
	    HttpServletResponse response,
	    String fileType,
	    String way,
	    Integer id) throws Exception{
	
	String fileName = "ammerterDetail.xls";
	
	if ("pdf".equals(fileType)) {
	    response.setContentType("application/pdf;charset=utf-8");
	} else if ("xls".equals(fileType)) {
	    response.setContentType("application/msexcel;charset=utf-8");
	} else if ("doc".equals(fileType)) {
	    response.setContentType("application/msword;charset=utf-8");
	}
	// 文件名
	response.setHeader("Content-Disposition", "attachment;filename=\""
		+ new String(fileName.getBytes(), "ISO8859-1") + "\"");

	ExportExcel<AmmeterData> ex = new ExportExcel<AmmeterData>();
	String[] headers = { "序号",   "用量（度）","时间", "电表编号"};
	List< AmmeterData> list = new ArrayList<AmmeterData>();
	//按楼层
	if("floor".equals(way)){
	    list =   new AmmeterDataDaoImpl().getAmmeterDatasByFloors(id);
	}else {
	    //按编号
	    list =  new AmmeterDataDaoImpl().getAmmeterDatasByAmmeterID(id);
	}
	
	try {
	    OutputStream out = new BufferedOutputStream(response.getOutputStream());
	    ex.exportExcel(headers, list, out);
	    out.close();
	    System.out.println("------ok!");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
    public static void main(String[] args){
//	builPhoneExcel("e:/hello.xls");
//	exportAmmeterDataList("e:/testme.xls",20120001);
    }
}
