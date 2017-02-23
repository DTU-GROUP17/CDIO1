package models;

import factories.exceptions.InvalidCprException;
import factories.exceptions.InvalidInitialsException;
import factories.exceptions.InvalidPasswordException;
import factories.exceptions.InvalidUsernameException;

import javax.naming.InvalidNameException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class User implements DTO {

	private Integer id;
	private String name;
	private String initials;
	private String cpr;
	private String password;
	private List<Role> roles;

	public User(Integer id, String name, String initials, String cpr, String password, List<Role> roles){
		this.id = id;
		this.name = name;
		this.initials = initials;
		this.cpr = cpr;
		this.password = password;
		this.roles = roles;
	}

	public User(String name, String initials, String cpr, String password, List<Role> roles) {
		this(null, name, initials, cpr, password, roles);
	}

	private static final Pattern[] passwordRegex = new Pattern[4]; static {
		passwordRegex[0] = Pattern.compile(".*[A-Z].*");
		passwordRegex[1] = Pattern.compile(".*[a-z].*");
		passwordRegex[2] = Pattern.compile(".*\\d.*");
		passwordRegex[3] = Pattern.compile(".*[.-_+!?=].*");
	}

	public void passwordVerify() throws InvalidPasswordException {

		if (Arrays.stream(passwordRegex).filter(regex -> regex.matcher(this.getPassword()).matches()).count() < 3)
			throw new InvalidPasswordException("Password does not contain 3 of the 4 categories.");

		if (this.getPassword().length() < 6)
			throw new InvalidPasswordException("Password is not 6 characters long.");

		if (this.getPassword().contains(this.getName()))
			throw new InvalidPasswordException("Username cannot be part of password.");

		if (this.getPassword().contains(Integer.toString(this.getId())))
			throw new InvalidPasswordException("User id cannot be part of password.");
	}

	public void cprVerify() throws InvalidCprException {
		if (this.getCpr().length()!=10) {
			throw new InvalidCprException("Cpr length not 10 characters");
		}

		if (!Pattern.matches("\\d*", this.getCpr())) {
			throw new InvalidCprException("Cpr can only contain numerals");
		}
	}

	public void initialVerify() throws InvalidInitialsException {
		if (this.getInitials().length()<2 || this.getInitials().length()>4) {
			throw new InvalidInitialsException("Initials not between 2 and 4 characters");
		}

		if (!Pattern.matches("[a-zA-Z]+", this.getInitials())) {
			throw new InvalidInitialsException("Initials can only contain letters");
		}
	}

	public void nameVerify() throws InvalidUsernameException {
		if (this.getInitials().length()<2 || this.getInitials().length()>20) {
			throw new InvalidUsernameException("Initials not between 2 and 20 characters");
		}
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", initials='" + initials + '\'' +
				", cpr='" + cpr + '\'' +
				", password='" + password + '\'' +
				", roles=" + roles +
				'}';
	}

}
