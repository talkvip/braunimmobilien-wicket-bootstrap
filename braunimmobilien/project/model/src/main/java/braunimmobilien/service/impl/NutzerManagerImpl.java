package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.NutzerDao;
import braunimmobilien.service.NutzerManager;
import braunimmobilien.service.NutzerExistsException;
import braunimmobilien.model.Nutzer;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("nutzerManager")
public class NutzerManagerImpl extends GenericManagerImpl<Nutzer, Long> implements NutzerManager {

  private NutzerDao nutzerDao;

  @Autowired
  public void setNutzerDao(NutzerDao nutzerDao) {
      this.dao = nutzerDao;
      this.nutzerDao = nutzerDao;
  }

  /**
   * {@inheritDoc}
   */
  public Nutzer getNutzer(String nutzerId) {
      return nutzerDao.get(new Long(nutzerId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Nutzer> getNutzer() {
      return nutzerDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Nutzer saveNutzer(Nutzer nutzer) throws NutzerExistsException {

    
         


      try {
          return nutzerDao.saveNutzer(nutzer);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new NutzerExistsException("Nutzer '" + nutzer.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new NutzerExistsException("Nutzer '" + nutzer.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeNutzer(Nutzer nutzer) {
      log.debug("removing user: " + nutzer);
      nutzerDao.remove(nutzer);
  }

  /**
   * {@inheritDoc}
   */
  public void removeNutzer(String nutzerId) {
      log.debug("removing user: " + nutzerId);
      nutzerDao.remove(new Long(nutzerId));
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
  public List<Nutzer> search(String searchTerm) {
      return super.search(searchTerm, Nutzer.class);
  }

 
}
