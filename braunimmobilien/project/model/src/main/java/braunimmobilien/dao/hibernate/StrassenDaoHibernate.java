package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.StrassenDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Strassen;
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
@Repository("strassenDao")
public class StrassenDaoHibernate extends GenericDaoHibernate<Strassen, Long> implements StrassenDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public StrassenDaoHibernate() {
        super(Strassen.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Strassen> getStrassens(String ortid) {
        Query qry = getSession().createQuery("from Strassen u where ortid="+ortid+" order by upper(u.strname)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Strassen saveStrassen(Strassen strassen) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + strassen.getId());
        }
        getSession().saveOrUpdate(strassen);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return strassen;
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
    public Strassen save(Strassen strassen) {
        return this.saveStrassen(strassen);
    }

   
 
}