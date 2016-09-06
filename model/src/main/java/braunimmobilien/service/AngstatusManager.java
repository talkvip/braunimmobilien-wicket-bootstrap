package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.AngstatusDao;
import braunimmobilien.model.Angstatus;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface AngstatusManager extends GenericManager<Angstatus, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setAngstatusDao(AngstatusDao angstatusDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Angstatus getAngstatus(String angstatusId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Angstatus> getAngstatuses();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Angstatus saveAngstatus(Angstatus angstatus) throws AngstatusExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeAngstatus(Angstatus angstatus);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeAngstatus(String angstatusId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Angstatus> search(String searchTerm);

 
}
