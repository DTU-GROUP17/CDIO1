package models;

import factories.InvalidInputException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class User implements DTO {

	private Integer id;
	private String userName;                
	private String initials;
	private String cpr;
	private String password;
	private List<Role> roles;

	public User(Integer id, String userName, String initials, String cpr, String password, List<Role> roles){
		this.id = id;
		this.userName = userName;
		this.initials = initials;
		this.cpr = cpr;
		this.password = password;
		this.roles = roles;
	}

	public User(String userName, String initials, String cpr, String password, List<Role> roles) {
		this(null, userName, initials, cpr, password, roles);
	}

	private static final Pattern[] passwordRegex = new Pattern[4]; {
		passwordRegex[0] = Pattern.compile(".*[A-Z].*");
		passwordRegex[1] = Pattern.compile(".*[a-z].*");
		passwordRegex[2] = Pattern.compile(".*\\d.*");
		passwordRegex[3] = Pattern.compile(".*[.-_+!?=].*");
	}

	public boolean passwordVerify() throws InvalidInputException {

		if (Arrays.stream(this.passwordRegex).filter(regex -> regex.matcher(this.getPassword()).matches()).count() < 3)
			throw new InvalidInputException("Password does not contain 3 of the 4 categories.");

		if (this.getPassword().length() < 6)
			throw new InvalidInputException("Password is not 6 characters long.");

		if (this.getPassword().contains(this.getUserName()))
			throw new InvalidInputException("Username cannot be part of password.");

		if (this.getPassword().contains(Integer.toString(this.getId())))
			throw new InvalidInputException("User id cannot be part of password.");

		return true;

	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Role> getRoles() {
		return roles;
	}
	
	public void addRole(Role role){
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


}
