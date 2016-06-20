package braunimmobilien.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import braunimmobilien.model.Member;
import braunimmobilien.dao.MemberDao;

@Repository(value = "memberDao")
public class MemberDaoImpl extends AbstractDAO<Member> implements MemberDao {

public MemberDaoImpl(){
super(Member.class);
}

@SuppressWarnings({ "unchecked", "unchecked" })
@Override
public List<Member> findByLastName(String lastName) {
Session session = this.sessionFactory.getCurrentSession();

Criteria crit = session.createCriteria(Member.class);
crit.add(Restrictions.ilike("lastName", lastName));

return crit.list();
}

@Override
public void create(String firstName, String lastName) {
Member newMember = new Member();
newMember.setFirstName(firstName);
newMember.setLastName(lastName);

this.sessionFactory.getCurrentSession().saveOrUpdate(newMember);

this.sessionFactory.getCurrentSession().flush();

}

@Override
public long count() {
return ((Long) getSession().createQuery("select count(*) from Member")
.uniqueResult()).intValue();
}

}

