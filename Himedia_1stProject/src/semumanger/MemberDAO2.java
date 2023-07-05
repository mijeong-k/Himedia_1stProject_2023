package semumanger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MemberDAO2 {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##firstproj";
	String password = "firstproj";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<MemberVo2> requestlist(String email) {
		ArrayList<MemberVo2> requestlist = new ArrayList<MemberVo2>();

		try {
			connDB();
			String query = "SELECT * FROM (SELECT us.USER_ID, s.USER_NAME, s.USER_EMAIL, us.COMPANY, "
					+ "us.CEO, us.REGIDATE, us.STAFF, us.MANAGER_ID, us.ISMATCH, us.FIXGUIDE, "
					+ "m.MANAGER_NAME , m.MANAGER_EMAIL FROM SUSER s, USERREQUEST us, MEMBER m "
					+ "WHERE s.USER_ID = us.USER_ID	AND us.MANAGER_ID = m.MANAGER_ID)";
			if (email != null) {
				query += " where USER_EMAIL=TRIM('" + email + "')";
			}
//			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
//			System.out.println("rs.getRow() : " + rs.getRow());
			if (rs.getRow() == 0) {
				System.out.println("탐색결과 : 0 row selected.....");
				String rname = "의뢰내역 없음";
				String company = "의뢰내역 없음";
				String ceo = "의뢰내역 없음";
				String date = "의뢰내역 없음";
				String staff = "의뢰내역 없음";
				String manager = "의뢰내역 없음";
				String guide = "의뢰내역 없음";
				String mngName = "의뢰내역 없음";
				String mngEmail = "의뢰내역 없음";
				MemberVo2 datalist = new MemberVo2(rname, company, ceo, date, staff, manager, guide, mngName, mngEmail);
				requestlist.add(datalist);
			} else {
//				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String rname = rs.getString("USER_NAME");
					String company = rs.getString("COMPANY");
					String ceo = rs.getString("CEO");
					String date = rs.getString("REGIDATE");
					String staff = rs.getString("STAFF");
					String manager = rs.getString("MANAGER_ID");
					String guide = rs.getString("FIXGUIDE");
					String mngName = rs.getString("MANAGER_NAME");
					String mngEmail = rs.getString("MANAGER_EMAIL");

					if (guide != null) {
						MemberVo2 datalist = new MemberVo2(rname, company, ceo, date, staff, manager, guide, mngName,
								mngEmail);
						requestlist.add(datalist);
					}else {
						MemberVo2 datalist = new MemberVo2(rname, company, ceo, date, staff, manager, "의뢰내역 확인중", mngName,
								mngEmail);
						requestlist.add(datalist);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestlist;
	}
	
	public ArrayList<MemberVo2> requestlist2(String usercode) {
		ArrayList<MemberVo2> requestlist2 = new ArrayList<MemberVo2>();

		try {
			connDB();
			String query = "SELECT * FROM (SELECT us.USER_ID, s.USER_NAME, s.USER_EMAIL, us.COMPANY, "
					+ "us.CEO, us.REGIDATE, us.STAFF, us.MANAGER_ID, us.ISMATCH, us.FIXGUIDE, "
					+ "m.MANAGER_NAME , m.MANAGER_EMAIL FROM SUSER s, USERREQUEST us, MEMBER m "
					+ "WHERE s.USER_ID = us.USER_ID	AND us.MANAGER_ID = m.MANAGER_ID)";
			if (usercode != null) {
				query += " where USER_ID=TRIM('" + usercode + "')";
			}
//			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
//			System.out.println("rs.getRow() : " + rs.getRow());
			if (rs.getRow() == 0) {
				System.out.println("탐색결과 : 0 row selected.....");
				String rname = "의뢰내역 없음";
				String company = "의뢰내역 없음";
				String ceo = "의뢰내역 없음";
				String date = "의뢰내역 없음";
				String staff = "의뢰내역 없음";
				String manager = "의뢰내역 없음";
				String guide = "의뢰내역 없음";
				String mngName = "의뢰내역 없음";
				String mngEmail = "의뢰내역 없음";
				MemberVo2 datalist = new MemberVo2(rname, company, ceo, date, staff, manager, guide, mngName, mngEmail);
				requestlist2.add(datalist);
			} else {
//				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String rname = rs.getString("USER_NAME");
					String company = rs.getString("COMPANY");
					String ceo = rs.getString("CEO");
					String date = rs.getString("REGIDATE");
					String staff = rs.getString("STAFF");
					String manager = rs.getString("MANAGER_ID");
					String guide = rs.getString("FIXGUIDE");
					String mngName = rs.getString("MANAGER_NAME");
					String mngEmail = rs.getString("MANAGER_EMAIL");

					if (guide != null) {
						MemberVo2 datalist = new MemberVo2(rname, company, ceo, date, staff, manager, guide, mngName,
								mngEmail);
						requestlist2.add(datalist);
					}else {
						MemberVo2 datalist = new MemberVo2(rname, company, ceo, date, staff, manager, "담당자 배치중", mngName,
								mngEmail);
						requestlist2.add(datalist);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestlist2;
	}
	
	public ArrayList<MemberVo2> doclist(String userEmail) {
		ArrayList<MemberVo2> doclist = new ArrayList<MemberVo2>();

		try {
			connDB();
			String query = "SELECT * FROM (SELECT us.USER_ID, s.USER_EMAIL, d.filename1, d.FILENAME2, "
					+ "d.FILENAME3 , d.FILENAME4 , d.FILENAME5 , d.FILENAME6 , d.FILENAME7 , d.FILENAME8"
					+ " FROM SUSER s , USERREQUEST us, DOCUMENTWHERE d "
					+ "WHERE s.USER_ID = us.USER_ID AND us.USER_ID = d.USER_ID)";
			if (userEmail != null) {
				query += " where USER_EMAIL=TRIM('" + userEmail + "')";
			}
//			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
//			System.out.println("rs.getRow() : " + rs.getRow());
			if (rs.getRow() == 0) {
				System.out.println("탐색결과 : 0 row selected.....");
				String filename1 = "";
				String filename2 = "";
				String filename3 = "";
				String filename4 = "";
				String filename5 = "";
				String filename6 = "";
				String filename7 = "";
				String filename8 = "";
				MemberVo2 datalist = new MemberVo2(filename1, filename2, filename3, filename4, filename5, filename6, filename7, filename8);
				doclist.add(datalist);
			} else {
//				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String filename1 = rs.getString("filename1");
					String filename2 = rs.getString("filename2");
					String filename3 = rs.getString("filename3");
					String filename4 = rs.getString("filename4");
					String filename5 = rs.getString("filename5");
					String filename6 = rs.getString("filename6");
					String filename7 = rs.getString("filename7");
					String filename8 = rs.getString("filename8");

					MemberVo2 datalist = new MemberVo2(filename1, filename2, filename3, filename4, filename5, filename6, filename7, filename8);
					doclist.add(datalist);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doclist;
	}

	public void connDB() {
		try {
			Class.forName(driver);
//			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
//			System.out.println("oracle connection success.");
			// stmt = con.createStatement();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
