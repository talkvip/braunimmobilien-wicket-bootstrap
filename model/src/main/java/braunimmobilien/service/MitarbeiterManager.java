package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.MitarbeiterDao;
import braunimmobilien.model.Mitarbeiter;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface MitarbeiterManager extends GenericManager<Mitarbeiter, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setMitarbeiterDao(MitarbeiterDao mitarbeiterDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Mitarbeiter getMitarbeiter(String mitarbeiterId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Mitarbeiter> getMitarbeiteres();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Mitarbeiter saveMitarbeiter(Mitarbeiter mitarbeiter) throws MitarbeiterExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeMitarbeiter(Mitarbeiter mitarbeiter);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeMitarbeiter(String mitarbeiterId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Mitarbeiter> search(String searchTerm);

 
}
