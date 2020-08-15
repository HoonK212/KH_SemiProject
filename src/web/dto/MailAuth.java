package web.dto;

public class MailAuth {
	
	private String email;
	private String random;

	
	@Override
	public String toString() {
		return "MailAuth [email=" + email + ", random=" + random + "]";
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRandom() {
		return random;
	}


	public void setRandom(String random) {
		this.random = random;
	}
	
}
