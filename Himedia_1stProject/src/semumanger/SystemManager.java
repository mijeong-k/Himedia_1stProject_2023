package semumanger;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SystemManager extends WindowAdapter implements ActionListener {
	private JFrame smf, home, userlistf, mnglistf, mngedit;
	private JDialog mngadd, mngdelete;
	private Button login, ulBt, mlBt, maBt, add, delete;
	private JTable ul, ml;
	private JTextField title, code, pwd, nameTxt, emailTxt, mngcodeTxt;
	private SysMngDAO dao;
	private JLabel addment, deletement;

	public SystemManager() {
		dao = new SysMngDAO();

//		로그인 메뉴
		smf = new JFrame("관리자 페이지");
		smf.setSize(330, 400);
		smf.setLayout(null);

		title = new JTextField("   <관리자 전용 로그인> 코드&비밀번호 입력");
		code = new HintTextField("관리자코드");
		pwd = new HintTextField("비밀번호");
		title.setBackground(Color.black);
		title.setEnabled(false);
		title.setBounds(32, 90, 250, 30);
		code.setBounds(32, 120, 250, 30);
		pwd.setBounds(32, 150, 250, 30);

		login = new Button("login");
		login.setBounds(32, 200, 250, 30);

//		home 메뉴		
		home = new JFrame("관리자 홈");
		home.setSize(330, 400);
		home.setLayout(null);

		ulBt = new Button("USER list");
		mlBt = new Button("MANAGER list");

		ulBt.setBounds(32, 90, 250, 30);
		mlBt.setBounds(32, 130, 250, 30);

//		userlist 메뉴		
		userlistf = new JFrame("사용자목록");
		userlistf.setSize(500, 500);

		JPanel upanel = new JPanel();
		ul = new JTable();
		JScrollPane uscrollPane = new JScrollPane(ul);
		String uheader[] = { "유저코드", "성함", "이메일(ID)", "비밀번호", "연락처" };
		DefaultTableModel udtm = new DefaultTableModel(uheader, 0);

		ArrayList<SysMngVO> userlist = dao.userlist();

		for (SysMngVO vo : userlist) {
			Object[] urow = { vo.getUserid(), vo.getUsername(), vo.getUseremail(), vo.getUserpwd(), vo.getUserconn() };
			udtm.addRow(urow);
		}

		ul.setModel(udtm);
		userlistf.add(upanel);
		upanel.setBounds(0, 0, 490, 200);
		upanel.add(uscrollPane);
		uscrollPane.setBounds(0, 0, 490, 200);

//		mnglist 메뉴			
		mnglistf = new JFrame("담당자목록");
		mnglistf.setSize(500, 530);

		JPanel mpanel = new JPanel();
		ml = new JTable();
		JScrollPane mscrollPane = new JScrollPane(ml);
		String mheader[] = { "담당자사번", "성함", "이메일(ID)", "비밀번호", "배치여부" };
		DefaultTableModel mdtm = new DefaultTableModel(mheader, 0);

		ArrayList<SysMngVO> mnglist = dao.mnglist();

		for (SysMngVO vo : mnglist) {
			Object[] mrow = { vo.getMmngid(), vo.getMngname(), vo.getMmngid(), vo.getMngpwd(), vo.getMatch() };
			mdtm.addRow(mrow);
		}

		ml.setModel(mdtm);
		mnglistf.add(mpanel);
		mpanel.setBounds(0, 0, 400, 180);
		mpanel.add(mscrollPane);
		mscrollPane.setBounds(0, 0, 400, 180);

//		mngadd 메뉴			
		mngedit = new JFrame("담당자 권한설정");
		mngedit.setSize(330, 400);
		mngedit.setLayout(null);

		maBt = new Button("MANAGER edit");
		maBt.setBounds(200, 400, 125, 30);

		mngadd = new JDialog(mngedit, "담당자 추가", false);
		mngadd.setSize(330, 300);
		mngadd.setLayout(null);
		
		addment = new JLabel("추가하려는 담당자 성함과 이메일을 입력하세요.");
		addment.setBounds(32, 60, 250, 30);		
		nameTxt = new JTextField();
		nameTxt.setBounds(32, 90, 250, 30);
		emailTxt = new JTextField();
		emailTxt.setBounds(32, 120, 250, 30);
		add = new Button("MANAGER add");
		add.setBounds(32, 150, 250, 30);

		deletement = new JLabel("삭제하려는 담당자 사번을 입력하세요.");
		deletement.setBounds(32, 200, 250, 30);		
		mngcodeTxt = new JTextField();
		mngcodeTxt.setBounds(32, 230, 250, 30);	
		delete = new Button("MANAGER delete");
		delete.setBounds(32, 260, 250, 30);		

		smf.add(title);
		smf.add(code);
		smf.add(pwd);
		smf.add(login);

		home.add(ulBt);
		home.add(mlBt);
		mpanel.add(maBt);
		mngedit.add(addment);
		mngedit.add(add);
		mngedit.add(nameTxt);
		mngedit.add(emailTxt);
		mngedit.add(deletement);
		mngedit.add(delete);
		mngedit.add(mngcodeTxt);

		smf.addWindowListener(this);
		home.addWindowListener(this);
		userlistf.addWindowListener(this);
		mngedit.addWindowListener(this);
		login.addActionListener(this);
		ulBt.addActionListener(this);
		mlBt.addActionListener(this);
		maBt.addActionListener(this);
		add.addActionListener(this);
		delete.addActionListener(this);
		userlistf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mnglistf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		smf.getContentPane().setBackground(Color.black);
		home.getContentPane().setBackground(Color.black);
		userlistf.getContentPane().setBackground(Color.black);
		mnglistf.getContentPane().setBackground(Color.black);
		smf.setVisible(true);
		smf.setResizable(false);
		smf.setLocationRelativeTo(null);
		home.setResizable(false);
		home.setLocationRelativeTo(null);
		userlistf.setResizable(false);
		userlistf.setLocationRelativeTo(null);
		mnglistf.setResizable(false);
		mnglistf.setLocationRelativeTo(null);
		mngedit.setResizable(false);
		mngedit.setLocationRelativeTo(null);
		mngadd.setResizable(false);
		mngadd.setLocationRelativeTo(null);
	}

	public void windowClosing(WindowEvent e) {
		if (e.getComponent() == home) {
			home.dispose();
		} else if (e.getComponent() == userlistf) {
			userlistf.dispose();
		} else if (e.getComponent() == smf) {
			smf.dispose();
		} else if (e.getComponent() == mngedit) {
			mngedit.dispose();
		} else {
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login) {
			if(code.getText().equals("316497") && pwd.getText().equals("sysmng0000")) {
				home.setVisible(true);
				smf.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "코드와 비밀번호를 확인해주세요", "로그인오류", JOptionPane.OK_OPTION);
			}
		}
		
		if(e.getSource() == ulBt) {
			userlistf.setVisible(true);
		}
		
		if(e.getSource() == mlBt) {
			mnglistf.setVisible(true);
		}
		
		if(e.getSource() == maBt) {
			mngedit.setVisible(true);
		}

		if (e.getSource() == add) {
			if (nameTxt.getText().equals(null) || nameTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "이름 공백", JOptionPane.WARNING_MESSAGE);
				nameTxt.requestFocus(true);
				return;
			}
			if (!nameTxt.getText().matches("^[a-zA-Z]*$") && !nameTxt.getText().matches("^[가-힣]*$")) {
				JOptionPane.showMessageDialog(null, "이름은 영문 또는 한글로 입력하세요.", "이름 오류", JOptionPane.WARNING_MESSAGE);
				nameTxt.requestFocus(true);
				return;
			}
			if (emailTxt.getText().equals(null) || emailTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.", "이메일 공백", JOptionPane.WARNING_MESSAGE);
				emailTxt.requestFocus(true);
				return;
			}
			if (!emailTxt.getText()
					.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")) {
				JOptionPane.showMessageDialog(null, "이메일 형식을 지켜주세요.", "이메일형식 오류", JOptionPane.WARNING_MESSAGE);
				emailTxt.requestFocus(true);
				return;
			}

			dao.insert(nameTxt.getText(), emailTxt.getText());
			JOptionPane.showMessageDialog(null, "담당자 추가 성공", "추가성공", JOptionPane.OK_OPTION);
		}
		
		if (e.getSource() == delete) {
			if (!mngcodeTxt.getText().matches("^[0-9]*$")) {
				JOptionPane.showMessageDialog(null, "사번은 숫자로 입력하세요.", "사번 오류", JOptionPane.WARNING_MESSAGE);
				mngcodeTxt.requestFocus(true);
				return;
			}

			if (mngcodeTxt.getText().equals(null) || mngcodeTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "사번을 입력해주세요.", "사번 공백", JOptionPane.WARNING_MESSAGE);
				mngcodeTxt.requestFocus(true);
				return;
			}

			dao.delete(mngcodeTxt.getText());
			JOptionPane.showMessageDialog(null, "담당자 삭제 성공", "삭제성공", JOptionPane.OK_OPTION);
		}
	}
}

