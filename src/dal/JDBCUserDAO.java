package dal;

import models.User;

import java.util.List;

public class JDBCUserDAO implements UserDAO {
	@Override
	public User findUser(int userId) throws DALException {
		return null;
	}

	@Override
	public List<User> getUsers() throws DALException {
		return null;
	}

	@Override
	public void createUser(User user) throws DALException {

	}

	@Override
	public void updateUser(User user) throws DALException {

	}

	@Override
	public void deleteUser(int userId) throws DALException {

	}

}
