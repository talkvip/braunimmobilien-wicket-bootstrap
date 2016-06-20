package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import braunimmobilien.dao.EigtstatusDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.EigtstatusManager;
import braunimmobilien.service.EigtstatusExistsException;
import braunimmobilien.model.Eigtstatus;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("eigtstatusManager")
public class EigtstatusManagerImpl extends GenericManagerImpl<Eigtstatus, Long> implements EigtstatusManager {

  private EigtstatusDao eigtstatusDao;

  @Autowired
  public void setEigtstatusDao(EigtstatusDao eigtstatusDao) {
      this.dao = eigtstatusDao;
      this.eigtstatusDao = eigtstatusDao;
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public Eigtstatus getEigtstatus(String eigtstatusId) {
      return eigtstatusDao.get(new Long(eigtstatusId));
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public List<Eigtstatus> getEigtstatuses() {
      return eigtstatusDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public Eigtstatus saveEigtstatus(Eigtstatus eigtstatus) throws EigtstatusExistsException {

    
         


      try {
          return eigtstatusDao.saveEigtstatus(eigtstatus);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new EigtstatusExistsException("Eigtstatus '" + eigtstatus.getEigt_status_text() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new EigtstatusExistsException("Eigtstatus '" + eigtstatus.getEigt_status_text() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public void removeEigtstatus(Eigtstatus eigtstatus) {
      log.debug("removing user: " + eigtstatus);
      eigtstatusDao.remove(eigtstatus);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public void removeEigtstatus(String eigtstatusId) {
      log.debug("removing user: " + eigtstatusId);
      eigtstatusDao.remove(new Long(eigtstatusId));
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
  @Transactional
  public List<Eigtstatus> search(String searchTerm) {
      return super.search(searchTerm, Eigtstatus.class);
  }

 
}
