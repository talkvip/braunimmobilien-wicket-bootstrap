package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.NachweiseDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Nachweise;
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
@Repository("nachweiseDao")
public class NachweiseDaoHibernate extends GenericDaoHibernate<Nachweise, Long> implements NachweiseDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public NachweiseDaoHibernate() {
        super(Nachweise.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Nachweise> getNachweisees() {
        Query qry = getSession().createQuery("from Nachweise u order by u.nachdatum");
        return qry.list();
    }

    
    public List<Nachweise> getNachweiseNachObjekte (Long objektid){
    	 Query qry = getSession().createQuery("from Nachweise u where nachobjnr="+objektid+" order by u.nachdatum");
         return qry.list();
    }
    
    /**
     * {@inheritDoc}
     */
    public Nachweise saveNachweise(Nachweise nachweise) {
        if (log.isDebugEnabled()) {
            log.debug("nachweises's id: " + nachweise.getId());
        }
        getSession().saveOrUpdate(nachweise);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return nachweise;
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
    public Nachweise save(Nachweise nachweise) {
        return this.saveNachweise(nachweise);
    }

   
 
}
