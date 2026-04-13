package Railway;

public class RegisterAccount {
	// Fields
	private String email;
	private String password;
	private String pip;
	
	//Getters
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPip() {
		return pip;
	}
	
	//Setters
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Constructor
	public RegisterAccount () {}
	
	public RegisterAccount(String email, String password) {
        this(email, password, null);
    }
	
	public RegisterAccount(String email, String password, String pip) {
		this.email = email;
		this.password = password;
		this.pip = pip;
	}
	
	public RegisterAccount(RegisterAccount account) {
		this.email = account.email;
		this.password = account.password;
		this.pip = account.pip;
	}
}
