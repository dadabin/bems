/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cdu.edu.TQC.bems.Utils;
import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.AmmeterData;
import cn.cdu.edu.TQC.bems.db.bean.MeterData;
import cn.cdu.edu.TQC.bems.db.dao.MeterDataDao;

/**
 * @ClassName: MeterDataDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:47:40
 *
 */
public class MeterDataDaoImpl implements MeterDataDao{
    private DBManager db = null;
    private Utils utils = null;
    
    
    public MeterDataDaoImpl(){
	this.db = new DBManager();
	this.utils = new Utils();
    }

	@Override
	public boolean addMeterData(MeterData meterData) {
	    boolean flag = false;
		String sql = "insert into meter_data(METERDATAID," +
				"DATA,TIME,METERID) values (?,?,?,?)";
		Object[] params = new Object[4];
		params[0] = meterData.getMeterDataId();
		params[1] = meterData.getData();
		params[2] = meterData.getTime();
		params[3] = meterData.getMeterId();
		try {
		    int i = db.ExecuteNonQuery(sql, params);
		    if(i > 0){
			flag = true;
		    }
		} catch (Exception e) {
		    // TODO: handle exception
		}
		return flag;
	}

	@Override
	public boolean updateMeterData(MeterData meterData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MeterData getMeterDataById(Integer meterDataid) {
	    MeterData meterData = new MeterData();
		String sql = "select * from meter_data where METERDATAID = ?";
		Object[] params = new Object[1];
		params[0] = meterDataid;
		List<HashMap<String, Object>> list =  db.ExecuteQuery(sql, params);
		for(Map map:list){
		    meterData.setMeterDataId((Integer)map.get("METERDATAID"));
			meterData.setMeterId((String)map.get("METERID"));
			meterData.setData(utils.getRounding( (Double)map.get("DATA") ));
			meterData.setTime((Date) map.get("TIME"));
		}
		
		return meterData;
	}

	@Override
	public List<MeterData> getMeterDatas() {
	    List<MeterData> all = new ArrayList<MeterData>();
	    String sql = "select * from meter_data";
	    List<HashMap<String, Object>> list =  db.ExecuteQuery(sql);
	    for(Map map : list){
		MeterData meterData = new MeterData();
		meterData.setMeterDataId((Integer)map.get("METERDATAID"));
		meterData.setMeterId((String)map.get("METERID"));
		meterData.setData(utils.getRounding( (Double)map.get("DATA") ));
		meterData.setTime((Date) map.get("TIME"));
		all.add(meterData);
	    }
	     
		return all;
	}
	public boolean collectMeterData(MeterData meterData) {
	    boolean flag = false;
		String sql = "insert into meter_data(" +
				"DATA,TIME,METERID) values (?,?,?)";
		Object[] params = new Object[3];
		params[0] = meterData.getData();
		params[1] = meterData.getTime();
		params[2] = meterData.getMeterId();
		try {
		    int i = db.ExecuteNonQuery(sql, params);
		    if(i > 0){
			flag = true;
		    }
		} catch (Exception e) {
		    // TODO: handle exception
		}
		return flag;
	}
	
	public List<MeterData> getMeterDatas(String sql) {
	    List<MeterData> all = new ArrayList<MeterData>();
	    List<HashMap<String, Object>> list =  db.ExecuteQuery(sql);
	    for(Map map : list){
		MeterData meterData = new MeterData();
		meterData.setMeterDataId((Integer)map.get("METERDATAID"));
		meterData.setMeterId((String)map.get("METERID"));
		meterData.setData(utils.getRounding( (Double)map.get("DATA") ));
		meterData.setTime((Date) map.get("TIME"));
		all.add(meterData);
	    }
	     
		return all;
	}
	
	public List<MeterData> getMeterDatas(String MeterID,String year,String mouth){
//	    String sql = "select * from meter_data where " +
//	    		"METERID='"+MeterID+"' AND year(time)='"+year+"' and MONTH(TIME)='"+mouth+"'  order by `time`";
	    String sql = "select METERID,`DATA`,year(time) as y ,MONTH(time) ,DAY(time) from meter_data where METERID=20000003 AND year(time)=2012 and MONTH(TIME) between 1 and 3  and day(time) between 1 and 31  order by `time`";
	    List<MeterData> all = new ArrayList<MeterData>();
	    List<HashMap<String, Object>> list =  db.ExecuteQuery(sql);
	    for(Map map : list){
//		MeterData meterData = new MeterData();
//		meterData.setMeterDataId((Integer)map.get("METERDATAID"));
//		meterData.setMeterId((String)map.get("METERID"));
//		meterData.setData((Double)map.get("DATA"));
//		meterData.setTime((Date) map.get("TIME"));
//		all.add(meterData);
		System.out.println(map.size()+"!!--------->"+map.get("y"));
	    }
	    return all;
	}

	@Override
	public List<MeterData> getMeterDatasByMeterID(Integer meterid) {
	    List<MeterData> all = new ArrayList<MeterData>();
	    String sql = "select * from meter_data where METERID = ?";
		Object[] params = new Object[1];
		params[0] = meterid;
	    List<HashMap<String, Object>> list =  db.ExecuteQuery(sql,params);
	    for(Map map : list){
		MeterData meterData = new MeterData();
		meterData.setMeterDataId((Integer)map.get("METERDATAID"));
		meterData.setMeterId((String)map.get("METERID"));
		meterData.setData(utils.getRounding( (Double)map.get("DATA") ));
		meterData.setTime((Date) map.get("TIME"));
		all.add(meterData);
	    }
	     
		return all;
	}
}
