package dal.jdbcdao;

import dal.contracts.UserDAO;
import dal.exceptions.NotConnectedException;
import dal.exceptions.NotFoundException;
import models.Role;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JDBCUserDAO implements UserDAO {

	private JDBCDAO parent;

	public JDBCUserDAO(JDBCDAO parent){
		this.parent = parent;
	}

	private boolean isResultSetEmpty(ResultSet resultSet) throws SQLException {
		return !resultSet.first();
	}

	private List<Role> getUserRoles(int userID) throws NotFoundException, NotConnectedException{
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
			List<Role> roles = new ArrayList<>();
			while (results.next()){
				roles.add(new Role(results.getString(1)));
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
					"id, " +
					"name, " +
					"ini, " +
					"cpr, " +
					"psswrd " +
				"FROM users " +
				"WHERE id = ?";
			PreparedStatement statement = this.parent.getConnection().prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet results = statement.executeQuery();
			if(!results.first()) {
				throw new NotFoundException();
			}

			int id = results.getInt(1);
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

			PreparedStatement statement = this.parent.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getInitials());
			statement.setString(3, user.getCpr());
			statement.setString(4, user.getPassword());
			statement.executeUpdate();
			ResultSet results = statement.getGeneratedKeys();
			int userId;
			if (results.next()){
				userId = results.getInt(1);
			} else {
				throw new NotConnectedException();
			}
			query =
				"INSERT " +
				"INTO roles_users (user_id, role_id) " +
				"VALUES";
			for (int i = 0; i<user.getRoles().size(); i++){
				query += "(?, (SELECT id FROM roles WHERE name=?)),";
			}
			query = query.substring(0, query.length()-1)+';';
			statement = this.parent.getConnection().prepareStatement(query);
			int counter = 0;
			for (String role : user.getRoles().stream().map(Role::getName).collect(Collectors.toList())){
				statement.setInt(++counter, userId);
				statement.setString(++counter, role);
			}
			statement.execute();
		} catch (SQLException e) {
			throw new NotConnectedException();
		}
	}

	@Override
	public void updateUser(User user) throws NotFoundException, NotConnectedException {
		try {
			System.out.println("user.getId() = " + user.getId());
			String updateStatement =
				"UPDATE users " +
				"SET " +
					"name=?, " +
					"ini=?, " +
					"cpr=?, " +
					"psswrd=? " +
				"WHERE id=?";
			System.out.println("updateStatement = " + updateStatement);
			PreparedStatement statement = this.parent.getConnection().prepareStatement(updateStatement);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getInitials());
			statement.setString(3, user.getCpr());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getId());
			System.out.println(statement.toString());
			statement.execute();
			String query =
				"DELETE " +
				"FROM roles_users " +
				"WHERE user_id=?";
			statement = this.parent.getConnection().prepareStatement(query);
			statement.setInt(1, user.getId());
			query =
				"INSERT " +
				"INTO roles_users (user_id, role_id) " +
				"VALUES";
			for (int i = 0; i<user.getRoles().size(); i++){
				query += "(?, (SELECT id FROM roles WHERE name=?)),";
			}
			query = query.substring(0, query.length()-1)+';';
			statement = this.parent.getConnection().prepareStatement(query);
			int counter = 0;
			for (String role : user.getRoles().stream().map(Role::getName).collect(Collectors.toList())){
				statement.setInt(++counter, user.getId());
				statement.setString(++counter, role);
			}

		} catch (SQLException e) {
			System.out.println("e = " + e);
			throw new NotConnectedException();
		}
	}

	@Override
	public void deleteUser(int userId) throws NotFoundException, NotConnectedException{
		try {
			String deleteStatement =
				"DELETE " +
				"FROM users " +
				"WHERE id=? ";
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
