package cn.cdu.edu.TQC.simulation.bean;

import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;

import cn.cdu.edu.TQC.simulation.io.FixStringIO;

/**
 * @ClassName: Meter
 *
 * @Description: 
 * TODO(Tell the reader such role.)
 *
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-14 下午3:05:24
 *
 * @Version V1.0
 *
 */
public class SMeter {
	private String 		mID;//仪表编号
	private boolean 	state;//状态 true 表示开，flase 表示关闭
	private double 		totalNum;//累计用量
	
	public static final int 	METER_NO = 10; // 电表编号
	public static final int 	TOTAL_NUM = 8;//计数
	public static final int 	STATE = 1; //状态（开、关、忙）
	public static final int 	RECORD_LENGTH = METER_NO * 2  + TOTAL_NUM + STATE;
	
	
	
	/***************************************
	 * 		构造器	               *
	 ***************************************/
	public SMeter(){
		
	}
	public SMeter(String id,boolean state,double num){
		this.mID = id;
		this.totalNum = num;
		this.state = state;
	}
	
	/***************************************
	 * 				接口方法				   *
	 ***************************************/
	
	/**
	 * 写入一条固定长度的记录，即一个人的数据到输出流。
	 * @param out
	 * @throws IOException
	 */
	public void writeData(DataOutput out) throws IOException {
		FixStringIO.writeFixString(mID+"",METER_NO, out);
		out.writeDouble(totalNum);
		out.writeBoolean(state);
	}

	/**
	 * 写入一条固定长度的记录到随机读取文件中。
	 * @param out
	 * @throws IOException
	 */
	private void writeData(RandomAccessFile out) throws IOException {
		FixStringIO.writeFixString(mID+"",METER_NO, out);
		out.writeDouble(totalNum);
		out.writeBoolean(state);
	}

	// 随机写入一条固定长度的记录到输出流的指定位置。
	public void writeData(RandomAccessFile out, int n) throws IOException {
		out.seek((n - 1) * RECORD_LENGTH);
		writeData(out);
	}

	/**
	 * 从输入流随机读出一条记录，即一个人的数据。
	 * @param in
	 * @throws IOException
	 */
	private void readData(RandomAccessFile in) throws IOException {
		
		mID = FixStringIO.readFixString(METER_NO, in);
		totalNum = in.readDouble();
		state = in.readBoolean();
	}

	/**
	 * 从输入流随机读入指定位置的记录。
	 * @param in
	 * @param n
	 * @throws IOException
	 */
	public void readData(RandomAccessFile in, int n) throws IOException {
		in.seek((n - 1) * RECORD_LENGTH);
		readData(in);
	}
	
	
	/***************************************
	 * 	setter and getter	       *
	 ***************************************/
	
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public double getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(double totalNum) {
		this.totalNum = totalNum;
	}	
}
