package braunimmobilien.service;

import java.util.List;

import org.hibernate.Query;

import braunimmobilien.dao.ObjektDao;
import braunimmobilien.model.Objekte;

public interface ObjektManager extends GenericManager<Objekte, Long>{

	  /**
   * Convenience method for testing - allows you to mock the DAO and set it on an interface.
   * @param userDao the UserDao implementation to use
   */
  void setObjektDao(ObjektDao objektDao);

  /**
   * Retrieves a user by userId.  An exception is thrown if user not found
   *
   * @param userId the identifier for the user
   * @return User
   */
  Objekte getObjekt(Long objektId);

 

  /**
   * Retrieves a list of all users.
   * @return List
   */
  List<Objekte> getObjekteen(String strid);

  /**
   * Saves a user's information.
   *
   * @param user the user's information
   * @throws UserExistsException thrown when user already exists
   * @return user the updated user object
   */
  Objekte saveObjekt(Objekte objekt) throws ObjektExistsException;

  /**
   * Removes a user from the database
   *
   * @param user the user to remove
   */
  void removeObjekt(Objekte objekt);

  /**
   * Removes a user from the database by their userId
   *
   * @param userId the user's id
   */
  void removeObjekt(Long objektId);

  /**
   * Search a user for search terms.
   * @param searchTerm the search terms.
   * @return a list of matches, or all if no searchTerm.
   */
  List<Objekte> search(String searchTerm);
 
  List<Objekte> getObjekteNachObjektsuch (Long objektsuchid);
 	
 List<Objekte> getObjekteNachVorlagedatum (java.util.Date beginDate,java.util.Date endDate);

}

