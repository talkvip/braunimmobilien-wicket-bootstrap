package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.NachweiseDao;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Objekte;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface NachweiseManager extends GenericManager<Nachweise, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setNachweiseDao(NachweiseDao nachweiseDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Nachweise getNachweise(String nachweiseId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Nachweise> getNachweisees();

    
    List<Nachweise> getNachweiseNachObjekte (String objektid);
    
    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Nachweise saveNachweise(Nachweise nachweise) throws NachweiseExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeNachweise(Nachweise nachweise);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeNachweise(String nachweiseId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Nachweise> search(String searchTerm);

 
}
