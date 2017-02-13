package models;

import java.util.List;

public class User {

	private Integer	userId;
	private String userName;                
	private String initials;
	private String cpr;
	private String password;
	private List<String> roles;

	public User(Integer userId, String userName, String initials, String cpr, String password, List<String> roles){
		this.userId = userId;
		this.userName = userName;
		this.initials = initials;
		this.cpr = cpr;
		this.password = password;
		this.roles = roles;
	}

	public User(String userName, String initials, String cpr, String password, List<String> roles) {
		this(null, userName, initials, cpr, password, roles);
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public List<String> getRoles() {
		return roles;
	}
	
	public void addRole(String role){
		this.roles.add(role);
	}

	public boolean removeRole(String role){
		return this.roles.remove(role);
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
