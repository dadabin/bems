/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.simulation.timer;

import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cn.cdu.edu.TQC.bems.Constant;
import cn.cdu.edu.TQC.bems.Utils;
import cn.cdu.edu.TQC.bems.db.bean.AmmeterData;
import cn.cdu.edu.TQC.bems.db.dao.AmmeterDataDao;
import cn.cdu.edu.TQC.bems.db.dao.impl.AmmeterDataDaoImpl;
import cn.cdu.edu.TQC.simulation.bean.SAmmeter;
import cn.cdu.edu.TQC.simulation.dao.SAmmeterDao;

/**
 * @ClassName: CollectAmmeterData
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-9 下午6:53:48
 *
 */
public class CollectAmmeterData extends TimerTask{
    private AmmeterDataDao dao 		= null;
    private Integer 	runtime 	= 0;
    
    public CollectAmmeterData(){
	dao = new AmmeterDataDaoImpl();
    }
   
    @Override
    public void run() {
	SAmmeterDao dao1 = new SAmmeterDao();
	SAmmeter[] ammeters = dao1.getAmmeters();  
	    for(SAmmeter ammeter : ammeters){
		   
		    try {
			Date date = new Utils().addTime(Constant.BEGAIN_TIME, "day", 7* runtime);
			System.out.println("!!----------->"+date.toLocaleString());
			AmmeterData data = new AmmeterData();
			
			    data.setAmmeterId(ammeter.getaID());
			    data.setTime(date);
			    data.setData(ammeter.getTotalNum());
			    
			    dao.collectionData(data);
		    } catch (ParseException e) {
			e.printStackTrace();
		    }
		    
	    }
	runtime++;
	System.out.println("++++++++++++++++++++++++++++++");
    }
  
}
