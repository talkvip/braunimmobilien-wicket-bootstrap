package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.ScoutDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.ScoutExistsException;
import braunimmobilien.model.Scout;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("scoutManager")
public class ScoutManagerImpl extends GenericManagerImpl<Scout, Long> implements ScoutManager {

  private ScoutDao scoutDao;

  @Autowired
  public void setScoutDao(ScoutDao scoutDao) {
      this.dao = scoutDao;
      this.scoutDao = scoutDao;
  }

  /**
   * {@inheritDoc}
   */
  public Scout getScout(String scoutId) {
      return scoutDao.get(new Long(scoutId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Scout> getScoutes() {
      return scoutDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Scout saveScout(Scout scout) throws ScoutExistsException {

    
         


      try {
          return scoutDao.saveScout(scout);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new ScoutExistsException("Scout '" + scout.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new ScoutExistsException("Scout '" + scout.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeScout(Scout scout) {
      log.debug("removing user: " + scout);
      scoutDao.remove(scout);
  }

  /**
   * {@inheritDoc}
   */
  public void removeScout(String scoutId) {
      log.debug("removing user: " + scoutId);
      scoutDao.remove(new Long(scoutId));
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
  public List<Scout> search(String searchTerm) {
      return super.search(searchTerm, Scout.class);
  }

 
}
