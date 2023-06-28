package semumanger;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MngWorking extends WindowAdapter implements ActionListener {
	private String mngId, selectId;
	private JFrame mw;
	private JTextField title, mngNum, mngName;
	private JPanel panel;
	private ManagerDAO dao;
	private JLabel numtitle, nametitle, ment;

	public MngWorking(String mngId, String selectId) {
		dao = new ManagerDAO();
		this.mngId = mngId;

		mw = new JFrame("담당자 조회화면");
		mw.setSize(360, 200);
		mw.setLayout(null);
		mw.getContentPane().setBackground(Color.white);

		title = new JTextField("    담당자조회");
		title.setBounds(20, 10, 90, 30);
		title.setBackground(Color.black);
		title.setForeground(Color.white);
		title.setEditable(false);

		panel = new JPanel();
		panel.setBounds(20, 40, 300, 100);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);

		numtitle = new JLabel("사번");
		numtitle.setBounds(20, 20, 50, 30);
		nametitle = new JLabel("이름");
		nametitle.setBounds(160, 20, 50, 30);
		ment = new JLabel("담당자 변경은 관리자에게 문의");
		ment.setBounds(60, 60, 230, 30);
		ment.setForeground(Color.red);

		ArrayList<ManagerVo> mnglist2 = dao.mnglist2(selectId);
		ManagerVo data = (ManagerVo) mnglist2.get(0);
		String number = data.getNumber();
		String name = data.getName();

		mngNum = new JTextField();
		mngNum.setBounds(50, 20, 90, 30);
		mngNum.setEditable(false);
		mngNum.setText(number);

		mngName = new JTextField();
		mngName.setBounds(190, 20, 90, 30);
		mngName.setEditable(false);
		mngName.setText(name);

		mw.add(title);
		mw.add(panel);
		panel.add(numtitle);
		panel.add(nametitle);
		panel.add(mngNum);
		panel.add(mngName);
		panel.add(ment);
		mw.setLocationRelativeTo(null);
		mw.addWindowListener(this);
		mw.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

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
