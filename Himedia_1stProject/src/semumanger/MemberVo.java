package semumanger;

public class MemberVo {
	private String id;
	private String password;
	private String email;

	public MemberVo() {
	}

	public MemberVo(String email) {
		this.email = email;
	}
	
	public MemberVo(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}

}
