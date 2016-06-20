package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.LandDao;
import braunimmobilien.model.Land;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface LandManager extends GenericManager<Land, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setLandDao(LandDao landDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Land getLand(String landId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Land> getLandes();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Land saveLand(Land land) throws LandExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeLand(Land land);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeLand(String landId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Land> search(String searchTerm);

 
}
