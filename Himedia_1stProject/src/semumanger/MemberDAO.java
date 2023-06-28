package semumanger;

import java.sql.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##firstproj";
	String password = "firstproj";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String strdept;
	private String checkname;

	public ArrayList<MemberVo> list(String id) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();

		try {
			connDB();
			String query = "SELECT * FROM SUSER";
			if (id != null) {
				query += " where USER_EMAIL=TRIM('" + id + "')";
			}
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
					String strid = rs.getString("USER_EMAIL");
					String password = rs.getString("USER_PWD");
					MemberVo data = new MemberVo(strid, password);
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public ArrayList<MemberVo> check(String email) {
		ArrayList<MemberVo> check = new ArrayList<MemberVo>();

		try {
			connDB();
			String query = "SELECT * FROM SUSER";
			if (email != null) {
				query += " where USER_EMAIL=TRIM('" + email + "')";
			}
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
					String strid = rs.getString("USER_ID");
					MemberVo data = new MemberVo(strid);
					check.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public String checkUserId(String email) {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.\n");
			Statement stmt = conn.createStatement();

			String sql3 = "SELECT USER_ID FROM SUSER";
			sql3 += " where USER_EMAIL=TRIM('" + email + "')";
			ResultSet rs2 = stmt.executeQuery(sql3);

			checkname = "";
			while (rs2.next()) {
				System.out.println(rs2.getString("USER_ID") + " ");
				checkname = rs2.getString("USER_ID");
			}

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return checkname;
	}
	
	
	public String findUserId() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.\n");
			Statement stmt = conn.createStatement();

			String sql3 = "SELECT max(USER_ID) FROM SUSER";
			ResultSet rs2 = stmt.executeQuery(sql3);

			String ret = "";
			while (rs2.next()) {
				System.out.println(rs2.getString("MAX(USER_ID)") + " ");
				ret = rs2.getString("MAX(USER_ID)");
			}
			int ideptret = Integer.parseInt(ret);
			ideptret += 1;
			strdept = Integer.toString(ideptret);

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return strdept;
	}

	public void insert(String userid, String name, String email, String pwd, String con) {

		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.\n");
			Statement stmt = conn.createStatement();

			String sql = "INSERT INTO SUSER VALUES ('" + userid + "','" + name + "','" + email + "','" + pwd + "','"
					+ con + "')";
			System.out.println(sql);
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Insert success.\n");
			} else {
				System.out.println("Insert fail.\n");
			}

			// String sql = "SELECT * FROM dept";
			String sql2 = "SELECT USER_NAME, USER_EMAIL, USER_PWD, USER_CON FROM SUSER";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				System.out.print(rs.getString("USER_NAME") + "\t");
				System.out.print(rs.getString("USER_EMAIL") + "\t");
				System.out.print(rs.getString("USER_PWD") + "\t");
				System.out.println(rs.getString("USER_CON") + " ");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public void request(String userid, String company, String ceo, String date, String staff, String manager, String ismatch, String fixguide) {

		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.\n");
			Statement stmt = conn.createStatement();

			String sql = "INSERT INTO USERREQUEST VALUES ('" + userid + "','" + company + "','" + ceo + "','" + date + "','" + staff + "','" + manager
					+ "','" + ismatch + "','"+ fixguide + "')";
			System.out.println(sql);
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Insert success.\n");
			} else {
				System.out.println("Insert fail.\n");
			}

			// String sql = "SELECT * FROM dept";
			String sql2 = "SELECT USER_ID, COMPANY, CEO, REGIDATE, STAFF, MANAGER_ID, ISMATCH, FIXGUIDE FROM USERREQUEST";
			System.out.println(sql2);
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				System.out.print(rs.getString("USER_ID") + "\t");
				System.out.print(rs.getString("COMPANY") + "\t");
				System.out.print(rs.getString("CEO") + "\t");
				System.out.print(rs.getString("REGIDATE") + "\t");
				System.out.print(rs.getString("STAFF") + "\t");
				System.out.print(rs.getString("MANAGER_ID") + "\t");
				System.out.print(rs.getString("FIXGUIDE") + "\t");
				System.out.println(rs.getString("ISMATCH") + " ");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	
	public void fileposition(String userid, String name1, String name2, String name3, String name4, String name5, String name6, String name7, String name8) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			
			String sql = "INSERT INTO DOCUMENTWHERE VALUES ('" + userid + "','" + name1 + "','"+ name2 + "','"+ name3 + "','"+ name4 + "','"+ name5 + "','"+ name6 + "','"+ name7 + "','" + name8 + "')";
			System.out.println(sql);
			boolean b = stmt.execute(sql);
			
		}catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void deleteFilePosition(String userid, int i) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			
			String sql = "UPDATE DOCUMENTWHERE SET FILENAME"+ i +" = '' WHERE USER_ID = "+ "'" + userid + "'";			
			System.out.println("--------------------------쿼리확인 : "+sql);
			boolean b = stmt.execute(sql);
			
		}catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}	
	
	public void updateFilePosition(String userid, int i, String position) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			
			String sql = "UPDATE DOCUMENTWHERE SET FILENAME"+ i +" = '"+position+"' WHERE USER_ID = "+ "'" + userid + "'";			
			System.out.println("--------------------------쿼리확인 : "+sql);
			boolean b = stmt.execute(sql);
			
		}catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}	
	
	public void updateinfo(String userid, String company, String ceo, String date, String staff) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			
			String sql = "UPDATE USERREQUEST SET COMPANY = '"+ company +"', ceo = '"+ceo+"', regidate = '"+date+"', staff = '"+staff+"' WHERE USER_ID = "+ "'" + userid + "'";			
			System.out.println("--------------------------쿼리확인 : "+sql);
			boolean b = stmt.execute(sql);
			
		}catch (ClassNotFoundException e) {
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
