package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.KonditionDao;
import braunimmobilien.model.Kondition;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface KonditionManager extends GenericManager<Kondition, String>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setKonditionDao(KonditionDao konditionDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Kondition getKondition(String konditionId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Kondition> getKonditionen();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Kondition saveKondition(Kondition kondition) throws KonditionExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeKondition(Kondition kondition);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeKondition(String konditionId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Kondition> search(String searchTerm);

 
}

