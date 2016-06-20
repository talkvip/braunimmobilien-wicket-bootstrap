package braunimmobilien.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import braunimmobilien.dao.SubjectsDao;
import braunimmobilien.model.Subjects;


/**
 * Interface of land manager.
 * 
 * @author rjansen
 */
public interface SubjectsManager extends GenericManager<Subjects, Long>{

	  /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setSubjectsDao(SubjectsDao subjectsDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    Subjects getSubjects(String subjectsId);

   

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Subjects> getSubjectses();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    Subjects saveSubjects(Subjects subjects) throws SubjectsExistsException;

    /**
     * Removes a user from the database
     *
     * @param user the user to remove
     */
    void removeSubjects(Subjects subjects);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeSubjects(String subjectsId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Subjects> search(String searchTerm);

 
}
