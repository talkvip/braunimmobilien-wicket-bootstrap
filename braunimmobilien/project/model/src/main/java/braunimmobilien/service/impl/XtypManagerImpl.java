package braunimmobilien.service.impl;

	import java.util.Collection;
	import java.util.List;

	import javax.jws.WebService;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.DataIntegrityViolationException;
	import org.springframework.orm.jpa.JpaSystemException;
	import org.springframework.stereotype.Service;

	import braunimmobilien.dao.XtypDao;
	import braunimmobilien.service.XtypManager;
	import braunimmobilien.service.XtypExistsException;
	import braunimmobilien.model.Xtyp;

	/**
	 * Implementation of Manager to handle Status data.
	 * 
	 * @author rjansen
	 */
	@Service("xtypManager")
	public class XtypManagerImpl extends GenericManagerImpl<Xtyp, Long> implements XtypManager {

	  private XtypDao xtypDao;

	  @Autowired
	  public void setXtypDao(XtypDao xtypDao) {
	      this.dao = xtypDao;
	      this.xtypDao = xtypDao;
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public Xtyp getXtyp(String xtypId) {
	      return xtypDao.get(new Long(xtypId));
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public List<Xtyp> getXtyps() {
	      return xtypDao.getAllDistinct();
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public Xtyp saveXtyp(Xtyp xtyp) throws XtypExistsException {

	    
	         


	      try {
	          return xtypDao.saveXtyp(xtyp);
	      } catch (DataIntegrityViolationException e) {
	          //e.printStackTrace();
	          log.warn(e.getMessage());
	          throw new XtypExistsException("Xtyp '" + xtyp.getXtypkuerzel() + "' already exists!");
	      } catch (JpaSystemException e) { // needed for JPA
	          //e.printStackTrace();
	          log.warn(e.getMessage());
	          throw new XtypExistsException("Xtyp '" + xtyp.getXtypkuerzel() + "' already exists!");
	      }
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public void removeXtyp(Xtyp xtyp) {
	      log.debug("removing xtyp: " + xtyp);
	      xtypDao.remove(xtyp);
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public void removeXtyp(String xtypId) {
	      log.debug("removing xtyp: " + xtypId);
	      xtypDao.remove(new Long(xtypId));
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
	  public List<Xtyp> search(String searchTerm) {
	      return super.search(searchTerm, Xtyp.class);
	  }

	 
	}
