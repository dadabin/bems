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
import cn.cdu.edu.TQC.bems.db.bean.Phone;
import cn.cdu.edu.TQC.bems.db.dao.PhoneDao;

/**
 * @ClassName: PhomeDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:50:49
 *
 */
public class PhoneDaoImpl implements PhoneDao{

	@Override
	public boolean addPhone(Phone phone) {
		String sql="INSERT INTO phone (PHONENUMBER,FLOORID,HOUSENUMBER,PHONEPOWER,PERSONSRESPONESIBLEFOR) VALUES (?,?,?,?,?)";
		Object[] params=new Object[5];
		params[0]=phone.getPhoneNumber();
		params[1]=phone.getFloorId();
		params[2]=phone.getHouseNumber();
		params[3]=phone.getPhonePower();
		params[4]=phone.getPersonsresPoneSibleFor();
		DBManager db=new DBManager();
		if(0<db.ExecuteNonQuery(sql,params)){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean updatePhone(Phone phone) {
		
		return false;
	}

	@Override
	public Phone getPhoneById(Integer phoneId) {
		String sql="SELECT PHONEID,PHONENUMBER,FLOORID,HOUSENUMBER,PHONEPOWER,PERSONSRESPONESIBLEFOR From phone WHERE PHONEID=?";
		Object[] params=new Object[1];
		params[0]=phoneId;
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql,params);
		Phone phone=new Phone(
				Integer.parseInt(list.get(0).get("PHONEID").toString()),
				list.get(0).get("PHONENUMBER").toString(),
				Integer.parseInt(list.get(0).get("FLOORID").toString()),
				list.get(0).get("HOUSENUMBER").toString(),
				list.get(0).get("PHONEPOWER").toString(),
				list.get(0).get("PERSONSRESPONESIBLEFOR").toString()
				);
		return phone;
	}

	@Override
	public List<Phone> getPhones() {
		String sql="SELECT PHONEID,PHONENUMBER,FLOORID,HOUSENUMBER,PHONEPOWER,PERSONSRESPONESIBLEFOR From phone";
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		List<Phone> phones=new ArrayList<Phone>();
		for(Map map:list){
			Phone phone=new Phone(
					Integer.parseInt(map.get("PHONEID").toString()),
					map.get("PHONENUMBER").toString(),
					Integer.parseInt(map.get("FLOORID").toString()),
					map.get("HOUSENUMBER").toString(),
					map.get("PHONEPOWER").toString(),
					map.get("PERSONSRESPONESIBLEFOR").toString()
					);
			
			phones.add(phone);
			
		}
		return phones;
	}

}
