package come.yedam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * EmpDAO, StudnetDAO....
 * 
 */
public class DAO {
	// Connection객체. Statement, PreparedStatement, ResultSet
	Connection conn = null;
	Statement stmt; // 퀴리 실행하고 결과 반환 클래스.
	PreparedStatement psmt;
	ResultSet rs;

	// 세션해제.
	void disConnect() {
	    try {
	        if (conn != null) {         
	            conn.close();
	        }
	        if (rs != null) {         
	            rs.close();
	        }
	        if (psmt != null) {       
	            psmt.close();
	        }
	        if (stmt != null) {       
	            stmt.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// 오라클 접속 세션연결.
	Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";// 오라클DB의 접속정보.
		String user = "hr";
		String password = "hr";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}// end of getConnect().
}
