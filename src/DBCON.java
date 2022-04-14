import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBCON {
	// 드라이브 로딩
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("jdbc 드라이브 로딩 실패");
			e.printStackTrace();
		}
	}
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String name = "c##bitna";
	String pwd = "1234";
	
	public DBCON() {
	}
	
	public void dbConn() {
		try {
			conn = DriverManager.getConnection(url, name, pwd);
		}catch(Exception e) {
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}
		
	}
	
	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {
			System.out.println("DB 닫기 실패");
			e.printStackTrace();
		}
	}

}
