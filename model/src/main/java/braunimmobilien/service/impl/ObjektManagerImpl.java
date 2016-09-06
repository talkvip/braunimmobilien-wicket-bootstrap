package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.ObjektDao;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.ObjektExistsException;
import braunimmobilien.model.Objekte;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("objektManager")
public class ObjektManagerImpl extends GenericManagerImpl<Objekte, Long> implements ObjektManager {

  private ObjektDao objektDao;

  @Autowired
  public void setObjektDao(ObjektDao objektDao) {
      this.dao = objektDao;
      this.objektDao = objektDao;
  }

  /**
   * {@inheritDoc}
   */
  public Objekte getObjekt(Long objektId) {
      return objektDao.get(objektId);
  }

  /**
   * {@inheritDoc}
   */
  public List<Objekte> getObjekteen(String strid) {
      return objektDao.getObjektes(strid);
  }

  /**
   * {@inheritDoc}
   */
  public Objekte saveObjekt(Objekte objekt) throws ObjektExistsException {

    
         


      try {
          return objektDao.saveObjekt(objekt);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new ObjektExistsException("Objekte '" + objekt.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new ObjektExistsException("Objekte '" + objekt.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeObjekt(Objekte objekt) {
      log.debug("removing user: " + objekt);
      objektDao.remove(objekt);
  }

  /**
   * {@inheritDoc}
   */
  public void removeObjekt(Long objektId) {
      log.debug("removing user: " + objektId);
      objektDao.remove(objektId);
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
  public List<Objekte> search(String searchTerm) {
      return super.search(searchTerm, Objekte.class);
  }
  public List<Objekte> getObjekteNachObjektsuch (Long objektsuchid){
	  
	  return objektDao.getObjekteNachObjektsuch(objektsuchid);
	  
  }
	
  public List<Objekte> getObjekteNachVorlagedatum (java.util.Date beginDate,java.util.Date endDate){
	  return objektDao.getObjekteNachVorlagedatum(beginDate, endDate);
			  }

 
}
