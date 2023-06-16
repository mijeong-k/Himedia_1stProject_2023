package semumanger;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Join  extends WindowAdapter implements ActionListener{
	private JFrame join;
	private JLabel title, nameLb, idLb, pwLb, phoneLb, que, guide;
	private JTextField nameTxt, idTxt, phoneTxt;
	private JPasswordField pwTxt;
	private JButton joinBt, loginBt;
	private MemberDAO dao;
	private Dialog joingood;

	public Join() {
		
		dao = new MemberDAO();
		
// 사용자 회원가입 화면
		join = new JFrame("회원가입");
		join.setSize(400, 400);
		join.setLayout(null);

		title = new JLabel("회원가입");
		nameLb = new JLabel("성함");
		idLb = new JLabel("이메일");
		pwLb = new JLabel("비밀번호");
		phoneLb = new JLabel("휴대폰번호");
		que = new JLabel("이미 가입하셨나요?");
		guide = new JLabel("<html><body><center>가입은 이용약관과 개인정보<br> 취급방침에 동의한 것으로 봅니다.</center></body></html>");
		title.setBounds(150, 20, 200, 50);
		nameLb.setBounds(90, 80, 60, 20);
		idLb.setBounds(80, 110, 50, 20);
		pwLb.setBounds(70, 140, 50, 20);
		phoneLb.setBounds(60, 170, 70, 20);
		que.setBounds(135, 300, 150, 20);
		guide.setBounds(100, 230, 200, 50);
		
		nameTxt = new JTextField();
		idTxt = new JTextField();
		pwTxt = new JPasswordField();
		phoneTxt = new JTextField();
		nameTxt.setBounds(140, 80, 150, 20);
		idTxt.setBounds(140, 110, 150, 20);
		pwTxt.setBounds(140, 140, 150, 20);	
		phoneTxt.setBounds(140, 170, 150, 20);	
		
		joinBt = new JButton("회원가입");
		loginBt = new JButton("로그인");
		joinBt.setBounds(140, 200, 100, 20);		
		loginBt.setBounds(140, 320, 100, 20);
		
		title.setFont(new Font("맑은 고딕", Font.BOLD, 25));						
		
		
		joingood = new Dialog(join, "가입성공", false);
		joingood.setSize(250, 200);
		joingood.setLayout(null);
		
		
		
		join.add(title);				
		join.add(nameLb);
		join.add(idLb);
		join.add(pwLb);
		join.add(phoneLb);
		join.add(que);
		join.add(guide);
		join.add(nameTxt);
		join.add(idTxt);
		join.add(pwTxt);
		join.add(phoneTxt);
		join.add(joinBt);
		join.add(loginBt);
		joinBt.addActionListener(this);
		loginBt.addActionListener(this);
		
		
		join.setLocationRelativeTo(null);
		joingood.setLocationRelativeTo(null);
		
		
		join.getContentPane().setBackground(Color.white);
		join.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == joinBt) {
			dao.insert(dao.findUserId(), nameTxt.getText(), idTxt.getText(), pwTxt.getText(), phoneTxt.getText());
			joingood.setVisible(true);
		}
		
		if(e.getSource() == loginBt) {				
			Login lo = new Login();
			join.setVisible(false);
			
		}
		
	}
}
