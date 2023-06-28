package semumanger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##firstproj";
	String password = "firstproj";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<ManagerVo> mnglist(String loginid) {
		ArrayList<ManagerVo> mnglist = new ArrayList<ManagerVo>();

		try {
			connDB();
			String query = "SELECT * FROM MEMBER";
			if (loginid != null) {
				query += " where MANAGER_EMAIL=TRIM('" + loginid + "')";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String number = rs.getString("MANAGER_ID");
					String name = rs.getString("MANAGER_NAME");
					String email = rs.getString("MANAGER_EMAIL");
					String password = rs.getString("MANAGER_PWD");
					ManagerVo data = new ManagerVo(number, name, email, password);
					mnglist.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mnglist;
	}

	public ArrayList<ManagerVo> mnglist2(String mngcode) {
		ArrayList<ManagerVo> mnglist2 = new ArrayList<ManagerVo>();

		try {
			connDB();
			String query = "SELECT * FROM MEMBER";
			if (mngcode != null) {
				query += " where MANAGER_ID=TRIM('" + mngcode + "')";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String number = rs.getString("MANAGER_ID");
					String name = rs.getString("MANAGER_NAME");
					String email = rs.getString("MANAGER_EMAIL");
					String password = rs.getString("MANAGER_PWD");
					ManagerVo data = new ManagerVo(number, name, email, password);
					mnglist2.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mnglist2;
	}

	public ArrayList<ManagerVo> reqlist(int num) {
		ArrayList<ManagerVo> reqlist = new ArrayList<ManagerVo>();

		try {
			connDB();
			String query = "SELECT * FROM (SELECT ROWNUM AS rownumber, user_id, COMPANY, CEO, REGIDATE, STAFF, MANAGER_ID, ISMATCH, fixguide FROM USERREQUEST) E WHERE E.rownumber = "
					+ num;
//			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			if (rs.getRow() == 0) {
//				System.out.println("0 row selected.....");
				String userid = "";
				String company = "";
				String ceo = "";
				String date = "";
				String staff = "";
				String mngid = "";
				String match = "";
				String guide = "";
				ManagerVo data = new ManagerVo(userid, company, ceo, date, staff, mngid, match, guide);
				reqlist.add(data);
			} else {
//				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String userid = rs.getString("USER_ID");
					String company = rs.getString("COMPANY");
					String ceo = rs.getString("CEO");
					String date = rs.getString("REGIDATE");
					String staff = rs.getString("STAFF");
					String mngid = rs.getString("MANAGER_ID");
					String match = rs.getString("ISMATCH");
					String guide = rs.getString("fixguide");
					ManagerVo data = new ManagerVo(userid, company, ceo, date, staff, mngid, match, guide);
					reqlist.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reqlist;
	}

	public ArrayList<ManagerVo> requserlist(String usercode) {
		ArrayList<ManagerVo> requserlist = new ArrayList<ManagerVo>();

		try {
			connDB();
			String query = "SELECT * FROM USERREQUEST WHERE user_id=" + usercode;
//			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			if (rs.getRow() == 0) {
//				System.out.println("0 row selected.....");
				String userid = "";
				String company = "";
				String ceo = "";
				String date = "";
				String staff = "";
				String mngid = "";
				String match = "";
				String guide = "";
				ManagerVo data = new ManagerVo(userid, company, ceo, date, staff, mngid, match, guide);
				requserlist.add(data);
			} else {
//				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String userid = rs.getString("USER_ID");
					String company = rs.getString("COMPANY");
					String ceo = rs.getString("CEO");
					String date = rs.getString("REGIDATE");
					String staff = rs.getString("STAFF");
					String mngid = rs.getString("MANAGER_ID");
					String match = rs.getString("ISMATCH");
					String guide = rs.getString("fixguide");
					ManagerVo data = new ManagerVo(userid, company, ceo, date, staff, mngid, match, guide);
					requserlist.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requserlist;
	}

	public ArrayList<ManagerVo> doclist(String userid) {
		ArrayList<ManagerVo> doclist = new ArrayList<ManagerVo>();

		try {
			connDB();
			String query = "SELECT * FROM (SELECT us.USER_ID, d.filename1, d.FILENAME2, "
					+ "d.FILENAME3 , d.FILENAME4 , d.FILENAME5 , d.FILENAME6 , d.FILENAME7 , d.FILENAME8"
					+ " FROM SUSER s , USERREQUEST us, DOCUMENTWHERE d "
					+ "WHERE s.USER_ID = us.USER_ID AND us.USER_ID = d.USER_ID)";
			if (userid != null) {
				query += " where USER_ID=TRIM('" + userid + "')";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());
			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
				String filename1 = "";
				String filename2 = "";
				String filename3 = "";
				String filename4 = "";
				String filename5 = "";
				String filename6 = "";
				String filename7 = "";
				String filename8 = "";
				ManagerVo datalist = new ManagerVo(userid, filename1, filename2, filename3, filename4, filename5, filename6,
						filename7, filename8);
				doclist.add(datalist);
			} else {
				System.out.println(rs.getRow() + " rows selected.....");
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

					ManagerVo datalist = new ManagerVo(userid, filename1, filename2, filename3, filename4, filename5, filename6,
							filename7, filename8);
					doclist.add(datalist);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doclist;
	}

//	public ArrayList<MemberVo> check(String email) {
//		ArrayList<MemberVo> check = new ArrayList<MemberVo>();
//
//		try {
//			connDB();
//			String query = "SELECT * FROM SUSER";
//			if (email != null) {
//				query += " where USER_EMAIL=TRIM('" + email + "')";
//			}
//			System.out.println("SQL : " + query);
//			rs = stmt.executeQuery(query);
//			rs.last();
//			System.out.println("rs.getRow() : " + rs.getRow());
//			if (rs.getRow() == 0) {
//				System.out.println("0 row selected.....");
//			} else {
//				System.out.println(rs.getRow() + " rows selected.....");
//				rs.previous();
//				while (rs.next()) {
//					String strid = rs.getString("USER_ID");
//					MemberVo data = new MemberVo(strid);
//					check.add(data);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return check;
//	}

	public void updateMng(String mngNum, String userid) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();

			String sql = "UPDATE USERREQUEST SET manager_id = " + "'" + mngNum + "', ismatch = 'Y' WHERE USER_ID = "
					+ "'" + userid + "'";
			System.out.println("--------------------------쿼리확인 : " + sql);
			boolean b = stmt.execute(sql);

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void updateGuide(String guide, String userid) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();

			String sql = "UPDATE USERREQUEST SET fixguide = " + "'" + guide + "'" + " WHERE USER_ID = " + "'" + userid
					+ "'";
			System.out.println("--------------------------쿼리확인 : " + sql);
			boolean b = stmt.execute(sql);

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
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
