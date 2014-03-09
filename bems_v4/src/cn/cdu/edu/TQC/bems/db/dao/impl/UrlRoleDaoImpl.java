package cn.cdu.edu.TQC.bems.db.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cdu.edu.TQC.bems.db.DBManager;
import cn.cdu.edu.TQC.bems.db.dao.UrlRoleDao;

/**
 *<p>版权所有:(c)2012-2013 sun</
 *@作者: Administrator
 *@时间: 2012-8-12 下午9:21:09
 *@描述: [UrlRoleDaoImpl]请在此简要描述类的功能
 *
 */

public class UrlRoleDaoImpl implements UrlRoleDao{

	@Override
	public boolean updateUrlRole(String sql[]) {
		DBManager db=new DBManager();
		if(db.ExecuteNonQuery(sql)==1){
			return true;
		}else{
		
		return false;
		}
	}

	@Override
	public List<String> getUrlByRoleId(Integer roleid) {
     DBManager db=new DBManager();
     List<String> relist=new ArrayList<String>();
    List<HashMap<String,Object>> list=db.ExecuteQuery("select url,role from url_role where role="+roleid);
    for(Map map:list){
    	relist.add(map.get("url").toString());
    }
		return relist;
	}

}


