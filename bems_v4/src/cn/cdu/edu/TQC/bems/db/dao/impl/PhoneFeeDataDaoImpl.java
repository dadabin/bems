/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.db.dao.impl;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.AmmeterData;
import cn.cdu.edu.TQC.bems.db.bean.PhoneFeeData;
import cn.cdu.edu.TQC.bems.db.dao.PhoneFeeDataDao;

/**
 * @ClassName: PhoneFeeDataDaoImpl 说明： TODO(Tell the reader such role.)
 * @Author SUN 【email:1096490965@qq.com】
 * @Version V1.0 2012-7-8 下午8:53:54
 * 
 */
public class PhoneFeeDataDaoImpl implements PhoneFeeDataDao {
    private DBManager db = null;

    public PhoneFeeDataDaoImpl() {
	this.db = new DBManager();
    }

    @Override
    public boolean addPhoneFeeData(PhoneFeeData phoneFeeData) {
	boolean flag = false;
	String sql = "insert into phone_fee_data(PHONEFEEDATAID,"
		+ "PHONEID,TIME,FEE,STAFFID,PHONENUM) values (?,?,?,?,?,?)";
	Object[] params = new Object[6];
	params[0] = phoneFeeData.getPhoneFeeDataId();
	params[1] = phoneFeeData.getPhoneId();
	params[2] = phoneFeeData.getTime();
	params[3] = phoneFeeData.getFee();
	params[4] = phoneFeeData.getStaffId();
	params[5] = phoneFeeData.getPhoneNum();
	try {
	    int i = db.ExecuteNonQuery(sql, params);
	    if (i > 0) {
		flag = true;
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return flag;
    }

    @Override
    public boolean updatePhoneFeeData(PhoneFeeData phoneFeeData) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public PhoneFeeData getPhoneFeeDataById(Integer phoneFeeDataid) {
	PhoneFeeData feeData = new PhoneFeeData();
	String sql = "select * from phone_fee_data where PHONEFEEDATAID = ?";
	Object[] params = new Object[1];
	params[0] = phoneFeeDataid;
	List<HashMap<String, Object>> list = db.ExecuteQuery(sql, params);
	for(Map map:list){
	    feeData.setPhoneFeeDataId((Integer) map.get("PHONEFEEDATAID"));
		feeData.setFee((Double) map.get("FEE"));
		feeData.setPhoneId((Integer) map.get("PHONEID"));
		feeData.setStaffId((String) map.get("STAFFID"));
		feeData.setTime((Date) map.get("TIME"));
	}
	return feeData;
    }

    @Override
    public List<PhoneFeeData> getPhoneFeeDatas() {
	List<PhoneFeeData> all = new ArrayList<PhoneFeeData>();
	String sql = "select * from phone_fee_data";
	List<HashMap<String, Object>> list = db.ExecuteQuery(sql);
	for (Map map : list) {
	    PhoneFeeData feeData = new PhoneFeeData();
	    feeData.setPhoneFeeDataId((Integer) map.get("PHONEFEEDATAID"));
	    feeData.setFee((Double) map.get("FEE"));
	    feeData.setPhoneId((Integer) map.get("PHONEID"));
	    feeData.setStaffId((String) map.get("STAFFID"));
	    feeData.setTime((Date) map.get("TIME"));
	    all.add(feeData);
	}

	return all;
    }

    @Override
    public List<PhoneFeeData> getPhoneFeeDatasByPid(Integer pid) {
	List<PhoneFeeData> all = new ArrayList<PhoneFeeData>();
	String sql = "select * from phone_fee_data WHERE PHONEFEEID = ?";
	Object[] params = new Object[1];
	params[0] = pid;
	List<HashMap<String, Object>> list = db.ExecuteQuery(sql,params);
	for (Map map : list) {
	    PhoneFeeData feeData = new PhoneFeeData();
	    feeData.setPhoneFeeDataId((Integer) map.get("PHONEFEEDATAID"));
	    feeData.setFee((Double) map.get("FEE"));
	    feeData.setPhoneId((Integer) map.get("PHONEID"));
	    feeData.setStaffId((String) map.get("STAFFID"));
	    feeData.setTime((Date) map.get("TIME"));
	    all.add(feeData);
	}

	return all;
    }

    @Override
    public List<PhoneFeeData> getPhoneFeeDatas(int year, int month) {
	List<PhoneFeeData> all = new ArrayList<PhoneFeeData>();
	String sql = "select * from phone_fee_data where year(time)=? and month(time)=?";
	Object[] params = new Object[2];
	params[0] = year;
	params[1] = month;

	List<HashMap<String, Object>> list = db.ExecuteQuery(sql,params);
	for (Map map : list) {
	    PhoneFeeData feeData = new PhoneFeeData();
	    feeData.setPhoneFeeDataId((Integer) map.get("PHONEFEEDATAID"));
	    feeData.setFee((Double) map.get("FEE"));
	    feeData.setPhoneId((Integer) map.get("PHONEID"));
	    feeData.setStaffId((String) map.get("STAFFID"));
	    feeData.setTime((Date) map.get("TIME"));
	    feeData.setPhoneNum((String)map.get("PHONENUM"));
	    all.add(feeData);
	}
	return all;
    }

}
