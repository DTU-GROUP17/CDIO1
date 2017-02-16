package dal.jdbcdao;

import dal.DAO;
import dal.UserDAO;
import dal.exceptions.NotConnectedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDAO implements DAO {

	private JDBCUserDAO userDAO;
	private Connection connection;

	public JDBCDAO(String url) throws NotConnectedException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + url + "?verifyServerCertificate=false&useSSL=true",
					"root",
					""
			);
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new NotConnectedException();
		}
		this.userDAO = new JDBCUserDAO(this.connection);
	}

	@Override
	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}
