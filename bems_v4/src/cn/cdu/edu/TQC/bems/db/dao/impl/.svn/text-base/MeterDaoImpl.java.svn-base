/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.Meter;
import cn.cdu.edu.TQC.bems.db.dao.MeterDao;

/**
 * @ClassName: MeterDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:45:33
 *
 */
public class MeterDaoImpl implements MeterDao{
    	private DBManager db = null;
    	
    	public MeterDaoImpl(){
    	    db = new DBManager();
    	}
    
	@Override
	public boolean addMeter(Meter meter) {
	    	boolean flag = false;
	    
		String sql = "insert into meter(METERID,TYPE,FLOORID,LOCATION) values (?,?,?,?)";
		Object[] params = new Object[4];
		params[0] = meter.getMeterId();
		params[1] = meter.getType();
		params[2] = meter.getFloorId();
		params[3] = meter.getLocation();
		try {
		    int i = db.ExecuteNonQuery(sql, params);
		    if(i>0){
			flag = true;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateMeter(Meter meter) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Meter getMeterById(Integer meterid) {
		Meter meter = new Meter();
		String sql = "select * from meter where METERID = ?";
		Object[] params = new Object[1];
		params[0] = meterid;
		List<HashMap<String, Object>> list =  db.ExecuteQuery(sql, params);
		for(Map map:list){
			meter.setFloorId(Integer.parseInt(map.get("FLOORID").toString()));
			meter.setLocation(map.get("LOCATION").toString());
			meter.setMeterId((String)map.get("METERID"));
			meter.setType(map.get("TYPE").toString());
		}
		return meter;
	}

	@Override
	public List<Meter> getMeters() {
		List<Meter> meters = new ArrayList<Meter>();
		try {
		    String sql = "select * from meter ";
		    List<HashMap<String, Object>> list =  db.ExecuteQuery(sql);
		    for(Map map : list){
			Meter meter = new Meter();
			meter.setFloorId(Integer.parseInt(map.get("FLOORID").toString()));
			meter.setLocation(map.get("LOCATION").toString());
			meter.setMeterId((String)map.get("METERID"));
			meter.setType(map.get("TYPE").toString());
			meters.add(meter);
		    }
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return meters;
	}

	
	public boolean isRepeat(Meter meter) {
		String sql="SELECT COUNT(*) FROM meter WHERE METERID=?";
		Object[] params=new Object[]{meter.getMeterId()};
		DBManager db=new DBManager();
		if(db.ExecuteScalar(sql,params).equals("1")){
			return true;
		}else{
		return false;
		}
	}

}
