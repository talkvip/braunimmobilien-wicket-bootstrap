package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.StrassenDao;
import braunimmobilien.model.Strassen;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface StrassenManager extends GenericManager<Strassen, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setStrassenDao(StrassenDao strassenDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Strassen getStrassen(String strassenId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Strassen> getStrassenes(String ortid);

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Strassen saveStrassen(Strassen strassen) throws StrassenExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeStrassen(Strassen strassen);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeStrassen(String strassenId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Strassen> search(String searchTerm);

 
}
