package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conn {
	
		private Connection con;
		
		public Connection getConnection() {
			return con;
		}
		
		public conn(String address){
			
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					con=DriverManager.getConnection("jdbc:oracle:thin:@shared00.iptime.org:32779/XEPDB1", "user1", "user1");
				} catch (SQLException e) {
					System.out.println("▦▦▦▦▦서버와 연결되지 않았습니다.▦▦▦▦▦");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("conn.conn; ("+address+")접속 성공");
		}
		
} // Conn class-end


