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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.Game;
import dao.Toss_DAO;
import vo.VO2;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class CorrectAnswer extends JFrame {
	
	static VO2 vo2 = new VO2();
	static VO2 vo3 = new VO2();
	Toss_DAO td = new Toss_DAO();

	private JPanel contentPane;
	static String id;
	static String dap;
	static int q_type;
	static int sido;
	static int qnum;
	static int savetime;
	static int score;
	//static String qnumstr;
	static int countdownStarter;
	
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CorrectAnswer frame = 	new CorrectAnswer(vo2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public CorrectAnswer(VO2 vo2){
		setResizable(false);
		System.out.println("�ƢƢƢƢ� CorrectAnswer �̵� �Ϸ� �ƢƢƢƢ�");
		vo3 = vo2;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_passmark = new JLabel("");
		lbl_passmark.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_passmark.setFont(new Font("���� ��� Semilight", Font.PLAIN, 26));
		lbl_passmark.setBounds(419, 50, 500, 80);
		contentPane.add(lbl_passmark);
		
		JLabel lblQuiz = new JLabel("");
		lbl_passmark.setText("  "+vo3.getDap());
	      lblQuiz.setHorizontalAlignment(SwingConstants.CENTER);
	      lblQuiz.setFont(new Font("���� ��� Semilight", Font.PLAIN, 30));
	      lblQuiz.setBounds(419, 130, 507, 285);
	      contentPane.add(lblQuiz);
		

		JLabel lblPicture = new JLabel("");
		lblPicture.setBounds(686, 225, 185, 85);
		contentPane.add(lblPicture);
		contentPane.add(lbl_passmark);
		if(Game.check(vo3.getDap(), vo3.getQnum(), vo3.getQ_type())) {
			lblQuiz.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/"+vo3.getQ_type()+"/"+vo3.getQnum()+".jpg")));
			lbl_passmark.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/icon_yes.png")));
			lblPicture.repaint();
			contentPane.add(lblPicture);
			contentPane.add(lbl_passmark);
		} else {
			lblQuiz.setText("틀렸습니다!");
			//lblQuiz.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/fail.jpg")));
			lbl_passmark.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/icon_no.png")));
			lblPicture.setBounds(124, 169, 185, 85);

		}

		JButton btnGiveUp = new JButton("\uADF8\uB9CC\uD558\uAE30");

		btnGiveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnGiveUp) {
					String stop="������ �����ϰڽ��ϱ�?";
					int result=JOptionPane.showConfirmDialog
					(btnGiveUp, stop, "������ �������ϴ�", JOptionPane.YES_NO_OPTION);
					if(result==JOptionPane.YES_OPTION) {
						dispose();
						setVisible(false);
							try {
								new Rank(vo3 = td.TOtopicNrank(vo3.getId())).setVisible(true);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}	
				}
			}
			
		});
		btnGiveUp.setBackground(new Color(245, 245, 245));
		btnGiveUp.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		btnGiveUp.setBounds(419, 443, 245, 40);
		contentPane.add(btnGiveUp);
		
		JButton btnNext = new JButton("\uB2E4\uC74C \uBB38\uC81C");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
					if(vo3.getSido()>0) { // �õ�Ƚ���� �ִٸ�
						try {
							new Quiz(CorrectAnswer.vo2 = td.TOquiz(vo3.getId(), vo3.getQ_type(), vo3.getCountdownStarter(), vo3.getSido(), vo3.getScore(), vo3.getNowscore(), vo3.getQ())).setVisible(true);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if(vo3.getSido()==0) {
						scheduler.shutdown();
						dispose();
						setVisible(false);
						String stop="��������:YES / �絵��:NO";
						int result=JOptionPane.showConfirmDialog
						(null, stop, "������ �������ϴ�", JOptionPane.YES_NO_OPTION);

						if(result==JOptionPane.YES_OPTION) {
							Game.update(vo3.getId(), vo3.getScore());
							scheduler.shutdown();
							try {
								new Rank(vo3 = td.TOtopicNrank(vo3.getId())).setVisible(true);
							} catch (ClassNotFoundException | SQLException e1) {
								e1.printStackTrace();
							}
						}else {
							 new Topic(vo3 = td.TOtopicNrank(vo3.getId())).setVisible(true);
						}
					}
			}
		});
		btnNext.setBackground(SystemColor.activeCaption);
		btnNext.setBackground(new Color(225, 212, 0));
		btnNext.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		btnNext.setBounds(674, 443, 245, 40);
		contentPane.add(btnNext);
		
		JPanel cp_left_m = new JPanel();
		cp_left_m.setLayout(null);
		cp_left_m.setBounds(0, 0, 330, 565);
		contentPane.add(cp_left_m);
		
		JLabel lbl_user = new JLabel((String) null);
		lbl_user.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lbl_user.setBounds(164, 160, 110, 15);
		lbl_user.setText(String.valueOf(vo3.getId()));
		cp_left_m.add(lbl_user);
		
		JLabel lbl_maxscore = new JLabel("0");
		lbl_maxscore.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
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
		icon_logout.setToolTipText("\uB85C\uADF8\uC544\uC6C3");
		icon_logout.setBounds(120, 495, 24, 24);
		icon_logout.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/logout.png")));
		cp_left_m.add(icon_logout);
		
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
		
		JLabel lbl_dapall = new JLabel("0");
		lbl_dapall.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lbl_dapall.setBounds(164, 240, 110, 15);
		lbl_dapall.setText(String.valueOf(vo3.getDap_all()));
		cp_left_m.add(lbl_dapall);
		
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
		
		JLabel lbl_nowscore = new JLabel(String.valueOf(vo3.getNowscore()));
		lbl_nowscore.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lbl_nowscore.setBounds(164, 294, 110, 15);
		cp_left_m.add(lbl_nowscore);
		
		JLabel lbl_chance = new JLabel(String.valueOf(vo3.getSido()));
		lbl_chance.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lbl_chance.setBounds(164, 319, 110, 15);
		cp_left_m.add(lbl_chance);
		
		JLabel lbl_dapnow = new JLabel(String.valueOf(vo3.getNowscore()/10));
		lbl_dapnow.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lbl_dapnow.setBounds(164, 344, 110, 15);
		cp_left_m.add(lbl_dapnow);
		
		JLabel lbl_ui_vcount = new JLabel("\uBC29\uBB38\uD69F\uC218");
		lbl_ui_vcount.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lbl_ui_vcount.setBounds(74, 401, 80, 15);
		cp_left_m.add(lbl_ui_vcount);
		
		JLabel lbl_ui_fdate = new JLabel("\uAC00\uC785\uC77C");
		lbl_ui_fdate.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lbl_ui_fdate.setBounds(74, 426, 80, 15);
		cp_left_m.add(lbl_ui_fdate);
		
		JLabel lbl_fdate = new JLabel("0");
		lbl_fdate.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lbl_fdate.setBounds(164, 426, 110, 15);
		lbl_fdate.setText(String.valueOf(vo3.getFdate()));
		cp_left_m.add(lbl_fdate);
		
		JLabel lbl_vcount = new JLabel("0");
		lbl_vcount.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lbl_vcount.setBounds(164, 401, 110, 15);
		lbl_vcount.setText(String.valueOf(vo3.getV_count()));
		cp_left_m.add(lbl_vcount);
		
		JLabel lbl_ui_leftbar_1_1 = new JLabel("");
		lbl_ui_leftbar_1_1.setOpaque(true);
		lbl_ui_leftbar_1_1.setBackground(SystemColor.activeCaptionBorder);
		lbl_ui_leftbar_1_1.setBounds(40, 193, 250, 1);
		cp_left_m.add(lbl_ui_leftbar_1_1);
		
		JLabel lbl_ui_id_1 = new JLabel("\uB85C\uADF8\uC544\uC6C3");
		lbl_ui_id_1.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lbl_ui_id_1.setBounds(150, 500, 80, 15);
		cp_left_m.add(lbl_ui_id_1);
		
		JLabel lbl_logout = new JLabel("");
		lbl_logout.setOpaque(true);
		lbl_logout.setFont(new Font("���� ��� Semilight", Font.PLAIN, 14));
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
		
		JLabel lbl_left_title = new JLabel("");
		lbl_left_title.setBounds(40, 109, 250, 36);
		cp_left_m.add(lbl_left_title);
		lbl_left_title.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/title_user.png")));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("���� ��� Semilight", Font.PLAIN, 12));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(Color.WHITE);
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
		
		JLabel lblNewLabel_1 = new JLabel("Copyright 2022. 2\uC870 \uAD6C\uBCF8\uACBD \uBB38\uADDC\uD658 \uC774\uC778\uD638 \uC804\uD638\uD615 \uD55C\uAE30\uD0DC all right reversed.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("���� ��� Semilight", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 19, 642, 17);
		cp_down_m.add(lblNewLabel_1);
	}

}