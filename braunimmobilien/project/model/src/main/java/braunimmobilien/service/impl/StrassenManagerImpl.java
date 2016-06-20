package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.StrassenDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.StrassenExistsException;
import braunimmobilien.model.Strassen;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("strassenManager")
public class StrassenManagerImpl extends GenericManagerImpl<Strassen, Long> implements StrassenManager {

  private StrassenDao strassenDao;

  @Autowired
  public void setStrassenDao(StrassenDao strassenDao) {
      this.dao = strassenDao;
      this.strassenDao = strassenDao;
  }

  /**
   * {@inheritDoc}
   */
  public Strassen getStrassen(String strassenId) {
      return strassenDao.get(new Long(strassenId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Strassen> getStrassenes(String ortid) {
      return strassenDao.getStrassens(ortid);
  }

  /**
   * {@inheritDoc}
   */
  public Strassen saveStrassen(Strassen strassen) throws StrassenExistsException {

    
         


      try {
          return strassenDao.saveStrassen(strassen);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new StrassenExistsException("Strassen '" + strassen.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new StrassenExistsException("Strassen '" + strassen.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeStrassen(Strassen strassen) {
      log.debug("removing strasse: " + strassen);
      strassenDao.remove(strassen);
  }

  /**
   * {@inheritDoc}
   */
  public void removeStrassen(String strassenId) {
      log.debug("removing strasse: " + strassenId);
      strassenDao.remove(new Long(strassenId));
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
  public List<Strassen> search(String searchTerm) {
      return super.search(searchTerm, Strassen.class);
  }

 
}
