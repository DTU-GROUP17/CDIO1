package dal.inmemorydao;

import dal.DAO;
import dal.UserDAO;

public class InMemoryDAO implements DAO {

	private InMemoryUserDAO userDAO;

	public InMemoryDAO(){
		userDAO = new InMemoryUserDAO();
	}

	@Override
	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}
