package dal.jdbcdao;

import dal.contracts.RoleDAO;
import dal.exceptions.NotConnectedException;
import models.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCRoleDAO implements RoleDAO {

	private JDBCDAO parent;

	public JDBCRoleDAO(JDBCDAO parent){
		this.parent = parent;
	}

	@Override
	public List<Role> getRoles() throws NotConnectedException {
		List<Role> roles = new ArrayList<>();
		try {
			String query =
				"SELECT " +
					"id, " +
					"name " +
				"FROM roles";
			ResultSet results = this.parent.getConnection().prepareStatement(query).executeQuery();
			while (results.next()) {
				roles.add(
					new Role(
						results.getString(2),
						results.getInt(1)
					)
				);
			}
		} catch (SQLException e) {
			throw new NotConnectedException();
		}
		return roles;
	}
}
