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
	private String uri;

	public JDBCDAO(String uri) {
		this.uri = uri;
		this.userDAO = new JDBCUserDAO(this);
	}

	public Connection getConnection() throws NotConnectedException{
		if (this.connection==null){
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				this.connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + this.uri,
					"root",
					""
				);
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				throw new NotConnectedException();
			}
		}
		return this.connection;
	}

	@Override
	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}
