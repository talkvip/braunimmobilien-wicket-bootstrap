package braunimmobilien.dao.hibernate;
import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.XtypDao;
import braunimmobilien.model.Xtyp;
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

	@Repository("xtypDao")
	public class XtypDaoHibernate extends GenericDaoHibernate<Xtyp,Long> implements XtypDao {
		  /**
	     * Constructor that sets the entity to User.class.
	     */
	    public XtypDaoHibernate() {
	        super(Xtyp.class);
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @SuppressWarnings("unchecked")
	    public List<Xtyp> getXtyps() {
	        Query qry = getSession().createQuery("from Xtyp u order by upper(u.xtypkuerzel)");
	        return qry.list();
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public Xtyp saveXtyp(Xtyp xtyp) {
	        if (log.isDebugEnabled()) {
	            log.debug("xtyp's id: " + xtyp.getId());
	        }
	        getSession().saveOrUpdate(xtyp);
	        // necessary to throw a DataIntegrityViolation and catch it in UserManager
	        getSession().flush();
	        return xtyp;
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
	    public Xtyp save(Xtyp xtyp) {
	        return this.saveXtyp(xtyp);
	    }
}