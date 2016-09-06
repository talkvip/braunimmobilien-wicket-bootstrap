package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.TypeDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Type;
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
@Repository("typeDao")
public class TypeDaoHibernate extends GenericDaoHibernate<Type, Long> implements TypeDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public TypeDaoHibernate() {
        super(Type.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Type> getTypees() {
        Query qry = getSession().createQuery("from Type u order by upper(u.Type)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Type saveType(Type type) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + type.getId());
        }
        getSession().saveOrUpdate(type);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return type;
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
    public Type save(Type type) {
        return this.saveType(type);
    }

   
 
}
