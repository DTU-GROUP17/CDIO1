package dal.jdbcdao;

import dal.UserDAO;
import dal.exceptions.NotConnectedException;
import dal.exceptions.NotFoundException;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDAO implements UserDAO {

	private JDBCDAO parent;

	public JDBCUserDAO(JDBCDAO parent){
		this.parent = parent;
	}

	private boolean isResultSetEmpty(ResultSet resultSet) throws SQLException {
		return !resultSet.first();
	}

	private List<String> getUserRoles(int userID) throws NotFoundException, NotConnectedException{
		try {
			String query =
				"SELECT roles.name " +
				"FROM roles_users " +
				"INNER JOIN roles " +
					"ON roles.id = roles_users.role_id " +
				"WHERE roles_users.user_id=?";
			PreparedStatement statement = this.parent.getConnection().prepareStatement(query);
			statement.setInt(1, userID);
			ResultSet results = statement.executeQuery();
			List<String> roles = new ArrayList<>();
			while (results.next()){
				roles.add(results.getString(1));
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
			ResultSet results = this.parent.getConnection().prepareStatement(query).executeQuery();
			if(isResultSetEmpty(results))
				throw new NotFoundException();

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
		List<User> users = new ArrayList<>();
		try {
			String query =
				"SELECT " +
					"id," +
					"name," +
					"ini," +
					"cpr," +
					"psswrd " +
				"FROM users";
			ResultSet results = this.parent.getConnection().prepareStatement(query).executeQuery();

			while (results.next()) {
				int id = results.getInt(1);
				users.add(
					new User(
						id,
						results.getString(2),
						results.getString(3),
						results.getString(4),
						results.getString(5),
						this.getUserRoles(id)
					)
				);
			}
		} catch (SQLException | NotFoundException e) {
			throw new NotConnectedException();
		}
		return users;
	}

	@Override
	public void createUser(User user) throws NotConnectedException {
		try {
			String query =
				"INSERT INTO users (" +
					"name, " +
					"ini, " +
					"cpr, " +
					"psswrd" +
				") " +
				"VALUES (?,?,?,?)";
			PreparedStatement statement = this.parent.getConnection().prepareStatement(query);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getInitials());
			statement.setString(3, user.getCpr());
			statement.setString(4, user.getPassword());
			statement.execute();
			//TODO add roles
		} catch (SQLException e) {
			System.out.println(e);
			throw new NotConnectedException();
		}
	}

	@Override
	public void updateUser(User user) throws NotFoundException, NotConnectedException {
		try {
			String updateStatement ="UPDATE users SET name=?, ini=?, cpr=?, password=?, roles=?"
					+ " WHERE id=?";
			PreparedStatement statement = this.parent.getConnection().prepareStatement(updateStatement);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getInitials());
			statement.setString(3, user.getCpr());
			statement.setString(4, user.getPassword());
			//TODO add roles
			statement.executeQuery();
		} catch (SQLException e) {
			throw new NotConnectedException();
		}
	}

	@Override
	public void deleteUser(int userId) throws NotFoundException, NotConnectedException{
		try {
			String deleteStatement = "DELETE FROM users WHERE id=?";
			PreparedStatement statement = this.parent.getConnection().prepareStatement(deleteStatement);
			statement.setInt(1, userId);
			if(statement.executeUpdate() == 0)
				throw new NotFoundException();
		}
		 catch (SQLException e) {
			throw new NotConnectedException();
		}
	}
}
