package factories;

import models.DTO;
import models.Role;
import models.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class UserFactory extends Factory {

	private Integer id;
	private String name;
	private String initials;
	private String cpr;
	private String password;
	private List<Role> roles;

	@SuppressWarnings("unchecked")
	@Override
	<T extends DTO> T instantiate() throws InvalidInputException {
		if (this.id==null){
			this.id = faker.number().randomDigit();
		}
		if (this.name==null) {
			this.name = faker.name().firstName();
		}
		if (this.initials==null) {
			this.initials = faker.name().prefix();
		}
		if (this.cpr==null) {
			this.cpr = faker.lorem().characters(10);
		}
		if (this.password==null) {
			this.password = faker.pokemon().name();
		}
		if (this.roles==null) {
			this.roles = new LinkedList<>();
		}
		User user = new User(id, name, initials, cpr, password, roles);
		user.passwordVerify();
		return (T) user;
	}

	public UserFactory setId(Integer id) {
		this.id = id;
		return this;
	}

	public UserFactory setName(String name) {
		this.name = name;
		return this;
	}

	public UserFactory setInitials(String initials) {
		this.initials = initials;
		return this;
	}

	public UserFactory setCpr(String cpr) {
		this.cpr = cpr;
		return this;
	}

	public UserFactory setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserFactory setRoles(List<Role> roles) {
		this.roles = roles;
		return this;
	}
}
