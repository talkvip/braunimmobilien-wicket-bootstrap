package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.EigentuemertypDao;
import braunimmobilien.model.Eigentuemertyp;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface EigentuemertypManager extends GenericManager<Eigentuemertyp, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setEigentuemertypDao(EigentuemertypDao eigentuemertypDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Eigentuemertyp getEigentuemertyp(String eigentuemertypId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Eigentuemertyp> getEigentuemertypes();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Eigentuemertyp saveEigentuemertyp(Eigentuemertyp eigentuemertyp) throws EigentuemertypExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeEigentuemertyp(Eigentuemertyp eigentuemertyp);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeEigentuemertyp(String eigentuemertypId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Eigentuemertyp> search(String searchTerm);

 
}