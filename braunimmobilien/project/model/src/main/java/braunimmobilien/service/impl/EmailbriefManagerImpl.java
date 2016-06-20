package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.EmailbriefDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.EmailbriefManager;
import braunimmobilien.service.EmailbriefExistsException;
import braunimmobilien.model.Emailbrief;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("emailbriefManager")
public class EmailbriefManagerImpl extends GenericManagerImpl<Emailbrief, String> implements EmailbriefManager {

  private EmailbriefDao emailbriefDao;

  @Autowired
  public void setEmailbriefDao(EmailbriefDao emailbriefDao) {
      this.dao = emailbriefDao;
      this.emailbriefDao = emailbriefDao;
  }

  /**
   * {@inheritDoc}
   */
  public Emailbrief getEmailbrief(String emailbriefId) {
      return emailbriefDao.get(emailbriefId);
  }

  /**
   * {@inheritDoc}
   */
  public List<Emailbrief> getEmailbriefes() {
      return emailbriefDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Emailbrief saveEmailbrief(Emailbrief emailbrief) throws EmailbriefExistsException {

    
         


      try {
          return emailbriefDao.saveEmailbrief(emailbrief);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new EmailbriefExistsException("Emailbrief '" + emailbrief.getSubject() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new EmailbriefExistsException("Emailbrief '" + emailbrief.getSubject() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeEmailbrief(Emailbrief emailbrief) {
      log.debug("removing user: " + emailbrief);
      emailbriefDao.remove(emailbrief);
  }

  /**
   * {@inheritDoc}
   */
  public void removeEmailbrief(String emailbriefId) {
      log.debug("removing user: " + emailbriefId);
      emailbriefDao.remove(emailbriefId);
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
  public List<Emailbrief> search(String searchTerm) {
      return super.search(searchTerm, Emailbrief.class);
  }

 
}
