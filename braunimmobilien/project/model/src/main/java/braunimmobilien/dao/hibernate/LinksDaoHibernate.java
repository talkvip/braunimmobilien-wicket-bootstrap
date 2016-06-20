package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.LinksDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Links;
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
@Repository("linksDao")
public class LinksDaoHibernate extends GenericDaoHibernate<Links, Long> implements LinksDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public LinksDaoHibernate() {
        super(Links.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Links> getLinkses() {
        Query qry = getSession().createQuery("from Links u order by upper(u.beschreibung)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Links saveLinks(Links links) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + links.getId());
        }
        getSession().saveOrUpdate(links);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return links;
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
    public Links save(Links links) {
        return this.saveLinks(links);
    }

   
 
}
