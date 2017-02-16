package dal.jdbcdao;

import dal.exceptions.NotConnectedException;
import dal.exceptions.NotFoundException;
import dal.UserDAO;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JDBCUserDAO implements UserDAO {

	private Connection connection;

	public JDBCUserDAO(Connection connection){
		this.connection = connection;
	}

	private List<String> getUserRoles(int userID) throws NotFoundException, NotConnectedException{
		try {
			String query =
				"SELECT roles.name" +
				"FROM roles_users" +
				"INNER JOIN roles" +
					"ON roles.id = roles_users.role_id" +
				"WHERE roles_users.user_id=?";
			ResultSet results = connection.prepareStatement(query).executeQuery();
			List<String> roles = new ArrayList<>();
			while (results.next()){
				roles.add(results.getString(0));
			}
			return roles;
		} catch (SQLException e) {
			throw new NotConnectedException();
		}
	}

	@Override
	public User findUser(int userId) throws NotFoundException, NotConnectedException {
		try {
			String query =
				"SELECT " +
					"id," +
					"name," +
					"ini," +
					"cpr," +
					"password" +
				"FROM users" +
				"WHERE id = " + userId;
			ResultSet results = connection.prepareStatement(query).executeQuery();
			int id = results.getInt(0);
			return new User(
				id,
				results.getString(1),
				results.getString(2),
				results.getString(3),
				results.getString(4),
				this.getUserRoles(id)
			);
		} catch (SQLException e) {
			throw new NotConnectedException();
		} 
	}

	@Override
	public List<User> getUsers() throws NotConnectedException {
		try {
			List<User> users = new ArrayList<>();
			String query =
				"SELECT " +
					"id," +
					"name," +
					"ini," +
					"cpr," +
					"password" +
				"FROM users";
			ResultSet results = connection.prepareStatement(query).executeQuery();
			while (results.next()) {
				int id = results.getInt(0);
				users.add(
					new User(
						id,
						results.getString(1),
						results.getString(2),
						results.getString(3),
						results.getString(4),
						this.getUserRoles(id)
					)
				);
			}
			return users;
		} catch (SQLException | NotFoundException e) {
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
