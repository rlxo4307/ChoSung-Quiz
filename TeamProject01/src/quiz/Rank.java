package quiz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.Toss_DAO;
import vo.VO;
import vo.VO2;
import java.awt.SystemColor;

public class Rank extends JFrame {

	
	static VO2 vo2 = new VO2();
	static VO2 vo3 = new VO2();
	Toss_DAO td = new Toss_DAO();
	
	private JPanel contentPane;
	static String id; 
	static int cau;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rank frame = new Rank(vo2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param id 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Rank(VO2 vo2) throws ClassNotFoundException, SQLException {
		setResizable(false);
		System.out.println("¢Æ¢Æ¢Æ¢Æ¢Æ Rank ÀÌµ¿ ¿Ï·á ¢Æ¢Æ¢Æ¢Æ¢Æ");
		vo3 = vo2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\uB2E4\uC2DC\uD558\uAE30");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 212, 0));
		btnNewButton.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 14));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				new Topic(vo3 = td.TOtopicNrank(vo3.getId())).setVisible(true);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
				setVisible(false);
				new Topic(vo3 = td.TOtopicNrank(vo3.getId())).setVisible(true);
			}
		});
		btnNewButton.setBounds(508, 459, 340, 40);
		contentPane.add(btnNewButton);
		
		JLabel lbl_title = new JLabel("");
		lbl_title.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/icon_rank.png")));
		lbl_title.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 23));
		lbl_title.setBounds(637, 57, 80, 80);
		contentPane.add(lbl_title);
		
		JPanel jp_score = new JPanel();
		jp_score.setBounds(508, 159, 340, 285);
		contentPane.add(jp_score);
		jp_score.setLayout(new GridLayout(6, 4, 0, 0));
		
		JLabel lblNewLabel = new JLabel("\uC21C\uC704");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lblNewLabel.setBorder(new LineBorder(new Color(169, 169, 169)));
		lblNewLabel.setOpaque(true);
		jp_score.add(lblNewLabel);
		
		JLabel lblId = new JLabel("\uC544\uC774\uB514");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setOpaque(true);
		lblId.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lblId.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lblId);
		
		JLabel lblScore = new JLabel("\uC810\uC218");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setOpaque(true);
		lblScore.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lblScore.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lblScore);
		
		JLabel lbldap_all = new JLabel("\uB204\uC801 \uC815\uB2F5 \uC218");
		lbldap_all.setOpaque(true);
		lbldap_all.setHorizontalAlignment(SwingConstants.CENTER);
		lbldap_all.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbldap_all.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbldap_all);
		
		JLabel lbl_no1 = new JLabel("1");
		lbl_no1.setBackground(Color.WHITE);
		lbl_no1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_no1.setOpaque(true);
		lbl_no1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_no1.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_no1);
		

		
		JLabel lbl_id1 = new JLabel("-");
		lbl_id1.setBackground(Color.WHITE);
		lbl_id1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id1.setOpaque(true);
		lbl_id1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_id1.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_id1);
		
		JLabel lbl_score1 = new JLabel("-");
		lbl_score1.setBackground(Color.WHITE);
		lbl_score1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_score1.setOpaque(true);
		lbl_score1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_score1.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_score1);
		
		JLabel lbl_dap_all1 = new JLabel("-");
		lbl_dap_all1.setOpaque(true);
		lbl_dap_all1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dap_all1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_dap_all1.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_dap_all1.setBackground(Color.WHITE);
		jp_score.add(lbl_dap_all1);
		
		JLabel lbl_no2 = new JLabel("2");
		lbl_no2.setBackground(Color.WHITE);
		lbl_no2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_no2.setOpaque(true);
		lbl_no2.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_no2.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_no2);
		
		JLabel lbl_id2 = new JLabel("-");
		lbl_id2.setBackground(Color.WHITE);
		lbl_id2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id2.setOpaque(true);
		lbl_id2.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_id2.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_id2);
		
		JLabel lbl_score2 = new JLabel("-");
		lbl_score2.setBackground(Color.WHITE);
		lbl_score2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_score2.setOpaque(true);
		lbl_score2.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_score2.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_score2);
		
		JLabel lbl_dap_all2 = new JLabel("-");
		lbl_dap_all2.setOpaque(true);
		lbl_dap_all2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dap_all2.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_dap_all2.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_dap_all2.setBackground(Color.WHITE);
		jp_score.add(lbl_dap_all2);
		
		JLabel lbl_no3 = new JLabel("3");
		lbl_no3.setBackground(Color.WHITE);
		lbl_no3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_no3.setOpaque(true);
		lbl_no3.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_no3.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_no3);
		
		JLabel lbl_id3 = new JLabel("-");
		lbl_id3.setBackground(Color.WHITE);
		lbl_id3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id3.setOpaque(true);
		lbl_id3.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_id3.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_id3);
		
		JLabel lbl_score3 = new JLabel("-");
		lbl_score3.setBackground(Color.WHITE);
		lbl_score3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_score3.setOpaque(true);
		lbl_score3.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_score3.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_score3);
		
		JLabel lbl_dap_all3 = new JLabel("-");
		lbl_dap_all3.setOpaque(true);
		lbl_dap_all3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dap_all3.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_dap_all3.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_dap_all3.setBackground(Color.WHITE);
		jp_score.add(lbl_dap_all3);
		
		JLabel lbl_no4 = new JLabel("4");
		lbl_no4.setBackground(Color.WHITE);
		lbl_no4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_no4.setOpaque(true);
		lbl_no4.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_no4.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_no4);
		
		JLabel lbl_id4 = new JLabel("-");
		lbl_id4.setBackground(Color.WHITE);
		lbl_id4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id4.setOpaque(true);
		lbl_id4.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_id4.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_id4);
		
		JLabel lbl_score4 = new JLabel("-");
		lbl_score4.setBackground(Color.WHITE);
		lbl_score4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_score4.setOpaque(true);
		lbl_score4.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_score4.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_score4);
		
		JLabel lbl_dap_all4 = new JLabel("-");
		lbl_dap_all4.setOpaque(true);
		lbl_dap_all4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dap_all4.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_dap_all4.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_dap_all4.setBackground(Color.WHITE);
		jp_score.add(lbl_dap_all4);
		
		JLabel lbl_no5 = new JLabel("5");
		lbl_no5.setBackground(Color.WHITE);
		lbl_no5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_no5.setOpaque(true);
		lbl_no5.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_no5.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_no5);
		
		JLabel lbl_id5 = new JLabel("-");
		lbl_id5.setBackground(Color.WHITE);
		lbl_id5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id5.setOpaque(true);
		lbl_id5.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_id5.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_id5);
		
		JLabel lbl_score5 = new JLabel("-");
		lbl_score5.setBackground(Color.WHITE);
		lbl_score5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_score5.setOpaque(true);
		lbl_score5.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_score5.setBorder(new LineBorder(new Color(169, 169, 169)));
		jp_score.add(lbl_score5);
		
		JLabel lbl_dap_all5 = new JLabel("-");
		lbl_dap_all5.setOpaque(true);
		lbl_dap_all5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dap_all5.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_dap_all5.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_dap_all5.setBackground(Color.WHITE);
		jp_score.add(lbl_dap_all5);
		
		JPanel cp_left_m = new JPanel();
		cp_left_m.setLayout(null);
		cp_left_m.setBounds(0, 0, 330, 565);
		contentPane.add(cp_left_m);
		
		JLabel lbl_user = new JLabel(vo3.getId());
		lbl_user.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_user.setBounds(164, 160, 110, 15);
		cp_left_m.add(lbl_user);
		
		JLabel lbl_maxscore = new JLabel(String.valueOf(vo3.getScore()));
		lbl_maxscore.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_maxscore.setBounds(164, 215, 110, 15);
		cp_left_m.add(lbl_maxscore);
		
		JLabel icon_logout = new JLabel("");
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
		icon_logout.setToolTipText("\uB85C\uADF8\uC544\uC6C3");
		icon_logout.setBounds(120, 495, 24, 24);
	      icon_logout.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/logout.png")));
		cp_left_m.add(icon_logout);
		
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
		
		JLabel lbl_dapall = new JLabel(String.valueOf(vo3.getDap_all()));
		lbl_dapall.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_dapall.setBounds(164, 240, 110, 15);
		cp_left_m.add(lbl_dapall);
		
		JLabel lbl_ui_leftbar_1 = new JLabel("");
		lbl_ui_leftbar_1.setOpaque(true);
		lbl_ui_leftbar_1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_leftbar_1.setBackground(SystemColor.activeCaptionBorder);
		lbl_ui_leftbar_1.setBounds(40, 272, 250, 1);
		cp_left_m.add(lbl_ui_leftbar_1);
		
		JLabel lbl_ui_leftbar_2 = new JLabel("");
		lbl_ui_leftbar_2.setOpaque(true);
		lbl_ui_leftbar_2.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_leftbar_2.setBackground(SystemColor.activeCaptionBorder);
		lbl_ui_leftbar_2.setBounds(40, 380, 250, 1);
		cp_left_m.add(lbl_ui_leftbar_2);
		
		JLabel lbl_ui_dapnow = new JLabel("\uD604\uC7AC \uC815\uB2F5 \uC218");
		lbl_ui_dapnow.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_dapnow.setBounds(74, 344, 80, 15);
		cp_left_m.add(lbl_ui_dapnow);
		
		JLabel lbl_ui_chance = new JLabel("\uD604\uC7AC \uB0A8\uC740 \uAE30\uD68C");
		lbl_ui_chance.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_chance.setBounds(74, 319, 80, 15);
		cp_left_m.add(lbl_ui_chance);
		
		JLabel lbl_ui_nowscore = new JLabel("\uD604\uC7AC \uC810\uC218");
		lbl_ui_nowscore.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_nowscore.setBounds(74, 294, 80, 15);
		cp_left_m.add(lbl_ui_nowscore);
		
		JLabel lbl_nowscore = new JLabel("-");
		lbl_nowscore.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_nowscore.setBounds(164, 294, 110, 15);
		cp_left_m.add(lbl_nowscore);
		
		JLabel lbl_chance = new JLabel("-");
		lbl_chance.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_chance.setBounds(164, 319, 110, 15);
		cp_left_m.add(lbl_chance);
		
		JLabel lbl_dapnow = new JLabel("-");
		lbl_dapnow.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_dapnow.setBounds(164, 344, 110, 15);
		cp_left_m.add(lbl_dapnow);
		
		JLabel lbl_ui_vcount = new JLabel("\uBC29\uBB38 \uD69F\uC218");
		lbl_ui_vcount.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_vcount.setBounds(74, 401, 80, 15);
		cp_left_m.add(lbl_ui_vcount);
		
		JLabel lbl_ui_fdate = new JLabel("\uAC00\uC785\uC77C");
		lbl_ui_fdate.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_fdate.setBounds(74, 426, 80, 15);
		cp_left_m.add(lbl_ui_fdate);
		
		JLabel lbl_fdate = new JLabel(String.valueOf(vo3.getFdate()));
		lbl_fdate.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_fdate.setBounds(164, 426, 110, 15);
		cp_left_m.add(lbl_fdate);
		
		JLabel lbl_vcount = new JLabel(String.valueOf(vo3.getV_count()));
		lbl_vcount.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_vcount.setBounds(164, 401, 110, 15);
		cp_left_m.add(lbl_vcount);
		
		JLabel lbl_ui_leftbar_1_1 = new JLabel("");
		lbl_ui_leftbar_1_1.setOpaque(true);
		lbl_ui_leftbar_1_1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_leftbar_1_1.setBackground(SystemColor.activeCaptionBorder);
		lbl_ui_leftbar_1_1.setBounds(40, 193, 250, 1);
		cp_left_m.add(lbl_ui_leftbar_1_1);
		
		JLabel lbl_ui_id_1 = new JLabel("\uB85C\uADF8\uC544\uC6C3");
		lbl_ui_id_1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_ui_id_1.setBounds(150, 500, 80, 15);
		cp_left_m.add(lbl_ui_id_1);
		
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
		cp_left_m.add(lbl_logout);
		
		JLabel lbl_left_title = new JLabel("");
		lbl_left_title.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		lbl_left_title.setBounds(40, 109, 250, 36);
		lbl_left_title.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/title_user.png")));
		cp_left_m.add(lbl_left_title);
		
		ArrayList<VO> tiArray = td.score();
		ArrayList<String> id2 = new ArrayList<String>() ;
		ArrayList<Integer> score2 = new ArrayList<Integer>() ;
		ArrayList<Integer> dap_all2 = new ArrayList<Integer>() ;
		
			for(VO imsi:tiArray) {//ÇÏ³ª¾¿ ²¨³»¼­ VO¿¡ ³Ö¾îÁÜ
				
			id2.add(imsi.getId());
			score2.add(imsi.getScore());
			dap_all2.add(imsi.getDap_all());
		}
			
			if (id2.size()!=0) {
				lbl_id1.setText(id2.get(0));
				lbl_score1.setText(Integer.toString(score2.get(0)));
				lbl_dap_all1.setText(Integer.toString(dap_all2.get(0)));
			if (id2.size()>=2) {
				lbl_id2.setText(id2.get(1));
				lbl_score2.setText(Integer.toString(score2.get(1)));
				lbl_dap_all2.setText(Integer.toString(dap_all2.get(1)));
			if (id2.size()>=3) {
				lbl_id3.setText(id2.get(2));
				lbl_score3.setText(Integer.toString(score2.get(2)));
				lbl_dap_all3.setText(Integer.toString(dap_all2.get(2)));
			if (id2.size()>=4) {
				lbl_id4.setText(id2.get(3));
				lbl_score4.setText(Integer.toString(score2.get(3)));
				lbl_dap_all4.setText(Integer.toString(dap_all2.get(3)));
			if (id2.size()>=5) {		
				lbl_id5.setText(id2.get(4));
				lbl_score5.setText(Integer.toString(score2.get(4)));
				lbl_dap_all5.setText(Integer.toString(dap_all2.get(4)));
			}}}}}
			
		if(lbl_id1.getText().equals(vo3.getId())) {
			lbl_no1.setBackground(new Color(255,235,205));
			lbl_id1.setBackground(new Color(255,235,205));
			lbl_score1.setBackground(new Color(255,235,205));
			lbl_dap_all1.setBackground(new Color(255,235,205));
		}
		if(lbl_id2.getText().equals(vo3.getId())) {
			lbl_no2.setBackground(new Color(255,235,205));
			lbl_id2.setBackground(new Color(255,235,205));
			lbl_score2.setBackground(new Color(255,235,205));
			lbl_dap_all2.setBackground(new Color(255,235,205));
		}
		if(lbl_id3.getText().equals(vo3.getId())) {
			lbl_no3.setBackground(new Color(255,235,205));
			lbl_id3.setBackground(new Color(255,235,205));
			lbl_score3.setBackground(new Color(255,235,205));
			lbl_dap_all3.setBackground(new Color(255,235,205));
		}
		if(lbl_id4.getText().equals(vo3.getId())) {
			lbl_no4.setBackground(new Color(255,235,205));
			lbl_id4.setBackground(new Color(255,235,205));
			lbl_score4.setBackground(new Color(255,235,205));
			lbl_dap_all4.setBackground(new Color(255,235,205));
		}
		if(lbl_id5.getText().equals(vo3.getId())) {
			lbl_no5.setBackground(new Color(255,235,205));
			lbl_id5.setBackground(new Color(255,235,205));
			lbl_score5.setBackground(new Color(255,235,205));
			lbl_dap_all5.setBackground(new Color(255,235,205));
		}
	
	}
	
}

