package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.MitarbeiterDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.MitarbeiterManager;
import braunimmobilien.service.MitarbeiterExistsException;
import braunimmobilien.model.Mitarbeiter;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("mitarbeiterManager")
public class MitarbeiterManagerImpl extends GenericManagerImpl<Mitarbeiter, Long> implements MitarbeiterManager {

  private MitarbeiterDao mitarbeiterDao;

  @Autowired
  public void setMitarbeiterDao(MitarbeiterDao mitarbeiterDao) {
      this.dao = mitarbeiterDao;
      this.mitarbeiterDao = mitarbeiterDao;
  }

  /**
   * {@inheritDoc}
   */
  public Mitarbeiter getMitarbeiter(String mitarbeiterId) {
      return mitarbeiterDao.get(new Long(mitarbeiterId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Mitarbeiter> getMitarbeiteres() {
      return mitarbeiterDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Mitarbeiter saveMitarbeiter(Mitarbeiter mitarbeiter) throws MitarbeiterExistsException {

    
         


      try {
          return mitarbeiterDao.saveMitarbeiter(mitarbeiter);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new MitarbeiterExistsException("Mitarbeiter '" + mitarbeiter.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new MitarbeiterExistsException("Mitarbeiter '" + mitarbeiter.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeMitarbeiter(Mitarbeiter mitarbeiter) {
      log.debug("removing user: " + mitarbeiter);
      mitarbeiterDao.remove(mitarbeiter);
  }

  /**
   * {@inheritDoc}
   */
  public void removeMitarbeiter(String mitarbeiterId) {
      log.debug("removing user: " + mitarbeiterId);
      mitarbeiterDao.remove(new Long(mitarbeiterId));
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
  public List<Mitarbeiter> search(String searchTerm) {
      return super.search(searchTerm, Mitarbeiter.class);
  }

 
}
