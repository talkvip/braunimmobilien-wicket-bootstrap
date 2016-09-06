package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.PersonDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Personen;
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
@Repository("personDao")
public class PersonDaoHibernate extends GenericDaoHibernate<Personen, Long> implements PersonDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public PersonDaoHibernate() {
        super(Personen.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Personen> getPersonen(String strid) {
        Query qry = getSession().createQuery("from Personen u where eigtstrid="+strid+" order by upper(u.eigtName)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Personen savePerson(Personen person) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + person.getId());
        }
        getSession().saveOrUpdate(person);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return person;
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
    public Personen save(Personen person) {
        return this.savePerson(person);
    }

   
 
}
