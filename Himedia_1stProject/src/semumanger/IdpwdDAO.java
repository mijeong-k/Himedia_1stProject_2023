package semumanger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class IdpwdDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##firstproj";
	String password = "firstproj";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<IdpwdVo> list(String name, String phone) {
		ArrayList<IdpwdVo> list = new ArrayList<IdpwdVo>();
		try {
			connDB();
			String query = "SELECT * FROM SUSER where USER_NAME=TRIM('" + name + "') and USER_CON = "+"'"+phone+"'";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());
			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String username = rs.getString("USER_NAME");
					String email = rs.getString("USER_EMAIL");
					String password = rs.getString("USER_PWD");
					String userphone = rs.getString("USER_CON");
					IdpwdVo data = new IdpwdVo(username, email, password, userphone);
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<IdpwdVo> list2(String name, String email) {
		ArrayList<IdpwdVo> list2 = new ArrayList<IdpwdVo>();
		try {
			connDB();
			String query = "SELECT * FROM SUSER where USER_NAME=TRIM('" + name + "') and USER_EMAIL = "+"'"+email+"'";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());
			if (rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				System.out.println(rs.getRow() + " rows selected.....");
				rs.previous();
				while (rs.next()) {
					String username = rs.getString("USER_NAME");
					String useremail = rs.getString("USER_EMAIL");
					String password = rs.getString("USER_PWD");
					String userphone = rs.getString("USER_CON");
					IdpwdVo datas = new IdpwdVo(username, useremail, password, userphone);
					list2.add(datas);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list2;
	}
	
	public void updatePwd(String email, String repwd) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();

			String sql = "UPDATE SUSER SET USER_PWD = " + "'" + repwd + "' WHERE USER_EMAIL = " + "'" + email + "'";
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
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			// stmt = con.createStatement();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
