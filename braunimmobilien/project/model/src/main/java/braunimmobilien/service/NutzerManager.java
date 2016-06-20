package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import braunimmobilien.dao.NutzerDao;
import braunimmobilien.model.Nutzer;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface NutzerManager extends GenericManager<Nutzer, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setNutzerDao(NutzerDao nutzerDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Nutzer getNutzer(String nutzerId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Nutzer> getNutzer();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Nutzer saveNutzer(Nutzer nutzer) throws NutzerExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeNutzer(Nutzer nutzer);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeNutzer(String nutzerId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Nutzer> search(String searchTerm);

 
}