package cn.cdu.edu.TQC.simulation.timer;


import cn.cdu.edu.TQC.simulation.bean.SAmmeter;
import cn.cdu.edu.TQC.simulation.dao.SAmmeterDao;

/**
 * @ClassName: Task
 *
 * @Description: 
 * TODO(Tell the reader such role.)
 *
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-14 下午5:19:09
 *
 * @Version V1.0
 *
 */
public class test {
	public static void main(String args[])
	{
		SAmmeterDao dao = new SAmmeterDao();
		SAmmeter[] ammeters = dao.getAmmeters(); 
		
		
			for(SAmmeter aAmmeter : ammeters){
				System.out.println("-------------->"+aAmmeter.getaID() +"***" + aAmmeter.getTotalNum() +"%%" );
		
		}
		
	}
}
