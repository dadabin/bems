package cn.cdu.edu.TQC.simulation.dao;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import cn.cdu.edu.TQC.simulation.bean.SAmmeter;
import cn.cdu.edu.TQC.simulation.bean.SMeter;

/**
 * @ClassName: MeterDao
 *
 * @Description: 
 * TODO(Tell the reader such role.)
 *
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-14 下午4:35:41
 *
 * @Version V1.0
 *
 */
public class SMeterDao {
	private static String 	FileName = "MeterRandom.txt";
	
	/**
	 * 更新指定位置的数据
	 * @param meter
	 * @param n
	 */
	public void updateMeter(SMeter meter, int n){
		try {
			// 从二进制文件“peoplerandom.dat”中逆序读取数据。
			RandomAccessFile inOut = new RandomAccessFile(FileName,
					"rw");
			meter.writeData(inOut, n);
			inOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取所有的水表
	 * @return
	 */
	public SMeter[] getMeters(){
		SMeter meters[] = null;
		try {
			RandomAccessFile inOut = new RandomAccessFile(FileName,
					"rw");
			meters = readDataReverse(inOut);
			inOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meters;
	}
	
	/***
	 * 获取指定位置的水表 
	 * @param id
	 * @return
	 */
	public SMeter getMeterByItemID(int id){
		SMeter meter = new SMeter();
		try {
			RandomAccessFile inOut = new RandomAccessFile(FileName,
					"rw");
			meter.readData(inOut, id);
			inOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return meter;
	}
	
	/**
	 * 在文件末尾添加一个电表
	 * @param meter
	 */
	public void addMeter(SMeter meter){
		try {
			RandomAccessFile inOut = new RandomAccessFile(FileName,
					"rw");
			meter.writeData(inOut);
			inOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 添加若干水表
	 * @param meters
	 */
	public void addMeters(SMeter[] meters){
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					FileName));
			// 将人员数据保存至“peoplerandom.dat”二进制文件中。
			writeData(meters, out);
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
		//TODO 删除AmmeterRandom.txt文件
	}

	/**************************************************
	 **												**
	 **               	工具方法						**
	 **												**
	 *************************************************/
	
	/**
	 * 将数据从输入流中逆序读出。
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private SMeter[] readDataReverse(RandomAccessFile in) throws IOException {
		// 获得记录数目。
		int record_num = (int) (in.length() / SMeter.RECORD_LENGTH);
		SMeter[] m = new SMeter[record_num];
		// 逆序读取。
		for (int i = record_num - 1; i >= 0; i--) {
			m[i] = new SMeter();
			// 文件定位。
			in.seek(i * SAmmeter.RECORD_LENGTH);
			m[i].readData(in, i + 1);
		}
		return m;
	}

	/**
	 * 将数据写入输出流。
	 * @param a
	 * @param out
	 * @throws IOException
	 */
	private void writeData(SMeter[] m, DataOutputStream out)
			throws IOException {
		for (int i = 0; i < m.length; i++) {
			m[i].writeData(out);
		}
	}

}
