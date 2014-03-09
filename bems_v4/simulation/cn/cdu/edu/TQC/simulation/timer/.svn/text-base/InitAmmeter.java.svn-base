package cn.cdu.edu.TQC.simulation.timer;

import java.util.TimerTask;

import cn.cdu.edu.TQC.simulation.bean.SAmmeter;
import cn.cdu.edu.TQC.simulation.dao.SAmmeterDao;

/**
 * @ClassName: InitAmmeter
 *
 * @Description: 
 * TODO(Tell the reader such role.)
 *
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-24 上午10:07:08
 *
 * @Version V1.0
 *
 */
public class InitAmmeter{
	
	private SAmmeter[] 		ammeters;
	private SAmmeterDao 		dao;
	public final static int 	num = 21;
	
	
	public InitAmmeter() {
		dao = new SAmmeterDao();
		if(ammeters == null){
			ammeters = new SAmmeter[num];
		}		
		for(int i = 0;i < num ; i++){
			ammeters[i] = new SAmmeter(20120000+i+1+"",true,0);
		}
		dao.addAmmeters(ammeters);
	}

	public SAmmeter[] getAmmeters() {
		return ammeters;
	}
	public void setAmmeters(SAmmeter[] ammeters) {
		this.ammeters = ammeters;
	}

	

}
