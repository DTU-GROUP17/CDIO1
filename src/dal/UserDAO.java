package dal;

import java.sql.SQLException;
import java.util.List;

import models.User;

public interface UserDAO {

	User findUser(int userId) throws NotFoundException, NotConnectedException, DALException;
	List<User> getUsers() throws NotConnectedException, DALException;
	void createUser(User user) throws NotConnectedException;
	void updateUser(User user) throws NotFoundException, NotConnectedException;
	void deleteUser(int userId) throws NotFoundException, NotConnectedException;

}
