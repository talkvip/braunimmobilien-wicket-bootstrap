package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.EigentuemertypDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Eigentuemertyp;
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
@Repository("eigentuemertypDao")
public class EigentuemertypDaoHibernate extends GenericDaoHibernate<Eigentuemertyp, Long> implements EigentuemertypDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public EigentuemertypDaoHibernate() {
        super(Eigentuemertyp.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Eigentuemertyp> getEigentuemertyps() {
        Query qry = getSession().createQuery("from Eigentuemertyp u order by upper(u.typenbeschreibung)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Eigentuemertyp saveEigentuemertyp(Eigentuemertyp eigentuemertyp) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + eigentuemertyp.getId());
        }
        getSession().saveOrUpdate(eigentuemertyp);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return eigentuemertyp;
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
    public Eigentuemertyp save(Eigentuemertyp eigentuemertyp) {
        return this.saveEigentuemertyp(eigentuemertyp);
    }

   
 
}
