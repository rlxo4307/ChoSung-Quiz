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
					System.out.println("�ˢˢˢˢ˼����� ������� �ʾҽ��ϴ�.�ˢˢˢˢ�");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("conn.conn; ("+address+")���� ����");
		}
		
} // Conn class-end


