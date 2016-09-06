package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.AngebotDao;
import braunimmobilien.model.Angebot;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface AngebotManager extends GenericManager<Angebot, String>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setAngebotDao(AngebotDao angebotDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Angebot getAngebot(String angebotId);


	  /**
   * Convenience method for testing - allows you to mock the DAO and set it on an interface.
   * @param userDao the UserDao implementation to use
   */
 
    List<Angebot> getAngebote();

    
    
  
    Angebot saveAngebot(Angebot angebot) throws AngebotExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeAngebot(Angebot angebot);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeAngebot(String angebotId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Angebot> search(String searchTerm);
    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    
}
