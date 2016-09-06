package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.NachweiseDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.NachweiseExistsException;
import braunimmobilien.model.Nachweise;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("nachweiseManager")
public class NachweiseManagerImpl extends GenericManagerImpl<Nachweise, Long> implements NachweiseManager {

  private NachweiseDao nachweiseDao;


  @Autowired
  public void setNachweiseDao(NachweiseDao nachweiseDao) {
      this.dao = nachweiseDao;
      this.nachweiseDao = nachweiseDao;
  }
  
  /**
   * {@inheritDoc}
   */
  public Nachweise getNachweise(String nachweiseId) {
      return nachweiseDao.get(new Long(nachweiseId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Nachweise> getNachweisees() {
      return nachweiseDao.getAllDistinct();
  }
  
  public List<Nachweise> getNachweiseNachObjekte(String objektid) {
      return nachweiseDao.getNachweiseNachObjekte(new Long(objektid));
  }

  /**
   * {@inheritDoc}
   */
  public Nachweise saveNachweise(Nachweise nachweise) throws NachweiseExistsException {
		
      try {
    	  if(nachweise.getId()==null){
    		  System.err.println("xxxxxxxxxxxxxxxxxx Id=null");
    		  
    		  if(nachweise.getMitarbeiter()!=null) {
    			}
    			
    		if(nachweise.getKunde()!=null) {
    				System.err.println("xxxxxxxxxxxxxxxxxx Kunde");
//    				(nachweise.getKunde()).addNachweis(nachweise);	
    			}
    			if(nachweise.getXtyp()!=null) {
    			}
    			
    				if(nachweise.getAngebot()!=null) {
    					nachweise.getAngebot().addNachweis(nachweise);
    			System.err.println("xxxxxxxxxxxxxxxxxx Angebot");
    				}
    				
    				if(nachweise.getAngebot1()!=null) {
    					nachweise.getAngebot1().addNachweis1(nachweise);
    				}
    				
    				if(nachweise.getAngebot2()!=null) {
    				nachweise.getAngebot2().addNachweis2(nachweise);
    				}
    				
    				
    				if(nachweise.getObjekt()!=null) {
    					nachweise.getObjekt().addNachweise(nachweise);
    				}
    				
    				if(nachweise.getPerson()!=null) {
    					nachweise.getPerson().addNachweis(nachweise);
    				}	  
    	  }
          return nachweiseDao.saveNachweise(nachweise);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new NachweiseExistsException("Nachweise '" + nachweise.getId() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new NachweiseExistsException("Nachweise '" + nachweise.getId() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeNachweise(Nachweise nachweise) {
      log.debug("removing nachweise: " + nachweise);
      nachweiseDao.remove(nachweise);
  }

  /**
   * {@inheritDoc}
   */
  public void removeNachweise(String nachweiseId) {
      log.debug("removing nachweise: " + nachweiseId);
      nachweiseDao.remove(new Long(nachweiseId));
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
  public List<Nachweise> search(String searchTerm) {
      return super.search(searchTerm, Nachweise.class);
  }

 
}
