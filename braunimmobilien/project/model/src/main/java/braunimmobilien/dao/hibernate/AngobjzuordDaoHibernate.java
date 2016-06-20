package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.AngobjzuordDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Angobjzuord;
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
@Repository("angobjzuordDao")
public class AngobjzuordDaoHibernate extends GenericDaoHibernate<Angobjzuord, Long> implements AngobjzuordDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public AngobjzuordDaoHibernate() {
        super(Angobjzuord.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Angobjzuord> getAngobjzuordes(String objid) {
        Query qry = getSession().createQuery("from Angobjzuord where objnr="+objid);
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Angobjzuord saveAngobjzuord(Angobjzuord angobjzuord) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + angobjzuord.getId());
        }
        getSession().saveOrUpdate(angobjzuord);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return angobjzuord;
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
    public Angobjzuord save(Angobjzuord angobjzuord) {
        return this.saveAngobjzuord(angobjzuord);
    }

   
 
}
