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
import cn.cdu.edu.TQC.bems.db.bean.Staff;
import cn.cdu.edu.TQC.bems.db.dao.StaffDao;

/**
 * @ClassName: StaffDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午9:07:14
 *
 */
public class StaffDaoImpl implements StaffDao{

	@Override
	public boolean addStaff(Staff staff) {
		String sql="insert into staff(staffid,name,password,PHONENUMBER,email,role,photo) values (?,?,?,?,?,?,?)";
	    Object[] params=new Object[]{
	    		staff.getStaffId(),
	    		staff.getName(),
	    		staff.getPassword(),
	    		staff.getPhoneNumber(),
	    		staff.getEmail(),
	    		staff.getRole(),
	    		staff.getPhoto()
	    };
	    DBManager db=new DBManager();
	    if(db.ExecuteNonQuery(sql,params)==1){
	    	return true;
	    }else{
		return false;
	    }
	}

	@Override
	public boolean updateStaffAndPic(Staff staff) {
		String sql="update staff set name=?,password=?,phonenumber=?,email=?,role=?,photo=? where staffid=?";
		Object[] params=new Object[]{
				staff.getName(),
				staff.getPassword(),
				staff.getPhoneNumber(),
				staff.getEmail(),
				staff.getRole(),
				staff.getPhoto(),
				staff.getStaffId()
		};
		
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sql,params)==1){
			return true;
		}else{
		
		return false;
		}
	}

	/**
	 * 根据ID查询用户基本信息
	 */
	@Override
	public Staff getStaffById(String staffId) {
		String sql="SELECT STAFFID,NAME,PASSWORD,PHONENUMBER,EMAIL,ROLE,PHOTO FROM staff WHERE STAFFID=?";
		Object[] params=new Object[]{staffId};
		DBManager db=new DBManager();
		
	    List<HashMap<String,Object>> list=db.ExecuteQuery(sql,params);
	    
	    Staff staff=new Staff();
	   for(Map map:list){
		   staff.setStaffId(map.get("STAFFID").toString());
		   staff.setName(map.get("NAME").toString());
		   staff.setPassword(map.get("PASSWORD").toString());
		   staff.setPhoneNumber(map.get("PHONENUMBER").toString());
		   staff.setEmail(map.get("EMAIL").toString());
		   staff.setRole(map.get("ROLE").toString());
		   staff.setPhoto(map.get("PHOTO").toString());
	   }
	   
		return staff;
	}

	/**
	 * sun 2012-7-15
	 * 查询所有的人员信息
	 */
	@Override
	public List<Staff> getStaffs() {
		
		String sql="SELECT STAFFID,NAME,PASSWORD,PHONENUMBER,EMAIL,ROLE,PHOTO FROM staff ";
	
		DBManager db=new DBManager();
		
	    List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
	    List<Staff> staffs=new ArrayList<Staff>();
	   for(Map map:list){
		   Staff staff=new Staff();
		   staff.setStaffId(map.get("STAFFID").toString());
		   staff.setName(map.get("NAME").toString());
		   staff.setPassword(map.get("PASSWORD").toString());
		   staff.setPhoneNumber(map.get("PHONENUMBER").toString());
		   staff.setEmail(map.get("EMAIL").toString());
		   staff.setRole(map.get("ROLE").toString());
		   staff.setPhoto(map.get("PHOTO").toString());
		   staffs.add(staff);
	   }
	   
		return staffs;
	}

	/**
	 * sun 2012-7-15
	 * 根据Id删除人员
	 */
	public boolean deleteStaff(String staffId) {
		String sql="delete from staff where STAFFID=?";
		Object[] params=new Object[]{staffId};
		DBManager db=new DBManager();
	    if(db.ExecuteNonQuery(sql,params)==1){
	    	return true;
	    }else{
		    return false;
	    }
	}
	
	/**
	 * sun  2012-7-15
	 * 批量删除
	 * @param staffIdStr
	 * @return
	 */
	public boolean bulkDelete(String  staffIdStr){
		String sql="delete from staff where STAFFID in ("+staffIdStr+")";
		
		DBManager db=new DBManager();
	    if(db.ExecuteNonQuery(sql)>=1){
	    	return true;
	    }else{
		    return false;
	    }
	}

	@Override
	public boolean updateImage(String staffid, String fileName) {
		String sql="update staff set photo=? where staffid=?";
		Object[] params=new Object[]{fileName,staffid};
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sql,params)==1){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public boolean updateStaff(Staff staff) {
		String sql="update staff set name=?,password=?,phonenumber=?,email=?,role=? where staffid=?";
		Object[] params=new Object[]{
				staff.getName(),
				staff.getPassword(),
				staff.getPhoneNumber(),
				staff.getEmail(),
				staff.getRole(),
				staff.getStaffId()
		};
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sql,params)==1){
			return true;
		}else{
		
		return false;
		}
	}

	public String getUrlByRole(Integer role){
		
		String sql="select u.urlid,u.parentid, u.url,u.urlname from url u,url_role ur where ur.url=u.urlid and ur.role=?";
		Object[] params=new Object[]{role};
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql,params);
		
		return null;
	}

	@Override
	public Staff getStaffByPhoneNo(String PhoneNo) {
	    String sql="SELECT STAFFID,NAME,PASSWORD,PHONENUMBER,EMAIL,ROLE,PHOTO FROM staff WHERE PHONENUMBER=?";
		Object[] params=new Object[]{PhoneNo};


		DBManager db=new DBManager();
		
	    List<HashMap<String,Object>> list=db.ExecuteQuery(sql,params);
	    
	    Staff staff=new Staff();
	   for(Map map:list){
		   staff.setStaffId(map.get("STAFFID").toString());
		   staff.setName(map.get("NAME").toString());
		   staff.setPassword(map.get("PASSWORD").toString());
		   staff.setPhoneNumber(map.get("PHONENUMBER").toString());
		   staff.setEmail(map.get("EMAIL").toString());
		   staff.setRole(map.get("ROLE").toString());
		   staff.setPhoto(map.get("PHOTO").toString());
	   }
	   
		return staff;
	}
	
	public static void main(String[] args){
//		String sql="select * from staff";
//		DBManager db=new DBManager();
//		System.out.println(db.ExecuteNonQuery("insert into staff(staffid,name,password,phonenumber,email,role,photo) values('好','好','1234','1234','1234',1,'123412')"));
//		System.out.println(db.ExecuteQuery(sql));
	    
	}


}
