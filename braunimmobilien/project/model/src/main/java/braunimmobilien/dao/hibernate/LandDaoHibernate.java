package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.LandDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Land;
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
import org.springframework.transaction.annotation.Transactional;
/**
 * @author rjansen
 * 
 */
@Repository("landDao")
public class LandDaoHibernate extends GenericDaoHibernate<Land, Long> implements LandDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public LandDaoHibernate() {
        super(Land.class);
    }

    /**
     * {@inheritDoc}
     */
   
    @SuppressWarnings("unchecked")
    public List<Land> getLands() {
        Query qry = getSession().createQuery("from Land u order by upper(u.landname)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Land saveLand(Land land) {
        if (log.isDebugEnabled()) {
            log.debug("land's id: " + land.getId());
        }
        getSession().saveOrUpdate(land);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return land;
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
    public Land save(Land land) {
        return this.saveLand(land);
    }

   
 
}

