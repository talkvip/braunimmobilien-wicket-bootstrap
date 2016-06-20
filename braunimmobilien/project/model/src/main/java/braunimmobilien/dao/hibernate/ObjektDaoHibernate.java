package braunimmobilien.dao.hibernate;


import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.ObjektDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * @author rjansen
 * 
 */
@Repository("objektDao")
public class ObjektDaoHibernate extends GenericDaoHibernate<Objekte, Long> implements ObjektDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public ObjektDaoHibernate() {
        super(Objekte.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Objekte> getObjektes(String strid) {
        Query qry = getSession().createQuery("from Objekte u where objstrasseid="+strid);
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Objekte saveObjekt(Objekte objekt) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + objekt.getId());
        }
        getSession().saveOrUpdate(objekt);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return objekt;
    }

    /**
     * Overridden simply to call the saveUser method. This is happening
     * because saveUser flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public Objekte save(Objekte objekt) {
        return this.saveObjekt(objekt);
    }
  public  List<Objekte> getObjekteNachObjektsuch (Long objektsuchid){
    	 Query qry = getSession().createQuery("from Objekte u where obkeinkontakt="+objektsuchid);
         return qry.list();
    }
	public  List<Objekte> getObjekteNachVorlagedatum (java.util.Date beginDate,java.util.Date endDate){
		  Query qry = getSession().createQuery("from Objekte u where u.objvorlage is not null and u.objvorlage >= :startDate and u.objvorlage <= :endDate");
		  qry.setParameter("startDate", beginDate);
		  qry.setParameter("endDate", endDate);
		  return qry.list();
	  }
		
   
 
}
