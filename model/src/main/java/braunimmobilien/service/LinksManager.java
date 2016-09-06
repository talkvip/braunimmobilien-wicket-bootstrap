package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.LinksDao;
import braunimmobilien.model.Links;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface LinksManager extends GenericManager<Links, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setLinksDao(LinksDao linksDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Links getLinks(String linksId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Links> getLinkses();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Links saveLinks(Links links) throws LinksExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeLinks(Links links);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeLinks(String linksId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Links> search(String searchTerm);

 
}
