package semumanger;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Home extends WindowAdapter implements ActionListener {
	private JFrame home;
	private JButton request, search;
	private JLabel icon1, icon2;
	private Icon iconImg1, iconImg2;
	private String userID;

	public Home(String userID) {
		this.userID = userID;

		home = new JFrame("세무매니저-홈");
		home.setSize(400, 400);
		home.setLayout(null);

		request = new JButton("부가가치세 신고의뢰");
		search = new JButton("신고의뢰내역 조회");
		request.setBounds(130, 100, 150, 30);
		search.setBounds(130, 150, 150, 30);

		iconImg1 = new ImageIcon("C:\\Users\\Class01\\Desktop\\request.png");
		iconImg2 = new ImageIcon("C:\\Users\\Class01\\Desktop\\search.png");
		icon1 = new JLabel(iconImg1);
		icon2 = new JLabel(iconImg2);
		icon1.setBounds(70, 90, 50, 50);
		icon2.setBounds(70, 140, 50, 50);

		home.add(request);
		home.add(search);
		home.add(icon1);
		home.add(icon2);
		request.addActionListener(this);
		search.addActionListener(this);
		home.addWindowListener(this);
		home.getContentPane().setBackground(Color.white);
		home.setLocationRelativeTo(null);
		home.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == request) {
			Request rq = new Request(userID);
		}

		if(e.getSource() == search) {
			Search sc = new Search(userID);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
