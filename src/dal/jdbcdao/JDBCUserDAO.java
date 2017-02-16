package dal.jdbcdao;

import dal.exceptions.NotConnectedException;
import dal.exceptions.NotFoundException;
import dal.UserDAO;
import models.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCUserDAO implements UserDAO {

	private Connection connection;

	public JDBCUserDAO(Connection connection){
		this.connection = connection;
	}

	private List<String> getRolesForUser(int userID){
		String query =
			"SELECT roles.name" +
			" FROM" +
				"roles_users" +
				"INNER JOIN roles" +
				"ON roles.id = roles_users.role_id" +
				" WHERE roles_users.user_id=?";
	}

	@Override
	public User findUser(int userId) throws NotFoundException, NotConnectedException {
		try {
			String query = ("SELECT userId, username, initials, cpr, password FROM personer WHERE userId = "
					+ userId);
			ResultSet results = connection.prepareStatement(query).executeQuery();
			
			this.userId = results.getInt(1);
			this.username = results.getString(2);
			this.initials = results.getString(3);
			this.cpr = results.getString(4);
			this.password = results.getString(5);
			this.roles.add(results.getString(6));

			User user = new User(this.userId, this.username, this.initials, this.cpr, this.password, this.roles);
			return user;
		} catch (SQLException e) {
			throw new NotConnectedException();
		} 
	}

	@Override
	public List<User> getUsers() throws NotConnectedException {
		try {
			List<User> brugere = new LinkedList<>();
			String selectStatement = "SELECT * FROM personer";
			statement = connection.prepareStatement(selectStatement);
			results = statement.executeQuery();
			while (results.next()) {
				this.userId = results.getInt(1);
				this.username = results.getString(2);
				this.initials = results.getString(3);
				this.cpr = results.getString(4);
				this.password = results.getString(5);
				this.roles.add(results.getString(6));
			}
			User user = new User(this.userId, this.username, this.initials, this.cpr, this.password, this.roles);
			brugere.add(user);
			return brugere;
		} catch (SQLException e) {
			throw new NotConnectedException();
		}
	}

	@Override
	public void createUser(User user) throws NotConnectedException {
		try {
			this.userId = user.getUserId();
			this.username = user.getUserName();
			this.initials = user.getInitials();
			this.cpr = user.getCpr();
			this.password = user.getPassword();
			this.roles = user.getRoles();

			this.roles.toArray();

			String insertTableSQL = "INSERT INTO " + tabel + "(userId, username, initials, cpr, password, roles) VALUES"
					+ "(?,?,?,?,?,?)";
			statement = connection.prepareStatement(insertTableSQL);
			statement.setInt(1, this.userId);
			statement.setString(2, this.username);
			statement.setString(3, this.initials);
			statement.setString(4, this.cpr);
			statement.setString(5, this.password);
			statement.setString(6, this.roles.get(0));
			statement.executeQuery();

		} catch (SQLException e) {
			throw new NotConnectedException();
		}
	}

	@Override
	public void updateUser(User user) throws NotFoundException, NotConnectedException {
		try {
			this.userId = user.getUserId();
			this.username = user.getUserName();
			this.initials = user.getInitials();
			this.cpr = user.getCpr();
			this.password = user.getPassword();
			this.roles = user.getRoles();
			this.roles.toArray();

			String updateStatement ="UPDATE " + tabel + " SET userId=?, username=?, initials=?, cpr=?, password=?, roles=?"
					+ " WHERE userId=?";
			statement = connection.prepareStatement(updateStatement);
			statement.setInt(1, this.userId);
			statement.setString(2, this.username);
			statement.setString(3, this.initials);
			statement.setString(4, this.cpr);
			statement.setString(5, this.password);
			statement.setString(6, this.roles.get(0));
			statement.executeQuery();
		} catch (SQLException e) {
			throw new NotConnectedException();
		}
	}

	@Override
	public void deleteUser(int userId) throws NotFoundException, NotConnectedException{
		try {
			String deleteStatement = "DELETE FROM " + tabel + " WHERE userId=?";
			statement = connection.prepareStatement(deleteStatement);
			statement.setInt(1, userId);
			statement.executeQuery();
		}
		 catch (SQLException e) {
			throw new NotConnectedException();
		}
	}
}
