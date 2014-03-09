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

import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.bean.InformRecord;
import cn.cdu.edu.TQC.bems.db.dao.InformRecordDao;

/**
 * @ClassName: InformRecordDaoImpl 说明： TODO(Tell the reader such role.)
 * @Author SUN 【email:1096490965@qq.com】
 * @Version V1.0 2012-7-8 下午8:40:09
 * 
 */
public class InformRecordDaoImpl implements InformRecordDao {

    @Override
    public boolean addInformRecord(InformRecord informRecord) {
	// INSERT INTO inform_record
	// (INFORMPERSON,ACCEPTPERSON,TYPE,TITTLE,CONTENT) values
	// ('lpm','QFP',1,'漏水了','大家赶快来修理水管！')
	String sql = "INSERT INTO inform_record (INFORMPERSON,ACCEPTPERSON,TYPE,TITTLE,CONTENT,SENDTIME) values (?,?,?,?,?,?)";
	Object[] params = new Object[6];

	params[0] = informRecord.getInformPerson();
	params[1] = informRecord.getAcceptPerson();
	params[2] = informRecord.getType();
	params[3] = informRecord.getTittle();
	params[4] = informRecord.getContent();
	params[5] = informRecord.getSendTime();

	DBManager db = new DBManager();
	if (0 < db.ExecuteNonQuery(sql, params)) {
	    return true;
	} else {
	    return false;
	}
    }

    @Override
    public boolean updateInformRecord(Object[] params) {
	boolean flag = false;
	String sql = "update inform_record set PEPLAY=? , PEPLAYTIME = ? ,ISDEAL='1' where ACCEPTPERSON = ? and ISDEAL = '0'";
	DBManager db = new DBManager();
	int i = db.ExecuteNonQuery(sql, params);
	if(i>0){
	    flag = true;
	}
	return flag;
    }

    @Override
    public List<InformRecord> getInformRecordById(String informRecordid) {
	String sql = "select * from inform_record where INFORMRECORDID="+informRecordid;
	DBManager db = new DBManager();
	List<InformRecord> list = new ArrayList<InformRecord>();
	List<HashMap<String, Object>> all = db.ExecuteQuery(sql);
	for (Map map : all) {
	    InformRecord record = new InformRecord();

	    record.setAcceptPerson((String) map.get("ACCEPTPERSON"));
	    record.setContent((String) map.get("CONTENT"));
	    record.setInformPerson((String) map.get("INFORMPERSON"));
	    record.setType((Integer) map.get("TYPE"));
	    record.setTittle((String) map.get("TITTLE"));
	    record.setSendTime((Date) map.get("SENDTIME"));
	    record.setInformRecordId((Integer)map.get("INFORMRECORDID"));

	    if (map.containsKey("ISDEAL") && map.get("ISDEAL") != null) {
		record.setPeplayTime((Date) map.get("PEPLAYTIME"));
		record.setIsDeal((Integer) map.get("ISDEAL"));
		record.setPeplay((String) map.get("PEPLAY"));
	    }

	    list.add(record);
	}
	return list;
    }

    
    /**
     * 返回所有消息记录
     */
    @Override
    public List<InformRecord> getInformRecords() {

	List<InformRecord> list = new ArrayList<InformRecord>();
	String sql = "select * from inform_record";
	DBManager db = new DBManager();

	List<HashMap<String, Object>> all = db.ExecuteQuery(sql);
	for (Map map : all) {
	    InformRecord record = new InformRecord();

	    record.setAcceptPerson((String) map.get("ACCEPTPERSON"));
	    record.setContent((String) map.get("CONTENT"));
	    record.setInformPerson((String) map.get("INFORMPERSON"));
	    record.setType((Integer) map.get("TYPE"));
	    record.setTittle((String) map.get("TITTLE"));
	    record.setSendTime((Date) map.get("SENDTIME"));
	    record.setInformRecordId((Integer)map.get("INFORMRECORDID"));

	    if (map.containsKey("ISDEAL") && map.get("ISDEAL") != null) {
		record.setPeplayTime((Date) map.get("PEPLAYTIME"));
		record.setIsDeal((Integer) map.get("ISDEAL"));
		record.setPeplay((String) map.get("PEPLAY"));
	    }

	    list.add(record);
	}
	return list;
    }

    /**
     * 返回发送消息的记录
     */
    @Override
    public List<InformRecord> getRecordsBySender(String senderName) {
	List<InformRecord> list = new ArrayList<InformRecord>();
	String sql = "select * from inform_record where INFORMPERSON = '"
		+ senderName + "'";
	DBManager db = new DBManager();

	List<HashMap<String, Object>> all = db.ExecuteQuery(sql);
	for (Map map : all) {
	    InformRecord record = new InformRecord();

	    record.setAcceptPerson((String) map.get("ACCEPTPERSON"));
	    record.setContent((String) map.get("CONTENT"));
	    record.setInformPerson((String) map.get("INFORMPERSON"));
	    record.setType((Integer) map.get("TYPE"));
	    record.setTittle((String) map.get("TITTLE"));
	    record.setSendTime((Date) map.get("SENDTIME"));
	    record.setInformRecordId((Integer)map.get("INFORMRECORDID"));

	    if (map.containsKey("ISDEAL") && map.get("ISDEAL") != null) {
		record.setPeplayTime((Date) map.get("PEPLAYTIME"));
		record.setIsDeal((Integer) map.get("ISDEAL"));
		record.setPeplay((String) map.get("PEPLAY"));
	    }

	    list.add(record);
	}
	return list;
    }

    /***
     * 返回接受消息的记录
     */
    @Override
    public List<InformRecord> getRecordsByAccepter(String accepterName) {
	List<InformRecord> list = new ArrayList<InformRecord>();
	String sql = "select * from inform_record where ACCEPTPERSON = '"
		+ accepterName + "'";
	DBManager db = new DBManager();

	List<HashMap<String, Object>> all = db.ExecuteQuery(sql);
	for (Map map : all) {
	    InformRecord record = new InformRecord();

	    record.setAcceptPerson((String) map.get("ACCEPTPERSON"));
	    record.setContent((String) map.get("CONTENT"));
	    record.setInformPerson((String) map.get("INFORMPERSON"));
	    record.setType((Integer) map.get("TYPE"));
	    record.setTittle((String) map.get("TITTLE"));
	    record.setSendTime((Date) map.get("SENDTIME"));
	    record.setInformRecordId((Integer)map.get("INFORMRECORDID"));

	    if (map.containsKey("ISDEAL") && map.get("ISDEAL") != null) {
		record.setPeplayTime((Date) map.get("PEPLAYTIME"));
		record.setIsDeal((Integer) map.get("ISDEAL"));
		record.setPeplay((String) map.get("PEPLAY"));
	    }

	    list.add(record);
	}
	return list;
    }

    /**
     * 返回没有处理的消息列表
     */
    @Override
    public List<InformRecord> getNoDeal(String accepterName) {
	List<InformRecord> list = new ArrayList<InformRecord>();
	String sql = "select * from inform_record where ACCEPTPERSON = '"
		+ accepterName + "'";
	DBManager db = new DBManager();

	List<HashMap<String, Object>> all = db.ExecuteQuery(sql);
	for (Map map : all) {
	    InformRecord record = new InformRecord();

	    record.setAcceptPerson((String) map.get("ACCEPTPERSON"));
	    record.setContent((String) map.get("CONTENT"));
	    record.setInformPerson((String) map.get("INFORMPERSON"));
	    record.setType((Integer) map.get("TYPE"));
	    record.setTittle((String) map.get("TITTLE"));
	    record.setSendTime((Date) map.get("SENDTIME"));
	    record.setInformRecordId((Integer)map.get("INFORMRECORDID"));

	    if (map.containsKey("ISDEAL") && map.get("ISDEAL") != null) {
		record.setPeplayTime((Date) map.get("PEPLAYTIME"));
		record.setIsDeal((Integer) map.get("ISDEAL"));
		record.setPeplay((String) map.get("PEPLAY"));
	    }
	    
	    if(record.getIsDeal() == 0 || record.getPeplay().equals("") || record == null ){
		list.add(record);
	    }
	    
	}
	return list;
    }


    @Override
    public boolean deleteRecord(Integer recordID) {
//	delete  from inform_record where INFORMRECORDID = 1;
	boolean flag = false;
	String sql = "delete  from inform_record where INFORMRECORDID = "+recordID;
	DBManager db = new DBManager();
	
	int result  =  db.ExecuteNonQuery(sql);
	if(result > 1){
	    flag = true;
	}
	return flag;
    }

}
