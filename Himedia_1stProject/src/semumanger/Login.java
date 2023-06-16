package semumanger;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends WindowAdapter implements ActionListener {
	private JFrame sort, userLogin, idFind, pwFind, home;
	private JLabel logo, logo2, idLb, pwLb, failTitle, failMent;
	private JTextField idTxt;
	private JPasswordField pwTxt;
	private JButton joinBt, idFindBt, pwFindBt, loginBt, userBt, adminBt, joinBt2, loginBt2;
	private Icon logoicon, logoicon2;
	private Dialog loginfail;
	private MemberDAO dao;

	public Login() {

//사용자로그인,관리자로그인 sorting 화면
		sort = new JFrame("세무매니저");
		sort.setSize(400, 400);
		sort.setLayout(null);

		logoicon = new ImageIcon("C:\\Users\\Class01\\Desktop\\logoicon.png");
		logo = new JLabel(logoicon);
		logo.setBounds(70, 90, 250, 90);

		userBt = new JButton("사용자 로그인");
		adminBt = new JButton("담당자 로그인");
		userBt.setBounds(70, 200, 120, 30);
		adminBt.setBounds(200, 200, 120, 30);

// 사용자로그인 화면 구현
		userLogin = new JFrame("세무매니저 사용자로그인");
		userLogin.setSize(400, 400);
		userLogin.setLayout(null);

		logoicon2 = new ImageIcon("C:\\Users\\Class01\\Desktop\\logoicon.png");
		logo2 = new JLabel(logoicon2);
		logo2.setBounds(70, 50, 250, 90);

		idLb = new JLabel("이메일");
		pwLb = new JLabel("비밀번호");
		idLb.setBounds(60, 160, 50, 20);
		pwLb.setBounds(50, 195, 50, 20);		

		idTxt = new JTextField();
		pwTxt = new JPasswordField();
		idTxt.setBounds(110, 160, 150, 20);
		pwTxt.setBounds(110, 195, 150, 20);

		joinBt = new JButton("회원가입 /");
		idFindBt = new JButton("이메일찾기 /");
		pwFindBt = new JButton("비밀번호찾기");
		loginBt = new JButton("로그인");
		joinBt.setBounds(55, 230, 90, 20);
		idFindBt.setBounds(125, 230, 120, 20);
		pwFindBt.setBounds(210, 230, 120, 20);
		loginBt.setBounds(270, 160, 75, 55);
		joinBt.setBorderPainted(false);
		joinBt.setContentAreaFilled(false);
		idFindBt.setBorderPainted(false);
		idFindBt.setContentAreaFilled(false);
		pwFindBt.setBorderPainted(false);
		pwFindBt.setContentAreaFilled(false);

// 로그인실패 팝업
		loginfail = new Dialog(userLogin, "로그인 실패", false);
		loginfail.setSize(250, 200);
		loginfail.setLayout(null);
		failMent = new JLabel("<html><body><center>가입된 이력이 없습니다.<br> 회원가입 또는 다시 로그인해주세요.</center></body></html>");		
		failTitle = new JLabel("로그인 실패");
		failTitle.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		joinBt2 = new JButton("회원가입");
		loginBt2 = new JButton("로그인");		
		
		failTitle.setBounds(55,15, 200,100);
		failMent.setBounds(25,85, 200,50);		
		joinBt2.setBounds(35,140, 90,20);		
		loginBt2.setBounds(135,140, 80,20);

// add		
		userLogin.add(idLb);
		userLogin.add(pwLb);
		sort.add(logo);
		userLogin.add(logo2);
		userLogin.add(idTxt);
		userLogin.add(pwTxt);
		userLogin.add(joinBt);
		userLogin.add(idFindBt);
		userLogin.add(pwFindBt);
		userLogin.add(loginBt);
		sort.add(userBt);
		sort.add(adminBt);
		loginfail.add(failMent);
		loginfail.add(failTitle);
		loginfail.add(joinBt2);
		loginfail.add(loginBt2);

		userBt.addActionListener(this);
		joinBt.addActionListener(this);
		loginBt.addActionListener(this);
		joinBt2.addActionListener(this);
		loginBt2.addActionListener(this);

// position , backgroungcolor 설정		
		
		sort.setLocationRelativeTo(null);	
		userLogin.setLocationRelativeTo(null);
		userLogin.getContentPane().setBackground(Color.white);	
		loginfail.setLocationRelativeTo(null);

// DB connect
		dao = new MemberDAO();
		
// visible
		sort.setVisible(true);
		sort.setResizable(false);
		userLogin.setResizable(false);
		sort.setResizable(false);
//		join.setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == userBt) {
			sort.setVisible(false);
			userLogin.setVisible(true);
		}
		
		if (e.getSource() == joinBt) {
			userLogin.setVisible(false);
			Join jo = new Join();
		}
		
		if (e.getSource() == loginBt) {
			System.out.println(idTxt.getText() + pwTxt.getText());
			String inpid = idTxt.getText();
			ArrayList<MemberVo> list = dao.list(inpid);

			if (list.size() == 1) {
				MemberVo data = (MemberVo) list.get(0);
				String id = data.getId();
				String pwd = data.getPassword();
				System.out.println(id + " : " + pwd);

				if (pwTxt.getText().equals(pwd)) {
					Home ho = new Home();
				} else {
					loginfail.setVisible(true);
				}

			} else {
				loginfail.setVisible(true);
			}
		}
		
		if (e.getSource() == joinBt2) {
			loginfail.setVisible(false);
			userLogin.setVisible(false);
			Join jo = new Join();		
		}
		
		if (e.getSource() == loginBt2) {
			loginfail.setVisible(false);
		}		
		
	}

}
