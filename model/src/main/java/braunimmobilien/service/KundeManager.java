package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.KundeDao;
import braunimmobilien.model.Kunde;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface KundeManager extends GenericManager<Kunde, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setKundeDao(KundeDao kundeDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Kunde getKunde(String kundeId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Kunde> getKundees(String kundeigtnr);

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Kunde saveKunde(Kunde kunde) throws KundeExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeKunde(Kunde kunde);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeKunde(String kundeId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Kunde> search(String searchTerm);

 
}
