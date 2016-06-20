package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.EigentuemermusterDao;
import braunimmobilien.model.Eigentuemermuster;
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
@Repository("eigentuemermusterDao")
public class EigentuemermusterDaoHibernate extends GenericDaoHibernate<Eigentuemermuster, Long> implements EigentuemermusterDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public EigentuemermusterDaoHibernate() {
        super(Eigentuemermuster.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Eigentuemermuster> getEigentuemermusters() {
        Query qry = getSession().createQuery("from Eigentuemermuster u order by upper(u.eigentuemermuster)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Eigentuemermuster saveEigentuemermuster(Eigentuemermuster eigentuemermuster) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + eigentuemermuster.getId());
        }
        getSession().saveOrUpdate(eigentuemermuster);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return eigentuemermuster;
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
    public Eigentuemermuster save(Eigentuemermuster eigentuemermuster) {
        return this.saveEigentuemermuster(eigentuemermuster);
    }

   
 
}
