package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.OrteDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Orte;
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
@Repository("orteDao")
public class OrteDaoHibernate extends GenericDaoHibernate<Orte, Long> implements OrteDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public OrteDaoHibernate() {
        super(Orte.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Orte> getOrtes(String ortid) {
        Query qry = getSession().createQuery("from Orte u  where landid="+ortid+" order by upper(u.ortname)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Orte saveOrte(Orte orte) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + orte.getId());
        }
        getSession().saveOrUpdate(orte);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return orte;
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
    public Orte save(Orte orte) {
        return this.saveOrte(orte);
    }

   
 
}
