package semumanger;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Pwdfind extends WindowAdapter implements ActionListener, FocusListener {
	private IdpwdDAO dao;
	private JFrame pff;
	private JLabel title, ment, changetitle, changement;
	private JTextField name, email, pwd, repwd;
	private JButton ok, change;
	private JDialog pwdchange;
	
	public Pwdfind() {
		dao = new IdpwdDAO();

		pff = new JFrame("비밀번호 찾기");
		pff.setSize(330, 330);
		pff.setLayout(null);
		
		title = new JLabel("비밀번호 찾기");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		title.setBounds(85, 20, 200, 50);
		
		ment = new JLabel("가입했던 성함과 이메일주소를 입력하세요.");
		ment.setBounds(40, 80, 250, 50);
	
		name = new HintTextField("성함");
		email = new HintTextField("이메일");
		name.setBounds(32, 120, 250, 30);
		email.setBounds(32, 150, 250, 30);
		
		ok = new JButton("확인");
		ok.setBounds(130, 200, 60, 30);
		
		pwdchange = new JDialog(pff, "비밀번호 재설정", false);
		pwdchange.setSize(300, 300);
		pwdchange.setLayout(null);
		changetitle = new JLabel("비밀번호 재설정");
		changetitle.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		changement = new JLabel("<html><body><center>비밀번호는 숫자, 문자, 특수문자를<br> 포함하여 8~15자 이내로 작성하세요.</center></body></html>");
		changetitle.setBounds(45,5,200,100);
		changement.setBounds(40,85,250,50);	
		
		pwd = new HintTextField("비밀번호");
		repwd = new HintTextField("비밀번호 확인");
		pwd.setBounds(30,140,230,30);
		repwd.setBounds(30,170,230,30);	
		
		change = new JButton("재설정");
		change.setBounds(100,210,100,30);	
		
		pff.add(title);
		pff.add(ment);
		pff.add(name);
		pff.add(email);
		pff.add(ok);
		pwdchange.add(changetitle);
		pwdchange.add(changement);
		pwdchange.add(pwd);
		pwdchange.add(repwd);
		pwdchange.add(change);	
		ok.addActionListener(this);
		change.addActionListener(this);
		pff.setLocationRelativeTo(null);
		pwdchange.setLocationRelativeTo(null);
		pff.setVisible(true);
		pff.addWindowListener(this);
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok) {
			if (name.getText().equals(null) || name.getText().equals("") || name.getText().equals("성함")) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "이름 공백", JOptionPane.WARNING_MESSAGE);
				name.requestFocus(true);
				return;
			}
			if (!name.getText().matches("^[a-zA-Z]*$") && !name.getText().matches("^[가-힣]*$")) {
				JOptionPane.showMessageDialog(null, "이름은 영문 또는 한글로 입력하세요.", "이름 오류", JOptionPane.WARNING_MESSAGE);
				name.requestFocus(true);
				return;
			}
			
			if (email.getText().equals(null) || email.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.", "이메일 공백", JOptionPane.WARNING_MESSAGE);
				email.addFocusListener(this);
				return;
			}
			if (!email.getText()
					.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")) {
				JOptionPane.showMessageDialog(null, "이메일 형식을 지켜주세요.", "이메일형식 오류", JOptionPane.WARNING_MESSAGE);
				email.addFocusListener(this);
				return;
			}
			
			ArrayList<IdpwdVo> list2 = dao.list2(name.getText(), email.getText());
			if (list2.size() == 1) {
				IdpwdVo datas = (IdpwdVo) list2.get(0);
				String username = datas.getUsername();
				String useremail = datas.getEmail();
				if (name.getText().equals(username) && email.getText().equals(useremail)) {
					pwdchange.setVisible(true);					
				} else {
					JOptionPane.showMessageDialog(null, "성함과 이메일이 일치하지 않습니다.", "확인 오류", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "성함과 이메일이 일치하지 않습니다.", "확인 오류", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if(e.getSource() == change) {
			if(pwd.getText().equals("") || pwd.getText().equals(null) || pwd.getText().equals("비밀번호")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.", "확인 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(repwd.getText().equals("") || repwd.getText().equals(null) || repwd.getText().equals("비밀번호 확인")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.", "확인 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!pwd.getText().equals(repwd.getText())){
				JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 불일치합니다.", "확인 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwd.getText().equals(repwd.getText())){
				dao.updatePwd(email.getText(), repwd.getText());			
				JOptionPane.showMessageDialog(null, "비밀번호 재설정 완료", "재설정 완료", JOptionPane.WARNING_MESSAGE);
				return;
			}			
		}
		
	}
	
	public void windowClosing(WindowEvent e) {		
		if(e.getComponent() == pff) {
			pff.dispose();
		}else {
			System.exit(0);
		}
		
	}
}
