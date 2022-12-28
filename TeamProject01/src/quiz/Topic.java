package quiz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.Toss_DAO;
import vo.VO2;

public class Topic extends JFrame {

   private JPanel contentPane;
   static int sido = 10;
   static int q_type;
   static int countdownStarter = 60;
   static int score;
   static int cau;
   static String id;
   static int nowscore;
   int [] q = new int [10];
   
   
// Ŭ���� �߰�
	static VO2 vo2 = new VO2(); // �޴¿�
	static VO2 vo3 = new VO2(); // �����¿�
	Toss_DAO td = new Toss_DAO();
  	
  	
   
   //�޼ҵ� �߰�-----------
 	private void topic_select(int i) { // ���� ����
        dispose();
        setVisible(false);
        q_type = i;
    	score = 0;
		try {
			new Quiz(vo3 = td.TOquiz(vo3.getId(), q_type, countdownStarter,sido,score, nowscore, vo3.getQ())).setVisible(true);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	
  	private void img(JLabel i, String s) { // �̹��� ����
		i.setIcon(new ImageIcon(Topic.class.getResource(s)));
		i.repaint();
		}
 	
   /**
    * Launch the application.
    */
  	
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Topic frame = new Topic(vo2);
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   /**
    * Create the frame.
    * @throws SQLException 
    * @throws ClassNotFoundException 
    */
   
   public Topic(VO2 vo2){
	  System.out.println("�ƢƢƢƢ� Topic �̵� �Ϸ� �ƢƢƢƢ�");
	  vo3 = vo2;
	  setResizable(false);

	// ���� ���� ������ ���� ����==================
		for (int i=0 ; i < 10 ; i++ ) { 
			q[i]=(int) (Math.ceil(Math.random()*10));
			for (int j=0 ; j < i; j++) {
				if(q[i]==q[j]) 
					i--;
			}
		}
		vo3.setQ(q);
	// ���� ���� ������ ���� ����=================
   
	  countdownStarter = 60; // Topic ���� ���� �� ���� �ʸ� �ٽ� 100����     
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 1024, 600);
      contentPane = new JPanel();
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lbl_title = new JLabel("");
      lbl_title.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/topic2.png")));
      lbl_title.setForeground(new Color(0, 51, 153));
      lbl_title.setFont(new Font("���� ���", Font.PLAIN, 22));
      lbl_title.setBounds(545, 57, 253, 35);
      contentPane.add(lbl_title);
      
      JPanel cp_left_m = new JPanel();
      cp_left_m.setBounds(0, 0, 330, 565);
      contentPane.add(cp_left_m);
      cp_left_m.setLayout(null);
      
      JLabel lbl_user = new JLabel(vo3.getId());
      lbl_user.setBounds(164, 160, 110, 15);
      cp_left_m.add(lbl_user);
      lbl_user.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      
      JLabel lbl_maxscore = new JLabel(String.valueOf(vo3.getScore()));
      lbl_maxscore.setBounds(164, 215, 110, 15);
      cp_left_m.add(lbl_maxscore);
      lbl_maxscore.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      
      JLabel icon_logout = new JLabel("");
      icon_logout.setBounds(120, 495, 24, 24);
      cp_left_m.add(icon_logout);
      icon_logout.setToolTipText("\uB85C\uADF8\uC544\uC6C3");
		icon_logout.addMouseListener(new MouseAdapter() {
		  	@Override
		  	public void mouseClicked(MouseEvent e) {
		  		if(e.getSource()==icon_logout) {
		  	       dispose();
		  	       setVisible(false);
					new Start().setVisible(true);
		  		}
		  	}
		  	@Override
		  	public void mouseReleased(MouseEvent e) {
		 		if(e.getSource()==icon_logout) {
		   	       dispose();
		   	       setVisible(false);
					new Start().setVisible(true);
		   		}
		  	}
		  });
      icon_logout.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/logout.png")));
      
      JLabel lbl_ui_id = new JLabel("\uC544\uC774\uB514");
      lbl_ui_id.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_id.setBounds(74, 160, 80, 15);
      cp_left_m.add(lbl_ui_id);
      
      JLabel lbl_ui_maxscore = new JLabel("\uCD5C\uACE0 \uC810\uC218");
      lbl_ui_maxscore.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_maxscore.setBounds(74, 215, 80, 15);
      cp_left_m.add(lbl_ui_maxscore);
      
      JLabel lbl_ui_dap_all = new JLabel("\uB204\uC801 \uC815\uB2F5 \uC218");
      lbl_ui_dap_all.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_dap_all.setBounds(74, 240, 80, 15);
      cp_left_m.add(lbl_ui_dap_all);
      
      JLabel lbl_dapall = new JLabel("���� ����� üũ");
      lbl_dapall.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_dapall.setBounds(164, 240, 110, 15);
      lbl_dapall.setText(String.valueOf(vo2.getDap_all()));
      
      cp_left_m.add(lbl_dapall);
      
      JLabel lbl_ui_leftbar_1 = new JLabel("");
      lbl_ui_leftbar_1.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_leftbar_1.setBounds(40, 272, 250, 1);
      cp_left_m.add(lbl_ui_leftbar_1);
      lbl_ui_leftbar_1.setOpaque(true);
      lbl_ui_leftbar_1.setBackground(new Color(180, 180, 180));
      
      JLabel lbl_ui_leftbar_2 = new JLabel("");
      lbl_ui_leftbar_2.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_leftbar_2.setOpaque(true);
      lbl_ui_leftbar_2.setBackground(SystemColor.activeCaptionBorder);
      lbl_ui_leftbar_2.setBounds(40, 380, 250, 1);
      cp_left_m.add(lbl_ui_leftbar_2);
      
      JLabel lbl_ui_dapnow = new JLabel("\uD604\uC7AC \uC815\uB2F5 \uC218");
      lbl_ui_dapnow.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_dapnow.setBounds(74, 344, 80, 15);
      cp_left_m.add(lbl_ui_dapnow);
      
      JLabel lbl_ui_chance = new JLabel("\uD604\uC7AC \uB0A8\uC740 \uAE30\uD68C");
      lbl_ui_chance.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_chance.setBounds(74, 319, 80, 15);
      cp_left_m.add(lbl_ui_chance);
      
      JLabel lbl_ui_nowscore = new JLabel("\uD604\uC7AC \uC810\uC218");
      lbl_ui_nowscore.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_nowscore.setBounds(74, 294, 80, 15);
      cp_left_m.add(lbl_ui_nowscore);
      
      JLabel lbl_nowscore = new JLabel("-");
      lbl_nowscore.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_nowscore.setBounds(164, 294, 110, 15);
      cp_left_m.add(lbl_nowscore);
      
      JLabel lbl_chance = new JLabel("-");
      lbl_chance.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_chance.setBounds(164, 319, 110, 15);
      cp_left_m.add(lbl_chance);
      
      JLabel lbl_dapnow = new JLabel("-");
      lbl_dapnow.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_dapnow.setBounds(164, 344, 110, 15);
      cp_left_m.add(lbl_dapnow);
      
      JLabel lbl_ui_vcount = new JLabel("\uBC29\uBB38 \uD69F\uC218");
      lbl_ui_vcount.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_vcount.setBounds(74, 401, 80, 15);
      cp_left_m.add(lbl_ui_vcount);
      
      JLabel lbl_ui_fdate = new JLabel("\uAC00\uC785\uC77C");
      lbl_ui_fdate.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_fdate.setBounds(74, 426, 80, 15);
      cp_left_m.add(lbl_ui_fdate);
      
      JLabel lbl_fdate = new JLabel("�ҷ����� ����");
      lbl_fdate.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_fdate.setBounds(164, 426, 110, 15);
      lbl_fdate.setText(String.valueOf(vo3.getFdate()));
      cp_left_m.add(lbl_fdate);
      
      JLabel lbl_vcount = new JLabel("�湮Ƚ��");
      lbl_vcount.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_vcount.setBounds(164, 401, 110, 15);
      lbl_vcount.setText(String.valueOf(vo3.getV_count()));
      cp_left_m.add(lbl_vcount);
      
      JLabel lbl_ui_leftbar_1_1 = new JLabel("");
      lbl_ui_leftbar_1_1.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_leftbar_1_1.setOpaque(true);
      lbl_ui_leftbar_1_1.setBackground(SystemColor.activeCaptionBorder);
      lbl_ui_leftbar_1_1.setBounds(40, 193, 250, 1);
      cp_left_m.add(lbl_ui_leftbar_1_1);
      
      JLabel lbl_ui_id_1 = new JLabel("\uB85C\uADF8\uC544\uC6C3");
      lbl_ui_id_1.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_ui_id_1.setBounds(150, 500, 80, 15);
      cp_left_m.add(lbl_ui_id_1);
      
      JLabel lbl_logout = new JLabel("");
		lbl_logout.addMouseListener(new MouseAdapter() {
		  	@Override
		  	public void mouseClicked(MouseEvent e) {
		  		if(e.getSource()==lbl_logout) {
		  	       dispose();
		  	       setVisible(false);
					new Start().setVisible(true);
		  		}
		  	}
		  	@Override
		  	public void mouseReleased(MouseEvent e) {
		 		if(e.getSource()==lbl_logout) {
		   	       dispose();
		   	       setVisible(false);
					new Start().setVisible(true);
		   		}
		  	}
		  });
      lbl_logout.setOpaque(true);
      lbl_logout.setFont(new Font("���� ��� Semilight", Font.PLAIN, 14));
      lbl_logout.setBorder(new LineBorder(new Color(169, 169, 169)));
      lbl_logout.setBackground(Color.WHITE);
      lbl_logout.setBounds(40, 487, 250, 40);
      cp_left_m.add(lbl_logout);
      
      JLabel lbl_left_title = new JLabel("");
      lbl_left_title.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
      lbl_left_title.setBounds(40, 109, 250, 36);
      cp_left_m.add(lbl_left_title);
      lbl_left_title.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/title_user.png")));
      
      JPanel cp_down_m = new JPanel();
      cp_down_m.setLayout(null);
      cp_down_m.setOpaque(false);
      cp_down_m.setBounds(340, 517, 662, 48);
      contentPane.add(cp_down_m);
      
      JLabel lbl_ui_downbar = new JLabel("");
      lbl_ui_downbar.setOpaque(true);
      lbl_ui_downbar.setBackground(new Color(224, 224, 224));
      lbl_ui_downbar.setBounds(10, 10, 642, 1);
      cp_down_m.add(lbl_ui_downbar);
      
      JLabel lblNewLabel = new JLabel("Copyright 2022. 2\uC870 \uAD6C\uBCF8\uACBD \uBB38\uADDC\uD658 \uC774\uC778\uD638 \uC804\uD638\uD615 \uD55C\uAE30\uD0DC all right reversed.");
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setFont(new Font("���� ��� Semilight", Font.PLAIN, 11));
      lblNewLabel.setBounds(10, 19, 642, 17);
      cp_down_m.add(lblNewLabel);
      
      JLabel lbl_topic1 = new JLabel("");
      lbl_topic1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		if(e.getSource()==lbl_topic1) {
        			img(lbl_topic1,"/picture/ui/to1_1.jpg");
        		}
        	}
		@Override
      	public void mouseExited(MouseEvent e) {
      		if(e.getSource()==lbl_topic1) {
      			img(lbl_topic1,"/picture/ui/to1_2.png");
    		}
      	}
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		topic_select(1);
      	}
      	@Override
      	public void mousePressed(MouseEvent e) {
    		if(e.getSource()==lbl_topic1) {
    			topic_select(1);
            } 
      	}
    });
      lbl_topic1.setBounds(352, 114, 160, 384);
      lbl_topic1.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/to1_2.png")));
      
      
      contentPane.add(lbl_topic1);
      
      JLabel lbl_topic2 = new JLabel("");
      lbl_topic2.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseEntered(MouseEvent e) {
      		if(e.getSource()==lbl_topic2) {
      			img(lbl_topic2,"/picture/ui/to2_1.png");
      		}
      	}
      	@Override
      	public void mouseExited(MouseEvent e) {
      		if(e.getSource()==lbl_topic2) {
      			img(lbl_topic2,"/picture/ui/to2_2.png");
    		}
      	}
      	public void mouseClicked(MouseEvent e) {
    		if(e.getSource()==lbl_topic2) {
    			topic_select(2);
            } 
      	}
      	public void mousePressed(MouseEvent e) {
    		if(e.getSource()==lbl_topic2) {
    			topic_select(2);
            } 
      	}
    });
      lbl_topic2.setBounds(512, 114, 160, 384);
      lbl_topic2.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/to2_2.png")));
      contentPane.add(lbl_topic2);
      
      JLabel lbl_topic4 = new JLabel("");
      lbl_topic4.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		if(e.getSource()==lbl_topic4) {
        			img(lbl_topic4,"/picture/ui/to4_1.png");
        		}
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		if(e.getSource()==lbl_topic4) {
        			img(lbl_topic4,"/picture/ui/to4_2.png");
        		}
        	}
          	public void mouseClicked(MouseEvent e) {
        		if(e.getSource()==lbl_topic4) {
        			topic_select(4);
                } 
          	}

			public void mousePressed(MouseEvent e) {
        		if(e.getSource()==lbl_topic4) {
        			topic_select(4);
        		}
          	}
        });
      lbl_topic4.setBounds(830, 114, 160, 384);
      lbl_topic4.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/to4_2.png")));
      contentPane.add(lbl_topic4);
      
      JLabel lbl_topic3 = new JLabel("");
      lbl_topic3.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		if(e.getSource()==lbl_topic3) {
        			img(lbl_topic3,"/picture/ui/to3_1.png");
        		}
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		if(e.getSource()==lbl_topic3) {
        			img(lbl_topic3,"/picture/ui/to3_2.png");
        		}
        	}
          	public void mouseClicked(MouseEvent e) {
        		if(e.getSource()==lbl_topic3) {
        			topic_select(3);
                } 
          	}
          	public void mousePressed(MouseEvent e) {
        		if(e.getSource()==lbl_topic3) {
        			topic_select(3);
        		}
          	}
        });
      lbl_topic3.setBounds(671, 114, 160, 384);
      lbl_topic3.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/to3_2.png")));
      contentPane.add(lbl_topic3);
   }
}