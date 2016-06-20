package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.KonditionDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.KonditionManager;
import braunimmobilien.service.KonditionExistsException;
import braunimmobilien.model.Kondition;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("konditionManager")
public class KonditionManagerImpl extends GenericManagerImpl<Kondition, String> implements KonditionManager {

  private KonditionDao konditionDao;

  @Autowired
  public void setKonditionDao(KonditionDao konditionDao) {
      this.dao = konditionDao;
      this.konditionDao = konditionDao;
  }

  /**
   * {@inheritDoc}
   */
  public Kondition getKondition(String konditionId) {
      return konditionDao.get(konditionId);
  }

  /**
   * {@inheritDoc}
   */
  public List<Kondition> getKonditionen() {
      return konditionDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Kondition saveKondition(Kondition kondition) throws KonditionExistsException {

    
         


      try {
          return konditionDao.saveKondition(kondition);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new KonditionExistsException("Kondition '" + kondition.getKontext() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new KonditionExistsException("Kondition '" + kondition.getKontext() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeKondition(Kondition kondition) {
      log.debug("removing user: " + kondition);
      konditionDao.remove(kondition);
  }

  /**
   * {@inheritDoc}
   */
  public void removeKondition(String konditionId) {
      log.debug("removing user: " + konditionId);
      konditionDao.remove(konditionId);
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
  public List<Kondition> search(String searchTerm) {
      return super.search(searchTerm, Kondition.class);
  }

 
}
