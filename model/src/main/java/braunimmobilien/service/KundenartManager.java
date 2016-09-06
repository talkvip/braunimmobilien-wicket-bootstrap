package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.KundenartDao;
import braunimmobilien.model.Kundenart;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface KundenartManager extends GenericManager<Kundenart, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setKundenartDao(KundenartDao kundenartDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Kundenart getKundenart(String kundenartId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Kundenart> getKundenartes();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Kundenart saveKundenart(Kundenart kundenart) throws KundenartExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeKundenart(Kundenart kundenart);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeKundenart(String kundenartId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Kundenart> search(String searchTerm);

 
}
