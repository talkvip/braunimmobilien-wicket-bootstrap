package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.AngobjzuordDao;
import braunimmobilien.model.Angobjzuord;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface AngobjzuordManager extends GenericManager<Angobjzuord, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setAngobjzuordDao(AngobjzuordDao angobjzuordDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Angobjzuord getAngobjzuord(String angobjzuordId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Angobjzuord> getAngobjzuordes(String objid);

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Angobjzuord saveAngobjzuord(Angobjzuord angobjzuord) throws AngobjzuordExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeAngobjzuord(Angobjzuord angobjzuord);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeAngobjzuord(String angobjzuordId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Angobjzuord> search(String searchTerm);

 
}