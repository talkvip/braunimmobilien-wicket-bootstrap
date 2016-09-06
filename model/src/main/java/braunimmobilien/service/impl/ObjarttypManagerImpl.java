package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.ObjarttypDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.service.ObjarttypExistsException;
import braunimmobilien.model.Objarttyp;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("objarttypManager")
public class ObjarttypManagerImpl extends GenericManagerImpl<Objarttyp, Long> implements ObjarttypManager {

  private ObjarttypDao objarttypDao;

  @Autowired
  public void setObjarttypDao(ObjarttypDao objarttypDao) {
      this.dao = objarttypDao;
      this.objarttypDao = objarttypDao;
  }

  /**
   * {@inheritDoc}
   */
  public Objarttyp getObjarttyp(String objarttypId) {
      return objarttypDao.get(new Long(objarttypId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Objarttyp> getObjarttypes() {
      return objarttypDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Objarttyp saveObjarttyp(Objarttyp objarttyp) throws ObjarttypExistsException {

    
         


      try {
          return objarttypDao.saveObjarttyp(objarttyp);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new ObjarttypExistsException("Objarttyp '" + objarttyp.getTypentext() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new ObjarttypExistsException("Objarttyp '" + objarttyp.getTypentext() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeObjarttyp(Objarttyp objarttyp) {
      log.debug("removing user: " + objarttyp);
      objarttypDao.remove(objarttyp);
  }

  /**
   * {@inheritDoc}
   */
  public void removeObjarttyp(String objarttypId) {
      log.debug("removing user: " + objarttypId);
      objarttypDao.remove(new Long(objarttypId));
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
  public List<Objarttyp> search(String searchTerm) {
      return super.search(searchTerm, Objarttyp.class);
  }

 
}
