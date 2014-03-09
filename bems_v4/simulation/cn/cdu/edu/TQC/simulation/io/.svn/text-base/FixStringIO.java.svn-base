package cn.cdu.edu.TQC.simulation.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author LPM
 * @学校：成都大学、信息科学与技术学院
 * @团队：TQC
 * @FixStringIO
 * @说明：
 * 
 *  对固定长度字符串从文件读出、写入文件
 *  
 * 2012-6-11
 */
public class FixStringIO {
	// 读取固定长度的Unicode字符串。
		public static String readFixString(int size, DataInput in)
				throws IOException {
			StringBuffer b = new StringBuffer(size);
			int i = 0;
			boolean more = true;

			while (more && i < size) {
				char ch = in.readChar();
				i++;
				if (ch == 0) {
					more = false;
				} else {
					b.append(ch);
				}
			}
			// 跳过剩余的字节。
			in.skipBytes(2 * (size - i));
			return b.toString();
		}

		// 写入固定长度的Unicode字符串。
		public static void writeFixString(String s, int size, DataOutput out)
				throws IOException {
			int i;
			for (i = 0; i < size; i++) {
				char ch = 0;
				if (i < s.length()) {
					ch = s.charAt(i);
				}
				out.writeChar(ch);
			}
		}
}
