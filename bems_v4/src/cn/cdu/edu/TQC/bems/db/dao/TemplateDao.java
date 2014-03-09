/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao;

import java.util.List;

import cn.cdu.edu.TQC.bems.db.bean.Template;

/**
 * @ClassName: BuildingDao
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-6 下午12:17:29
 *
 */
public interface TemplateDao {
    /**
     * 执行更新(无参)
     * @param sql
     * @return
     */
    public int upDate(String sql);
    
    /**
     * 多条语句更新
     * @param sql
     * @return
     */
    public int upDate(String[] sql);
    
    /**
     * 执行更新（带参） 
     * @param sql
     * @param paramters
     * @return
     */
    public int upDate(String sql,Object[] paramters);
    
    /**
     * 获取大楼对象（无参）
     * @param sql
     * @return
     */
    public Template getTemplate(String sql);
    
    /**
     * 获取大楼对象（带参）
     * @param sql
     * @param paramters
     * @return
     */
    public Template getTemplate(String sql,Object[] paramters);
    
    /**
     * 获取大楼集合（不带约束）
     * @param sql
     * @return
     */
    public List<Template> getTemplates(String sql);
    
    /**
     * 获取大楼集合(带约束)
     * @param sql
     * @param paramters
     * @return
     */
    public List<Template> getTemplates(String sql,Object[] paramters);

}
