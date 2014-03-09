package cn.cdu.edu.TQC.simulation.timer;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.TimerTask;

import javax.naming.InitialContext;

import org.omg.CORBA.PRIVATE_MEMBER;

import cn.cdu.edu.TQC.bems.StringUtills;
import cn.cdu.edu.TQC.simulation.bean.SAmmeter;
import cn.cdu.edu.TQC.simulation.dao.SAmmeterDao;

/**
 * @ClassName: UpDateAmmeterTask
 * 
 * @Description: 更新电表数据。 TODO(Tell the reader such role.)
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-14 下午4:53:25
 * @Version V1.0
 * 
 */
public class UpDateAmmeterTask extends TimerTask {

    private SAmmeterDao dao;
    private int num;
    private SAmmeter[] ammets;

    public UpDateAmmeterTask() {
	dao = new SAmmeterDao();
	setNum(InitAmmeter.num);
	ammets = new SAmmeter[num];
    }

    @Override
    public void run() {
	// 获取所有的电表对象
	SAmmeter[] ammeters = dao.getAmmeters();
	// 在原来电表数据上累加，生成新的电表数据
	if (ammeters.length > 0) {
	    for (SAmmeter ammeter : ammeters) {

		int i = new StringUtills().productIntegerAmong(200, 100);
		Double d = new Random().nextDouble() + i
			+ ammeter.getTotalNum();
		DecimalFormat format = new DecimalFormat("######.##");
		double newNum = Double.parseDouble(format.format(d));

		ammeter.setTotalNum(newNum);
		System.out.println(ammeter.getaID() + "电表update--------->"
			+ ammeter.getTotalNum());
	    }
	    // 更新数据
	    dao.addAmmeters(ammeters);

	} else {
	    new InitAmmeter();
	}
    }

    public int getNum() {
	return num;
    }

    public void setNum(int num) {
	this.num = num;
    }

}
