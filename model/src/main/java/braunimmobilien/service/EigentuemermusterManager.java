package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.EigentuemermusterDao;
import braunimmobilien.model.Eigentuemermuster;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface EigentuemermusterManager extends GenericManager<Eigentuemermuster, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setEigentuemermusterDao(EigentuemermusterDao eigentuemermusterDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Eigentuemermuster getEigentuemermuster(String eigentuemermusterId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Eigentuemermuster> getEigentuemermusters();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Eigentuemermuster saveEigentuemermuster(Eigentuemermuster eigentuemermuster) throws EigentuemermusterExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeEigentuemermuster(Eigentuemermuster eigentuemermuster);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeEigentuemermuster(String eigentuemermusterId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Eigentuemermuster> search(String searchTerm);

 
}
