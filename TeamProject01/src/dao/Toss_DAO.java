// 

package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.conn;
import vo.VO;
import vo.VO2;

public class Toss_DAO {
	
	private Connection con;
	int rowcnt;
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Toss_DAO()	{
		con = new conn("Toss_DAO").getConnection(); 	
	}
	public  VO2 TOtopicNrank(String id) {		
		VO2 vo2 = new VO2();
		
		String sql = "SELECT * FROM userinfo WHERE id = ? ";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			rs.next();
			
			int score = rs.getInt(3);
			Date fdate = rs.getDate(4);
			int vcount = rs.getInt(5);
			int dap_all = rs.getInt(6);
			
			vo2.setId(id);
			vo2.setScore(score);
			vo2.setFdate(fdate);
			vo2.setV_count(vcount);
			vo2.setDap_all(dap_all);
		}
		catch (SQLException e){
			System.out.println("Toss_DAO.TOtopicNrank; 오류");
    	}finally {
    		try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    //try { con.close(); } catch (Exception e) { /* ignored */ }
    	}	
		System.out.println("Toss_DAO.TOtopicNrank; 성공");
		return vo2;
	}	
			
		
	public  VO2 TOquiz(String id, int q_type, int countdownStarter, int sido, int score, int nowscore, int[] q){   
		VO2 vo2 = new VO2();

		String sql = "SELECT * FROM userinfo WHERE id = ? ";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			rs.next();
			score = rs.getInt(3);
			Date fdate = rs.getDate(4);
			int vcount = rs.getInt(5);
			int dap_all = rs.getInt(6);
			
			vo2.setId(id);
			vo2.setScore(score);
			vo2.setFdate(fdate);
			vo2.setV_count(vcount);
			vo2.setDap_all(dap_all);

			vo2.setQ_type(q_type);
			vo2.setCountdownStarter(countdownStarter);
			vo2.setSido(sido);
			vo2.setQ(q);
			vo2.setNowscore(nowscore);
		}
		catch (SQLException e){
			System.out.println("Toss_DAO.TOquiz; 오류");
    	}finally {
    		try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    //try { con.close(); } catch (Exception e) { /* ignored */ }
    	}	
		System.out.println("Toss_DAO.TOquiz; 완료");
		return vo2;
	}	
	
	
	
	
	public  VO2 TOcorrectAnswer(String id, int q_type, int qnum, String dap, int sido, int savetime, int score, int[] q, int nowscore) {		
		VO2 vo2 = new VO2();
		
		String sql = "SELECT * FROM userinfo WHERE id = ? ";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			rs.next();
			
			score = rs.getInt(3);
			Date fdate = rs.getDate(4);
			int vcount = rs.getInt(5);
			int dap_all = rs.getInt(6);
			
			vo2.setId(id);
			vo2.setScore(score);
			vo2.setFdate(fdate);
			vo2.setV_count(vcount);
			vo2.setDap_all(dap_all);
			
			vo2.setDap(dap);
			vo2.setQ_type(q_type);
			vo2.setSido(sido);
			vo2.setQ(q);
			vo2.setQnum(qnum);
			vo2.setNowscore(nowscore);
	
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println("Toss_DAO.TOcorrectAnswer; 오류");
    	}finally {
    		try { rs.close(); } catch (Exception e) { /* ignored */ }
    		try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { con.close(); } catch (Exception e) { /* ignored */ }
    	}	
		System.out.println("Toss_DAO.TOcorrectAnswer; 성공");
		return vo2;
	}	
	
	
	
	
	
	
	public boolean qcountup(String id)  {	
		String sql = "Update userinfo SET dap_all=dap_all+1 where id=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			rowcnt = ps.executeUpdate();
		} catch(SQLException e){
			System.out.println("Toss_DAO.qcountup; 맞춘문제수DAO_에러");
			return false;
    	}finally {
//    		try { rs.close(); } catch (Exception e) { /* ignored */ }
//		    try { ps.close(); } catch (Exception e) { /* ignored */ }
//		    try { con.close(); } catch (Exception e) { /* ignored */ }
    	}	
		return true;
		
	}

	public ArrayList<VO> score() throws SQLException {
		
		ArrayList<VO> array = new ArrayList<VO>();
		String sql = "SELECT id,score,dap_all from userinfo order by score desc";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			String id = rs.getString(1);
			int score = rs.getInt(2);
			int dap_all = rs.getInt(3);
			
			VO rank = new VO(id, score, dap_all);
			
			array.add(rank);; 
		}//while-end
			return array; 
	}//score()-end
	
	
	
	
}
