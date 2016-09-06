package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import braunimmobilien.dao.PersonDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.PersonExistsException;
import braunimmobilien.model.Personen;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("personManager")
public class PersonManagerImpl extends GenericManagerImpl<Personen, Long> implements PersonManager {

  private PersonDao personDao;

  @Autowired
  public void setPersonDao(PersonDao personDao) {
      this.dao = personDao;
      this.personDao = personDao;
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public Personen getPerson(Long personId) {
      return personDao.get(new Long(personId));
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public List<Personen> getPersonen(String strid) {
      return personDao.getPersonen(strid);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public Personen savePerson(Personen person) throws PersonExistsException {

    
         


      try {
          return personDao.savePerson(person);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new PersonExistsException(e.getMessage());
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new PersonExistsException(e.getMessage());
      }
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public void removePerson(Personen person) {
      log.debug("removing user: " + person);
      personDao.remove(person);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public void removePerson(Long personId) {
      log.debug("removing user: " + personId);
      personDao.remove(new Long(personId));
  }

  /**
   * {@inheritDoc}
   *
   * @param username the login name of the human
   * @return User the populated user object
   * @throws UsernameNotFoundException thrown when username not found
   */
  

  /**
   * {@inheritDoc}
   */
  @Transactional
  public List<Personen> search(String searchTerm) {
      return super.search(searchTerm, Personen.class);
  }

 
}
