package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.ObjektsuchDao;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.ObjektsuchExistsException;
import braunimmobilien.model.Objektsuch;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("objektsuchManager")
public class ObjektsuchManagerImpl extends GenericManagerImpl<Objektsuch, Long> implements ObjektsuchManager {

  private ObjektsuchDao objektsuchDao;

  @Autowired
  public void setObjektsuchDao(ObjektsuchDao objektsuchDao) {
      this.dao = objektsuchDao;
      this.objektsuchDao = objektsuchDao;
  }

  /**
   * {@inheritDoc}
   */
  public Objektsuch getObjektsuch(Long objektsuchId) {
      return objektsuchDao.get(objektsuchId);
  }

  /**
   * {@inheritDoc}
   */
  public List<Objektsuch> getObjektsuchen() {
      return objektsuchDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Objektsuch saveObjektsuch(Objektsuch objektsuch) throws ObjektsuchExistsException {

    
         


      try {
          return objektsuchDao.saveObjektsuch(objektsuch);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new ObjektsuchExistsException("Objektsuch '" + objektsuch.getSuchtext() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new ObjektsuchExistsException("Objektsuch '" + objektsuch.getSuchtext() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeObjektsuch(Objektsuch objektsuch) {
      log.debug("removing user: " + objektsuch);
      objektsuchDao.remove(objektsuch);
  }

  /**
   * {@inheritDoc}
   */
  public void removeObjektsuch(Long objektsuchId) {
      log.debug("removing user: " + objektsuchId);
      objektsuchDao.remove(objektsuchId);
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
  public List<Objektsuch> search(String searchTerm) {
      return super.search(searchTerm, Objektsuch.class);
  }

 
}
