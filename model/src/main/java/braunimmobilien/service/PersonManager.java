package braunimmobilien.service;

import java.util.List;

import braunimmobilien.dao.PersonDao;
import braunimmobilien.model.Personen;

public interface PersonManager extends GenericManager<Personen, Long>{

	  /**
   * Convenience method for testing - allows you to mock the DAO and set it on an interface.
   * @param userDao the UserDao implementation to use
   */
  void setPersonDao(PersonDao personDao);

  /**
   * Retrieves a user by userId.  An exception is thrown if user not found
   *
   * @param userId the identifier for the user
   * @return User
   */
  Personen getPerson(Long personId);

 

  /**
   * Retrieves a list of all users.
   * @return List
   */
  List<Personen> getPersonen(String strid);

  /**
   * Saves a user's information.
   *
   * @param user the user's information
   * @throws UserExistsException thrown when user already exists
   * @return user the updated user object
   */
  Personen savePerson(Personen person) throws PersonExistsException;

  /**
   * Removes a user from the database
   *
   * @param user the user to remove
   */
  void removePerson(Personen objekt);

  /**
   * Removes a user from the database by their userId
   *
   * @param userId the user's id
   */
  void removePerson(Long objektId);

  /**
   * Search a user for search terms.
   * @param searchTerm the search terms.
   * @return a list of matches, or all if no searchTerm.
   */
  List<Personen> search(String searchTerm);


}

