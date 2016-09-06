package braunimmobilien.dao.hibernate;

	import java.util.List;

	import javax.persistence.Table;

	import braunimmobilien.dao.AngebotDao;
	import braunimmobilien.model.Angebot;
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
	@Repository("angebotDao")
	public class AngebotDaoHibernate extends GenericDaoHibernate<Angebot, String> implements AngebotDao {
		  /**
	     * Constructor that sets the entity to User.class.
	     */
	    public AngebotDaoHibernate() {
	        super(Angebot.class);
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @SuppressWarnings("unchecked")
	    public List<Angebot> getAngebote() {
	        Query qry = getSession().createQuery("from Angebot u order by substring(id,2) ASC,length(id) DESC,substring(id,0,2) ASC");
	        return qry.list();
	    }
	
	    /**
	     * {@inheritDoc}
	     */
	    public Angebot saveAngebot(Angebot angebot) {
	        if (log.isDebugEnabled()) {
	            log.debug("user's id: " + angebot.getId());
	        }
	        getSession().saveOrUpdate(angebot);
	        // necessary to throw a DataIntegrityViolation and catch it in UserManager
	        getSession().flush();
	        return angebot;
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
	    public Angebot save(Angebot angebot) {
	        return this.saveAngebot(angebot);
	    }

	   
	 
	}
