package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.StatusDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.StatusManager;
import braunimmobilien.service.StatusExistsException;
import braunimmobilien.model.Status;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("statusManager")
public class StatusManagerImpl extends GenericManagerImpl<Status, Long> implements StatusManager {

  private StatusDao statusDao;

  @Autowired
  public void setStatusDao(StatusDao statusDao) {
      this.dao = statusDao;
      this.statusDao = statusDao;
  }

  /**
   * {@inheritDoc}
   */
  public Status getStatus(String statusId) {
      return statusDao.get(new Long(statusId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Status> getStatuses() {
      return statusDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Status saveStatus(Status status) throws StatusExistsException {

    
         


      try {
          return statusDao.saveStatus(status);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new StatusExistsException("Status '" + status.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new StatusExistsException("Status '" + status.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeStatus(Status status) {
      log.debug("removing status: " + status);
      statusDao.remove(status);
  }

  /**
   * {@inheritDoc}
   */
  public void removeStatus(String statusId) {
      log.debug("removing status: " + statusId);
      statusDao.remove(new Long(statusId));
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
  public List<Status> search(String searchTerm) {
      return super.search(searchTerm, Status.class);
  }

 
}
