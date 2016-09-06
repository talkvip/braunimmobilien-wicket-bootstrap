package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.AngebotDao;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.AngebotExistsException;
import braunimmobilien.model.Angebot;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("angebotManager")
public class AngebotManagerImpl extends GenericManagerImpl<Angebot, String> implements AngebotManager {

  private AngebotDao angebotDao;

  @Autowired
  public void setAngebotDao(AngebotDao angebotDao) {
      this.dao = angebotDao;
      this.angebotDao = angebotDao;
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public Angebot getAngebot(String angebotId) {
      return angebotDao.get(angebotId);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public List<Angebot> getAngebote() {
      return angebotDao.getAngebote();
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public Angebot saveAngebot(Angebot angebot) throws AngebotExistsException {

    
         


      try {
          return angebotDao.saveAngebot(angebot);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new AngebotExistsException("Angebot '" + angebot.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new AngebotExistsException("Angebot '" + angebot.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public void removeAngebot(Angebot angebot) {
      log.debug("removing user: " + angebot);
      angebotDao.remove(angebot);
  }

  /**
   * {@inheritDoc}
   */
  @Transactional
  public void removeAngebot(String angebotId) {
      log.debug("removing angebot: " + angebotId);
      angebotDao.remove(angebotId);
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
  public List<Angebot> search(String searchTerm) {
      return super.search(searchTerm, Angebot.class);
  }

 
}
