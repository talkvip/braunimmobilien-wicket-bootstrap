package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.ObjarttypDao;
import braunimmobilien.model.Objarttyp;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface ObjarttypManager extends GenericManager<Objarttyp, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setObjarttypDao(ObjarttypDao objarttypDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Objarttyp getObjarttyp(String objarttypId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Objarttyp> getObjarttypes();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Objarttyp saveObjarttyp(Objarttyp objarttyp) throws ObjarttypExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeObjarttyp(Objarttyp objarttyp);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeObjarttyp(String objarttypId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Objarttyp> search(String searchTerm);

 
}
