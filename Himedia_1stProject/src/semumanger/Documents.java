package semumanger;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JButton submit;
	private JButton[] select, remove;
//	private JButton[] save;
	private JFileChooser fileComponent = new JFileChooser();
    private int find, last;
//    private boolean []match = new boolean[] {true, true, true, true, true, true, true, true};
    private String[] where;
	
//	public String getUserID() {
//		return userID;
//	}

	public Documents(String userID) {
		this.userID = userID;
		dao = new MemberDAO();
		where= new String[]{"","","","","","","",""};
		
		docHome = new JFrame("세무매니저-자료제출");
		docHome.setSize(385, 570);
		docHome.setLayout(null);
		
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
			txtField[i].setBounds(105, height, 180, 20);
			height += 50;
			docHome.add(txtField[i]);
			txtField[i].setForeground(Color.black);
			txtField[i].setEditable(false);
			txtField[i].setBackground(Color.lightGray);
		}

// 저장 button		
//		save = new JButton[8];
//		height = 80;
//		for (int i = 0; i < save.length; i++) {
//			save[i] = new JButton("저장");
//			save[i].setBounds(285, height, 60, 20);
//			height += 50;
//			docHome.add(save[i]);
//			save[i].addActionListener(this);
//		}

// 삭제 button		
		remove = new JButton[8];
		height = 80;
		for (int i = 0; i < remove.length; i++) {
			remove[i] = new JButton("삭제");
			remove[i].setBounds(285, height, 60, 20);
			height += 50;
			docHome.add(remove[i]);
			remove[i].addActionListener(this);
		}		
		
		
		submit = new JButton("저장 및 제출");
		submit.setBounds(130, 470, 120, 30);
		submit.setBackground(Color.blue);
		submit.setForeground(Color.white);
		submit.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		docHome.add(title);
		docHome.add(submit);
		docHome.addWindowListener(this);
		submit.addActionListener(this);
		
		
        fileComponent.setFileFilter(new FileNameExtensionFilter("pdf", "pdf")); // 확장자 .xlsx, xls만 선택가
        fileComponent.setMultiSelectionEnabled(false); // 다중 선택 불가 설정
		
		docHome.getContentPane().setBackground(Color.white);
		docHome.setLocationRelativeTo(null);
		docHome.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
// 파일선택 액션
		if (e.getSource() == select[0]) {
			if (fileComponent.showSaveDialog(filechoose) == JFileChooser.APPROVE_OPTION) {
				find = fileComponent.getSelectedFile().toString().lastIndexOf("\\");
				last = fileComponent.getSelectedFile().toString().length();
				txtField[0].setText(fileComponent.getSelectedFile().toString().substring(find + 1, last - 6) + "...");
				where[0]=  fileComponent.getSelectedFile().toString();
				System.out.println("1번째 파일 경로"+where[0]);
			}
		}
		if (e.getSource() == select[1]) {
			if (fileComponent.showSaveDialog(filechoose) == JFileChooser.APPROVE_OPTION) {
				find = fileComponent.getSelectedFile().toString().lastIndexOf("\\");
				last = fileComponent.getSelectedFile().toString().length();
				txtField[1].setText(fileComponent.getSelectedFile().toString().substring(find + 1, last - 6) + "...");
				where[1] = fileComponent.getSelectedFile().toString();
				System.out.println("2번째 파일 경로" + where[1]);
			}
		}
		if (e.getSource() == select[2]) {
			if (fileComponent.showSaveDialog(filechoose) == JFileChooser.APPROVE_OPTION) {
				find = fileComponent.getSelectedFile().toString().lastIndexOf("\\");
				last = fileComponent.getSelectedFile().toString().length();
				txtField[2].setText(fileComponent.getSelectedFile().toString().substring(find + 1, last - 6) + "...");
				where[2] = fileComponent.getSelectedFile().toString();
				System.out.println("3번째 파일 경로" + where[2]);
			}
		}
		if (e.getSource() == select[3]) {
			if (fileComponent.showSaveDialog(filechoose) == JFileChooser.APPROVE_OPTION) {
				find = fileComponent.getSelectedFile().toString().lastIndexOf("\\");
				last = fileComponent.getSelectedFile().toString().length();
				txtField[3].setText(fileComponent.getSelectedFile().toString().substring(find + 1, last - 6) + "...");
				where[3] = fileComponent.getSelectedFile().toString();
				System.out.println("4번째 파일 경로" + where[3]);
			}
		}
		if (e.getSource() == select[4]) {
			if (fileComponent.showSaveDialog(filechoose) == JFileChooser.APPROVE_OPTION) {
				find = fileComponent.getSelectedFile().toString().lastIndexOf("\\");
				last = fileComponent.getSelectedFile().toString().length();
				txtField[4].setText(fileComponent.getSelectedFile().toString().substring(find + 1, last - 6) + "...");
				where[4] = fileComponent.getSelectedFile().toString();
				System.out.println("5번째 파일 경로" + where[4]);
			}
		}
		if (e.getSource() == select[5]) {
			if (fileComponent.showSaveDialog(filechoose) == JFileChooser.APPROVE_OPTION) {
				find = fileComponent.getSelectedFile().toString().lastIndexOf("\\");
				last = fileComponent.getSelectedFile().toString().length();
				txtField[5].setText(fileComponent.getSelectedFile().toString().substring(find + 1, last - 6) + "...");
				where[5] = fileComponent.getSelectedFile().toString();
				System.out.println("6번째 파일 경로" + where[5]);
			}
		}
		if (e.getSource() == select[6]) {
			if (fileComponent.showSaveDialog(filechoose) == JFileChooser.APPROVE_OPTION) {
				find = fileComponent.getSelectedFile().toString().lastIndexOf("\\");
				last = fileComponent.getSelectedFile().toString().length();
				txtField[6].setText(fileComponent.getSelectedFile().toString().substring(find + 1, last - 6) + "...");
				where[6] = fileComponent.getSelectedFile().toString();
				System.out.println("7번째 파일 경로" + where[6]);
			}
		}
		if (e.getSource() == select[7]) {
			if (fileComponent.showSaveDialog(filechoose) == JFileChooser.APPROVE_OPTION) {
				find = fileComponent.getSelectedFile().toString().lastIndexOf("\\");
				last = fileComponent.getSelectedFile().toString().length();
				txtField[7].setText(fileComponent.getSelectedFile().toString().substring(find + 1, last - 6) + "...");
				where[7] = fileComponent.getSelectedFile().toString();
				System.out.println("8번째 파일 경로" + where[7]);
			}
		}

		
//		if(e.getSource() == remove[0]) {
//			txtField[0].setText("");
//			
//			String filename = "FIENAME"+"1";
//			String inpid = userID;
//			ArrayList<MemberVo> check = dao.check(inpid);
//
//			MemberVo data = (MemberVo) check.get(0);
//			String email = data.getEmail();
//			System.out.println("-----------로그인유저 아이디 :"+email);
//			
//			dao.deleteFilePosition(dao.checkUserId(inpid), filename);			
//			
//		}
		
		
		
		
		
		
		
		
// 제출 액션
		if (e.getSource() == submit) {
			String inpid = userID;
			ArrayList<MemberVo> check = dao.check(inpid);

			MemberVo data = (MemberVo) check.get(0);
			String email = data.getEmail();
			System.out.println("-----------로그인유저 아이디 :"+email);
			
//			dao.fileposition(dao.checkUserId(inpid), where[0] ,where[1],where[2],where[3],where[4],where[5],where[6],where[7]);
			dao.fileposition("1001", where[0], where[1], where[2], where[3], where[4], where[5], where[6], where[7]);
			JOptionPane.showMessageDialog(null, "제출되었습니다.", "제출성공", JOptionPane.WARNING_MESSAGE);
		}
				
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
}
