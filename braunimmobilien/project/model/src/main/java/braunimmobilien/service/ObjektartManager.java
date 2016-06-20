package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.ObjektartDao;
import braunimmobilien.model.Objektart;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface ObjektartManager extends GenericManager<Objektart, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setObjektartDao(ObjektartDao objektartDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Objektart getObjektart(Long objektartId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Objektart> getObjektartes();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Objektart saveObjektart(Objektart objektart) throws ObjektartExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeObjektart(Objektart objektart);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeObjektart(Long objektartId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Objektart> search(String searchTerm);

 
}
