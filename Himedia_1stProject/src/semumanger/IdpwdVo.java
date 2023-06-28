package semumanger;

public class IdpwdVo {
	private String username, email, password, userphone;

	public IdpwdVo(String username, String email, String password, String userphone) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.userphone = userphone;
	}	
	
	public String getUsername() {
		return username;
	}

	public String getUserphone() {
		return userphone;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
