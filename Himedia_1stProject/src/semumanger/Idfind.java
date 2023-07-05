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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Idfind extends WindowAdapter implements ActionListener, FocusListener {
	private IdpwdDAO dao;
	private JFrame iff;
	private JLabel title, ment;
	private JTextField name, phone;
	private JButton ok;

	public Idfind() {
		dao = new IdpwdDAO();

		iff = new JFrame("이메일(ID) 찾기");
		iff.setSize(330, 330);
		iff.setLayout(null);

		title = new JLabel("이메일 찾기");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		title.setBounds(90, 20, 200, 50);

		ment = new JLabel("<html><body><center>가입했던 성함과 휴대폰번호를 입력하세요.<br> (휴대폰번호는 '-' 포함하여 입력)</center></body></html>");
		ment.setBounds(40, 80, 250, 50);

		name = new HintTextField("성함");
		phone = new HintTextField("휴대폰 번호('-' 포함)");

		name.setBounds(40, 150, 250, 30);
		phone.setBounds(40, 180, 250, 30);

		ok = new JButton("확인");
		ok.setBounds(140, 220, 60, 30);

		iff.add(title);
		iff.add(ment);
		iff.add(name);
		iff.add(phone);
		iff.add(ok);
		ok.addActionListener(this);
		iff.setLocationRelativeTo(null);
		iff.setVisible(true);
		iff.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
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

			if (phone.getText().equals(null) || phone.getText().equals("")
					|| phone.getText().equals("휴대폰 번호('-' 포함)")) {
				JOptionPane.showMessageDialog(null, "핸드폰번호를 입력해주세요.", "핸드폰번호 공백", JOptionPane.WARNING_MESSAGE);
				phone.requestFocus(true);
				return;
			}
			if (!phone.getText().matches("^\\d{3}-\\d{3,4}-\\d{4}$")
					&& !phone.getText().matches("^\\\\d{2,3}-\\\\d{3,4}-\\\\d{4}$")) {
				JOptionPane.showMessageDialog(null, "핸드폰번호는 - 을 포함하여 작성하세요.", "핸드폰번호 오류", JOptionPane.WARNING_MESSAGE);
				phone.requestFocus(true);
				return;
			}

			ArrayList<IdpwdVo> list = dao.list(name.getText(), phone.getText());
			if (list.size() == 1) {
				IdpwdVo data = (IdpwdVo) list.get(0);
				String username = data.getUsername();
				String email = data.getEmail();
				String userphone = data.getUserphone();
				if (name.getText().equals(username) && phone.getText().equals(userphone)) {
					JOptionPane.showMessageDialog(null, "이메일은 "+email.replaceAll("(?<=.{3}).(?=.*@)", "*")+" 입니다.", "이메일 안내",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.", "확인 오류", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.", "확인 오류", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
	}

	public void windowClosing(WindowEvent e) {		
		if(e.getComponent() == iff) {
			iff.dispose();
		}else {
			System.exit(0);
		}
		
	}
}
