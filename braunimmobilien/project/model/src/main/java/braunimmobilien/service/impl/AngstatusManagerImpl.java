package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.AngstatusDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.AngstatusManager;
import braunimmobilien.service.AngstatusExistsException;
import braunimmobilien.model.Angstatus;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("angstatusManager")
public class AngstatusManagerImpl extends GenericManagerImpl<Angstatus, Long> implements AngstatusManager {

  private AngstatusDao angstatusDao;

  @Autowired
  public void setAngstatusDao(AngstatusDao angstatusDao) {
      this.dao = angstatusDao;
      this.angstatusDao = angstatusDao;
  }

  /**
   * {@inheritDoc}
   */
  public Angstatus getAngstatus(String angstatusId) {
      return angstatusDao.get(new Long(angstatusId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Angstatus> getAngstatuses() {
      return angstatusDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Angstatus saveAngstatus(Angstatus angstatus) throws AngstatusExistsException {

    
         


      try {
          return angstatusDao.saveAngstatus(angstatus);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new AngstatusExistsException("Angstatus '" + angstatus.getStatustext() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new AngstatusExistsException("Angstatus '" + angstatus.getStatustext() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeAngstatus(Angstatus angstatus) {
      log.debug("removing user: " + angstatus);
      angstatusDao.remove(angstatus);
  }

  /**
   * {@inheritDoc}
   */
  public void removeAngstatus(String angstatusId) {
      log.debug("removing user: " + angstatusId);
      angstatusDao.remove(new Long(angstatusId));
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
  public List<Angstatus> search(String searchTerm) {
      return super.search(searchTerm, Angstatus.class);
  }

 
}
