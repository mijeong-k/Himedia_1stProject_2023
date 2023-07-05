package semumanger;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class MngSearch extends WindowAdapter implements ActionListener {
	private MemberDAO2 dao;
	private ManagerDAO mngdao;
	private String userid;
	private JFrame mw;
	private JTextField title, title2, infoTxt, companyTxt, ceoTxt, dateTxt, staffTxt;
	private JLabel info, company, ceo, date, people;
	private JPanel panel, panel2;
	private JButton fix, next;
	private JTextArea txtArea;
	private JScrollPane scrollbar;
	private Date today = new Date();
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd / hh:mm");

	public MngSearch(String userid) {
		dao = new MemberDAO2();
		mngdao = new ManagerDAO();
		
		this.userid = userid;

//		System.out.println(userid);
		mw = new JFrame("담당자-의뢰내역조회");
		mw.setSize(390, 540);
		mw.setLayout(null);
		mw.getContentPane().setBackground(Color.white);

		title = new JTextField("  의뢰내역상세");
		title.setBounds(20, 10, 90, 30);
		title.setBackground(Color.black);
		title.setEnabled(false);

		title2 = new JTextField("    수정요청");
		title2.setBounds(20, 290, 80, 30);
		title2.setBackground(Color.black);
		title2.setEnabled(false);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(21, 40, 320, 220);
		panel.setBackground(Color.getHSBColor(0, 0, (float) 0.9));

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(21, 320, 320, 100);
		panel2.setBackground(Color.getHSBColor(0, (float) 0.05, (float) 0.9));

		// 수정요청내역 텍스트필드
		txtArea = new JTextArea();
		txtArea.setLineWrap(true);
//		txtArea.setEnabled(false);
//		txtArea.setEditable(false);
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

		fix = new JButton("수정요청");
		fix.setBounds(250, 430, 90, 20);
		// 수정요청 버튼 누르면 수정멘트남기는 다이알로그 뜸. 입력하면 DB-fixguide 인서트.
		next = new JButton("다음");
		next.setBounds(280, 280, 60, 20);

		mw.add(panel);
		mw.add(panel2);
		panel.add(info);
		panel.add(company);
		panel.add(ceo);
		panel.add(date);
		panel.add(people);
		panel.add(infoTxt);
		panel.add(companyTxt);
		panel.add(ceoTxt);
		panel.add(dateTxt);
		panel.add(staffTxt);
		panel2.add(scrollbar);
		mw.add(fix);
		mw.add(next);
		next.addActionListener(this);
		fix.addActionListener(this);
		mw.addWindowListener(this);

		mw.add(title);
		mw.add(title2);
		mw.setLocationRelativeTo(null);
		mw.setVisible(true);

	}

	// 신청자명 조회메서드
	public String searchRequestInfoName() {
		String inpid = userid;
//		String inpid = "gs@naver.com";
//		String inpid = "";
		ArrayList<MemberVo2> requestlist2 = dao.requestlist2(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist2.get(0);

		String rname = datalist.getRname();
		System.out.println(dateformat.format(today)+" : <신청자정보> 신청자명 : " + rname);
		return rname;
	}

	// 회사명 조회메서드
	public String searchRequestInfoCompany() {
		String inpid = userid;
//		String inpid = "gs@naver.com";
//		String inpid = "";
		ArrayList<MemberVo2> requestlist2 = dao.requestlist2(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist2.get(0);
		String company = datalist.getCompany();
		System.out.println(dateformat.format(today)+" : <신청자정보> 회사명 : " + company);

		return company;
	}

	// 대표자명 조회메서드
	public String searchRequestInfoCeo() {
		String inpid = userid;
//			String inpid = "gs@naver.com";
//			String inpid = "";
		ArrayList<MemberVo2> requestlist2 = dao.requestlist2(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist2.get(0);
		String ceo = datalist.getCeo();
		System.out.println(dateformat.format(today)+" : <신청자정보> 대표자명 : " + ceo);

		return ceo;
	}

	// 설립일 조회메서드
	public String searchRequestInfoDate() {
		String inpid = userid;
//			String inpid = "gs@naver.com";
//			String inpid = "";
		ArrayList<MemberVo2> requestlist2 = dao.requestlist2(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist2.get(0);
		String date = datalist.getDate();
		System.out.println(dateformat.format(today)+" : <신청자정보> 설립일 : " + date);

		return date;
	}

	// 직원수 조회메서드
	public String searchRequestInfoStaff() {
		String inpid = userid;
//			String inpid = "gs@naver.com";
//			String inpid = "";
		ArrayList<MemberVo2> requestlist2 = dao.requestlist2(inpid);
		MemberVo2 datalist = (MemberVo2) requestlist2.get(0);
		String staff = datalist.getStaff();
		System.out.println(dateformat.format(today)+" : <신청자정보> 직원수 : " + staff);

		return staff;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			MngSearchDoc msd = new MngSearchDoc(userid);
//			System.out.println("userid 확인 :"+userid);
			// user code로 산출됨
//			SearchDoc sd = new SearchDoc("");
		}
		
		if(e.getSource() == fix) {
			String guide = txtArea.getText();
			mngdao.updateGuide(guide, userid);
			JOptionPane.showMessageDialog(null, "수정요청이 완료되었습니다.", "수정요청", JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (e.getComponent() == mw) {
			mw.setVisible(false);
		} else {
			System.exit(0);
		}
	}
}
