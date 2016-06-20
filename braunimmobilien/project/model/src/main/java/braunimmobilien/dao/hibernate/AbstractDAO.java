package braunimmobilien.dao.hibernate;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import braunimmobilien.dao.DAO;
import braunimmobilien.model.AbstractEntity;

@Repository
public abstract class AbstractDAO<EntityClass extends AbstractEntity>
implements DAO<EntityClass> {

protected Class<EntityClass> clazz;

public AbstractDAO(){

}

public AbstractDAO(Class<EntityClass> clazz){
this.clazz = clazz;
}

@Resource
protected SessionFactory sessionFactory;

public Session getSession() {
try {
return sessionFactory.getCurrentSession();
} catch (HibernateException he) {
return sessionFactory.openSession();
}
}


@Transactional
public void persist(EntityClass entity) {
getSession().saveOrUpdate(entity);
getSession().flush();
}

@Transactional
public void remove(EntityClass entity) {
getSession().delete(entity);
getSession().flush();
}

@SuppressWarnings("unchecked")
@Transactional(readOnly=true)
public List<EntityClass> list(long offset, long count) {
Session session = this.sessionFactory.getCurrentSession();

Criteria crit = session.createCriteria(getClazz());
crit.addOrder(Order.asc("lastName"));
crit.setFirstResult((int)offset);
crit.setMaxResults((int)count);

return crit.list();
}

@SuppressWarnings("unchecked")
@Transactional(readOnly= true)
public List<EntityClass> findAll() {
Criteria criteria = getSession().createCriteria(getClazz());
List<EntityClass> entities = (List<EntityClass>) criteria.list();
return entities;
}

@SuppressWarnings("unchecked")
@Transactional(readOnly= true)
public EntityClass findById(Long id) {
return (EntityClass) getSession().load(clazz, id);
}

@SuppressWarnings("unchecked")
@Transactional(readOnly= true)
public EntityClass findByProperty(String propertyName, Object propertyValue){
Criteria crit = getSession().createCriteria(clazz);
crit.add(Restrictions.eq(propertyName, propertyValue));
return (EntityClass) crit.uniqueResult();
}

public Class<EntityClass> getClazz(){
return this.clazz;
}

public EntityClass getReference(EntityClass entity){
throw new RuntimeException("Not yet implemented.");
}

}