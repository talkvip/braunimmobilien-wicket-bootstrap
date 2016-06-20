package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.EigtstatusDao;
import braunimmobilien.model.Eigtstatus;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface EigtstatusManager extends GenericManager<Eigtstatus, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setEigtstatusDao(EigtstatusDao eigtstatusDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Eigtstatus getEigtstatus(String eigtstatusId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Eigtstatus> getEigtstatuses();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Eigtstatus saveEigtstatus(Eigtstatus eigtstatus) throws EigtstatusExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeEigtstatus(Eigtstatus eigtstatus);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeEigtstatus(String eigtstatusId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Eigtstatus> search(String searchTerm);

 
}
