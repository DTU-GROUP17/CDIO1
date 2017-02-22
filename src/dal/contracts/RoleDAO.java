package dal.contracts;

import dal.exceptions.NotConnectedException;
import models.Role;

import java.util.List;

public interface RoleDAO {

	List<Role> getRoles() throws NotConnectedException;

}
