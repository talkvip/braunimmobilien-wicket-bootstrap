package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.TelefonartDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.TelefonartManager;
import braunimmobilien.service.TelefonartExistsException;
import braunimmobilien.model.Telefonart;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("telefonartManager")
public class TelefonartManagerImpl extends GenericManagerImpl<Telefonart, Long> implements TelefonartManager {

  private TelefonartDao telefonartDao;

  @Autowired
  public void setTelefonartDao(TelefonartDao telefonartDao) {
      this.dao = telefonartDao;
      this.telefonartDao = telefonartDao;
  }

  /**
   * {@inheritDoc}
   */
  public Telefonart getTelefonart(String telefonartId) {
      return telefonartDao.get(new Long(telefonartId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Telefonart> getTelefonartes() {
      return telefonartDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Telefonart saveTelefonart(Telefonart telefonart) throws TelefonartExistsException {

    
         


      try {
          return telefonartDao.saveTelefonart(telefonart);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new TelefonartExistsException("Telefonart '" + telefonart.getEn() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new TelefonartExistsException("Telefonart '" + telefonart.getEn() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeTelefonart(Telefonart telefonart) {
      log.debug("removing user: " + telefonart);
      telefonartDao.remove(telefonart);
  }

  /**
   * {@inheritDoc}
   */
  public void removeTelefonart(String telefonartId) {
      log.debug("removing user: " + telefonartId);
      telefonartDao.remove(new Long(telefonartId));
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
  public List<Telefonart> search(String searchTerm) {
      return super.search(searchTerm, Telefonart.class);
  }

 
}
