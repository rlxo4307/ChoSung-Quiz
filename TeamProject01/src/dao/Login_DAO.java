package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conn.conn;


public class Login_DAO {
		
		private Connection con;
		int rowcnt;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		public Login_DAO() {
			con = new conn("Login_DAO").getConnection();
		}

		public boolean login(String id, String pw)  {	
			String sql = "SELECT * FROM USERINFO WHERE id = ? AND pw = ?";
			try {
				ps=con.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, pw);
				rowcnt = ps.executeUpdate(); // ID/PW 일치 체크
			} catch(SQLException e){
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!Login_DAO.login ID/PW 일치 체크 에러");
				return false;
	    	}finally {
	    		try { rs.close(); } catch (Exception e) { /* ignored */ }
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
	    	}	

			if(rowcnt == 1) { // ID/PW가 일치할 경우
				// 방문 횟수 +1 증가
				sql = "UPDATE userinfo SET v_count = v_count+1 WHERE id = ?";
				try {
				ps=con.prepareStatement(sql);
				ps.setString(1, id);
				ps.executeUpdate(); 
				}
				catch (SQLException e){
					e.printStackTrace();
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!Login_DAO.login; 방문횟수 처리 에러");
		    	}finally {
		    		try { rs.close(); } catch (Exception e) { /* ignored */ }
				    try { ps.close(); } catch (Exception e) { /* ignored */ }
				    try { con.close(); } catch (Exception e) { /* ignored */ }
		    	}	
				System.out.println("Login_DAO.login; 로그인 완료");
				return true;
			}else {
				return false;
			}
		}
}

	 
	