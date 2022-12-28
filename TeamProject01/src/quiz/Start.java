package quiz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.Login_DAO;
import dao.SHA256;
import dao.Toss_DAO;
import vo.VO2;

public class Start extends JFrame {

	VO2 vo2 = new VO2();
	Toss_DAO td = new Toss_DAO();
	
	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField pf_pw;
	String Cryptogram; // PW 암호화
	
	/**
	 * Launch the application.
	 */
	
	public static class conn_id {
		String id;
		
		public conn_id () {
			
		}	
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
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
	public Start(){
		setResizable(false);
		System.out.println("▒▒▒▒▒ Start 이동 완료 ▒▒▒▒▒");
		Login_DAO l_dao = new Login_DAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_ui_title = new JLabel("");
		lbl_ui_title.setBounds(560, 163, 238, 238);
		contentPane.add(lbl_ui_title);
		lbl_ui_title.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/title.png")));
		
		JPanel cp_left_m = new JPanel();
		cp_left_m.setBackground(new Color(240, 240, 240));
		cp_left_m.setBounds(0, 0, 330, 565);
		contentPane.add(cp_left_m);
		SpringLayout sl_cp_left_m = new SpringLayout();
		cp_left_m.setLayout(sl_cp_left_m);
			
		txtId = new JTextField();
		sl_cp_left_m.putConstraint(SpringLayout.NORTH, txtId, 165, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.WEST, txtId, 95, SpringLayout.WEST, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.SOUTH, txtId, 195, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.EAST, txtId, 280, SpringLayout.WEST, cp_left_m);
		cp_left_m.add(txtId);
		txtId.setBorder(null);
		txtId.setBackground(Color.WHITE);
		txtId.addMouseListener(new MouseAdapter() {
			int sw = 0;
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==txtId) {
					if (sw == 0) {
						txtId.setText("");
						sw = 1;
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getSource()==txtId) {
					if (sw == 0) {
						txtId.setText("");
						sw = 1;
					}
				}
			}
		});
		txtId.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		txtId.setColumns(10);
		JButton btn_Enter = new JButton("\uC785\uC7A5");
		sl_cp_left_m.putConstraint(SpringLayout.NORTH, btn_Enter, 260, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.WEST, btn_Enter, 170, SpringLayout.WEST, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.SOUTH, btn_Enter, 300, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.EAST, btn_Enter, 290, SpringLayout.WEST, cp_left_m);
		cp_left_m.add(btn_Enter);
		btn_Enter.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btn_Enter.setBackground(new Color(225,212,0));
		
		JButton btn_SignUp = new JButton("\uD68C\uC6D0\uAC00\uC785");
		sl_cp_left_m.putConstraint(SpringLayout.NORTH, btn_SignUp, 260, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.WEST, btn_SignUp, 40, SpringLayout.WEST, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.SOUTH, btn_SignUp, 300, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.EAST, btn_SignUp, 160, SpringLayout.WEST, cp_left_m);
		cp_left_m.add(btn_SignUp);
		btn_SignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false); 
				try {
					new SignUp().setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_SignUp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btn_SignUp.setBackground(new Color(245, 245, 245));
		
		pf_pw = new JPasswordField();
		sl_cp_left_m.putConstraint(SpringLayout.NORTH, pf_pw, 215, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.WEST, pf_pw, 95, SpringLayout.WEST, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.SOUTH, pf_pw, 245, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.EAST, pf_pw, 280, SpringLayout.WEST, cp_left_m);
		cp_left_m.add(pf_pw);
		pf_pw.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 26));
		pf_pw.setBorder(null);
		
		JLabel lbl_id = new JLabel("  ID");
		sl_cp_left_m.putConstraint(SpringLayout.NORTH, lbl_id, 160, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.WEST, lbl_id, 40, SpringLayout.WEST, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.SOUTH, lbl_id, 200, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.EAST, lbl_id, 290, SpringLayout.WEST, cp_left_m);
		cp_left_m.add(lbl_id);
		lbl_id.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		lbl_id.setOpaque(true);
		lbl_id.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_id.setBackground(Color.WHITE);
		
		JLabel lbl_pw = new JLabel("  PW");
		sl_cp_left_m.putConstraint(SpringLayout.NORTH, lbl_pw, 210, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.WEST, lbl_pw, 40, SpringLayout.WEST, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.SOUTH, lbl_pw, 250, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.EAST, lbl_pw, 290, SpringLayout.WEST, cp_left_m);
		cp_left_m.add(lbl_pw);
		lbl_pw.setOpaque(true);
		lbl_pw.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		lbl_pw.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_pw.setBackground(Color.WHITE);
		
		JLabel lbl_left_title = new JLabel("");
		sl_cp_left_m.putConstraint(SpringLayout.NORTH, lbl_left_title, 114, SpringLayout.NORTH, cp_left_m);
		sl_cp_left_m.putConstraint(SpringLayout.WEST, lbl_left_title, 40, SpringLayout.WEST, cp_left_m);
		cp_left_m.add(lbl_left_title);
		lbl_left_title.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/title_login.png")));
		
		JPanel cp_down_m = new JPanel();
		cp_down_m.setOpaque(false);
		cp_down_m.setBounds(340, 517, 662, 48);
		contentPane.add(cp_down_m);
		cp_down_m.setLayout(null);
		
		JLabel lbl_ui_downbar = new JLabel("");
		lbl_ui_downbar.setOpaque(true);
		lbl_ui_downbar.setBackground(new Color(224, 224, 224));
		lbl_ui_downbar.setBounds(10, 10, 642, 1);
		cp_down_m.add(lbl_ui_downbar);
		
		JLabel lblNewLabel = new JLabel("Copyright 2022. 2\uC870 \uAD6C\uBCF8\uACBD \uBB38\uADDC\uD658 \uC774\uC778\uD638 \uC804\uD638\uD615 \uD55C\uAE30\uD0DC all right reversed.");
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 19, 642, 17);
		cp_down_m.add(lblNewLabel);
		btn_Enter.addActionListener(new ActionListener() {
			SHA256 sha256 = new SHA256();
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource()==btn_Enter) {		
					// ↓ ID, PW 입력 받기
					String id= txtId.getText();
					String pw = pf_pw.getText();				
					
					// PASSWORD 암호화 시작 =========================
					try {
						Cryptogram = sha256.encrypt(pw);
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// PASSWORD 암호화 완료 =========================
					boolean b1 = l_dao.login(id, Cryptogram);

					// ↓ 접속 여부 확인
				
					if(id.equals("") || pw.equals("")) {
						JOptionPane.showMessageDialog(null, "아이디, 패스워드를 입력해주세요.");
						System.out.println("아이디, 패스워드를 입력해주세요.");
						return;
					}		
					if(b1) {
						dispose();
						setVisible(false);
						new Topic(vo2 = td.TOtopicNrank(id)).setVisible(true); // Toss_DAO -> VO2 반환
					}
					else {
						JOptionPane.showMessageDialog(null, "아이디, 패스워드가 일치하지 않습니다.");
						System.out.println("아이디, 패스워드가 일치하지 않습니다.");
						return;
					}
				}
			}
		});
	}
}