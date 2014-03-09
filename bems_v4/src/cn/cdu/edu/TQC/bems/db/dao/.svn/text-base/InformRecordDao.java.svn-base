/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao;

import java.util.List;

import cn.cdu.edu.TQC.bems.db.bean.InformRecord;

/**
 * @ClassName: InformRecordDao
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:15:04
 *
 */
public interface InformRecordDao {
	
	public boolean addInformRecord(InformRecord informRecord);
	
	public boolean updateInformRecord(Object[] params);//回复更新
	
	public List<InformRecord> getInformRecordById(String informRecordid);
	
	public List<InformRecord> getInformRecords();
	
	public List<InformRecord> getRecordsBySender(String senderName);
	
	public List<InformRecord> getRecordsByAccepter(String accepterName);
	
	public List<InformRecord> getNoDeal(String accepterName);
	
	public boolean deleteRecord(Integer recordID);


}
