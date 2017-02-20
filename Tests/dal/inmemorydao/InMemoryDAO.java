package dal.inmemorydao;

import dal.contracts.DAO;
import dal.contracts.UserDAO;

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
