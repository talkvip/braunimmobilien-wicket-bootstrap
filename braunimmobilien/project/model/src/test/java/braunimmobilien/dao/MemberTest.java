package braunimmobilien.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Iterator;
import braunimmobilien.model.Member;
import braunimmobilien.dao.MemberDao;

public class MemberTest extends EntityDaoTestCase {
	@Autowired
	private MemberDao memberdao;
	
	@Test
	    public void testAnything() throws Exception {
	      /*	   memberdao.create("Heiner", "Braun");     
	       	   List<Member> memberList=memberdao.findAll();
	       assertTrue(memberList.size()>0);
	       Iterator it=memberList.iterator();
	       while(it.hasNext()){
	    	   Member member=(Member)it.next();
	    	  assertEquals(member.getId().toString(),"1001");
	       }*/
	    }

}