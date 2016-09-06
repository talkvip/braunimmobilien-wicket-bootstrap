package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.OrteDao;
import braunimmobilien.model.Orte;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface OrteManager extends GenericManager<Orte, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setOrteDao(OrteDao orteDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Orte getOrte(Long orteId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Orte> getOrtees(String landid);

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Orte saveOrte(Orte orte) throws OrteExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeOrte(Orte orte);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeOrte(String orteId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Orte> search(String searchTerm);

 
}
