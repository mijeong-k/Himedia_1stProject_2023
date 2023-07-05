package semumanger;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Documents  extends WindowAdapter implements ActionListener{
	private MemberDAO dao;
	
	private String userID;
	private JFrame docHome, filechoose;
	private JLabel[] list;
	private String[] listname;
	private JTextField title;
	private JTextField[] txtField;
	private JButton submit, ok;
	private JButton[] select;
	private JFileChooser fileComponent = new JFileChooser();
    private int find, last;
    private String[] where;
    private JDialog submitTrue;	
    private JLabel submitment;
    private Date today;
    private SimpleDateFormat dateformat;

	public Documents(String userID) {
		this.userID = userID;
		dao = new MemberDAO();
		where= new String[]{"","","","","","","",""};
		today = new Date();
		dateformat = new SimpleDateFormat("yyyy-MM-dd / hh:mm");
		
		docHome = new JFrame("부가가치세 신고의뢰-자료제출");
		docHome.setSize(380, 570);
		docHome.setLayout(null);
		docHome.getContentPane().setBackground(Color.white);
		
		filechoose = new JFrame("파일선택");
		filechoose.setSize(440, 600);
		filechoose.setLayout(new FlowLayout());		

// title
		title = new JTextField("   자료제출");
		title.setBounds(20, 20, 70, 30);
		title.setBackground(Color.black);
		title.setForeground(Color.white);
		title.setEditable(false);

// list 라벨		
		listname = new String[] { "▶ 부가가치세신고서", "▶ 매출처별세금계산서합계표", "▶ 매출처별,매입처별계산서합계표", "▶ 신용카드매출전표", "▶ 사업장현황명세서",
				"▶ 현금매출명세서", "▶ 수출실적명세서", "▶ 기타증빙서류" };
		list = new JLabel[8];

		int height = 60;
		for (int i = 0; i < list.length; i++) {
			list[i] = new JLabel(listname[i]);
			list[i].setBounds(20, height, 200, 20);
			height += 50;
			docHome.add(list[i]);
		}

// 파일선택 button		
		select = new JButton[8];
		height = 80;
		for (int i = 0; i < select.length; i++) {
			select[i] = new JButton("파일선택");
			select[i].setBounds(20, height, 85, 20);
			height += 50;
			docHome.add(select[i]);
			select[i].addActionListener(this); 
		}

// 파일명 텍스트필드
		txtField = new JTextField[8];
		height = 80;
		for (int i = 0; i < txtField.length; i++) {
			txtField[i] = new JTextField("파일명");
			txtField[i].setBounds(105, height, 230, 20);
			height += 50;
			docHome.add(txtField[i]);
			txtField[i].setForeground(Color.black);
			txtField[i].setEditable(false);
			txtField[i].setBackground(Color.lightGray);
		}	
		
// 제출 팝업창		
		submitTrue = new JDialog(docHome, "제출성공", false);
		submitTrue.setSize(250, 200);
		submitTrue.setLayout(null);
		
		submit = new JButton("저장 및 제출");
		submit.setBounds(130, 470, 120, 30);
		submit.setBackground(Color.blue);
		submit.setForeground(Color.white);
		submit.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				
		submitment = new JLabel("<html><body><center>신고의뢰가 제출되었습니다.<br>서류추가 및 수정은<br>조회메뉴를 이용해주세요.</center></body></html>");
		ok = new JButton("확인");
		submitment.setBounds(40, 20, 250, 70);
		ok.setBounds(65, 90, 100, 20);
		
		
// add		
		docHome.add(title);
		docHome.add(submit);
		submitTrue.add(submitment);
		submitTrue.add(ok);
		docHome.addWindowListener(this);
		submit.addActionListener(this);		
		ok.addActionListener(this);		
		
        fileComponent.setFileFilter(new FileNameExtensionFilter("pdf", "pdf")); // 확장자 .xlsx, xls만 선택가
        fileComponent.setMultiSelectionEnabled(false); // 다중 선택 불가 설정		
		
		docHome.setLocationRelativeTo(null);
		submitTrue.setLocationRelativeTo(null);
		docHome.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
// 파일선택 액션
		
		for(int i=0; i<select.length; i++) {
			if(e.getSource() == select[i]) {
				if(fileComponent.showSaveDialog(filechoose) == JFileChooser.APPROVE_OPTION) {
					find = fileComponent.getSelectedFile().toString().lastIndexOf("\\");
					last = fileComponent.getSelectedFile().toString().length();
					txtField[i].setText(fileComponent.getSelectedFile().toString().substring(find + 1, last - 6) + "...");
					where[i]=  fileComponent.getSelectedFile().toString();
					System.out.println(dateformat.format(today)+" : "+(i+1)+"번째 파일 경로 "+where[i]);					
				}
			}
		}
						
		
// 제출 액션
		if (e.getSource() == submit) {
			String inpid = userID;
			ArrayList<MemberVo> check = dao.check(inpid);

			MemberVo data = (MemberVo) check.get(0);
			String email = data.getEmail();
			System.out.println(dateformat.format(today)+" : 로그인유저 아이디 : "+email);
			
			dao.fileposition(dao.checkUserId(inpid), where[0] ,where[1],where[2],where[3],where[4],where[5],where[6],where[7]);
//			dao.fileposition("1001", where[0], where[1], where[2], where[3], where[4], where[5], where[6], where[7]);
			submitTrue.setVisible(true);
		}
				
// 제출완료창 닫기=메인메뉴로 돌아가기
		if(e.getSource() == ok) {
			submitTrue.setVisible(false);
			docHome.setVisible(false);
		}	
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
}
