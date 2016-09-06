package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.MitrumpfDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.MitrumpfManager;
import braunimmobilien.service.MitrumpfExistsException;
import braunimmobilien.model.Mitarbeiter;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("mitrumpfManager")
public class MitrumpfManagerImpl extends GenericManagerImpl<Mitarbeiter, Long> implements MitrumpfManager {

  private MitrumpfDao mitrumpfDao;

  @Autowired
  public void setMitrumpfDao(MitrumpfDao mitrumpfDao) {
      this.dao = mitrumpfDao;
      this.mitrumpfDao = mitrumpfDao;
  }

  /**
   * {@inheritDoc}
   */
  public Mitarbeiter getMitrumpf(String mitrumpfId) {
      return mitrumpfDao.get(new Long(mitrumpfId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Mitarbeiter> getMitrumpfes() {
      return mitrumpfDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Mitarbeiter saveMitrumpf(Mitarbeiter mitrumpf) throws MitrumpfExistsException {

    
         


      try {
          return mitrumpfDao.saveMitrumpf(mitrumpf);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new MitrumpfExistsException("Mitrumpf '" + mitrumpf.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new MitrumpfExistsException("Mitrumpf '" + mitrumpf.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeMitrumpf(Mitarbeiter mitrumpf) {
      log.debug("removing user: " + mitrumpf);
      mitrumpfDao.remove(mitrumpf);
  }

  /**
   * {@inheritDoc}
   */
  public void removeMitrumpf(String mitrumpfId) {
      log.debug("removing user: " + mitrumpfId);
      mitrumpfDao.remove(new Long(mitrumpfId));
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
