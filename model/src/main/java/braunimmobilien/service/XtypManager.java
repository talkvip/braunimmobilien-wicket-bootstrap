package braunimmobilien.service;

	import java.util.Collection;
	import java.util.List;

	import org.springframework.security.core.userdetails.UsernameNotFoundException;

	import braunimmobilien.dao.XtypDao;
	import braunimmobilien.model.Xtyp;


	/**
	 * Interface of land manager.
	 * 
	 * @author rjansen
	 */
	public interface XtypManager extends GenericManager<Xtyp, Long>{

		  /**
	     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
	     * @param userDao the UserDao implementation to use
	     */
	    void setXtypDao(XtypDao xtypDao);

	    /**
	     * Retrieves a user by userId.  An exception is thrown if user not found
	     *
	     * @param userId the identifier for the user
	     * @return User
	     */
	    Xtyp getXtyp(String xtypId);

	   

	    /**
	     * Retrieves a list of all users.
	     * @return List
	     */
	    List<Xtyp> getXtyps();

	    /**
	     * Saves a user's information.
	     *
	     * @param user the user's information
	     * @throws UserExistsException thrown when user already exists
	     * @return user the updated user object
	     */
	    Xtyp saveXtyp(Xtyp xtyp) throws XtypExistsException;

	    /**
	     * Removes a user from the database
	     *
	     * @param user the user to remove
	     */
	    void removeXtyp(Xtyp xtyp);

	    /**
	     * Removes a user from the database by their userId
	     *
	     * @param userId the user's id
	     */
	    void removeXtyp(String xtypId);

	    /**
	     * Search a user for search terms.
	     * @param searchTerm the search terms.
	     * @return a list of matches, or all if no searchTerm.
	     */
	    List<Xtyp> search(String searchTerm);

	 
	}
