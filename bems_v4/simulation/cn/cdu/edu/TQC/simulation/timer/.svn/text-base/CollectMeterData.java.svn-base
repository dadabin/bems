/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.simulation.timer;

import java.text.ParseException;
import java.util.Date;
import java.util.TimerTask;

import cn.cdu.edu.TQC.bems.Constant;
import cn.cdu.edu.TQC.bems.Utils;
import cn.cdu.edu.TQC.bems.db.bean.MeterData;
import cn.cdu.edu.TQC.bems.db.dao.MeterDataDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.MeterDataDaoImpl;
import cn.cdu.edu.TQC.simulation.bean.SMeter;
import cn.cdu.edu.TQC.simulation.dao.SMeterDao;

/**
 * @ClassName: CollectMeterData
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-9 下午6:54:06
 *
 */
public class CollectMeterData extends TimerTask{
    private MeterDataDao dao	= null;
    private SMeterDao 	sdao 	= null;
    private Integer 	runtime = 0;
    
    public CollectMeterData(){
	this.dao = new MeterDataDaoImpl();
	this.sdao = new SMeterDao(); 
    }
    
    @Override
    public void run() {
	SMeter[] datas = sdao.getMeters();
	//TODO 将Smeter中的数据全部添加到数据库中
	for(SMeter meter : datas){
	    Date date = null;
	    try {
		date = new Utils().addTime(Constant.BEGAIN_TIME, "day", 7* runtime);
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	    MeterData mData = new MeterData();
	    
	    mData.setData(meter.getTotalNum());
	    mData.setTime(date);
	    mData.setMeterId(meter.getmID());
	    new MeterDataDaoImpl().collectMeterData(mData);
	}
	runtime++ ;
    }

}
