package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.EigentuemermusterDao;
import braunimmobilien.service.EigentuemermusterManager;
import braunimmobilien.service.EigentuemermusterExistsException;
import braunimmobilien.model.Eigentuemermuster;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("eigentuemermusterManager")
public class EigentuemermusterManagerImpl extends GenericManagerImpl<Eigentuemermuster, Long> implements EigentuemermusterManager {

  private EigentuemermusterDao eigentuemermusterDao;

  @Autowired
  public void setEigentuemermusterDao(EigentuemermusterDao eigentuemermusterDao) {
      this.dao = eigentuemermusterDao;
      this.eigentuemermusterDao = eigentuemermusterDao;
  }

  /**
   * {@inheritDoc}
   */
  public Eigentuemermuster getEigentuemermuster(String eigentuemermusterId) {
      return eigentuemermusterDao.get(new Long(eigentuemermusterId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Eigentuemermuster> getEigentuemermusters() {
      return eigentuemermusterDao.getEigentuemermusters();
  }

  /**
   * {@inheritDoc}
   */
  public Eigentuemermuster saveEigentuemermuster(Eigentuemermuster eigentuemermuster) throws EigentuemermusterExistsException {

    
         


      try {
          return eigentuemermusterDao.saveEigentuemermuster(eigentuemermuster);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new EigentuemermusterExistsException("Eigentuemermuster '" + eigentuemermuster.getEigentuemermuster() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new EigentuemermusterExistsException("Eigentuemermuster '" + eigentuemermuster.getEigentuemermuster() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeEigentuemermuster(Eigentuemermuster eigentuemermuster) {
      log.debug("removing user: " + eigentuemermuster);
      eigentuemermusterDao.remove(eigentuemermuster);
  }

  /**
   * {@inheritDoc}
   */
  public void removeEigentuemermuster(String eigentuemermusterId) {
      log.debug("removing eigentuemermuster: " + eigentuemermusterId);
      eigentuemermusterDao.remove(new Long(eigentuemermusterId));
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
  public List<Eigentuemermuster> search(String searchTerm) {
      return super.search(searchTerm, Eigentuemermuster.class);
  }

 
}
