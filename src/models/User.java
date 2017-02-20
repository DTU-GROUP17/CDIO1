package models;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class User extends Model{

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

	private static final Pattern[] passwordRegex = new Pattern[4];
	{
		passwordRegex[0] = Pattern.compile(".*[A-Z].*");
		passwordRegex[1] = Pattern.compile(".*[a-z].*");
		passwordRegex[2] = Pattern.compile(".*\\d.*");
		passwordRegex[3] = Pattern.compile(".*[.-_+!?=].*");
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

	public void setPassword(String password) throws InvalidInputException {
		passwordVerify(password);

		this.password = password;
	}

	public boolean passwordVerify() throws InvalidInputException{
		return passwordVerify(this.getPassword());
	}

	public boolean passwordVerify(String password) throws InvalidInputException {

		if( Arrays.stream(passwordRegex).filter(regex -> regex.matcher(password).matches()).count() < 3)
			throw new InvalidInputException("Password does not contain 3 of the 4 categories.");

		if(password.length() < 6)
			throw new InvalidInputException("Password is not 6 characters long.");

		if(password.contains(this.getUserName()))
			throw new InvalidInputException("Username cannot be part of password.");

		if(password.contains(Integer.toString(this.getUserId())))
			throw new InvalidInputException("User id cannot be part of password.");

		return true;
	}

}
