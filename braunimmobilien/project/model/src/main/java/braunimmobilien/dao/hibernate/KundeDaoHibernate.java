package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.KundeDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Kunde;
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
@Repository("kundeDao")
public class KundeDaoHibernate extends GenericDaoHibernate<Kunde, Long> implements KundeDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public KundeDaoHibernate() {
        super(Kunde.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Kunde> getKundees(String kundeigtnr) {
        Query qry = getSession().createQuery("from Kunde u where kundeigtnr="+kundeigtnr+" order by kundennr");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Kunde saveKunde(Kunde kunde) {
        if (log.isDebugEnabled()) {
            log.debug("kunde's id: " + kunde.getId());
        }
        getSession().saveOrUpdate(kunde);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return kunde;
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
    public Kunde save(Kunde kunde) {
        return this.saveKunde(kunde);
    }

   
 
}
