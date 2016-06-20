package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.ScoutDao;
import braunimmobilien.model.Scout;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface ScoutManager extends GenericManager<Scout, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setScoutDao(ScoutDao scoutDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Scout getScout(String scoutId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Scout> getScoutes();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Scout saveScout(Scout scout) throws ScoutExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeScout(Scout scout);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeScout(String scoutId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Scout> search(String searchTerm);

 
}
