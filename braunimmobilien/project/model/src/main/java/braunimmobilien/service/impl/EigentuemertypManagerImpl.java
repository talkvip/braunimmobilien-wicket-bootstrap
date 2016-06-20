package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.EigentuemertypDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.EigentuemertypManager;
import braunimmobilien.service.EigentuemertypExistsException;
import braunimmobilien.model.Eigentuemertyp;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("eigentuemertypManager")
public class EigentuemertypManagerImpl extends GenericManagerImpl<Eigentuemertyp, Long> implements EigentuemertypManager {

  private EigentuemertypDao eigentuemertypDao;

  @Autowired
  public void setEigentuemertypDao(EigentuemertypDao eigentuemertypDao) {
      this.dao = eigentuemertypDao;
      this.eigentuemertypDao = eigentuemertypDao;
  }

  /**
   * {@inheritDoc}
   */
  public Eigentuemertyp getEigentuemertyp(String eigentuemertypId) {
      return eigentuemertypDao.get(new Long(eigentuemertypId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Eigentuemertyp> getEigentuemertypes() {
      return eigentuemertypDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Eigentuemertyp saveEigentuemertyp(Eigentuemertyp eigentuemertyp) throws EigentuemertypExistsException {

    
         


      try {
          return eigentuemertypDao.saveEigentuemertyp(eigentuemertyp);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new EigentuemertypExistsException("Eigentuemertyp '" + eigentuemertyp.getTypenbeschreibung() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new EigentuemertypExistsException("Eigentuemertyp '" + eigentuemertyp.getTypenbeschreibung() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeEigentuemertyp(Eigentuemertyp eigentuemertyp) {
      log.debug("removing user: " + eigentuemertyp);
      eigentuemertypDao.remove(eigentuemertyp);
  }

  /**
   * {@inheritDoc}
   */
  public void removeEigentuemertyp(String eigentuemertypId) {
      log.debug("removing user: " + eigentuemertypId);
      eigentuemertypDao.remove(new Long(eigentuemertypId));
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
  public List<Eigentuemertyp> search(String searchTerm) {
      return super.search(searchTerm, Eigentuemertyp.class);
  }

 
}
