package cn.cdu.edu.TQC.simulation.dao;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import cn.cdu.edu.TQC.simulation.bean.SAmmeter;

/**
 * @ClassName: AmmterDao
 * 
 * @Description:实现电表的数据操作：加入多个电表 
 * TODO:
 * 
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-14 下午3:28:53
 * 
 * @Version V1.0
 * 
 */
public class SAmmeterDao {
	
	private static String FileName = "AmmeterRandom.txt";
	/**
	 * 更新电表
	 * @param ammeter
	 * @param n
	 */
	public void updateAmmeter(SAmmeter ammeter, int n) {
		try {
			// 从二进制文件“peoplerandom.dat”中逆序读取数据。
			RandomAccessFile inOut = new RandomAccessFile(FileName,
					"rw");
			ammeter.writeData(inOut, n);
			inOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有电表数据
	 * 
	 * @return
	 */
	public SAmmeter[] getAmmeters() {
		SAmmeter ammeters[] = null;
		try {
		    	System.out.println("-----------"+FileName);
			RandomAccessFile inOut = new RandomAccessFile(FileName,
					"rw");
			ammeters = readDataReverse(inOut);
			inOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ammeters;
	}

	/**
	 * 获取单个电表数据
	 * @param id
	 * @return
	 */
	public SAmmeter getAmmeterByItemId(int id) {

		SAmmeter ammeter = new SAmmeter();
		try {
			RandomAccessFile inOut = new RandomAccessFile(FileName,
					"rw");
			ammeter.readData(inOut, id);
			inOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ammeter;
	}

	/**
	 * @deprecated:添加电表,该方法没有实现
	 */
	public void addAmmeter(SAmmeter ammeter) {

		try {
			RandomAccessFile inOut = new RandomAccessFile(FileName,
					"rw");
			ammeter.writeData(inOut);
			inOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 添加若干电表
	 * @param ammeters
	 */
	public void addAmmeters(SAmmeter[] ammeters) {
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					FileName));
			// 将人员数据保存至“peoplerandom.dat”二进制文件中。
			writeData(ammeters, out);
			// 关闭流。
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重置所有电表信息
	 */
	public void rest() {
		//删除AmmeterRandom.txt文件
	}

	
	/**************************************************
	 **												**
	 **               	工具方法			**
	 **												**
	 *************************************************/
	
	/**
	 * 将数据从输入流中逆序读出。
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private SAmmeter[] readDataReverse(RandomAccessFile in) throws IOException {
		// 获得记录数目。
		int record_num = (int) (in.length() / SAmmeter.RECORD_LENGTH);
		SAmmeter[] a = new SAmmeter[record_num];
		// 逆序读取。
		for (int i = record_num - 1; i >= 0; i--) {
			a[i] = new SAmmeter();
			// 文件定位。
			in.seek(i * SAmmeter.RECORD_LENGTH);
			a[i].readData(in, i + 1);
		}
		return a;
	}

	/**
	 * 将数据写入输出流。
	 * @param a
	 * @param out
	 * @throws IOException
	 */
	private void writeData(SAmmeter[] a, DataOutputStream out)
			throws IOException {
		for (int i = 0; i < a.length; i++) {
			a[i].writeData(out);
		}
	}
	
	
	public static void main(String[] args){
	    
	    
	    
	    SAmmeter[] ammeters = new SAmmeterDao().getAmmeters();
	    for(SAmmeter ammeter : ammeters){
		System.out.println("-------------->"+ammeter.getTotalNum());
	    }
		    
	}
}
