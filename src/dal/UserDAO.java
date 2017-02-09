package dal;

import java.util.List;

import models.User;

public interface UserDAO {

	User findUser(int userId) throws NotFoundException, NotConnectedException;
	List<User> getUsers() throws NotConnectedException;
	void createUser(User user) throws NotConnectedException;
	void updateUser(User user) throws NotFoundException, NotConnectedException;
	void deleteUser(int userId) throws NotFoundException, NotConnectedException;

}
