package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.TypeDao;
import braunimmobilien.model.Type;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface TypeManager extends GenericManager<Type, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setTypeDao(TypeDao typeDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Type getType(Long typeId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Type> getTypees();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Type saveType(Type type) throws TypeExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeType(Type type);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeType(String typeId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Type> search(String searchTerm);

 
}
