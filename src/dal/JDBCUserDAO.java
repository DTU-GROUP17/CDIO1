package dal;

import models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class JDBCUserDAO implements UserDAO {

	private Connection con;
	private Statement s;
	PreparedStatement p;
	int userId;
	String username, initials, cpr, password;
	private List<String> roles;

	public JDBCUserDAO(boolean opretDB) throws DALException {
		try {
			//register the driver and load into the memory
			//makes the driver registration portable.
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			
			//had to use this, to get rid of eclipse complaining about security issues.
			//so SSL is on, no sniffing passwords on my watch!.
			//verifyServerCertificate=false&useSSL=true
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/3lm?verifyServerCertificate=false&useSSL=true", "root", "");
			s = con.createStatement();

			if (opretDB) {
				s.executeUpdate(
						"CREATE TABLE personer (userID INT(2) AUTO_INCREMENT=10,userName VARCHAR(20), ini VARCHAR(4),cpr INT(11), String VARCHAR(30), roles (varchar(20))");
				s.executeUpdate("INSERT INTO personer VALUES ('Henrik Ulriksen', '346543-2745')");
				s.executeUpdate("INSERT INTO personer VALUES ('Charlotte Mogensen', '364987-3274')");
				s.executeUpdate("INSERT INTO personer VALUES ('Lene Hansen', '833643-2746')");
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new DALException(e);
		}
	}
	
	
	//unsure if it works. Must test it to see if i can find users repeatedly
	//maybe some clear is needed for the roles at the end.
	@Override
	public User findUser(int userId) throws DALException {
		try {
		ResultSet rs = s.executeQuery(
				"SELECT userId, username, initials, cpr, password, roles FROM personer WHERE userId = " + userId);

		this.userId = rs.getInt(1);
		this.username = rs.getString(2);
		this.initials = rs.getString(3);
		this.cpr = rs.getString(4);
		this.password = rs.getString(5);
		this.roles.add(rs.getString(6));

		User user = new User(this.userId, this.username, this.initials, this.cpr, this.password, this.roles);
		return user;
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<User> getUsers() throws DALException {

		try {
			List<User> brugere = new LinkedList<User>();
			ResultSet rs = s.executeQuery("SELECT * FROM personer");

			while (rs.next()) {

				this.userId = rs.getInt(1);
				this.username = rs.getString(2);
				this.initials = rs.getString(3);
				this.cpr = rs.getString(4);
				this.password = rs.getString(5);
				this.roles.add(rs.getString(6));

				User user = new User(this.userId, this.username, this.initials, this.cpr, this.password, this.roles);
				brugere.add(user);
			}
			return brugere;
		} catch (SQLException e) {
			throw new DALException(e);
		}

	}

	@Override
	public void createUser(User user) throws DALException {
		try {
		this.userId = user.getUserId();
		this.username = user.getUserName();
		this.initials = user.getInitials();
		this.cpr = user.getCpr();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		
		String insertTableSQL = "INSERT INTO 3lm" 
				+ "(userId, username, initials, cpr, password, roles) VALUES" 
				+ "(?,?,?,?,?,?)";
		p = con.prepareStatement(insertTableSQL);
		p.setInt(1, this.userId);
		p.setString(2, this.username);
		p.setString(3, this.initials);
		p.setString(4, this.cpr);
		p.setString(5, this.password);
		p.setString(6, this.roles);
		p.setArray(6, this.roles);
		
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void updateUser(User user) throws DALException {

	}

	@Override
	public void deleteUser(int userId) throws DALException {

	}

}
