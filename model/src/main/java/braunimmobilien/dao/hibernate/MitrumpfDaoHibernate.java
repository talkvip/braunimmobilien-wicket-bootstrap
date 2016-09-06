package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.MitrumpfDao;
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
@Repository("mitrumpfDao")
public class MitrumpfDaoHibernate extends GenericDaoHibernate<Mitarbeiter, Long> implements MitrumpfDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public MitrumpfDaoHibernate() {
        super(Mitarbeiter.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Mitarbeiter> getMitrumpfes() {
        Query qry = getSession().createQuery("from Mitrumpf u order by upper(u.mitordner)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Mitarbeiter saveMitrumpf(Mitarbeiter mitrumpf) {
        if (log.isDebugEnabled()) {
            log.debug("mitrumpf's id: " + mitrumpf.getId());
        }
        getSession().saveOrUpdate(mitrumpf);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return mitrumpf;
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
    public Mitarbeiter save(Mitarbeiter mitrumpf) {
        return this.saveMitrumpf(mitrumpf);
    }

   
 
}
