package semumanger;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MngSelect extends WindowAdapter implements ActionListener{
	private String mngId, userId;
	private JFrame ms;
	private JTextField title, mngName;
	private String[] mngNum= {"10001", "10002", "10003", "10004", "10005", "10006", "10007","10008", "10009", "10010"};
	private String[] mngNames = {"김영희", "공유", "김고은" , "이엘" , "육성재" ,"유인나", "정해인" ,"김소현", "조우진" ,"관리자"};
	private JComboBox mngnumlist;
	private JButton select;
	private JPanel panel;
	private ManagerDAO dao;
	
	public MngSelect(String mngId, String userId) {
		this.mngId = mngId;
		this.userId = userId;
		
		dao = new ManagerDAO();
		
//		System.out.println(mngId+ ":" +userId);

		ms = new JFrame("담당자 선택");
		ms.setSize(255, 200);
		ms.setLayout(null);
		ms.getContentPane().setBackground(Color.white);

		// title
		title = new JTextField("    담당자설정");
		title.setBounds(20, 10, 90, 30);
		title.setBackground(Color.black);
		title.setForeground(Color.white);
		title.setEditable(false);
		
		panel = new JPanel();
		panel.setBounds(20, 40, 200, 100);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);		
		
		mngnumlist = new JComboBox(mngNum);
		mngnumlist.setBounds(20, 20, 70, 30);
		
		mngName = new JTextField();
		mngName.setBounds(100, 20, 80, 30);
		mngName.setEditable(false);
		
		select = new JButton("설정");
		select.setBounds(120, 60, 60, 30);
		
		ms.add(title);
		ms.add(panel);
		panel.add(mngnumlist);
		panel.add(mngName);
		panel.add(select);		
		ms.setLocationRelativeTo(null);
		ms.setVisible(true);
		mngnumlist.addActionListener(this);
		select.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<mngNum.length; i++) {
			if(mngnumlist.getSelectedItem().toString().equals(mngNum[i])) {
				mngName.setText(mngNames[i]);
			}
		}		
		if(e.getSource() == select) {
			String mngcode = mngnumlist.getSelectedItem().toString();
			dao.updateMng(mngcode, userId);
			JOptionPane.showMessageDialog(null, "담당자 설정완료!", "설정완료", JOptionPane.WARNING_MESSAGE);
			ms.setVisible(false);
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
