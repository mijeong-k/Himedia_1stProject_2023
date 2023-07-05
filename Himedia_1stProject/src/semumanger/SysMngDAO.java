package semumanger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SysMngDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##firstproj";
	String password = "firstproj";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private Date today = new Date();
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd / hh:mm");
	
	public ArrayList<SysMngVO> userlist() {
		ArrayList<SysMngVO> userlist = new ArrayList<SysMngVO>();

		try {
			connDB();
			String query = "SELECT * FROM SUSER";
			rs = stmt.executeQuery(query);
			rs.last();
			if (rs.getRow() == 0) {
				System.out.println(dateformat.format(today)+" : 탐색결과 : 0 row selected.....");
			} else {
//				System.out.println(rs.getRow() + " rows selected.....");
				rs.beforeFirst();
				while (rs.next()) {
					String userid = rs.getString("USER_ID");
					String username = rs.getString("USER_NAME");
					String useremail = rs.getString("USER_EMAIL");
					String userpwd = rs.getString("USER_PWD");
					String userconn = rs.getString("USER_CON");
					SysMngVO data = new SysMngVO(userid, username, useremail, userpwd, userconn);
					userlist.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userlist;
	}
	
	public ArrayList<SysMngVO> mnglist() {
		ArrayList<SysMngVO> mnglist = new ArrayList<SysMngVO>();

		try {
			connDB();
			String query = "SELECT m.manager_id,m.manager_name,m.manager_email, m.MANAGER_PWD, u.manager_id,"
					+ " CASE WHEN u.manager_id IS NULL THEN 'N' WHEN u.manager_id"
					+ " IS NOT NULL THEN 'Y' END ISMATCH "
					+ " FROM USERREQUEST u, member m"
					+ " WHERE m.manager_id = u.manager_id(+)";
			rs = stmt.executeQuery(query);
			rs.last();
			if (rs.getRow() == 0) {
				System.out.println(dateformat.format(today)+" : 탐색결과 : 0 row selected.....");
			} else {
//				System.out.println(rs.getRow() + " rows selected.....");
				rs.beforeFirst();
				while (rs.next()) {
					String mmngid = rs.getString("manager_id");
					String mngname = rs.getString("manager_name");
					String mngemail = rs.getString("manager_email");
					String mngpwd = rs.getString("MANAGER_PWD");
					String umngid = rs.getString("manager_id");
					String match = rs.getString("ISMATCH");
					SysMngVO data = new SysMngVO(mmngid, mngname, mngemail, mngpwd, umngid, match);
					mnglist.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mnglist;
	}	
	
	public void insert(String name, String email) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();

			String sql = "INSERT INTO MEMBER values"
					+ "((SELECT NVL(MAX(MANAGER_ID), 0) +1 FROM MEMBER),"
					+ " '"+name+"', '"+email+"',(SELECT NVL(MAX(MANAGER_PWD), 0) +1 FROM MEMBER))";
//			System.out.println(sql);
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println(dateformat.format(today)+" : <인서트쿼리> Insert success.\n");
			} else {
				System.out.println(dateformat.format(today)+" : <인서트쿼리> Insert fail.\n");
			}

		} catch (ClassNotFoundException e) {
//			System.out.println(e);
		} catch (SQLException e) {
//			System.out.println(e);
		}
	}
	
	public void delete(String mngid) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			
			String sql = "DELETE FROM MEMBER WHERE MANAGER_ID = '"+ mngid + "'";			
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println(dateformat.format(today)+" : <삭제쿼리> Delete success.\n");
			} else {
				System.out.println(dateformat.format(today)+" : <삭제쿼리> Delete fail.\n");
			}
			
		}catch (ClassNotFoundException e) {
//			System.out.println(e);
		} catch (SQLException e) {
//			System.out.println(e);
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
