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
import cn.cdu.edu.TQC.bems.db.bean.EmergncyStaff;
import cn.cdu.edu.TQC.bems.db.bean.Staff;
import cn.cdu.edu.TQC.bems.db.dao.EmergncyStaffDao;

/**
 * @ClassName: EmergncyStaffDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-17 下午1:08:56
 *
 */
public class EmergncyStaffDaoImpl implements EmergncyStaffDao{

	@Override
	public boolean addEmergncyStaff(EmergncyStaff emergncyStaff) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmergncyStaff(EmergncyStaff emergncyStaff) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EmergncyStaff getEmergncyStaffById(EmergncyStaff emergncyStaff) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmergncyStaff> getEmergncyStaffs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public boolean deleteEmergncyStaff(Integer emergncyStaffId){
		String sql="delete from emergncy_staff where emergncystaffId=?";
		Object[] params=new Object[]{emergncyStaffId};
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sql,params)==1){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	public boolean deleteEmergncyStaffByStaffId(String staffId){
		String sql="delete from emergncy_staff where staffid=?";
		Object[] params=new Object[]{staffId};
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sql,params)==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Staff> getStaffByEmergncyGroupId(Integer groupId) {


		String sql="select s.STAFFID,s.NAME,s.PASSWORD,s.PHONENUMBER,es.position,s.EMAIL,s.ROLE,s.PHOTO from staff s,emergncy_staff es where es.staffid=s.staffid and es.emergncygroupid="+groupId;
		DBManager db=new DBManager();
		
	 List<HashMap<String,Object>> list=	db.ExecuteQuery(sql);
	 List<Staff> staffs=new ArrayList<Staff>();
	 for(Map map:list){
		   Staff staff=new Staff();
		   staff.setStaffId(map.get("STAFFID").toString());
		   staff.setName(map.get("NAME").toString());
		   staff.setPassword(map.get("PASSWORD").toString());
		   staff.setPhoneNumber(map.get("PHONENUMBER").toString());
		   staff.setEmail(map.get("EMAIL").toString());
		   staff.setRole(map.get("position").toString());
		   staff.setPhoto(map.get("PHOTO").toString());
		   staffs.add(staff);
	   }
	   
		return staffs;
	}

	@Override
	public boolean addEmergncyStaffs(String[] sqls) {
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sqls)==1){
			return true;
		}else{
		
		return false;
		}
	}

}
