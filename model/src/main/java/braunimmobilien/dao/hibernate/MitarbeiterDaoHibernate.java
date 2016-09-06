package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.MitarbeiterDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Mitarbeiter;
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
@Repository("mitarbeiterDao")
public class MitarbeiterDaoHibernate extends GenericDaoHibernate<Mitarbeiter, Long> implements MitarbeiterDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public MitarbeiterDaoHibernate() {
        super(Mitarbeiter.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Mitarbeiter> getMitarbeiteres() {
        Query qry = getSession().createQuery("from Mitrumpf u");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Mitarbeiter saveMitarbeiter(Mitarbeiter mitarbeiter) {
        if (log.isDebugEnabled()) {
            log.debug("mitarbeiter's id: " + mitarbeiter.getId());
        }
        getSession().saveOrUpdate(mitarbeiter);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return mitarbeiter;
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
    public Mitarbeiter save(Mitarbeiter mitarbeiter) {
        return this.saveMitarbeiter(mitarbeiter);
    }

   
 
}
