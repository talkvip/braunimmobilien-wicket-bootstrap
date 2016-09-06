package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.LandDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.LandManager;
import braunimmobilien.service.LandExistsException;
import braunimmobilien.model.Land;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("landManager")
public class LandManagerImpl extends GenericManagerImpl<Land, Long> implements LandManager {

  private LandDao landDao;

  @Autowired
  public void setLandDao(LandDao landDao) {
      this.dao = landDao;
      this.landDao = landDao;
  }

  /**
   * {@inheritDoc}
   */
  public Land getLand(String landId) {
      return landDao.get(new Long(landId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Land> getLandes() {
      return landDao.getLands();
  }

  /**
   * {@inheritDoc}
   */
  public Land saveLand(Land land) throws LandExistsException {

    
         


      try {
          return landDao.saveLand(land);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new LandExistsException("Land '" + land.getLandname() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new LandExistsException("Land '" + land.getLandname() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeLand(Land land) {
      log.debug("removing user: " + land);
      landDao.remove(land);
  }

  /**
   * {@inheritDoc}
   */
  public void removeLand(String landId) {
      log.debug("removing user: " + landId);
      landDao.remove(new Long(landId));
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
  public List<Land> search(String searchTerm) {
      return super.search(searchTerm, Land.class);
  }

 
}
