package semumanger;

public class SysMngVO {
	private String userid, username, useremail, userpwd, userconn;
	private String mmngid, mngname, mngemail, mngpwd, umngid, match;

	public SysMngVO(String userid, String username, String useremail, String userpwd, String userconn) {
		this.userid = userid;
		this.username = username;
		this.useremail = useremail;
		this.userpwd = userpwd;
		this.userconn = userconn;
	}
	
	public SysMngVO(String mmngid, String mngname, String mngemail, String mngpwd, String umngid, String match) {
		this.mmngid = mmngid;
		this.mngname = mngname;
		this.mngemail = mngemail;
		this.mngpwd = mngpwd;
		this.umngid = umngid;
		this.match = match;
	}	
	
	public String getUserid() {
		return userid;
	}


	public String getUsername() {
		return username;
	}


	public String getUseremail() {
		return useremail;
	}


	public String getUserpwd() {
		return userpwd;
	}


	public String getUserconn() {
		return userconn;
	}

	public String getMmngid() {
		return mmngid;
	}

	public String getMngname() {
		return mngname;
	}

	public String getMngemail() {
		return mngemail;
	}

	public String getMngpwd() {
		return mngpwd;
	}

	public String getMatch() {
		return match;
	}

	public String getUmngid() {
		return umngid;
	}
	
	
}
