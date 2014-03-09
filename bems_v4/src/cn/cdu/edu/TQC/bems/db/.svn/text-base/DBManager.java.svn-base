/**
 * @Copyright (c) 成都大学信息科学与技术学院
 * 服务外包与创意大赛
 */
package cn.cdu.edu.TQC.bems.db;

import java.nio.charset.Charset;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: DBManager 说明： 已经测试通过
 * @Author LPM 【email:shouli1990@gmail.com 】
 * @Version V1.0 2012-7-5 下午6:02:55
 * 
 */
public class DBManager extends DB {

	/***********************
	 * construction *
	 **********************/

	public DBManager() {
		super();
	}

	/**
	 * 更新(无参)
	 * 
	 * @param sql
	 * @return
	 */
	public int ExecuteNonQuery(String sql) {
		int retNum = -1;
		try {
			Connection conn = this.getConn();
			Statement stat = null;
			stat = conn.createStatement();
			retNum = stat.executeUpdate(sql);
			this.colse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retNum;
	}

	/**
	 * 同时执行多条更新
	 * 
	 * @param sqls
	 * @return
	 */
	public int ExecuteNonQuery(String[] sqls) {
		int reNumber = -1;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = this.getConn();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (int i = 0; i < sqls.length; i++) {
				stmt.executeUpdate(sqls[i]);
			}
			conn.commit();
			reNumber = 1;

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.colse();
		}
		return reNumber;
	}

	/**
	 * 更新(带参)
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int ExecuteNonQuery(String sql, Object[] params) {
		int reNumber = -1;
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection conn = this.getConn();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			reNumber = ps.executeUpdate();
			this.colse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reNumber;
	}

	/**
	 * 查询（返回单个对象）
	 * 
	 * @param sql
	 * @return
	 */
	public Object ExecuteScalar(String sql) {
		Connection conn = this.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object ob = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				ob = rs.getObject(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.colse();
		}
		return ob;
	}

	/**
	 * 查询（返回多个对象（多参））
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object ExecuteScalar(String sql, Object[] params) {
		Object reNumber = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = this.getConn();
		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
				rs = ps.executeQuery();
				if (rs.next()) {
					reNumber = rs.getObject(1);
					// System.out.println("--->"+reNumber.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.colse();
			}
		} else {
			System.out.println("Connection is null");
		}

		return reNumber;
	}

	/**
	 * 查询（返回集合）
	 * 
	 * @param sql
	 * @return
	 */
	public List<HashMap<String, Object>> ExecuteQuery(String sql) {
		List<HashMap<String, Object>> datas = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		Connection conn = this.getConn();
		if (conn != null) {
			try {
				sta = conn.prepareStatement(sql);
				rs = sta.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int recount = rsmd.getColumnCount();
				String[] colLabels = new String[recount];
				for (int i = 0; i < recount; i++) {
					colLabels[i] = rsmd.getColumnLabel(i + 1);
				}
				datas = new ArrayList<HashMap<String, Object>>();
				while (rs.next()) {
					rs.getObject(1);
					HashMap<String, Object> data = new HashMap<String, Object>();
					for (int i = 0; i < colLabels.length; i++) {
						data.put(colLabels[i], rs.getObject(colLabels[i]));

						// System.out.println(colLabels[i]
						// +"----->"+data.get(colLabels[i]));

					}
					datas.add(data);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.colse();
			}
		} else {
			System.out.println("Connection is null");
		}
		return datas;
	}

	/**
	 * 查询（返回集合（带参））
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> ExecuteQuery(String sql,
			Object[] params) {
		List<HashMap<String, Object>> datas = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = this.getConn();
		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
				rs = ps.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int recount = rsmd.getColumnCount();
				String[] colLabels = new String[recount];
				for (int i = 0; i < recount; i++) {
					colLabels[i] = rsmd.getColumnLabel(i + 1);
				}
				datas = new ArrayList<HashMap<String, Object>>();
				while (rs.next()) {
					HashMap<String, Object> data = new HashMap<String, Object>();
					for (int i = 0; i < colLabels.length; i++) {
						data.put(colLabels[i], rs.getObject(colLabels[i]));
					}
					datas.add(data);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.colse();
			}

		} else {
			System.out.println("Connection is null");
		}
		return datas;
	}

	/**
	 * 执行存储过程语句
	 * 
	 * @param sql
	 * @return
	 */
	public int ExecuteNonQueryProc(String sql) {
		int reNumber = -1;
		Connection conn = this.getConn();
		CallableStatement proc = null;
		try {
			proc = conn.prepareCall(sql);
			boolean blen = proc.execute();
			if (blen)
				reNumber = 1;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			this.colse();
		}
		return reNumber;

	}

	public static void main(String[] args) {
		DBManager dbManager = new DBManager();
		// String sql = "select * from t_users where username=?";
		// String pa[] = {"lpm"};
		// dbManager.ExecuteScalar(sql,pa);
		String sql = "select * from building";
		dbManager.ExecuteQuery(sql);
		String[] sq = new String[2];
		sq[0] = "insert into building(userNUm,floorsNum) values (102,22); ";
		sq[1] = "insert into building(userNUm,floorsNum) values (103,13);";
		dbManager.ExecuteNonQuery(sq[0]);

	}

}
