package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.OrteDao;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.OrteExistsException;
import braunimmobilien.model.Orte;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("orteManager")
public class OrteManagerImpl extends GenericManagerImpl<Orte, Long> implements OrteManager {

  private OrteDao orteDao;

  @Autowired
  public void setOrteDao(OrteDao orteDao) {
      this.dao = orteDao;
      this.orteDao = orteDao;
  }

  /**
   * {@inheritDoc}
   */
  public Orte getOrte(Long orteId) {
      return orteDao.get(orteId);
  }

  /**
   * {@inheritDoc}
   */
  public List<Orte> getOrtees(String landid) {
      return orteDao.getOrtes(landid);
  }

  /**
   * {@inheritDoc}
   */
  public Orte saveOrte(Orte orte) throws OrteExistsException {

    
         


      try {
          return orteDao.saveOrte(orte);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new OrteExistsException("Orte '" + orte.getOrtname() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new OrteExistsException("Orte '" + orte.getOrtname() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeOrte(Orte orte) {
      log.debug("removing ort: " + orte);
      orteDao.remove(orte);
  }

  /**
   * {@inheritDoc}
   */
  public void removeOrte(String orteId) {
      log.debug("removing ort: " + orteId);
      orteDao.remove(new Long(orteId));
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
  public List<Orte> search(String searchTerm) {
      return super.search(searchTerm, Orte.class);
  }

 
}
