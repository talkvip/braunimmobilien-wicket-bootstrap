package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.StatusDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Status;
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
@Repository("statusDao")
public class StatusDaoHibernate extends GenericDaoHibernate<Status, Long> implements StatusDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public StatusDaoHibernate() {
        super(Status.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Status> getStatuss() {
        Query qry = getSession().createQuery("from Status u order by upper(u.statustext)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Status saveStatus(Status status) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + status.getId());
        }
        getSession().saveOrUpdate(status);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return status;
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
    public Status save(Status status) {
        return this.saveStatus(status);
    }

   
 
}
