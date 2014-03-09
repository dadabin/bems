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
import cn.cdu.edu.TQC.bems.db.bean.EmergncyGroup;
import cn.cdu.edu.TQC.bems.db.dao.EmergncyGroupDao;

/**
 * @ClassName: EmergncyGroupDaoImpl
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:30:48
 *
 */
public class EmergncyGroupDaoImpl implements EmergncyGroupDao {

	@Override
	public boolean addEmergncyGroup(EmergncyGroup emergncyGroup) {
		String sql="INSERT INTO emergncy_group (GROUPNAME,DESCRIPTION) VALUES (?,?)";
		Object[] params=new Object[2];
		params[0]=emergncyGroup.getGroupName();
		params[1]=emergncyGroup.getDescription();
	    DBManager db=new DBManager();
	    if(1==db.ExecuteNonQuery(sql,params)){
	    	return true;
	    }else{
	    	return false;
	    }
	}

	@Override
	public boolean updateEmergncyGroup(EmergncyGroup emergncyGroup) {
        String sql="UPDATE emergncy_group SET GROUPNAME=?,DESCRIPTION=? WHERE EMERGNCYGROUPID=?";
        Object[] params=new Object[]{emergncyGroup.getGroupName(),
        		                     emergncyGroup.getDescription(),
        		                     emergncyGroup.getEmergncyGroupId()};
        DBManager db=new DBManager();
        if(db.ExecuteNonQuery(sql,params)==1){
        	return true;
        }else{
		return false;
        }
	}

	@Override
	public EmergncyGroup getEmergncyGroupById(Integer emergncyGroupId) {
		String sql="SELECT EMERGNCYGROUPID,GROUPNAME,DESCRIPTION FROM emergncy_group WHERE EMERGNCYGROUPID=?";
		Object[] params=new Object[]{emergncyGroupId};
		DBManager db=new DBManager();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql,params);
		EmergncyGroup emergncyGroup = null;
		for(Map map:list){
			emergncyGroup.setEmergncyGroupId((Integer)map.get("EMERGNCYGROUPID"));
			emergncyGroup.setGroupName(map.get("GROUPNAME").toString());
			emergncyGroup.setDescription(map.get("DESCRIPTION").toString());
		}
		return emergncyGroup;
	}


	public List<EmergncyGroup> getEmergncyGroups() {
		String sql="SELECT EMERGNCYGROUPID,GROUPNAME,DESCRIPTION FROM emergncy_group";
		DBManager db=new DBManager();
		List<EmergncyGroup> emergncyGroups=new ArrayList<EmergncyGroup>();
		List<HashMap<String,Object>> list=db.ExecuteQuery(sql);
		for(Map map:list){
			EmergncyGroup emergncyGroup=new EmergncyGroup();
			emergncyGroup.setEmergncyGroupId((Integer)map.get("EMERGNCYGROUPID"));
			emergncyGroup.setGroupName(map.get("GROUPNAME").toString());
			emergncyGroup.setDescription(map.get("DESCRIPTION").toString());
			emergncyGroups.add(emergncyGroup);
		}
		
		return emergncyGroups;
	}
	
	
	public static void main(String[] args){
		EmergncyGroupDaoImpl e=new EmergncyGroupDaoImpl();
		e.getEmergncyGroups();
	}
	
	
	public boolean deleteEmergncyGroupByid(Integer emergncyGroupId){
		String sql="delete from emergncy_group where EMERGNCYGROUPID=?";
		Object[] params=new Object[]{emergncyGroupId};
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sql,params)==1){
			return true;
		}else{
			return false;
		}
	}

}
