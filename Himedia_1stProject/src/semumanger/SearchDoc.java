package semumanger;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

public class SearchDoc extends WindowAdapter implements ActionListener, FocusListener {
	private MemberDAO dao1;
	private MemberDAO2 dao;
	private String userID;
	private JFrame sd, filechoose;
	private JTextField title;
	private JLabel[] list;
	private String[] listname;
	private JTextField[] txtField;
	private JButton[] open, save, remove, download;
	private JFileChooser fileComponent;
	private String[] where = {"","","","","","","",""};
	private int find, last;

	public SearchDoc(String userID) {
		dao1 = new MemberDAO();
		dao = new MemberDAO2();
		this.userID = userID;
		fileComponent = new JFileChooser();

		filechoose = new JFrame("파일선택");
		filechoose.setSize(440, 600);
		filechoose.setLayout(new FlowLayout());	
		
		sd = new JFrame("신고의뢰내역조회-제출자료");
		sd.setSize(500, 520);
		sd.setLayout(null);
		sd.getContentPane().setBackground(Color.white);

// title
		title = new JTextField("   자료제출");
		title.setBounds(20, 20, 70, 30);
		title.setBackground(Color.black);
		title.setForeground(Color.white);
		title.setEditable(false);

		sd.addWindowListener(this);
		sd.add(title);

// list 라벨
		listname = new String[] { "▶ 부가가치세신고서", "▶ 매출처별세금계산서합계표", "▶ 매출처별,매입처별계산서합계표", "▶ 신용카드매출전표", "▶ 사업장현황명세서",
				"▶ 현금매출명세서", "▶ 수출실적명세서", "▶ 기타증빙서류" };
		list = new JLabel[8];

		int height = 60;
		for (int i = 0; i < list.length; i++) {
			list[i] = new JLabel(listname[i]);
			list[i].setBounds(20, height, 200, 20);
			height += 50;
			sd.add(list[i]);
		}

// 파일명 텍스트필드
		txtField = new JTextField[8];
		height = 80;
		ArrayList<MemberVo2> doclist = dao.doclist(userID);
		MemberVo2 list = (MemberVo2) doclist.get(0);
		String[] datas = { list.getFilename1(), list.getFilename2(), list.getFilename3(), list.getFilename4(),
				list.getFilename5(), list.getFilename6(), list.getFilename7(), list.getFilename8() };

		for (int i = 0; i < txtField.length; i++) {
			txtField[i] = new JTextField("파일명");
			txtField[i].setBounds(20, height, 210, 20);
			height += 50;
			sd.add(txtField[i]);
			txtField[i].setForeground(Color.black);
			txtField[i].setEditable(false);
			txtField[i].setBackground(Color.lightGray);

			if (datas[i] == null || datas[i].equals("")) {
				txtField[i].setText("");
			} else if (datas[i].length() < 35) {
				txtField[i].setText(datas[i]);
			} else {
				find = datas[i].lastIndexOf("\\");
				last = datas[i].length();
				txtField[i].setText(datas[i].substring(find + 1, last));
			}
		}
		
		
//삭제 button		
		remove = new JButton[8];
		height = 80;
		for (int i = 0; i < remove.length; i++) {
			remove[i] = new JButton("삭제");
			remove[i].setBounds(230, height, 60, 20);
			height += 50;
			sd.add(remove[i]);
			remove[i].addActionListener(this);
			remove[i].setForeground(Color.red);
		}

// 열기 button
		open = new JButton[8];
		height = 80;
		for (int i = 0; i < open.length; i++) {
			open[i] = new JButton("열기");
			open[i].setBounds(290, height, 60, 20);
			height += 50;
			sd.add(open[i]);
			open[i].addActionListener(this);
			open[i].setForeground(Color.blue);
		}
		
// 저장 button		
		save = new JButton[8];
		height = 80;
		for (int i = 0; i < save.length; i++) {
			save[i] = new JButton("저장");
			save[i].setBounds(350, height, 60, 20);
			height += 50;
			sd.add(save[i]);
			save[i].addActionListener(this);
			save[i].setForeground(Color.blue);
		}

// 다운 button
		download = new JButton[8];
		height = 80;
		for (int i = 0; i < download.length; i++) {
			download[i] = new JButton("다운");
			download[i].setBounds(410, height, 60, 20);
			height += 50;
			sd.add(download[i]);
			download[i].addActionListener(this);
		}
		
		fileComponent.setFileFilter(new FileNameExtensionFilter("pdf", "pdf")); // 확장자 .xlsx, xls만 선택가
        fileComponent.setMultiSelectionEnabled(false); // 다중 선택 불가 설정		
		sd.setVisible(true);
		sd.setLocationRelativeTo(null);
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		String inpid = userID;

		for (int i = 0; i < save.length; i++) {
			if (e.getSource() == open[i]) {
				if (fileComponent.showSaveDialog(filechoose) == JFileChooser.APPROVE_OPTION) {				
					where[i] = fileComponent.getSelectedFile().toString();
					System.out.println((i + 1) + "번째 파일 경로" + where[i]);
				}
			}

			if (e.getSource() == save[i]) {			
				dao1.updateFilePosition(dao1.checkUserId(userID), (i + 1), where[i]);
				find = fileComponent.getSelectedFile().toString().lastIndexOf("\\");
				last = fileComponent.getSelectedFile().toString().length();	
				txtField[i].setText(fileComponent.getSelectedFile().toString().substring(find + 1, last - 6) + "...");
				JOptionPane.showMessageDialog(null, "파일 저장이 완료되었습니다.", "파일 저장", JOptionPane.WARNING_MESSAGE);
			}
			
			if (e.getSource() == remove[i]) {
				dao1.deleteFilePosition(dao1.checkUserId(userID), i+1);
				JOptionPane.showMessageDialog(null, "파일 삭제가 완료되었습니다.", "파일 삭제", JOptionPane.WARNING_MESSAGE);
				txtField[i].setText("");
			}

		}		
		
		if (e.getSource() == download[0]) {
			if (txtField[0] != null || txtField[0].equals("")) {
				ArrayList<MemberVo2> doclist = dao.doclist(userID);
				MemberVo2 datalist = (MemberVo2) doclist.get(0);
				String filename = datalist.getFilename1();
				find = filename.lastIndexOf("\\");
				last = filename.length();
				String orgFilePath = filename;
				String outFilePath = "C:\\Download\\" + filename.substring(find + 1, last);
				fileCopy(orgFilePath, outFilePath);
				JOptionPane.showMessageDialog(null, "파일 다운을 완료했습니다.(경로: C:\\Download)", "다운 완료", JOptionPane.WARNING_MESSAGE);					
			}else {
				JOptionPane.showMessageDialog(null, "제출된 파일이 없습니다.", "다운 오류", JOptionPane.WARNING_MESSAGE);				
			}
		}
		
		if (e.getSource() == download[1]) {
			if (txtField[1] != null || txtField[1].equals("")) {
				ArrayList<MemberVo2> doclist = dao.doclist(userID);
				MemberVo2 datalist = (MemberVo2) doclist.get(0);
				String filename = datalist.getFilename2();
				find = filename.lastIndexOf("\\");
				last = filename.length();
				String orgFilePath = filename;
				String outFilePath = "C:\\Download\\" + filename.substring(find + 1, last);
				fileCopy(orgFilePath, outFilePath);
				JOptionPane.showMessageDialog(null, "파일 다운을 완료했습니다.(경로: C:\\Download)", "다운 완료", JOptionPane.WARNING_MESSAGE);					
			}else {
				JOptionPane.showMessageDialog(null, "제출된 파일이 없습니다.", "다운 오류", JOptionPane.WARNING_MESSAGE);				
			}
		}
		
		if (e.getSource() == download[2]) {
			if (txtField[2] != null || txtField[2].equals("")) {
				ArrayList<MemberVo2> doclist = dao.doclist(userID);
				MemberVo2 datalist = (MemberVo2) doclist.get(0);
				String filename = datalist.getFilename3();
				find = filename.lastIndexOf("\\");
				last = filename.length();
				String orgFilePath = filename;
				String outFilePath = "C:\\Download\\" + filename.substring(find + 1, last);
				fileCopy(orgFilePath, outFilePath);
				JOptionPane.showMessageDialog(null, "파일 다운을 완료했습니다.(경로: C:\\Download)", "다운 완료", JOptionPane.WARNING_MESSAGE);					
			}else {
				JOptionPane.showMessageDialog(null, "제출된 파일이 없습니다.", "다운 오류", JOptionPane.WARNING_MESSAGE);				
			}
		}
		
		if (e.getSource() == download[3]) {
			if (txtField[3] != null || txtField[3].equals("")) {
				ArrayList<MemberVo2> doclist = dao.doclist(userID);
				MemberVo2 datalist = (MemberVo2) doclist.get(0);
				String filename = datalist.getFilename4();
				find = filename.lastIndexOf("\\");
				last = filename.length();
				String orgFilePath = filename;
				String outFilePath = "C:\\Download\\" + filename.substring(find + 1, last);
				fileCopy(orgFilePath, outFilePath);
				JOptionPane.showMessageDialog(null, "파일 다운을 완료했습니다.(경로: C:\\Download)", "다운 완료", JOptionPane.WARNING_MESSAGE);					
			}else {
				JOptionPane.showMessageDialog(null, "제출된 파일이 없습니다.", "다운 오류", JOptionPane.WARNING_MESSAGE);				
			}
		}
		
		if (e.getSource() == download[4]) {
			if (txtField[4] != null || txtField[4].equals("")) {
				ArrayList<MemberVo2> doclist = dao.doclist(userID);
				MemberVo2 datalist = (MemberVo2) doclist.get(0);
				String filename = datalist.getFilename5();
				find = filename.lastIndexOf("\\");
				last = filename.length();
				String orgFilePath = filename;
				String outFilePath = "C:\\Download\\" + filename.substring(find + 1, last);
				fileCopy(orgFilePath, outFilePath);
				JOptionPane.showMessageDialog(null, "파일 다운을 완료했습니다.(경로: C:\\Download)", "다운 완료", JOptionPane.WARNING_MESSAGE);					
			}else {
				JOptionPane.showMessageDialog(null, "제출된 파일이 없습니다.", "다운 오류", JOptionPane.WARNING_MESSAGE);				
			}
		}
		
		if (e.getSource() == download[5]) {
			if (txtField[5] != null || txtField[5].equals("")) {
				ArrayList<MemberVo2> doclist = dao.doclist(userID);
				MemberVo2 datalist = (MemberVo2) doclist.get(0);
				String filename = datalist.getFilename6();
				find = filename.lastIndexOf("\\");
				last = filename.length();
				String orgFilePath = filename;
				String outFilePath = "C:\\Download\\" + filename.substring(find + 1, last);
				fileCopy(orgFilePath, outFilePath);
				JOptionPane.showMessageDialog(null, "파일 다운을 완료했습니다.(경로: C:\\Download)", "다운 완료", JOptionPane.WARNING_MESSAGE);					
			}else {
				JOptionPane.showMessageDialog(null, "제출된 파일이 없습니다.", "다운 오류", JOptionPane.WARNING_MESSAGE);				
			}
		}
		
		if (e.getSource() == download[6]) {
			if (txtField[6] != null || txtField[6].equals("")) {
				ArrayList<MemberVo2> doclist = dao.doclist(userID);
				MemberVo2 datalist = (MemberVo2) doclist.get(0);
				String filename = datalist.getFilename7();
				find = filename.lastIndexOf("\\");
				last = filename.length();
				String orgFilePath = filename;
				String outFilePath = "C:\\Download\\" + filename.substring(find + 1, last);
				fileCopy(orgFilePath, outFilePath);
				JOptionPane.showMessageDialog(null, "파일 다운을 완료했습니다.(경로: C:\\Download)", "다운 완료", JOptionPane.WARNING_MESSAGE);					
			}else {
				JOptionPane.showMessageDialog(null, "제출된 파일이 없습니다.", "다운 오류", JOptionPane.WARNING_MESSAGE);				
			}
		}
		
		if (e.getSource() == download[7]) {
			if (txtField[7] != null || txtField[7].equals("")) {
				ArrayList<MemberVo2> doclist = dao.doclist(userID);
				MemberVo2 datalist = (MemberVo2) doclist.get(0);
				String filename = datalist.getFilename8();
				find = filename.lastIndexOf("\\");
				last = filename.length();
				String orgFilePath = filename;
				String outFilePath = "C:\\Download\\" + filename.substring(find + 1, last);
				fileCopy(orgFilePath, outFilePath);
				JOptionPane.showMessageDialog(null, "파일 다운을 완료했습니다.(경로: C:\\Download)", "다운 완료", JOptionPane.WARNING_MESSAGE);					
			}else {
				JOptionPane.showMessageDialog(null, "제출된 파일이 없습니다.", "다운 오류", JOptionPane.WARNING_MESSAGE);				
			}
		}
		

	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (e.getComponent() == sd) {
			sd.setVisible(false);
		} else {
			System.exit(0);
		}
	}
	
	  private static boolean fileCopy(String inFilePath, String outFilePath) {
	      try {
	         FileInputStream infile = new FileInputStream(inFilePath);
	         FileOutputStream outfile = new FileOutputStream(outFilePath);

	         byte[] b = new byte[1024];
	         int len;
	         while ((len = infile.read(b, 0, 1024)) > 0) {
	            outfile.write(b, 0, len);
	         }
	         infile.close();
	         outfile.close();
	      } catch (Exception e) {
	         e.printStackTrace();

	         return false;
	      }

	      return true;
	   }
}
