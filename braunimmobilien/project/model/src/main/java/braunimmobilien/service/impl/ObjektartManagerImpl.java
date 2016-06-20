package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.ObjektartDao;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.ObjektartExistsException;
import braunimmobilien.model.Objektart;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("objektartManager")
public class ObjektartManagerImpl extends GenericManagerImpl<Objektart, Long> implements ObjektartManager {

  private ObjektartDao objektartDao;

  @Autowired
  public void setObjektartDao(ObjektartDao objektartDao) {
      this.dao = objektartDao;
      this.objektartDao = objektartDao;
  }

  /**
   * {@inheritDoc}
   */
  public Objektart getObjektart(Long objektartId) {
      return objektartDao.get(objektartId);
  }

  /**
   * {@inheritDoc}
   */
  public List<Objektart> getObjektartes() {
      return objektartDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Objektart saveObjektart(Objektart objektart) throws ObjektartExistsException {

    
         


      try {
          return objektartDao.saveObjektart(objektart);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new ObjektartExistsException("Objektart '" + objektart.getObjartname() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new ObjektartExistsException("Objektart '" + objektart.getObjartname() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeObjektart(Objektart objektart) {
      log.debug("removing user: " + objektart);
      objektartDao.remove(objektart);
  }

  /**
   * {@inheritDoc}
   */
  public void removeObjektart(Long objektartId) {
      log.debug("removing user: " + objektartId);
      objektartDao.remove(objektartId);
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
  public List<Objektart> search(String searchTerm) {
      return super.search(searchTerm, Objektart.class);
  }

 
}
