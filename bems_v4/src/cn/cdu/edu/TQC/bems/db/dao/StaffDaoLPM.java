/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao;

import java.util.List;

import cn.cdu.edu.TQC.bems.db.bean.StaffLPM;

/**
 * @ClassName: StaffDaoLPM
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-5 下午5:58:34
 *
 */
public interface StaffDaoLPM {
    public List<StaffLPM> getStaffs();
    public List<StaffLPM> getStaffs(String[] paramters);
}
