package semumanger;

public class MemberVo2 {
	private String name;
	private String id;
	private String password;
	private String rname, company, ceo, date, staff, manager, guide, mngName, mngEmail;
	private String filename1, filename2, filename3, filename4, filename5, filename6, filename7, filename8;
	
	public MemberVo2() {
	}
	
	public MemberVo2(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
	}
	
	public MemberVo2(String rname, String company, String ceo, String date, String staff, String manager, String guide, String mngName, String mngEmail) {
		this.rname = rname;
		this.company = company;
		this.ceo = ceo;
		this.date = date;
		this.staff = staff;
		this.manager = manager;
		this.guide = guide;
		this.mngName = mngName;
		this.mngEmail = mngEmail;
	}
	
	public MemberVo2(String filename1, String filename2, String filename3, String filename4, String filename5, String filename6, String filename7, String filename8) {
		this.filename1 = filename1;
		this.filename2 = filename2;
		this.filename3 = filename3;
		this.filename4 = filename4;
		this.filename5 = filename5;
		this.filename6 = filename6;
		this.filename7 = filename7;
		this.filename8 = filename8;
	}
	
	public String getMngName() {
		return mngName;
	}

	public String getMngEmail() {
		return mngEmail;
	}

	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getRname() {
		return rname;
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

	public String getManager() {
		return manager;
	}

	public String getGuide() {
		return guide;
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
	
}
