package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.ObjarttypDao;
import braunimmobilien.model.Objarttyp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

/**
 * @author rjansen
 * 
 */
@Repository("objarttypDao")
public class ObjarttypDaoHibernate extends GenericDaoHibernate<Objarttyp, Long> implements ObjarttypDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public ObjarttypDaoHibernate() {
        super(Objarttyp.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Objarttyp> getObjarttypes() {
        Query qry = getSession().createQuery("from Objarttyp u order by upper(u.typentext)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Objarttyp saveObjarttyp(Objarttyp objarttyp) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + objarttyp.getId());
        }
        getSession().saveOrUpdate(objarttyp);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return objarttyp;
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
    public Objarttyp save(Objarttyp objarttyp) {
        return this.saveObjarttyp(objarttyp);
    }

   
 
}
