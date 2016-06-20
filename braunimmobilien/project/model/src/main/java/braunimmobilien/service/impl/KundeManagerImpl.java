package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.KundeDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.KundeExistsException;
import braunimmobilien.model.Kunde;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("kundeManager")
public class KundeManagerImpl extends GenericManagerImpl<Kunde, Long> implements KundeManager {

  private KundeDao kundeDao;

  @Autowired
  public void setKundeDao(KundeDao kundeDao) {
      this.dao = kundeDao;
      this.kundeDao = kundeDao;
  }

  /**
   * {@inheritDoc}
   */
  public Kunde getKunde(String kundeId) {
      return kundeDao.get(new Long(kundeId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Kunde> getKundees(String kundeigtnr) {
      return kundeDao.getKundees(kundeigtnr);
  }

  /**
   * {@inheritDoc}
   */
  public Kunde saveKunde(Kunde kunde) throws KundeExistsException {

    
         


      try {
          return kundeDao.saveKunde(kunde);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new KundeExistsException("Kunde '" + kunde.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new KundeExistsException("Kunde '" + kunde.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeKunde(Kunde kunde) {
      log.debug("removing user: " + kunde);
      kundeDao.remove(kunde);
  }

  /**
   * {@inheritDoc}
   */
  public void removeKunde(String kundeId) {
      log.debug("removing user: " + kundeId);
      kundeDao.remove(new Long(kundeId));
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
  public List<Kunde> search(String searchTerm) {
      return super.search(searchTerm, Kunde.class);
  }

 
}
