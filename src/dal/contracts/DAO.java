package dal.contracts;

/**
 * Contract for all DOA's
 */
public interface DAO {

	/**
	 * Gets the DAO for a user
	 *
	 * @return the chosen userDAO
	 */
	UserDAO getUserDAO();

	/**
	 * Gets the DAO for a role
	 *
	 * @return the chosen roleDAO
	 */
	RoleDAO getRoleDAO();

}