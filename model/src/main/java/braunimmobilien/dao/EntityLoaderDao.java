package braunimmobilien.dao;


import java.io.Serializable;
import braunimmobilien.dao.EntityLoaderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EntityLoaderDao{

@Autowired
SessionFactory sessionFactory;

public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

@SuppressWarnings("unchecked")
public <T> T load(Class<T> clazz, Serializable id){
System.out.println("Loading...");
Session session = sessionFactory.getCurrentSession();
System.out.println("Session "+session);
System.out.println("session.get "+session.get(clazz, id));
System.out.println("session.load "+session.load(clazz, id));
return (T) sessionFactory.getCurrentSession().load(clazz, id);
}
}