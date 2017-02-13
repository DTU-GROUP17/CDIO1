package dal;

import models.User;

import java.util.List;

public class JDBCUserDAO implements UserDAO {

	@Override
	public User findUser(int userId) throws NotFoundException, NotConnectedException {
		return null;
	}

	@Override
	public List<User> getUsers() throws NotConnectedException {
		return null;
	}

	@Override
	public void createUser(User user) throws NotConnectedException {

	}

	@Override
	public void updateUser(User user) throws NotFoundException, NotConnectedException {

	}

	@Override
	public void deleteUser(int userId) throws NotFoundException, NotConnectedException {

	}
}
