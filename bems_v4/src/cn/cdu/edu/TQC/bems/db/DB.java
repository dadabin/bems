/**
* @Copyright (c) 成都大学信息科学与技术学院
* 服务外包与创意大赛
*/
package cn.cdu.edu.TQC.bems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName: DB
 * 说明：
 * TODO(Tell the reader such role.)
 * @Author LPM	【email:shouli1990@gmail.com 】
 * @Version V1.0	2012-7-5 下午6:03:17
 *
 */
public class DB {
	private String DBDrive = "com.mysql.jdbc.Driver";
	private String DBUrl ="jdbc:mysql://127.0.0.1:3306/bems?useUnicode=true&characterEncoding=UTF-8";//"jdbc:mysql://180.84.33.71:3306/bems";
	private String DBUser = "root";
	private String DBPassword = "195891";
	private ResultSet _rs = null;
	private Connection _conn = null;
	private Statement _stmt = null;
	/**
	 * 执行一则更新语句，返回主键值
	 * 
	 * @param sql
	 * @return
	 */
	public int executeUpdate(String sql) {
		int result = -1;
		try {

			// 执行sql
			_stmt = getConn().createStatement();
			_stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			// 获取主键
			_rs = _stmt.getGeneratedKeys();
			while (_rs.next()) {
				// 获取最后一个主键值
				result = _rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.colse();
		}
		return result;
	}

	public ResultSet executeQuery(String sql) {

		try {
			// 执行sql
			_stmt = getConn().createStatement();
			_rs = _stmt.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return _rs;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public Connection getConn() {
		try {

			Class.forName(DBDrive);
			_conn = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
//			//"jdbc:MySQL(和PHP搭配之最佳组合)://[hostname]/[database]?user=&password=&useUnicode=true&characterEncoding=utf-8"
//
//
//			_conn = DriverManager.getConnection("jdbc:mysql://180.84.33.71:3306/bems?user=root&password=sa123&useUnicode=true&characterEncoding=utf-8");
//			_conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bems?user=root&password=sa123&useUnicode=true&characterEncoding=utf-8");
		} catch (Exception e) {
			System.out.println("---------出现异常，获取连接失败--------");
			e.printStackTrace();
		}
		return _conn;
	}

	// 关闭数据库操作。。。。
	public boolean colse() {
		try {
			if (_rs != null) {
				_rs.close();
			}
			if (_stmt != null) {
				_stmt.close();
			}
			if (_conn != null) {
				_conn.close();
			}
			return true;
		} catch (SQLException e) {
			System.out.println("关闭失败。。。。。");
			e.printStackTrace();
			return false;
		}
	}

}
