package cn.cdu.edu.TQC.simulation.timer;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.TimerTask;

import cn.cdu.edu.TQC.bems.StringUtills;
import cn.cdu.edu.TQC.simulation.bean.SMeter;
import cn.cdu.edu.TQC.simulation.dao.SMeterDao;

/**
 * @ClassName: UpDateMeterTask
 * 
 * @Description: TODO(Tell the reader such role.)
 * 
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-26 下午9:37:36
 * 
 * @Version V1.0
 * 
 */
public class UpDateMeterTask extends TimerTask {

    private SMeterDao dao;
    private int num;
    private SMeter[] meters;

    public UpDateMeterTask() {
	this.dao = new SMeterDao();
	this.num = InitMeter.num;
	this.meters = new SMeter[num];
    }

    @Override
    public void run() {
	// 在原来数据上加入数据
	SMeter[] meters = dao.getMeters();
	if (meters.length > 0) {
	    for (SMeter meter : meters) {
		int i = new StringUtills().productIntegerAmong(12, 5);
		Double d = new Random().nextDouble() + i + meter.getTotalNum();

		DecimalFormat format = new DecimalFormat("######.##");
		double newNum = Double.parseDouble(format.format(d));

		meter.setTotalNum(newNum);
		System.out.println("水表update--------->" + meter.getTotalNum());
	    }
	    dao.addMeters(meters);
	} else {
	    new InitMeter();
	}
    }

    public int getNum() {
	return num;
    }

    public void setNum(int num) {
	this.num = num;
    }

}
