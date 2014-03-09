/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db.dao;

import java.util.List;

import cn.cdu.edu.TQC.bems.db.bean.Staff;

/**
 * @ClassName: StaffDao
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author SUN	【email:1096490965@qq.com】
 * @Version V1.0	2012-7-8 下午9:05:50
 *
 */
public interface StaffDao {
	
	public boolean addStaff(Staff staff);
	
	public boolean updateStaff(Staff staff);
	
	public Staff getStaffById(String staffId);
	
	
	public List<Staff> getStaffs();
	
	
	public boolean deleteStaff(String staffId);
	
	public boolean bulkDelete(String staffIdsStr);

	public boolean updateImage(String staffid, String fileName);

	boolean updateStaffAndPic(Staff staff);
	
	public Staff getStaffByPhoneNo(String PhoneNo);

}
