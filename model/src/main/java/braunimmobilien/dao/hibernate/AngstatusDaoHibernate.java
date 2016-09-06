package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.AngstatusDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Angstatus;
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
@Repository("angstatusDao")
public class AngstatusDaoHibernate extends GenericDaoHibernate<Angstatus, Long> implements AngstatusDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public AngstatusDaoHibernate() {
        super(Angstatus.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Angstatus> getAngstatuses() {
        Query qry = getSession().createQuery("from Angstatus u order by upper(u.statustext)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Angstatus saveAngstatus(Angstatus angstatus) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + angstatus.getId());
        }
        getSession().saveOrUpdate(angstatus);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return angstatus;
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
    public Angstatus save(Angstatus angstatus) {
        return this.saveAngstatus(angstatus);
    }

   
 
}
