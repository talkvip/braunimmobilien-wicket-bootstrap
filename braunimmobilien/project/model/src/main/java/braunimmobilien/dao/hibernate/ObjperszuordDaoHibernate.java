package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.ObjperszuordDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Objperszuord;
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
@Repository("objperszuordDao")
public class ObjperszuordDaoHibernate extends GenericDaoHibernate<Objperszuord, Long> implements ObjperszuordDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public ObjperszuordDaoHibernate() {
        super(Objperszuord.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Objperszuord> getObjperszuordes() {
        Query qry = getSession().createQuery("from Objperszuord u order by upper(u.statustext)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Objperszuord saveObjperszuord(Objperszuord objperszuord) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + objperszuord.getId());
        }
        getSession().saveOrUpdate(objperszuord);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return objperszuord;
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
    public Objperszuord save(Objperszuord objperszuord) {
        return this.saveObjperszuord(objperszuord);
    }

   
 
}

