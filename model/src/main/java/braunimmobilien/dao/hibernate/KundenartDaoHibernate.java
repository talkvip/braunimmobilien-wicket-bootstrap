package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.KundenartDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Kundenart;
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
@Repository("kundenartDao")
public class KundenartDaoHibernate extends GenericDaoHibernate<Kundenart, Long> implements KundenartDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public KundenartDaoHibernate() {
        super(Kundenart.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Kundenart> getKundenartes() {
        Query qry = getSession().createQuery("from Kundenart u order by upper(u.kundenart)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Kundenart saveKundenart(Kundenart kundenart) {
        if (log.isDebugEnabled()) {
            log.debug("kundenart's id: " + kundenart.getId());
        }
        getSession().saveOrUpdate(kundenart);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return kundenart;
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
    public Kundenart save(Kundenart kundenart) {
        return this.saveKundenart(kundenart);
    }

   
 
}
