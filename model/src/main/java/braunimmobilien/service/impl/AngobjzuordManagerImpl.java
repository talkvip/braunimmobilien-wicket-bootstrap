package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.AngobjzuordDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.AngobjzuordManager;
import braunimmobilien.service.AngobjzuordExistsException;
import braunimmobilien.model.Angobjzuord;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("angobjzuordManager")
public class AngobjzuordManagerImpl extends GenericManagerImpl<Angobjzuord, Long> implements AngobjzuordManager {

  private AngobjzuordDao angobjzuordDao;

  @Autowired
  public void setAngobjzuordDao(AngobjzuordDao angobjzuordDao) {
      this.dao = angobjzuordDao;
      this.angobjzuordDao = angobjzuordDao;
  }

  /**
   * {@inheritDoc}
   */
  public Angobjzuord getAngobjzuord(String angobjzuordId) {
      return angobjzuordDao.get(new Long(angobjzuordId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Angobjzuord> getAngobjzuordes(String objid) {
      return angobjzuordDao.getAngobjzuordes(objid);
  }

  /**
   * {@inheritDoc}
   */
  public Angobjzuord saveAngobjzuord(Angobjzuord angobjzuord) throws AngobjzuordExistsException {

    
         


      try {
          return angobjzuordDao.saveAngobjzuord(angobjzuord);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new AngobjzuordExistsException("Angobjzuord '" + angobjzuord.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new AngobjzuordExistsException("Angobjzuord '" + angobjzuord.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeAngobjzuord(Angobjzuord angobjzuord) {
      log.debug("removing user: " + angobjzuord);
      angobjzuordDao.remove(angobjzuord);
  }

  /**
   * {@inheritDoc}
   */
  public void removeAngobjzuord(String angobjzuordId) {
      log.debug("removing user: " + angobjzuordId);
      angobjzuordDao.remove(new Long(angobjzuordId));
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
  public List<Angobjzuord> search(String searchTerm) {
      return super.search(searchTerm, Angobjzuord.class);
  }

 
}
