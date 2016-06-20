package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.ObjektsuchDao;
import braunimmobilien.model.Objektsuch;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface ObjektsuchManager extends GenericManager<Objektsuch, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setObjektsuchDao(ObjektsuchDao objektsuchDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Objektsuch getObjektsuch(Long objektsuchId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Objektsuch> getObjektsuchen();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Objektsuch saveObjektsuch(Objektsuch objektsuch) throws ObjektsuchExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeObjektsuch(Objektsuch objektsuch);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeObjektsuch(Long objektsuchId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Objektsuch> search(String searchTerm);

 
}

