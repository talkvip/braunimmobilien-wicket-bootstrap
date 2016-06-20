package braunimmobilien.dao.hibernate;
import static org.junit.Assert.*;
import braunimmobilien.dao.BaseDaoTestCase;
import braunimmobilien.dao.MemberDao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import braunimmobilien.dao.EntityLoaderDao;
import braunimmobilien.model.Member;

import java.util.List;
import java.util.Map;
public class EntityLoaderDaoTest extends BaseDaoTestCase{
	 @Autowired
	 EntityLoaderDao mEntityLoader;
	 public EntityLoaderDao getmEntityLoader() {
		return mEntityLoader;
	}
	public void setmEntityLoader(EntityLoaderDao mEntityLoader) {
		this.mEntityLoader = mEntityLoader;
	}
	@Autowired
		private MemberDao memberdao;
	   @Test
	    public void testEntityLoader(){
		   memberdao.create("Heiner", "Braun");     
       	   List<Member> memberList=memberdao.findAll();
       assertTrue(memberList.size()>0);
       Member member=mEntityLoader.load(Member.class, 1L);
       assertEquals(member.getFirstName(),"Heiner");
       memberdao.remove(member);
	    }
}
