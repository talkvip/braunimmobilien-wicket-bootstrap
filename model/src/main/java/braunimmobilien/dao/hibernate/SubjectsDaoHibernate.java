package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import braunimmobilien.dao.SubjectsDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.model.Subjects;
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
@Repository("subjectsDao")
public class SubjectsDaoHibernate extends GenericDaoHibernate<Subjects, Long> implements SubjectsDao {
	  /**
     * Constructor that sets the entity to User.class.
     */
    public SubjectsDaoHibernate() {
        super(Subjects.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Subjects> getSubjectses() {
        Query qry = getSession().createQuery("from Subjects u order by upper(u.subject)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public Subjects saveSubjects(Subjects subjects) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + subjects.getId());
        }
        getSession().saveOrUpdate(subjects);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return subjects;
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
    public Subjects save(Subjects subjects) {
        return this.saveSubjects(subjects);
    }

   
 
}
