package semumanger;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MngLogin extends WindowAdapter implements ActionListener, FocusListener  {
	private ManagerDAO mngdao;
	private String mngId;

	private JFrame mngLogin;
	private Icon logoicon;
	private JLabel logo, idLb, pwLb, numLb;
	private JTextField idTxt, numTxt;
	private JPasswordField pwTxt;
	private JButton loginBt;

	public MngLogin() {
		mngdao = new ManagerDAO();
		
		mngLogin = new JFrame("세무매니저 담당자로그인");
		mngLogin.setSize(400, 400);
		mngLogin.setLayout(null);

		logoicon = new ImageIcon("C:\\Users\\Class01\\Desktop\\logoicon.png");
		logo = new JLabel(logoicon);
		logo.setBounds(70, 50, 250, 90);

		idLb = new JLabel("이메일");
		pwLb = new JLabel("비밀번호");
		numLb = new JLabel("사번");
		idLb.setBounds(90, 160, 50, 20);
		pwLb.setBounds(80, 195, 50, 20);
		numLb.setBounds(105, 230, 50, 20);

		idTxt = new JTextField();
		pwTxt = new JPasswordField();
		numTxt = new JTextField();
		idTxt.setBounds(140, 160, 150, 20);
		pwTxt.setBounds(140, 195, 150, 20);
		numTxt.setBounds(140, 230, 150, 20);

		loginBt = new JButton("로그인");
		loginBt.setBounds(165, 280, 75, 25);

		mngLogin.add(logo);
		mngLogin.add(idLb);
		mngLogin.add(pwLb);
		mngLogin.add(numLb);
		mngLogin.add(idTxt);
		mngLogin.add(pwTxt);
		mngLogin.add(numTxt);
		mngLogin.add(loginBt);
		mngLogin.addWindowListener(this);
		loginBt.addActionListener(this);

		mngLogin.setLocationRelativeTo(null);
		mngLogin.getContentPane().setBackground(Color.white);
		mngLogin.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginBt) {
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
			if (!pwTxt.getText().matches("^[0-9]+$"))  {
				JOptionPane.showMessageDialog(null, "비밀번호는 숫자로 입력하세요.", "비밀번호 오류", JOptionPane.WARNING_MESSAGE);
				pwTxt.requestFocus(true);
				return;
			}
			
			if (numTxt.getText().equals(null) || numTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "사번을 입력해주세요.", "사번 공백", JOptionPane.WARNING_MESSAGE);
				numTxt.requestFocus(true);
				return; 
			}
			if (!numTxt.getText().matches("^[0-9]+$"))  {
				JOptionPane.showMessageDialog(null, "사번은 숫자로 입력하세요.", "사번 오류", JOptionPane.WARNING_MESSAGE);
				numTxt.requestFocus(true);
				return;
			}		
						
			System.out.println(idTxt.getText() + pwTxt.getText());
			String inpid = idTxt.getText();
			ArrayList<ManagerVo> mnglist = mngdao.mnglist(inpid);

			if (mnglist.size() == 1) {
				ManagerVo data = (ManagerVo) mnglist.get(0);
				String number = data.getNumber();
				String email = data.getEmail();
				String pwd = data.getPassword();			

				if (pwTxt.getText().equals(pwd) && numTxt.getText().equals(number) ) {
					System.out.println("--------<로그인성공> 아이디 : "+ email + ", 비번 : " + pwd +", 사번 : "+ number);
					mngId = number;
					MngHome mngho = new MngHome(mngId);
					mngLogin.setVisible(false);

				} else {
					System.out.println("로그인 실패");
					JOptionPane.showMessageDialog(null, "이메일/비밀번호/사번을 확인해주세요.", "로그인 오류", JOptionPane.WARNING_MESSAGE);
				}
			}else {
				System.out.println("로그인 실패");
				JOptionPane.showMessageDialog(null, "이메일/비밀번호/사번을 확인해주세요.", "로그인 오류", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}
