package semumanger;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Search extends WindowAdapter implements ActionListener, FocusListener {
	private String userID;
	private JFrame sf;
	private JPanel panel1, panel2, panel3;
	private JTextField title1, title2, title3, infoTxt, companyTxt, ceoTxt, dateTxt, peopleTxt, mngNtxt, mngEtxt, contents;
	private JLabel info, company, ceo, date, people, mngName, mngEmail, mngGuide;
	private JButton fix, save, next;

	public Search(String userID) {
		this.userID = userID;

		sf = new JFrame("신고의뢰내역조회-주요정보");
		sf.setSize(380, 680);
		sf.setLayout(null);
		sf.getContentPane().setBackground(Color.white);

// 메뉴 타이틀
		title1 = new JTextField("   주요정보");
		title1.setBounds(20, 10, 70, 30);
		title1.setBackground(Color.black);
		title1.setEnabled(false);

		title2 = new JTextField(" 담당자정보");
		title2.setBounds(20, 285, 70, 30);
		title2.setBackground(Color.black);
		title2.setEnabled(false);
		
		title3 = new JTextField("  수정요청내역");
		title3.setBounds(20, 470, 90, 30);
		title3.setBackground(Color.black);
		title3.setEnabled(false);

// 패널
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(21, 40, 320, 220);
		panel1.setBackground(Color.getHSBColor(0, 0, (float) 0.9));

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(21, 315, 320, 140);
		panel2.setBackground(Color.getHSBColor(0, (float) 0.05, (float) 0.9));	

		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(21, 500, 320, 100);
		panel3.setBackground(Color.getHSBColor(0, (float) 0.05, (float) 0.9));		

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
		peopleTxt = new JTextField();
		infoTxt.setBounds(95, 18, 200, 25);
		companyTxt.setBounds(95, 58, 200, 25);
		ceoTxt.setBounds(95, 98, 200, 25);
		dateTxt.setBounds(95, 138, 200, 25);
		peopleTxt.setBounds(95, 178, 200, 25);
		infoTxt.setEnabled(false);
		companyTxt.setEnabled(false);
		ceoTxt.setEnabled(false);
		dateTxt.setEnabled(false);
		peopleTxt.setEnabled(false);

// 버튼
		fix = new JButton("수정");
		save = new JButton("저장");
		next = new JButton("다음");
		fix.setBounds(210, 270, 60, 20);
		save.setBounds(280, 270, 60, 20);
//		save.setBackground(Color.blue);
//		save.setForeground(Color.white);	
		
		
// 담당자 라벨		
		mngName = new JLabel("담당자명");
		mngEmail = new JLabel("담당자이메일");
		mngGuide = new JLabel("<html><body><center>담당자 배치는 2~3일이 소요됩니다.<br>지속하여 배치 중일 경우, '02-000-0000' 으로 문의해주세요.</center></body></html>");
		mngName.setBounds(40, 20, 60, 20);
		mngEmail.setBounds(15, 60, 100, 20);	
		mngGuide.setBounds(20, 88, 275, 50);
		mngGuide.setFont(new Font("맑은 고딕", Font.ITALIC, 10));
		mngGuide.setForeground(Color.blue);

// 담당자 텍스트핃드
		mngNtxt = new JTextField();
		mngEtxt = new JTextField();
		mngNtxt.setBounds(100, 20, 200, 25);
		mngEtxt.setBounds(100, 60, 200, 25);
		mngNtxt.setEnabled(false);		
		mngEtxt.setEnabled(false);		
		
// 수정요청내역 텍스트필드	
		contents = new JTextField();
		contents.setBounds(20, 15, 280, 70);
		contents.setEnabled(false);		

// add		
		sf.add(title1);
		sf.add(title2);
		sf.add(title3);
		sf.add(panel1);
		sf.add(panel2);
		sf.add(panel3);
		sf.add(fix);
		sf.add(save);
		panel1.add(info);
		panel1.add(company);
		panel1.add(ceo);
		panel1.add(date);
		panel1.add(people);
		panel1.add(infoTxt);
		panel1.add(companyTxt);
		panel1.add(ceoTxt);
		panel1.add(dateTxt);
		panel1.add(peopleTxt);	
		panel2.add(mngName);	
		panel2.add(mngEmail);	
		panel2.add(mngNtxt);	
		panel2.add(mngEtxt);	
		panel2.add(mngGuide);
		panel3.add(contents);		

		sf.addWindowListener(this);

		sf.setLocationRelativeTo(null);
		sf.setVisible(true);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
