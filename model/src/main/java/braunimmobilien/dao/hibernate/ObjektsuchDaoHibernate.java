package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.ObjektsuchDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Objektsuch;
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
@Repository("objektsuchDao")
public class ObjektsuchDaoHibernate extends GenericDaoHibernate<Objektsuch, Long> implements ObjektsuchDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public ObjektsuchDaoHibernate() {
        super(Objektsuch.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Objektsuch> getObjektsuchs() {
        Query qry = getSession().createQuery("from Objektsuch u order by upper(u.suchtext)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Objektsuch saveObjektsuch(Objektsuch objektsuch) {
        if (log.isDebugEnabled()) {
            log.debug("objektsuch's id: " + objektsuch.getId());
        }
        getSession().saveOrUpdate(objektsuch);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return objektsuch;
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
    public Objektsuch save(Objektsuch objektsuch) {
        return this.saveObjektsuch(objektsuch);
    }

   
 
}
