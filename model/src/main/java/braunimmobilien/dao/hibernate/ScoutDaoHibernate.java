package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.ScoutDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Scout;
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
@Repository("scoutDao")
public class ScoutDaoHibernate extends GenericDaoHibernate<Scout, Long> implements ScoutDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public ScoutDaoHibernate() {
        super(Scout.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Scout> getScoutes() {
        Query qry = getSession().createQuery("from Scout u order by u.id");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Scout saveScout(Scout scout) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + scout.getId());
        }
        getSession().saveOrUpdate(scout);
       
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
     
        return scout;
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
    public Scout save(Scout scout) {
        return this.saveScout(scout);
    }

   
 
}
