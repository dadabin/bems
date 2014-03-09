package cn.cdu.edu.TQC.simulation.bean;

import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;

import cn.cdu.edu.TQC.simulation.io.FixStringIO;


/**
 * @ClassName: Ammeter
 *
 * @Description: 
 * TODO(Tell the reader such role.)
 *
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-14 下午3:03:10
 *
 * @Version V1.0
 *
 */
public class SAmmeter {
	
	private String aID;//仪表编号
	private boolean state;//状态 true 表示开，flase 表示关闭
	private double totalNum;//累计用量
	
	public static final int AMMETER_NO = 10; // 电表编号
	public static final int STATE = 1; //状态（开、关、忙）
	public static final int TOTAL_NUM = 8;//计数
	public static final int RECORD_LENGTH = AMMETER_NO * 2  + STATE + TOTAL_NUM;	
	public SAmmeter(){
		
	}
	public SAmmeter(String id,boolean state,double num){
		this.aID = id;
		this.state = state;
		this.totalNum = num;
	}
	public SAmmeter(String id,double num,int type){
		this.aID = id;
		this.totalNum = num;
	}
	
	public String getaID() {
		return aID;
	}
	public void setaID(String aID) {
		this.aID = aID;
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
	
	
		/**
		 * 设置输出格式。
		 */
		public String toString() {
			return getClass().getName() + "[floor=" + ",state=" + isState()
					+ ",totalNum=" + getTotalNum() + ",aID=" + getaID() + "]";
		}

		/**
		 * 写入一条固定长度的记录，即一个人的数据到输出流。
		 * @param out
		 * @throws IOException
		 */
		public void writeData(DataOutput out) throws IOException {
			FixStringIO.writeFixString(aID+"",AMMETER_NO, out);
			out.writeDouble(totalNum);
			out.writeBoolean(state);
		}

		/**
		 * 写入一条固定长度的记录到随机读取文件中。
		 * @param out
		 * @throws IOException
		 */
		private void writeData(RandomAccessFile out) throws IOException {
			FixStringIO.writeFixString(aID+"",AMMETER_NO, out);
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
	
			
			aID = FixStringIO.readFixString(AMMETER_NO, in);
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
}
