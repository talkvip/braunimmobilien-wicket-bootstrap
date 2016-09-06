package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.TelefonartDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Telefonart;
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
@Repository("telefonartDao")
public class TelefonartDaoHibernate extends GenericDaoHibernate<Telefonart, Long> implements TelefonartDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public TelefonartDaoHibernate() {
        super(Telefonart.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Telefonart> getTelefonartes() {
        Query qry = getSession().createQuery("from Telefonart u order by upper(u.en)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Telefonart saveTelefonart(Telefonart telefonart) {
        if (log.isDebugEnabled()) {
            log.debug("telefonart's id: " + telefonart.getId());
        }
        getSession().saveOrUpdate(telefonart);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return telefonart;
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
    public Telefonart save(Telefonart telefonart) {
        return this.saveTelefonart(telefonart);
    }

   
 
}
