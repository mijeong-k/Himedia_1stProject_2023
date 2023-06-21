package semumanger;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Join extends WindowAdapter implements ActionListener, FocusListener {
	private JFrame join;
	private JLabel title, nameLb, idLb, pwLb, phoneLb, que, guide, joinMent1, joinMent2;
	private JTextField nameTxt, idTxt, phoneTxt;
	private JPasswordField pwTxt;
	private JButton joinBt, loginBt, loginBtAfterJoin;
	private MemberDAO dao;
	private Dialog joingood;

	public Join() {

// DB 연동
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

// 가입성공		
		joingood = new Dialog(join, "가입성공", false);
		joingood.setSize(250, 200);
		joingood.setLayout(null);

		joinMent1 = new JLabel("가입완료!");
		joinMent2 = new JLabel("세무매니저 가입을 환영합니다.");
		joinMent1.setBounds(80, 80, 100, 20);
		joinMent2.setBounds(40, 100, 200, 20);

		loginBtAfterJoin = new JButton("로그인 하러가기");
		loginBtAfterJoin.setBounds(60, 130, 130, 20);

// 텍스트스타일 지정		
		title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		joinMent1.setFont(new Font("맑은 고딕", Font.BOLD, 20));

// add		
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
		joingood.add(joinMent1);
		joingood.add(joinMent2);
		joingood.add(loginBtAfterJoin);

// add 액션, 포커스, 윈도우
		joinBt.addActionListener(this);
		loginBt.addActionListener(this);
		loginBtAfterJoin.addActionListener(this);
		join.addWindowListener(this);
		joingood.addWindowListener(this);
		nameTxt.addFocusListener(this);
		idTxt.addFocusListener(this);
		pwTxt.addFocusListener(this);
		phoneTxt.addFocusListener(this);

// 포지션 설정, 배경화면 설정
		join.setLocationRelativeTo(null);
		joingood.setLocationRelativeTo(null);
		join.getContentPane().setBackground(Color.white);
		join.setVisible(true);
	}

// window 액션	
	public void windowClosing(WindowEvent e) {
		if (e.getComponent() == joingood) {
			joingood.dispose();
		} else {
			System.exit(0);
		}
	}

// focus 액션		
	@Override
	public void focusGained(FocusEvent arg0) {
	}

	@Override
	public void focusLost(FocusEvent arg0) {
	}

// 액션 - 회원가입 조건, 로그인하기	
	@Override
	public void actionPerformed(ActionEvent e) {
		String strId = idTxt.getText();
		ArrayList<MemberVo> list = dao.list(strId);

		if (e.getSource() == joinBt) {
			if (nameTxt.getText().equals(null) || nameTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "이름 공백", JOptionPane.WARNING_MESSAGE);
				nameTxt.requestFocus(true);
				return;
			}
			if (!nameTxt.getText().matches("^[a-zA-Z]*$") && !nameTxt.getText().matches("^[가-힣]*$")) {
				JOptionPane.showMessageDialog(null, "이름은 영문 또는 한글로 입력하세요.", "이름 오류", JOptionPane.WARNING_MESSAGE);
				nameTxt.requestFocus(true);
				return;
			}

			if (idTxt.getText().equals(null) || idTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.", "이메일 공백", JOptionPane.WARNING_MESSAGE);
				idTxt.addFocusListener(this);
				return;
			}
			if (!idTxt.getText()
					.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")) {
				JOptionPane.showMessageDialog(null, "이메일 형식을 지켜주세요.", "이메일형식 오류", JOptionPane.WARNING_MESSAGE);
				idTxt.addFocusListener(this);
				return;
			}

			if (pwTxt.getText().equals(null) || pwTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "비밀번호 공백", JOptionPane.WARNING_MESSAGE);
				pwTxt.requestFocus(true);
				return;
			}
			if (!pwTxt.getText().matches("^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")) {
				JOptionPane.showMessageDialog(null,
						"<html><body><center>비밀번호는 숫자, 문자, 특수문자를<br>포함하여 8~15자리 이내로 작성하세요.</center></body></html>",
						"비밀번호 오류", JOptionPane.WARNING_MESSAGE);
				pwTxt.requestFocus(true);
				return;
			}

			if (phoneTxt.getText().equals(null) || phoneTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "핸드폰번호를 입력해주세요.", "핸드폰번호 공백", JOptionPane.WARNING_MESSAGE);
				phoneTxt.addFocusListener(this);
				return;
			}
			if (!phoneTxt.getText().matches("^\\d{3}-\\d{3,4}-\\d{4}$")
					&& !phoneTxt.getText().matches("^\\\\d{2,3}-\\\\d{3,4}-\\\\d{4}$")) {
				JOptionPane.showMessageDialog(null, "핸드폰번호는 - 을 포함하여 작성하세요.", "핸드폰번호 오류", JOptionPane.WARNING_MESSAGE);
				phoneTxt.addFocusListener(this);
				return;
			}

			if (!(list.size() == 0)) {
				JOptionPane.showMessageDialog(null, "중복된 아이디가 있습니다.", "이메일 오류", JOptionPane.WARNING_MESSAGE);
				idTxt.requestFocus(true);
				return;
			}

			dao.insert(dao.findUserId(), nameTxt.getText(), idTxt.getText(), pwTxt.getText(), phoneTxt.getText());
			joingood.setVisible(true);
		}

		if (e.getSource() == loginBt || e.getSource() == loginBtAfterJoin) {
			Login lo = new Login();
			join.setVisible(false);
		}
	}
}
