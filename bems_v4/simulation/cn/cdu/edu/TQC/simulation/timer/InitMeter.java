package cn.cdu.edu.TQC.simulation.timer;

import cn.cdu.edu.TQC.simulation.bean.SAmmeter;
import cn.cdu.edu.TQC.simulation.bean.SMeter;
import cn.cdu.edu.TQC.simulation.dao.SAmmeterDao;
import cn.cdu.edu.TQC.simulation.dao.SMeterDao;

/**
 * @ClassName: InitMeter
 *
 * @Description: 
 * TODO(Tell the reader such role.)
 *
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-26 下午9:37:12
 *
 * @Version V1.0
 *
 */
public class InitMeter {
	private SMeter[] 		meters;
	private SMeterDao 		dao;
	public static int 		num = 8;
	

	public InitMeter(){
		dao = new SMeterDao();
		if(meters == null){
			meters = new SMeter[num];
		}
		for(int i = 0;i < num; i++){
			meters[i] = new SMeter(20000000+ 1 + i+"", true, 0);
		}
		dao.addMeters(meters);
	}
	
	public SMeter[] getMeters() {
		return meters;
	}
	public void setMeters(SMeter[] meters) {
		this.meters = meters;
	}
	
}
