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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.Newaccount_DAO;
import dao.SHA256;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField pf_pw;
	private JLabel lbl_id;
	private JLabel lbl_pw;
	private JLabel lbl_pw_1;
	private JPasswordField pf_pwre;
	private JPanel cp_down_m;
	private JLabel lbl_ui_downbar;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() throws ClassNotFoundException, SQLException {
		setResizable(false);
		System.out.println("▒▒▒▒▒ SignUp 이동 완료 ▒▒▒▒▒");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Newaccount_DAO na_dao = new Newaccount_DAO();
		
		JPanel cp_left_m = new JPanel();
		cp_left_m.setBounds(0, 0, 330, 565);
		contentPane.add(cp_left_m);
		cp_left_m.setLayout(null);
		
		JLabel lbl_left_title = new JLabel("");
		lbl_left_title.setBounds(40, 114, 250, 36);
		cp_left_m.add(lbl_left_title);
		lbl_left_title.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/title_join.png")));
		
		pf_pw = new JPasswordField();
		pf_pw.setBounds(95, 215, 185, 30);
		cp_left_m.add(pf_pw);
		pf_pw.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 26));
		pf_pw.setBorder(null);
		
		pf_pwre = new JPasswordField();
		pf_pwre.setBounds(95, 265, 185, 30);
		cp_left_m.add(pf_pwre);
		pf_pwre.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 26));
		pf_pwre.setBorder(null);
		
		txtId = new JTextField();
		txtId.setBounds(95, 165, 185, 30);
		cp_left_m.add(txtId);
		txtId.setBorder(null);
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
		txtId.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		txtId.setColumns(10);
		JButton btnSignUp = new JButton("\uC644\uB8CC");
		btnSignUp.setBounds(170, 310, 120, 40);
		cp_left_m.add(btnSignUp);
		btnSignUp.addActionListener(new ActionListener() {
			SHA256 sha256 = new SHA256();
			String Cryptogram;
			public void actionPerformed(ActionEvent e) {

				int b2 = 0;
				
				// ↓ ID, PW, PW_RE(동일 값체크) 입력 받기
				
				String id= txtId.getText();
				String pw = pf_pw.getText();		
				String pw_re = pf_pwre.getText();
				
				try {
					Cryptogram = sha256.encrypt(pw);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					System.out.println(Cryptogram);
				
				// ↓ 암호 입력값 동일 여부 체크
				if(pw.equals(pw_re)) {
					b2 = na_dao.new_account(id, Cryptogram);
				}else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					System.out.println("비밀번호가 일치하지 않습니다.");
					return;
				}

				// ↓ 계정 생성 여부 확인					
				if(b2==0) {
					JOptionPane.showMessageDialog(null, "ID가 생성되었습니다.");
					System.out.println("계정 생성 성공");
					dispose();
					setVisible(false);
						new Start().setVisible(true);
				} else if (b2==1){
					JOptionPane.showMessageDialog(null, "에러 발생 계정 생성 실패");
					System.out.println("에러 발생 계정 생성 실패");
				} else {
					JOptionPane.showMessageDialog(null, "사용 불가한 ID 입니다.");
					System.out.println("사용 불가한 ID 입니다.");
				}
			}
		});
		btnSignUp.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnSignUp.setBackground(new Color(225, 212, 0));
		
		lbl_id = new JLabel("  ID");
		lbl_id.setBounds(40, 160, 250, 40);
		cp_left_m.add(lbl_id);
		lbl_id.setOpaque(true);
		lbl_id.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		lbl_id.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_id.setBackground(Color.WHITE);
		
		lbl_pw = new JLabel("  PW(re)");
		lbl_pw.setBounds(40, 260, 250, 40);
		cp_left_m.add(lbl_pw);
		lbl_pw.setOpaque(true);
		lbl_pw.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		lbl_pw.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_pw.setBackground(Color.WHITE);
		
		lbl_pw_1 = new JLabel("  PW");
		lbl_pw_1.setBounds(40, 210, 250, 40);
		cp_left_m.add(lbl_pw_1);
		lbl_pw_1.setOpaque(true);
		lbl_pw_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		lbl_pw_1.setBorder(new LineBorder(new Color(169, 169, 169)));
		lbl_pw_1.setBackground(Color.WHITE);
		
		JButton btnBack = new JButton("\uB4A4\uB85C");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnBack) {
					dispose();
					setVisible(false);
						new Start().setVisible(true);
				}
			}
		});
		btnBack.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnBack.setBackground(new Color(245, 245, 245));
		btnBack.setBounds(40, 310, 120, 40);
		cp_left_m.add(btnBack);
		
		JLabel lbl_ui_title = new JLabel("");
		lbl_ui_title.setBounds(560, 163, 238, 238);
		contentPane.add(lbl_ui_title);
		lbl_ui_title.setIcon(new ImageIcon(CorrectAnswer.class.getResource("/picture/ui/title.png")));
		
		cp_down_m = new JPanel();
		cp_down_m.setLayout(null);
		cp_down_m.setOpaque(false);
		cp_down_m.setBounds(340, 507, 662, 48);
		contentPane.add(cp_down_m);
		
		lbl_ui_downbar = new JLabel("");
		lbl_ui_downbar.setOpaque(true);
		lbl_ui_downbar.setBackground(new Color(224, 224, 224));
		lbl_ui_downbar.setBounds(10, 10, 642, 1);
		cp_down_m.add(lbl_ui_downbar);
		
		lblNewLabel = new JLabel("Copyright 2022. 2\uC870 \uAD6C\uBCF8\uACBD \uBB38\uADDC\uD658 \uC774\uC778\uD638 \uC804\uD638\uD615 \uD55C\uAE30\uD0DC all right reversed.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 19, 642, 17);
		cp_down_m.add(lblNewLabel);
	}
}
