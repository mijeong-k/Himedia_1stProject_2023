package semumanger;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class MngHome extends WindowAdapter implements ActionListener {
	private ManagerDAO dao;
	private String mngId, userId, selectId;
	private JFrame mngHome;
	private JPanel panel;
	private JTextField title, field1, field2, field3, field4;
	private JTextField[] f2Txt, f3Txt;
	private JButton[] f1Bt, f4Bt;
	private JButton refresh;
	private int height;

	public MngHome(String mngId) {
		dao = new ManagerDAO();
		this.mngId = mngId;

		mngHome = new JFrame("신고의뢰내역조회-주요정보");
		mngHome.setSize(410, 540);
		mngHome.setLayout(null);
//		mngHome.getContentPane().setBackground(Color.white);

		title = new JTextField("  의뢰내역조회");
		title.setBounds(20, 10, 90, 30);
		title.setBackground(Color.black);
		title.setEnabled(false);
		
		refresh = new JButton("갱신");
		refresh.setBounds(310, 10, 60, 25);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(21, 40, 355, 430);
		panel.setBackground(Color.white);

		field1 = new JTextField(" 접수번호");
		field1.setBounds(10, 15, 60, 25);
		field1.setBackground(Color.lightGray);
		field1.setEditable(false);
		field1.setBorder(new LineBorder(Color.BLACK));

		field2 = new JTextField("                   기업명");
		field2.setBounds(69, 15, 160, 25);
		field2.setBackground(Color.lightGray);
		field2.setEditable(false);
		field2.setBorder(new LineBorder(Color.BLACK));

		field3 = new JTextField("  대표자명");
		field3.setBounds(228, 15, 60, 25);
		field3.setBackground(Color.lightGray);
		field3.setEditable(false);
		field3.setBorder(new LineBorder(Color.BLACK));

		field4 = new JTextField("  담당자");
		field4.setBounds(287, 15, 60, 25);
		field4.setBackground(Color.lightGray);
		field4.setEditable(false);
		field4.setBorder(new LineBorder(Color.BLACK));

		f1Bt = new JButton[15];
		height = 40;
		int num = 1;	
//		ArrayList<ManagerVo> reqlist = dao.reqlist(num);
		
//		System.out.println(userid);

		for (int i = 0; i < f1Bt.length; i++) {
			f1Bt[i] = new JButton();
			f1Bt[i].setBounds(10, height, 60, 25);
			height += 25;
			panel.add(f1Bt[i]);
			f1Bt[i].setBackground(Color.white);
			f1Bt[i].setBorder(new LineBorder(Color.BLACK));
			f1Bt[i].addActionListener(this);
			ArrayList<ManagerVo> reqlist = dao.reqlist(i + 1);
			ManagerVo data = (ManagerVo) reqlist.get(0);
			String userid = data.getUserid();	
			f1Bt[i].setText(userid);
		}

		f2Txt = new JTextField[15];
		height = 40;
		for (int i = 0; i < f2Txt.length; i++) {
			f2Txt[i] = new JTextField();
			f2Txt[i].setBounds(69, height, 160, 25);
			height += 25;
			panel.add(f2Txt[i]);
			f2Txt[i].setBackground(Color.white);
			f2Txt[i].setBorder(new LineBorder(Color.BLACK));
			f2Txt[i].setEditable(false);
			ArrayList<ManagerVo> reqlist = dao.reqlist(i + 1);
			ManagerVo data = (ManagerVo) reqlist.get(0);
			String company = data.getCompany();	
			f2Txt[i].setText(" "+company);
		}
		
		f3Txt = new JTextField[15];
		height = 40;
		for (int i = 0; i < f3Txt.length; i++) {
			f3Txt[i] = new JTextField();
			f3Txt[i].setBounds(228, height, 60, 25);
			height += 25;
			panel.add(f3Txt[i]);
			f3Txt[i].setBackground(Color.white);
			f3Txt[i].setBorder(new LineBorder(Color.BLACK));
			f3Txt[i].setEditable(false);
			ArrayList<ManagerVo> reqlist = dao.reqlist(i + 1);
			ManagerVo data = (ManagerVo) reqlist.get(0);
			String ceo = data.getCeo();	
			f3Txt[i].setText(" "+ceo);
		}
		
		f4Bt = new JButton[15];
		height = 40;
		for (int i = 0; i < f4Bt.length; i++) {
			f4Bt[i] = new JButton();
			f4Bt[i].setBounds(287, height, 60, 25);
			height += 25;
			panel.add(f4Bt[i]);
			f4Bt[i].addActionListener(this);
			f4Bt[i].setBackground(Color.white);
			f4Bt[i].setBorder(new LineBorder(Color.BLACK));
			f4Bt[i].setForeground(Color.red);
			ArrayList<ManagerVo> reqlist = dao.reqlist(i + 1);
			ManagerVo data = (ManagerVo) reqlist.get(0);
			String mngid = data.getMngid();	
			if(mngid == null || mngid.equals("10010")) {
				f4Bt[i].setText("배치필요");
			}else {
				f4Bt[i].setText(mngid);
			}
						
		}
											
		mngHome.add(panel);
		mngHome.add(title);
		mngHome.add(refresh);
		panel.add(field1);
		panel.add(field2);
		panel.add(field3);
		panel.add(field4);
		mngHome.addWindowListener(this);
		refresh.addActionListener(this);
		mngHome.setLocationRelativeTo(null);
		

		mngHome.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<f4Bt.length; i++) {
			if(e.getSource() == f4Bt[i]) {
				if(f4Bt[i].getText().equals("배치필요")) {
					userId = f1Bt[i].getText();
					MngSelect msf = new MngSelect(mngId, userId);
				}else {					
					selectId = f4Bt[i].getText(); 
					MngWorking mwf = new MngWorking(mngId, selectId);
					System.out.println(selectId);
				}	
			}
		}			

		for (int i = 0; i < f1Bt.length; i++) {
			if (e.getSource() == f1Bt[i]) {
				MngSearch mss = new MngSearch(f1Bt[i].getText());
			}
		}
		
		if(e.getSource() == refresh) {
//			mngHome.setVisible(false);
//			mngHome.setVisible(true);
//			mngHome.invalidate();
//			mngHome.validate();
//			mngHome.repaint();
//			panel.repaint(); 
//			SwingUtilities.updateComponentTreeUI(mngHome);
//			mngHome.revalidate();
//			mngHome.repaint();
			mngHome.setVisible(false);
			MngHome mh = new MngHome(mngId);
			
			System.out.println(mngId);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
