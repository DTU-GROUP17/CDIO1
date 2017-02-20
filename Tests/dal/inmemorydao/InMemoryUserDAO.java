package dal.inmemorydao;

import dal.contracts.UserDAO;
import dal.exceptions.NotFoundException;
import dal.exceptions.NotConnectedException;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDAO implements UserDAO {

	private List<User> users;
	private int idcounter;


	public InMemoryUserDAO(){
		this.users = new ArrayList<>();
		this.idcounter = 0;
	}

	private int getID(){
		return this.idcounter++;
	}

	@Override
	public User findUser(int userId) throws NotFoundException, NotConnectedException {
		try {
			return this.users.stream().filter(user -> user.getUserId() == userId).findFirst().get();
		} catch (Exception e){
			throw new NotFoundException("could not find user");
		}
	}

	@Override
	public List<User> getUsers() throws NotConnectedException {
		return this.users;
	}

	@Override
	public void createUser(User user) throws NotConnectedException {
		user.setUserId(this.getID());
		this.users.add(user);
	}

	@Override
	public void updateUser(User user) throws NotFoundException, NotConnectedException {
		try {
			this.users.remove(this.users.stream().filter(other -> other.getUserId() == user.getUserId()).findFirst().get());
			this.users.add(user);
		} catch (Exception e){
			throw new NotFoundException("could not find user");
		}
	}

	@Override
	public void deleteUser(int userId) throws NotFoundException, NotConnectedException {
		try {
			this.users.remove(this.users.stream().filter(other -> other.getUserId() == userId).findFirst().get());
		} catch (Exception e){
			throw new NotFoundException("could not find user");
		}
	}

	@Override
	public void create(User object) throws NotConnectedException {
		this.createUser(object);
	}
}
