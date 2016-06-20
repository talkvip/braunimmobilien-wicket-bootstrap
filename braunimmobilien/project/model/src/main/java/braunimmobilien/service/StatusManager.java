package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.StatusDao;
import braunimmobilien.model.Status;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface StatusManager extends GenericManager<Status, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setStatusDao(StatusDao statusDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Status getStatus(String statusId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Status> getStatuses();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Status saveStatus(Status status) throws StatusExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeStatus(Status status);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeStatus(String statusId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Status> search(String searchTerm);

 
}
