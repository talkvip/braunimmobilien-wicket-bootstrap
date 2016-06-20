package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.NutzerDao;
import braunimmobilien.model.Nutzer;

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
@Repository("nutzerDao")
public class NutzerDaoHibernate extends GenericDaoHibernate<Nutzer, Long> implements NutzerDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public NutzerDaoHibernate() {
        super(Nutzer.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Nutzer> getNutzer() {
        Query qry = getSession().createQuery("from User u order by upper(u.username)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Nutzer saveNutzer(Nutzer nutzer) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + nutzer.getId());
        }
        getSession().saveOrUpdate(nutzer);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return nutzer;
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
    public Nutzer save(Nutzer nutzer) {
        return this.saveNutzer(nutzer);
    }

   
 
}
