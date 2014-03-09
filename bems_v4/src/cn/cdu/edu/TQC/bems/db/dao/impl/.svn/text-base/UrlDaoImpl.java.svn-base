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
import cn.cdu.edu.TQC.bems.db.bean.Url;
import cn.cdu.edu.TQC.bems.db.dao.UrlDao;

/**
 * @ClassName: UrlDaoImpl 说明： TODO(Tell the reader such role.)
 * @Author SUN 【email:1096490965@qq.com】
 * @Version V1.0 2012-7-8 下午9:05:14
 * 
 */
public class UrlDaoImpl implements UrlDao {

	@Override
	public boolean addUrl(Url url) {
		String sql = "INSERT INTO url (URL,URLNAME,PARENTID) VALUES (?,?,?)";
		Object[] params = new Object[] { url.getUrl(), url.getUrlName(),url.getParentId() };
		DBManager db = new DBManager();
		if (1 == db.ExecuteNonQuery(sql, params)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUrl(Url url) {
		String sql = "UPDATE url SET URL=?,URLNAME=? WHERE URLID=?";
		Object[] params = new Object[] { url.getUrl(), url.getUrlName(),
				url.getUrlId() };
		DBManager db = new DBManager();
		if (1 == db.ExecuteNonQuery(sql, params)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Url getUrlById(Integer urlId) {
		String sql = "SELECT URLID,URL,URLNAME FROM url WHERE URLID=?";
		Object[] params = new Object[] { urlId };
		DBManager db = new DBManager();
		return null;
	}

	public List<Url> getUrls() {
		// TODO Auto-generated method stub
		String sql = "SELECT URLID,URL,URLNAME FROM url";
		DBManager db = new DBManager();
		List<HashMap<String, Object>> list = db.ExecuteQuery(sql);
		List<Url> urls = new ArrayList<Url>();
		for (Map map : list) {
			Url url = new Url();
			url.setUrlId((Integer) map.get("URLID"));
			url.setUrl(map.get("URL").toString());
			url.setUrlName(map.get("URLNAME").toString());
			urls.add(url);

		}
		return urls;
	}

	public boolean deleteUrlById(Integer urlId) {
		String sql = "delete from url where UrlId=?";
		Object[] params = new Object[] { urlId };
		DBManager db = new DBManager();
		if (db.ExecuteNonQuery(sql, params) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	

}
