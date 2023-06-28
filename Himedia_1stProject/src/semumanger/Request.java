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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Request extends WindowAdapter implements ActionListener, FocusListener {
	private JFrame reqInfo;
	private JLabel company, ceo, date, people, saveMent;
	private JPanel panel;
	private JTextField info, companyTxt, ceoTxt, dateTxt, staffTxt;
	private JButton save, next;
	private MemberDAO dao;
	private JDialog nextguide;
	private String userID;

//	public String getUserID() {
//		return userID;
//	}

	public Request(String userID) {
		this.userID = userID;

		dao = new MemberDAO();

		reqInfo = new JFrame("부가가치세 신고의뢰-정보입력");
		reqInfo.setSize(380, 380);

		panel = new JPanel();
		panel.setLayout(null);

// 메뉴 타이틀
		info = new JTextField("   주요정보");
		info.setBounds(40, 40, 70, 30);
		info.setBackground(Color.black);
		info.setEnabled(false);

// 하위메뉴 라벨
		company = new JLabel("회사명");
		ceo = new JLabel("대표자명");
		date = new JLabel("설립일자");
		people = new JLabel("직원수");
		company.setBounds(55, 50, 100, 100);
		ceo.setBounds(45, 90, 100, 100);
		date.setBounds(45, 130, 100, 100);
		people.setBounds(55, 170, 100, 100);

// 텍스트필드		
		companyTxt = new JTextField();
		ceoTxt = new JTextField();
		dateTxt = new JTextField();
		staffTxt = new JTextField();
		companyTxt.setBounds(110, 90, 200, 25);
		ceoTxt.setBounds(110, 130, 200, 25);
		dateTxt.setBounds(110, 170, 200, 25);
		staffTxt.setBounds(110, 210, 200, 25);

// 버튼
		save = new JButton("저장 및 다음");
		save.setBounds(160, 260, 150, 30);

// 다이알로그 세팅		
		nextguide = new JDialog(reqInfo, "저장성공", false);
		nextguide.setSize(250, 200);
		nextguide.setLayout(null);
		saveMent = new JLabel("저장성공!");
		next = new JButton("다음(서류제출)");
		saveMent.setBounds(70, 20, 120, 50);
		next.setBounds(45, 70, 150, 20);
		saveMent.setFont(new Font("맑은 고딕", Font.BOLD, 25));

// add		
		panel.add(info);
		panel.add(company);
		panel.add(ceo);
		panel.add(date);
		panel.add(people);
		panel.add(companyTxt);
		panel.add(ceoTxt);
		panel.add(dateTxt);
		panel.add(staffTxt);
		panel.add(save);
		nextguide.add(saveMent);
		nextguide.add(next);
		save.addActionListener(this);
		next.addActionListener(this);
		companyTxt.addFocusListener(this);
		ceoTxt.addFocusListener(this);
		dateTxt.addFocusListener(this);		
		staffTxt.addFocusListener(this);		
		
		reqInfo.add(panel);
		reqInfo.addWindowListener(this);
		reqInfo.setLocationRelativeTo(null);
		nextguide.setLocationRelativeTo(null);
		reqInfo.setVisible(true);
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
		if (e.getSource() == save) {
			if (companyTxt.getText().equals(null) || companyTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "회사명을 입력해주세요.", "회사명 공백", JOptionPane.WARNING_MESSAGE);
				companyTxt.requestFocus(true);
				return;
			}

			if (!ceoTxt.getText().matches("^[a-zA-Z]*$") && !ceoTxt.getText().matches("^[가-힣]*$")) {
				JOptionPane.showMessageDialog(null, "대표명은 영문 또는 한글로 입력하세요.", "대표명 오류", JOptionPane.WARNING_MESSAGE);
				ceoTxt.requestFocus(true);
				return;
			}

			if (ceoTxt.getText().equals(null) || ceoTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "대표명을 입력해주세요.", "대표명 공백", JOptionPane.WARNING_MESSAGE);
				ceoTxt.requestFocus(true);
				return;
			}

			if (dateTxt.getText().equals(null) || dateTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "날짜를 입력해주세요.", "날짜 공백", JOptionPane.WARNING_MESSAGE);
				dateTxt.requestFocus(true);
				return;
			}

			if (!dateTxt.getText().matches("^(19[0-9][0-9]|20[0-9][0-9])-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
				JOptionPane.showMessageDialog(null, "날짜는 0000-00-00 으로 입력하세요", "날짜 오류", JOptionPane.WARNING_MESSAGE);
				dateTxt.requestFocus(true);
				return;
			}

			if (!staffTxt.getText().matches("^[0-9]*$")) {
				JOptionPane.showMessageDialog(null, "직원수는 숫자로 입력하세요.", "직원수 오류", JOptionPane.WARNING_MESSAGE);
				staffTxt.requestFocus(true);
				return;
			}

			if (staffTxt.getText().equals(null) || staffTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "직원수를 입력해주세요.", "직원수 공백", JOptionPane.WARNING_MESSAGE);
				staffTxt.requestFocus(true);
				return;
			}

			String inpid = userID;
			ArrayList<MemberVo> check = dao.check(inpid);

			MemberVo data = (MemberVo) check.get(0);
			String email = data.getEmail();
			System.out.println(email);

			dao.request(dao.checkUserId(inpid), companyTxt.getText(), ceoTxt.getText(), dateTxt.getText(),
					staffTxt.getText(), "10010", null, "");
			nextguide.setVisible(true);
		}

		if (e.getSource() == next) {
			Documents doc = new Documents(userID);
			nextguide.setVisible(false);
			reqInfo.setVisible(false);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

}
