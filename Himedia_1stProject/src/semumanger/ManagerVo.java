package semumanger;

public class ManagerVo extends MemberVo2 {
	private String number, name, email, password;
	private String userid, company, ceo, date, staff, mngid, match, guide;
	private String usercode, filename1, filename2, filename3, filename4, filename5, filename6, filename7, filename8;

	public ManagerVo(String number, String name, String email, String password) {
		this.number = number;		
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public ManagerVo(String userid, String company, String ceo, String date, String staff, String mngid, String match, String guide) {
		this.userid = userid;
		this.company = company;
		this.ceo = ceo;
		this.date = date;
		this.staff = staff;
		this.mngid = mngid;
		this.match = match;
		this.guide = guide;
	}
	
	public ManagerVo(String usercode, String filename1, String filename2, String filename3, String filename4, String filename5, String filename6, String filename7, String filename8) {
		this.usercode = usercode;
		this.filename1 = filename1;
		this.filename2 = filename2;
		this.filename3 = filename3;
		this.filename4 = filename4;
		this.filename5 = filename5;
		this.filename6 = filename6;
		this.filename7 = filename7;
		this.filename8 = filename8;
	}
	
	public String getFilename1() {
		return filename1;
	}

	public String getFilename2() {
		return filename2;
	}

	public String getFilename3() {
		return filename3;
	}

	public String getFilename4() {
		return filename4;
	}

	public String getFilename5() {
		return filename5;
	}

	public String getFilename6() {
		return filename6;
	}

	public String getFilename7() {
		return filename7;
	}

	public String getFilename8() {
		return filename8;
	}	
	
	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	

	public String getUserid() {
		return userid;
	}

	public String getCompany() {
		return company;
	}

	public String getCeo() {
		return ceo;
	}

	public String getDate() {
		return date;
	}

	public String getStaff() {
		return staff;
	}

	public String getMngid() {
		return mngid;
	}

	public String getMatch() {
		return match;
	}

	public String getGuide() {
		return guide;
	}

	
}
