package semumanger;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Search extends WindowAdapter implements ActionListener, FocusListener {
	private MemberDAO dao1;
	private MemberDAO2 dao;
	private String userID;
	private JFrame sf;
	private JPanel panel1, panel2, panel3;
	private JTextField title1, title2, title3, infoTxt, companyTxt, ceoTxt, dateTxt, staffTxt, mngNtxt, mngEtxt;
	private JLabel info, company, ceo, date, people, mngName, mngEmail, mngGuide;
	private JButton fix, save, next;
	private boolean fixSwitch;
	private JTextArea txtArea;
	private JScrollPane scrollbar;
	private Date today = new Date();
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd / hh:mm");

	public Search(String userID) {
		this.userID = userID;
		dao = new MemberDAO2();
		dao1 = new MemberDAO();

		sf = new JFrame("신고의뢰내역조회-주요정보");
		sf.setSize(380, 680);
		sf.setLayout(null);
		sf.getContentPane().setBackground(Color.white);

// 메뉴 타이틀

		title1 = new JTextField("  수정요청내역");
		title1.setBounds(20, 10, 90, 30);
		title1.setBackground(Color.black);
		title1.setEnabled(false);

		title2 = new JTextField("   주요정보");
		title2.setBounds(20, 160, 70, 30);
		title2.setBackground(Color.black);
		title2.setEnabled(false);

		title3 = new JTextField(" 담당자정보");
		title3.setBounds(20, 445, 70, 30);
		title3.setBackground(Color.black);
		title3.setEnabled(false);

// 패널
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(21, 40, 320, 100);
		panel1.setBackground(Color.getHSBColor(0, (float) 0.05, (float) 0.9));

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(21, 190, 320, 220);
		panel2.setBackground(Color.getHSBColor(0, 0, (float) 0.9));

		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(21, 475, 320, 140);
		panel3.setBackground(Color.getHSBColor(0, (float) 0.05, (float) 0.9));

// 수정요청내역 텍스트필드		
		txtArea = new JTextArea("수정요청 사항이 없습니다.");
		txtArea.setLineWrap(true);
//		txtArea.setEnabled(false);
		txtArea.setEditable(false);
		txtArea.setForeground(Color.blue);
		scrollbar = new JScrollPane(txtArea);
		scrollbar.setBounds(20, 15, 280, 70);

// 주요정보 라벨		
		info = new JLabel("신청자명");
		company = new JLabel("회사명");
		ceo = new JLabel("대표자명");
		date = new JLabel("설립일자");
		people = new JLabel("직원수");
		info.setBounds(25, 20, 100, 20);
		company.setBounds(35, 60, 100, 20);
		ceo.setBounds(25, 100, 100, 20);
		date.setBounds(25, 140, 100, 20);
		people.setBounds(35, 180, 100, 20);

// 주요정보 텍스트필드
		fixSwitch = false;
		infoTxt = new JTextField();
		companyTxt = new JTextField();
		ceoTxt = new JTextField();
		dateTxt = new JTextField();
		staffTxt = new JTextField();
		infoTxt.setBounds(95, 18, 200, 25);
		companyTxt.setBounds(95, 58, 200, 25);
		ceoTxt.setBounds(95, 98, 200, 25);
		dateTxt.setBounds(95, 138, 200, 25);
		staffTxt.setBounds(95, 178, 200, 25);
		infoTxt.setEnabled(false);
		companyTxt.setEnabled(false);
		ceoTxt.setEnabled(false);
		dateTxt.setEnabled(false);
		staffTxt.setEnabled(false);

// 주요정보 텍스트필드 조회	
		infoTxt.setText(searchRequestInfoName());
		companyTxt.setText(searchRequestInfoCompany());
		ceoTxt.setText(searchRequestInfoCeo());
		dateTxt.setText(searchRequestInfoDate());
		staffTxt.setText(searchRequestInfoStaff());
		txtArea.setText(searchRequestInfoGuide());

// 버튼
		fix = new JButton("수정");
		save = new JButton("저장");
		next = new JButton("다음");
		fix.setBounds(140, 420, 60, 20);
		save.setBounds(210, 420, 60, 20);
		next.setBounds(280, 420, 60, 20);
//		save.setBackground(Color.blue);
//		save.setForeground(Color.white);	

// 담당자 라벨		
		mngName = new JLabel("담당자명");
		mngEmail = new JLabel("담당자이메일");
		mngGuide = new JLabel(
				"<html><body><center>담당자 배치는 2~3일이 소요됩니다.<br>지속하여 배치 중일 경우, 'qna@namu.co.kr' 으로 문의해주세요.</center></body></html>");
		mngName.setBounds(40, 20, 60, 20);
		mngEmail.setBounds(15, 60, 100, 20);
		mngGuide.setBounds(15, 88, 290, 50);
		mngGuide.setFont(new Font("맑은 고딕", Font.ITALIC, 10));
		mngGuide.setForeground(Color.blue);

// 담당자 텍스트핃드
		mngNtxt = new JTextField();
		mngEtxt = new JTextField();
		mngNtxt.setBounds(100, 20, 200, 25);
		mngEtxt.setBounds(100, 60, 200, 25);
		mngNtxt.setEnabled(false);
		mngEtxt.setEnabled(false);

// 담당자 텍스트필드 조회
		mngNtxt.setText(searchRequestInfoMngname());
		mngEtxt.setText(searchRequestInfoMngemail());

// add		
		sf.add(title1);
		sf.add(title2);
		sf.add(title3);
		sf.add(panel2);
		sf.add(panel3);
		sf.add(panel1);
		sf.add(fix);
		sf.add(save);
		sf.add(next);
		panel1.add(scrollbar);
		panel2.add(info);
		panel2.add(company);
		panel2.add(ceo);
		panel2.add(date);
		panel2.add(people);
		panel2.add(infoTxt);
		panel2.add(companyTxt);
		panel2.add(ceoTxt);
		panel2.add(dateTxt);
		panel2.add(staffTxt);
		panel3.add(mngName);
		panel3.add(mngEmail);
		panel3.add(mngNtxt);
		panel3.add(mngEtxt);
		panel3.add(mngGuide);

		companyTxt.addFocusListener(this);
		ceoTxt.addFocusListener(this);
		dateTxt.addFocusListener(this);
		staffTxt.addFocusListener(this);
		sf.addWindowListener(this);
		fix.addActionListener(this);
		save.addActionListener(this);
		next.addActionListener(this);
		sf.setLocationRelativeTo(null);
		sf.setVisible(true);
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
	}

// 신청자명 조회메서드
	public String searchRequestInfoName() {
		String inpid = userID;
//		String inpid = "gs@naver.com";
//		String inpid = "";
		ArrayList<MemberVo2> requestlist = dao.requestlist(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist.get(0);

		String rname = datalist.getRname();
		System.out.println(dateformat.format(today)+" : <신청자정보> 신청자명 : " + rname);
		return rname;
	}

// 회사명 조회메서드	
	public String searchRequestInfoCompany() {
		String inpid = userID;
//		String inpid = "gs@naver.com";
//		String inpid = "";
		ArrayList<MemberVo2> requestlist = dao.requestlist(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist.get(0);
		String company = datalist.getCompany();
		System.out.println(dateformat.format(today)+" : <신청자정보> 회사명 : " + company);

		return company;
	}

// 대표자명 조회메서드		
	public String searchRequestInfoCeo() {
		String inpid = userID;
//		String inpid = "gs@naver.com";
//		String inpid = "";
		ArrayList<MemberVo2> requestlist = dao.requestlist(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist.get(0);
		String ceo = datalist.getCeo();
		System.out.println(dateformat.format(today)+" : <신청자정보> 대표자명 : " + ceo);

		return ceo;
	}

// 설립일 조회메서드		
	public String searchRequestInfoDate() {
		String inpid = userID;
//		String inpid = "gs@naver.com";
//		String inpid = "";
		ArrayList<MemberVo2> requestlist = dao.requestlist(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist.get(0);
		String date = datalist.getDate();
		System.out.println(dateformat.format(today)+" : <신청자정보> 설립일 : " + date);

		return date;
	}

// 직원수 조회메서드		
	public String searchRequestInfoStaff() {
		String inpid = userID;
//		String inpid = "gs@naver.com";
//		String inpid = "";
		ArrayList<MemberVo2> requestlist = dao.requestlist(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist.get(0);
		String staff = datalist.getStaff();
		System.out.println(dateformat.format(today)+" : <신청자정보> 직원수 : " + staff);

		return staff;
	}

// 수정요청내역 조회메서드		
	public String searchRequestInfoGuide() {
		String inpid = userID;
//		String inpid = "gs@naver.com";
//		String inpid = "";
		ArrayList<MemberVo2> requestlist = dao.requestlist(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist.get(0);
		String guide = datalist.getGuide();
		System.out.println(dateformat.format(today)+" : <신청자정보> 수정요청내역 : " + guide);

		return guide;
	}

// 담당자명 조회메서드		
	public String searchRequestInfoMngname() {
		String inpid = userID;
//		String inpid = "gs@naver.com";
//					String inpid = "";
		ArrayList<MemberVo2> requestlist = dao.requestlist(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist.get(0);
		String mngName = datalist.getMngName();
		System.out.println(dateformat.format(today)+" : <신청자정보> 담당자명 : " + mngName);

		return mngName;
	}

// 담당자이메일 조회메서드		
	public String searchRequestInfoMngemail() {
		String inpid = userID;
//			String inpid = "gs@naver.com";
//			String inpid = "";
		ArrayList<MemberVo2> requestlist = dao.requestlist(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist.get(0);
		String mngEmail = datalist.getMngEmail();
		System.out.println(dateformat.format(today)+" : <신청자정보> 담당자이메일 : " + mngEmail);

		return mngEmail;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ment = "의뢰내역 없음";
		if (e.getSource() == fix) {
			if (!infoTxt.getText().equals(ment)) {
				fixSwitch = true;
				companyTxt.setEnabled(true);
				ceoTxt.setEnabled(true);
				dateTxt.setEnabled(true);
				staffTxt.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "신고의뢰내역이 없습니다.", "조회불가", JOptionPane.WARNING_MESSAGE);
			}
		}

		if (e.getSource() == save) {
			if (fixSwitch == true) {

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

				if (!dateTxt.getText()
						.matches("^(19[0-9][0-9]|20[0-9][0-9])-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
					JOptionPane.showMessageDialog(null, "날짜는 0000-00-00 으로 입력하세요", "날짜 오류",
							JOptionPane.WARNING_MESSAGE);
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

				dao1.updateinfo(dao1.checkUserId(userID), companyTxt.getText(), ceoTxt.getText(), dateTxt.getText(),
						staffTxt.getText());
				JOptionPane.showMessageDialog(null, "수정사항 저장이 완료되었습니다.", "수정반영", JOptionPane.WARNING_MESSAGE);
				companyTxt.setEnabled(false);
				ceoTxt.setEnabled(false);
				dateTxt.setEnabled(false);
				staffTxt.setEnabled(false);

			} else {
				JOptionPane.showMessageDialog(null, "수정사항이 없습니다.", "수정요청", JOptionPane.WARNING_MESSAGE);
			}
		}

		if (e.getSource() == next) {
			if (!infoTxt.getText().equals(ment)) {
				SearchDoc sd = new SearchDoc(userID);
//				SearchDoc sd = new SearchDoc("");
			} else {
				JOptionPane.showMessageDialog(null, "신고의뢰내역이 없습니다.", "조회불가", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (e.getComponent() == sf) {
			sf.setVisible(false);
		} else {
			System.exit(0);
		}
	}

}
