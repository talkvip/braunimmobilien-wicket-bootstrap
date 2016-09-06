package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.TelefonartDao;
import braunimmobilien.model.Telefonart;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface TelefonartManager extends GenericManager<Telefonart, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setTelefonartDao(TelefonartDao telefonartDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Telefonart getTelefonart(String telefonartId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Telefonart> getTelefonartes();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Telefonart saveTelefonart(Telefonart telefonart) throws TelefonartExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeTelefonart(Telefonart telefonart);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeTelefonart(String telefonartId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Telefonart> search(String searchTerm);

 
}
