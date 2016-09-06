package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.LinksDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.LinksManager;
import braunimmobilien.service.LinksExistsException;
import braunimmobilien.model.Links;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("linksManager")
public class LinksManagerImpl extends GenericManagerImpl<Links, Long> implements LinksManager {

  private LinksDao linksDao;

  @Autowired
  public void setLinksDao(LinksDao linksDao) {
      this.dao = linksDao;
      this.linksDao = linksDao;
  }

  /**
   * {@inheritDoc}
   */
  public Links getLinks(String linksId) {
      return linksDao.get(new Long(linksId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Links> getLinkses() {
      return linksDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Links saveLinks(Links links) throws LinksExistsException {

    
         


      try {
          return linksDao.saveLinks(links);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new LinksExistsException("Links '" + links.getBeschreibung() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new LinksExistsException("Links '" + links.getBeschreibung() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeLinks(Links links) {
      log.debug("removing user: " + links);
      linksDao.remove(links);
  }

  /**
   * {@inheritDoc}
   */
  public void removeLinks(String linksId) {
      log.debug("removing user: " + linksId);
      linksDao.remove(new Long(linksId));
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
  public List<Links> search(String searchTerm) {
      return super.search(searchTerm, Links.class);
  }

 
}
