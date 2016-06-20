package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.KundenartDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.KundenartManager;
import braunimmobilien.service.KundenartExistsException;
import braunimmobilien.model.Kundenart;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("kundenartManager")
public class KundenartManagerImpl extends GenericManagerImpl<Kundenart, Long> implements KundenartManager {

  private KundenartDao kundenartDao;

  @Autowired
  public void setKundenartDao(KundenartDao kundenartDao) {
      this.dao = kundenartDao;
      this.kundenartDao = kundenartDao;
  }

  /**
   * {@inheritDoc}
   */
  public Kundenart getKundenart(String kundenartId) {
      return kundenartDao.get(new Long(kundenartId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Kundenart> getKundenartes() {
      return kundenartDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Kundenart saveKundenart(Kundenart kundenart) throws KundenartExistsException {

    
         


      try {
          return kundenartDao.saveKundenart(kundenart);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new KundenartExistsException("Kundenart '" + kundenart.getKundenart() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new KundenartExistsException("Kundenart '" + kundenart.getKundenart() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeKundenart(Kundenart kundenart) {
      log.debug("removing user: " + kundenart);
      kundenartDao.remove(kundenart);
  }

  /**
   * {@inheritDoc}
   */
  public void removeKundenart(String kundenartId) {
      log.debug("removing user: " + kundenartId);
      kundenartDao.remove(new Long(kundenartId));
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
  public List<Kundenart> search(String searchTerm) {
      return super.search(searchTerm, Kundenart.class);
  }

 
}
