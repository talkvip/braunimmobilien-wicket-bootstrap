package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.KonditionDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Kondition;
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
@Repository("konditionDao")
public class KonditionDaoHibernate extends GenericDaoHibernate<Kondition, String> implements KonditionDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public KonditionDaoHibernate() {
        super(Kondition.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Kondition> getKonditionen() {
        Query qry = getSession().createQuery("from Kondition u order by upper(u.statustext)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Kondition saveKondition(Kondition kondition) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + kondition.getId());
        }
        getSession().saveOrUpdate(kondition);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return kondition;
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
    public Kondition save(Kondition kondition) {
        return this.saveKondition(kondition);
    }

   
 
}
