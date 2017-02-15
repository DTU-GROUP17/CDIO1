package dal;


import java.util.List;

import models.User;

public interface UserDAO {

	User findUser(int userId) throws NotFoundException, NotConnectedException, DALException;
	List<User> getUsers() throws NotConnectedException, DALException;
	void createUser(User user) throws NotConnectedException, DALException;
	void updateUser(User user) throws NotFoundException, NotConnectedException, DALException;
	void deleteUser(int userId) throws NotFoundException, NotConnectedException, DALException;

}
