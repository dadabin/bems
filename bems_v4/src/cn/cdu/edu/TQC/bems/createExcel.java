/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.Region;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import cn.cdu.edu.TQC.bems.db.DBManager;

/**
 * @ClassName: createExcel 说明： TODO(Tell the reader such role.)
 * @Author SUN 【email:1096490965@qq.com】
 * @Version V1.0 2012-7-13 下午2:59:09
 * 
 */
public class createExcel {
	
	//
	public HashMap<String,String> createBarChartToExcel(String path,String excelName){
		
		createExcel c=new createExcel();
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery("select ammeterId as '电表编号',type as '电表类型',floorid as '楼层编号',circuittype as '电路类型' from ammeter ");
		String[] names=new String[] {"电表编号","电表类型","楼层编号","电路类型"};
		
		String sheetName="电表";
		
		//创建图片
		double[][] data = new double[][] { { 672, 766, 223, 540, 126 },
				{ 325, 521, 210, 340, 106 }, { 332, 256, 523, 240, 526 } };
		String[] rowKeys = { "设备", "照明", "空调电路" };
		String[] columnKeys = { "一月", "二月", "三月", "四月", "五月" };
		JfreeUtils jffreeUtils=new JfreeUtils();
		CategoryDataset dataset = jffreeUtils.getBarData(data, rowKeys, columnKeys);
		jffreeUtils.createBarChart(dataset, "x坐标", "y坐标", "柱状图", "barGroup.png",path);
        picInsertToExcelByPicPath(path,"barGroup.png",excelName,list,names,sheetName);
		HashMap<String,String> hashMap=new HashMap<String,String>();
		hashMap.put("excelName", excelName);
		return hashMap;
		
	}
	
	
	
	/**
	 * 根据图片的路径插入到excel中
	 * @param path
	 * @param pictureName
	 * @param excelName
	 * @param list
	 * @param names
	 * @param sheetName
	 */
	public void picInsertToExcelByPicPath(
			String path,
			String pictureName,
			String excelName,
			List<HashMap<String,Object>> list,
			String[] names,
			String sheetName
			){
		String pathOfPicture=path+pictureName;
		String pathOfExcel=path+excelName;
		FileOutputStream fileOut = null;
		//创建图片
	
		ByteArrayOutputStream handlePicture = new ByteArrayOutputStream();
		handlePicture = handlePicture(pathOfPicture);
		HSSFWorkbook wb = new HSSFWorkbook();
		//HSSFSheet sheet = wb.createSheet("电表");
		HSSFSheet sheet= dataAddSheet(list,names,wb,sheetName);
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023,255, (short)8,1, (short) 18, 25);
		anchor.setAnchorType(2);//加入
	    patriarch.createPicture(anchor, wb.addPicture(handlePicture.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
		try {
			fileOut = new FileOutputStream(pathOfExcel);
			wb.write(fileOut);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param path 路径
	 * @param response  
	 * @return
	 * @throws IOException
	 */
	public String gettoExcel(String path,HttpServletResponse response) throws IOException{
		createExcel c=new createExcel();
		//String path="E:/test/";
		String pictureName="l.png";
		String excelName="l.xls";
		double[][] data = new double[][] { { 672, 766, 223, 540, 126 },
				{ 325, 521, 210, 340, 106 }, { 332, 256, 523, 240, 526 } };
		String[] rowKeys = { "设备电路", "空调电路", "照明电路" };
		String[] columnKeys = { "一月", "二月", "三月", "四月", "五月" };
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery("select ammeterId as '电表编号',type as '电表类型',floorid as '楼层编号',circuittype as '电路类型' from ammeter ");
		String[] names=new String[] {"电表编号","电表类型","楼层编号","电路类型"};
		
		String sheetName="电表";
		
		c.insertPicAndData(path, pictureName, excelName,data,rowKeys,columnKeys,"电表分析","x轴","y轴",list,names,sheetName);
		//
		
		String fileType="xls";
		if ("pdf".equals(fileType)) {
		    response.setContentType("application/pdf;charset=utf-8");
		} else if ("xls".equals(fileType)) {
		    response.setContentType("application/msexcel;charset=utf-8");
		} else if ("doc".equals(fileType)) {
		    response.setContentType("application/msword;charset=utf-8");
		}
		//文件名
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ new String(excelName.getBytes(), "ISO8859-1") + "\"");
		File file=new File(path+excelName);
		response.setContentLength((int)file.length());
		byte[] buffer=new byte[4096];//缓冲区
		BufferedOutputStream output=null;
		BufferedInputStream input =null;
		try{
			 output = new BufferedOutputStream(response.getOutputStream());
			    input = new BufferedInputStream(new FileInputStream(file));
			    int n = -1;
			    // 遍历，开始下载
			    while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			    }
			    output.write(buffer,0,n);
			    response.flushBuffer();
			
		}catch(Exception e){
			
		}finally{
			//关闭流
			if(input!=null)
				input.close();
			if (output != null)
				output.close();
			
		}
		return path+excelName;
		
	}
	
	
	public static void main(String[] args) throws IOException{
		createExcel c=new createExcel();
		String path="E:/test/";
		
		String pictureName="l.png";
		
		String excelName="test1.xls";
		double[][] data = new double[][] { { 672, 766, 223, 540, 126 },
				{ 325, 521, 210, 340, 106 }, { 332, 256, 523, 240, 526 } };
		String[] rowKeys = { "设备电路", "空调电路", "照明电路" };
		String[] columnKeys = { "一月", "二月", "三月", "四月", "五月" };
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery("select ammeterId as '电表编号',type as '电表类型',floorid as '楼层编号',circuittype as '电路类型' from ammeter ");
		String[] names=new String[] {"电表编号","电表类型","楼层编号","电路类型"};
		
		String sheetName="电表";
		
		c.insertPicAndData(path, pictureName, excelName,data,rowKeys,columnKeys,"电表分析","x轴","y轴",list,names,sheetName);
		
	}
	
	/**
	 * sun 2012-7-24
	 * 
	 * @param path  Excel和生成的图片的存放的路径
	 * @param pictureName  图片的名称加上后缀名
	 * @param excelName  excel的名称 加上后缀名
	 * @param data   作图的数据double[][] 
	 * @param rowKeys  多少根线（数据组名称数组）
	 * @param columnKeys   横坐标（data[][]的二维的个数）
	 * @param picTitle   生成图片的中的图片标题
	 * @param xName      生成图片的x轴的名称
	 * @param yName      生成图片的y轴的名称
	 * @param list       Excel中显示的数据List<HashMap<String,Object>>类型的
	 * @param names       Excel中显示的数据的列名称
	 * @param sheetName   生成的工作薄的名称
	 */
	public  void insertPicAndData(
			String path,
			String pictureName,
			String excelName,
			double[][] data,
			String[] rowKeys,
			String[] columnKeys,
			String picTitle,
			String xName,
			String yName,
			List<HashMap<String,Object>> list,
			String[] names,
			String sheetName
			){
		String pathOfPicture=path+pictureName;
		String pathOfExcel=path+excelName;
		FileOutputStream fileOut = null;
		//创建图片
		JfreeUtils jfreeUtils=new JfreeUtils();
		CategoryDataset dataset = jfreeUtils.getBarData(data, rowKeys, columnKeys);
		String str=jfreeUtils.createTimeXYChar(picTitle, xName, yName, dataset, pictureName,path);
		ByteArrayOutputStream handlePicture = new ByteArrayOutputStream();
		handlePicture = handlePicture(pathOfPicture);
		//插入到excel
		
		
		HSSFWorkbook wb = new HSSFWorkbook();
		//HSSFSheet sheet = wb.createSheet("电表");
		HSSFSheet sheet= dataAddSheet(list,names,wb,sheetName);
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFClientAnchor anchor = new HSSFClientAnchor(20, 20, 1023,255, (short)8,6, (short)20, 30);
		anchor.setAnchorType(1);//加入
	    patriarch.createPicture(anchor, wb.addPicture(handlePicture.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
	    
		try {
			fileOut = new FileOutputStream(pathOfExcel);
			wb.write(fileOut);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将图片读入到缓冲区中
	 * 
	 * ByteArrayOutputStream :可以捕获内存缓冲区的数据，转换成字节数组
	 * @param pathOfPicture
	 * @return
	 */
	private  ByteArrayOutputStream handlePicture(String pathOfPicture) {
		BufferedImage bufferImg = null;
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		try {
			bufferImg = ImageIO.read(new File(pathOfPicture));
			ImageIO.write(bufferImg, "png", byteArrayOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArrayOut;
	}
	
	/**
	 * 将图片插入到Excel的工作薄中
	 * @param wb
	 * @param pa
	 * @param data
	 * @param row
	 * @param column
	 * @param index
	 */
	public  void insertImage(HSSFWorkbook wb,HSSFPatriarch pa,byte[] data,int row,int column,int index){
		int x1=index*250;
		int y1=0;
		int x2=x1+255;
		int y2=255;
		HSSFClientAnchor anchor=new HSSFClientAnchor(x1,y1,x2,y2,(short)column,row,(short)column,row);
		anchor.setAnchorType(3);
		pa.createPicture(anchor, wb.addPicture(data, HSSFWorkbook.PICTURE_TYPE_DIB));
	}
	
	/**
	 * 
	 * @param 
	 * @return
	 */
	private static byte[] getImageData(BufferedImage bi){
		try{
			ByteArrayOutputStream bOutputStream=new ByteArrayOutputStream();
			ImageIO.write(bi, "JPG", bOutputStream);
			return bOutputStream.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 
	 *  sun 2012-7-15
	 *  
	 *  将数据插入到Excel中
	 * @param xls 传入的数据列表
	 * @param names  数据列表的列名数组
	 * @param hwb    HSSFWorkbook 
	 * @param sheetName   工作簿的名称
	 * @return   返回工作薄
	 */
	private  HSSFSheet dataAddSheet(List<HashMap<String,Object>> xls,String[] names,HSSFWorkbook hwb,String sheetName){
	     
		       //获取总列数
				int CountColumnNum=xls.size();
			  //创建Excel文档
				HashMap<String,Object> map=null;
				//sheet对应一个工作页
				HSSFSheet sheet=hwb.createSheet(sheetName);
				//HSSFRow firstrow=sheet.createRow(1);//下标为0的行开始
				
				
				 HSSFRow   row1   =   sheet.createRow((short)   0); 
			        HSSFCell   cell   =   row1.createCell((short)   1); 
			        cell.setCellValue( "2010年数据分析 "); 
			        sheet.addMergedRegion(new   Region(0,(short)1,0,(short)19));//指定合并区域
			        
			        
				
				HSSFRow secdrow=sheet.createRow(2);
				HSSFCell[] firstcell=new HSSFCell[CountColumnNum];
			
				for(int j=0;j<names.length;j++){
					firstcell[j]=secdrow.createCell(j);
					firstcell[j].setCellValue(new HSSFRichTextString(names[j]));
				}
				for (int i = 0; i < xls.size(); i++) {
		            // 创建一行
		            HSSFRow row = sheet.createRow(i + 5);
		            // 得到要插入的每一条记录
		            map = xls.get(i);
		            for (int colu = 0; colu < names.length; colu++) {
		                // 在一行内循环
		                HSSFCell xh = row.createCell(colu);
		                xh.setCellValue(map.get(names[colu]).toString());
		            }
		            
		        }
				
		return sheet;
	}
	
	
	public static void dataToExcel(List<HashMap<String,Object>> xls,String[] names) throws IOException{
		//获取总列数
		int CountColumnNum=xls.size();
		//创建Excel文档
		HSSFWorkbook hwb=new HSSFWorkbook();
		HashMap<String,Object> map=null;
		//sheet对应一个工作页
		HSSFSheet sheet=hwb.createSheet("pldrxkxxmb");
		HSSFRow firstrow=sheet.createRow(0);//下标为0的行开始
		HSSFCell[] firstcell=new HSSFCell[CountColumnNum];
		for(int j=0;j<names.length;j++){
			firstcell[j]=firstrow.createCell(j);
			firstcell[j].setCellValue(new HSSFRichTextString(names[j]));
			
		}
		for (int i = 0; i < xls.size(); i++) {
            // 创建一行
            HSSFRow row = sheet.createRow(i + 1);
            // 得到要插入的每一条记录
            map = xls.get(i);
            for (int colu = 0; colu < names.length; colu++) {
                // 在一行内循环
                HSSFCell xh = row.createCell(colu);
                xh.setCellValue(map.get(names[colu]).toString());
            }
        }
		//创建文件输出流，准比输出电子表格
		OutputStream out=new FileOutputStream("E:/test/kkk.xls");
		
		hwb.write(out);
		out.close();
		System.out.println("数据库导出成功");
	}
	

}
