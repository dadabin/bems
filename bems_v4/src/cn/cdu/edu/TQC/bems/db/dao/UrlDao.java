/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao;

import java.util.List;

import cn.cdu.edu.TQC.bems.db.bean.Url;

/**
 * @ClassName: UrlDao
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午8:17:33
 *
 */
public interface UrlDao {
	public boolean addUrl(Url url);
	
	public boolean updateUrl(Url url);
	
	public Url getUrlById(Integer urlId);
	
	public List<Url> getUrls();
	public boolean deleteUrlById(Integer urlId);
	

}
