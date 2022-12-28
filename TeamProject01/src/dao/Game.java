package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conn.conn;

public class Game {

      private static Connection con;
      static PreparedStatement ps = null;
      static ResultSet rs = null;
      static String question;
      
      public Game(){
         con = new conn("game").getConnection();    
      }
      
      public String question(int q_type, int qnum) { 
         
         String sql = "SELECT * FROM qa WHERE q_type = ? AND q_number= ?";
          try {
             ps = con.prepareStatement(sql);
             ps.setInt(1, q_type);
             ps.setInt(2, qnum);
             rs = ps.executeQuery();
             rs.next();
             question = rs.getString("question");
          }catch(SQLException e) {
             System.out.println("game_question; 문제 출력 에러발생");
             return null;
          }
          System.out.println("Game.question; 문제 출력 완료");
          return question;
      } 
      // HH 작업 end ==================================
      
      
      public static boolean check(String dap, int qunm, int type) {
         String sql = "SELECT answer FROM qa WHERE answer=? and q_number=? and q_type=? ";
          try {
             ps = con.prepareStatement(sql);
             ps.setString(1,dap);
             ps.setInt(2, qunm);
             ps.setInt(3, type);
             rs = ps.executeQuery();
             
             if (rs.next()) {
            	 question = rs.getString("answer");
                 System.out.println("Game.check; 정답");
                 return true;
             }else {
                 System.out.println("Game.check; 오답");
                 return false;
             }
          }catch(SQLException e) {
             e.printStackTrace();
        	  System.out.println("Game.check; 오류");
             return false;
          }
      }

      public static boolean update(String id, int score) {
            String sql = "UPDATE userinfo  SET score=? WHERE id=? and score<?";
          try {
             ps = con.prepareStatement(sql);
             ps.setInt(1,score);
             ps.setString(2,id);
             ps.setInt(3,score);
             ps.executeUpdate();
          }catch(SQLException e) {
             System.out.println("Game.update; SCORE 업데이트 오류");
             return false;
          }
          System.out.println("Game.update; SCORE 업데이트 완료");
          return true;
      }
   
}