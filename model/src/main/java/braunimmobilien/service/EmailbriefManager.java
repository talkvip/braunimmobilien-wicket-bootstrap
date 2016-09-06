package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.EmailbriefDao;
import braunimmobilien.model.Emailbrief;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface EmailbriefManager extends GenericManager<Emailbrief, String>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setEmailbriefDao(EmailbriefDao emailbriefDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Emailbrief getEmailbrief(String emailbriefId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Emailbrief> getEmailbriefes();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Emailbrief saveEmailbrief(Emailbrief emailbrief) throws EmailbriefExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeEmailbrief(Emailbrief emailbrief);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeEmailbrief(String emailbriefId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Emailbrief> search(String searchTerm);

 
}
