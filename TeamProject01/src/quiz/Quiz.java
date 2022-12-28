package quiz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.Game;
import dao.Toss_DAO;
import vo.VO2;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;


public class Quiz extends JFrame {
	
	static VO2 vo2 = new VO2();
	static VO2 vo3 = new VO2();
	Toss_DAO td = new Toss_DAO();

	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private JPanel contentPane;
	private JTextField txtInput;
	
	String stop="°ÔÀÓÁ¾·á:YES / ÀçµµÀü:NO";
	
	static String id;
	static int q_type;
	boolean correct = true;
	boolean chance = true;
	boolean want = true;
	static int countdownStarter;
	static int savetime=60;
	static int score;
	int qnum;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vo3.setScore(0);
					Quiz frame = new Quiz(vo2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Quiz(VO2 vo2) throws ClassNotFoundException, SQLException {
		setResizable(false);

		System.out.println("¢Æ¢Æ¢Æ¢Æ¢Æ Quiz ÀÌµ¿ ¿Ï·á ¢Æ¢Æ¢Æ¢Æ¢Æ");
		vo3 = vo2;
		vo3.setQnum(vo3.getQ()[vo3.getSido()-1]);
		
		if(vo3.getSido()==0) vo3.setSido(10);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// HH ÀÛ¾÷ start ==============================

		JLabel lblQuiz = new JLabel(" ¹®Á¦ ÁØºñÁß ");
		lblQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuiz.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 50));
		lblQuiz.setBounds(419, 199, 500, 185);
		contentPane.add(lblQuiz);
		Game que = new Game();
		String question = que.question(vo3.getQ_type(), vo3.getQnum()); // ¹®Á¦ °¡Á®¿À±â
		lblQuiz.setText(question);
		
		txtInput = new JTextField();
		txtInput.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtInput.setHorizontalAlignment(SwingConstants.CENTER);
		txtInput.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		txtInput.addMouseListener(new MouseAdapter() {
			int sw = 0; 
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==txtInput) {
					if (sw == 0) {
						txtInput.setText("");
						sw = 1;
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getSource()==txtInput) {
					if (sw == 0) {
						txtInput.setText("");
						sw = 1;
					}
				}
			}
		});
		
		
		txtInput.setText("\uC815\uB2F5\uC744 \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		txtInput.setBounds(419, 394, 500, 40);
		contentPane.add(txtInput);
		txtInput.setColumns(10);
		
		JButton btnGiveUp = new JButton("\uADF8\uB9CC\uD558\uAE30");
		btnGiveUp.setBackground(new Color(245, 245, 245));
		btnGiveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnGiveUp) {
					Game.update(vo3.getId(), vo3.getNowscore());
					dispose();
					setVisible(false);
					try {
						scheduler.shutdown();
						new Rank(vo3 = td.TOtopicNrank(vo3.getId())).setVisible(true);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnGiveUp.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 15));
		btnGiveUp.setBounds(419, 443, 245, 40);
		contentPane.add(btnGiveUp);
		
		JLabel lblTime = new JLabel("times");
		lblTime.setForeground(UIManager.getColor("Button.foreground"));
		
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 50));
		lblTime.setBounds(419, 132, 500, 57);
		contentPane.add(lblTime);
		
        final Runnable runnable = new Runnable() {// Ä«¿îÆ®´Ù¿î
            public void run() { 
            	vo3.setSavetime(Topic.countdownStarter);
            	if(vo3.getSavetime() <= 30)
            		lblTime.setForeground(new Color(255,127,0));
        		if(vo3.getSavetime() <= 10)
            		lblTime.setForeground(new Color(220,20,60));
        		
            	lblTime.setText(String.valueOf(vo3.getSavetime())); // time ¶óº§¿¡ Ãâ·Â
                Topic.countdownStarter--;
                
                if (vo3.getSavetime() == 0) {
                    System.out.println("Timer Over!"); // Á¾·á½Ã task ÁöÁ¤
                    scheduler.shutdown();
                    
                    Game.update(vo3.getId(), vo3.getNowscore());
    						
    				int result=JOptionPane.showConfirmDialog
    						(btnGiveUp, stop, "°ÔÀÓÀÌ ³¡³µ½À´Ï´Ù", JOptionPane.YES_NO_OPTION);

    				if(result==JOptionPane.YES_OPTION) {
    					dispose();
    					setVisible(false);
    					
    					try {
    						new Rank(vo3 = td.TOtopicNrank(vo3.getId())).setVisible(true);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
    				else {
    					Topic.countdownStarter=60;
    					dispose();
        				setVisible(false);
        				scheduler.shutdown();
    					new Start().setVisible(true);
    				}	
                }
            }
        }; 
        scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
		
		JButton btnChecking = new JButton("\uC815\uB2F5 \uC81C\uCD9C");	
		btnChecking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Á¤´äÁ¦Ãâ		
				if(vo3.getSido()>0) {
					if(e.getSource() == btnChecking) { 
						vo3.setDap(txtInput.getText());
						vo3.setSido(vo3.getSido()-1); 
						if(Game.check(vo3.getDap(), vo3.getQnum(), vo3.getQ_type())) { // Á¤´äÀÌ ¸Â´Ù¸é
							vo3.setNowscore(vo3.getNowscore()+10); // ÇöÀç Á¡¼ö Áõ°¡
							td.qcountup(vo3.getId()); // ´©Àû Á¤´ä ¼ö Áõ°¡
							dispose();
							setVisible(false);
							scheduler.shutdown();
							Game.update(vo3.getId(), vo3.getNowscore()); // Á¡¼ö°¡ ³ôÀ¸¸é °»½Å			
							new CorrectAnswer(vo3 = td.TOcorrectAnswer(vo3.getId(), vo3.getQ_type(), vo3.getQnum(), vo3.getDap(), vo3.getSido(), vo3.getSavetime(), vo3.getScore(),vo3.getQ(), vo3.getNowscore())).setVisible(true);
						}else { // Á¤´äÀÌ ¾Æ´Ï¶ó¸é
		                     dispose();
		                     setVisible(false);
		                     scheduler.shutdown();
		                     Game.update(vo3.getId(), vo3.getNowscore());  // Á¡¼ö°¡ ³ôÀ¸¸é °»½Å
		                     new CorrectAnswer(vo3 = td.TOcorrectAnswer(vo3.getId(), vo3.getQ_type(), vo3.getQnum(), vo3.getDap(), vo3.getSido(), vo3.getSavetime(), vo3.getScore(), vo3.getQ(), vo3.getNowscore())).setVisible(true);
		                  }
					}
				}
			}
		});
		btnChecking.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 15));
		btnChecking.setBackground(new Color(225, 212, 0));
		btnChecking.setBounds(674, 443, 245, 40);
		contentPane.add(btnChecking);
		
		JLabel lblLeanTime = new JLabel("\uB0A8\uC740 \uC2DC\uAC04");
		lblLeanTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeanTime.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lblLeanTime.setBounds(419, 107, 500, 15);
		contentPane.add(lblLeanTime);

		JPanel cp_left_m = new JPanel();
		cp_left_m.setLayout(null);
		cp_left_m.setBounds(0, 0, 330, 565);
		contentPane.add(cp_left_m);
		
		JLabel lbl_user = new JLabel((String) null);
		lbl_user.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_user.setBounds(164, 160, 110, 15);
		lbl_user.setText(vo3.getId());
		cp_left_m.add(lbl_user);
		
		JLabel lbl_maxscore = new JLabel("0");
		lbl_maxscore.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_maxscore.setBounds(164, 215, 110, 15);
		lbl_maxscore.setText(String.valueOf(vo3.getScore()));
		cp_left_m.add(lbl_maxscore);
		
		JLabel icon_logout = new JLabel("");
		icon_logout.addMouseListener(new MouseAdapter() {
		  	@Override
		  	public void mouseClicked(MouseEvent e) {
		  		if(e.getSource()==icon_logout) {
		  	       dispose();
		  	       setVisible(false);
		  	       scheduler.shutdown();
					new Start().setVisible(true);
		  		}
		  	}
		  	@Override
		  	public void mouseReleased(MouseEvent e) {
		 		if(e.getSource()==icon_logout) {
		   	       dispose();
		   	       setVisible(false);
		   	       scheduler.shutdown();
					new Start().setVisible(true);
		   		}
		  	}
		  });
		icon_logout.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		icon_logout.setToolTipText("\uB85C\uADF8\uC544\uC6C3");
		icon_logout.setBounds(120, 495, 24, 24);
		cp_left_m.add(icon_logout);
	    icon_logout.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/logout.png")));
		
		JLabel lbl_ui_id = new JLabel("\uC544\uC774\uB514");
		lbl_ui_id.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_id.setBounds(74, 160, 80, 15);
		cp_left_m.add(lbl_ui_id);
		
		JLabel lbl_ui_maxscore = new JLabel("\uCD5C\uACE0 \uC810\uC218");
		lbl_ui_maxscore.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_maxscore.setBounds(74, 215, 80, 15);
		cp_left_m.add(lbl_ui_maxscore);
		
		JLabel lbl_ui_dap_all = new JLabel("\uB204\uC801 \uC815\uB2F5 \uC218");
		lbl_ui_dap_all.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_dap_all.setBounds(74, 240, 80, 15);
		cp_left_m.add(lbl_ui_dap_all);
		
		JLabel lbl_dapall = new JLabel("");
		lbl_dapall.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_dapall.setBounds(164, 240, 110, 15);
		lbl_dapall.setText(Integer.toString(vo3.getDap_all()));
		cp_left_m.add(lbl_dapall);
		
		JLabel lbl_ui_vcount = new JLabel("\uBC29\uBB38 \uD69F\uC218");
		lbl_ui_vcount.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_vcount.setBounds(74, 401, 80, 15);
		cp_left_m.add(lbl_ui_vcount);
		
		JLabel lbl_ui_fdate = new JLabel("\uAC00\uC785\uC77C");
		lbl_ui_fdate.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_fdate.setBounds(74, 426, 80, 15);
		cp_left_m.add(lbl_ui_fdate);
		
		JLabel lbl_fdate = new JLabel("null");
		lbl_fdate.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_fdate.setBounds(164, 426, 110, 15);
		lbl_fdate.setText(String.valueOf(vo3.getFdate()));
		cp_left_m.add(lbl_fdate);
		
		JLabel lbl_vcount = new JLabel("0");
		lbl_vcount.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_vcount.setBounds(164, 401, 110, 15);
	    lbl_vcount.setText(String.valueOf(vo3.getV_count()));
		cp_left_m.add(lbl_vcount);
		
		JLabel lbl_ui_leftbar_1_1 = new JLabel("");
		lbl_ui_leftbar_1_1.setOpaque(true);
		lbl_ui_leftbar_1_1.setBackground(SystemColor.activeCaptionBorder);
		lbl_ui_leftbar_1_1.setBounds(40, 193, 250, 1);
		cp_left_m.add(lbl_ui_leftbar_1_1);
		
		JLabel lbl_ui_id_1 = new JLabel("\uB85C\uADF8\uC544\uC6C3");
		lbl_ui_id_1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_id_1.setBounds(150, 500, 80, 15);
		cp_left_m.add(lbl_ui_id_1);
		
		JLabel lbl_left_title = new JLabel("");
		lbl_left_title.setBounds(40, 109, 250, 36);
		cp_left_m.add(lbl_left_title);
		lbl_left_title.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/title_user.png")));
		
		JLabel lbl_logout = new JLabel("");
		lbl_logout.setOpaque(true);
		lbl_logout.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 14));
		lbl_logout.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_logout.setBackground(Color.WHITE);
		lbl_logout.setBounds(40, 487, 250, 40);
		lbl_logout.addMouseListener(new MouseAdapter() {
		  	@Override
		  	public void mouseClicked(MouseEvent e) {
		  		if(e.getSource()==lbl_logout) {
		  	       dispose();
		  	       setVisible(false);
		  	       scheduler.shutdown();
					new Start().setVisible(true);
		  		}
		  	}
		  	@Override
		  	public void mouseReleased(MouseEvent e) {
		 		if(e.getSource()==lbl_logout) {
		   	       dispose();
		   	       setVisible(false);
		   	       scheduler.shutdown();
					new Start().setVisible(true);
		   		}
		  	}
		  });
		cp_left_m.add(lbl_logout);
		
		JLabel lbl_ui_nowscore = new JLabel("\uD604\uC7AC \uC810\uC218");
		lbl_ui_nowscore.setBounds(74, 294, 80, 15);
		cp_left_m.add(lbl_ui_nowscore);
		lbl_ui_nowscore.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		
		JLabel lbl_ui_chance = new JLabel("\uD604\uC7AC \uB0A8\uC740 \uAE30\uD68C");
		lbl_ui_chance.setBounds(74, 319, 80, 15);
		cp_left_m.add(lbl_ui_chance);
		lbl_ui_chance.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		

		
		JLabel lbl_dapnow = new JLabel(String.valueOf(vo3.getNowscore()/10));
		lbl_dapnow.setBounds(164, 344, 110, 15);
		cp_left_m.add(lbl_dapnow);
		lbl_dapnow.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		
		JLabel lbl_chance = new JLabel(String.valueOf(vo3.getSido()));
		lbl_chance.setBounds(164, 319, 110, 15);
		cp_left_m.add(lbl_chance);
		lbl_chance.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		
		JLabel lbl_nowscore = new JLabel(String.valueOf(vo3.getNowscore()));
		lbl_nowscore.setBounds(164, 294, 110, 15);
		cp_left_m.add(lbl_nowscore);
		lbl_nowscore.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		
		JLabel lbl_ui_dapnow = new JLabel("\uD604\uC7AC \uC815\uB2F5 \uC218");
		lbl_ui_dapnow.setBounds(74, 344, 80, 15);
		cp_left_m.add(lbl_ui_dapnow);
		lbl_ui_dapnow.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBounds(0, 272, 330, 109);
		cp_left_m.add(lblNewLabel_3);
		
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
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 19, 642, 17);
		cp_down_m.add(lblNewLabel);
			
	}
}