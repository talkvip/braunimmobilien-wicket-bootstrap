package braunimmobilien.service;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import braunimmobilien.model.Angstatus;
import braunimmobilien.service.MemberManager;
import braunimmobilien.model.Member;
public class MemberManagerTest extends BaseManagerTestCase {
	 @Autowired
	    private MemberManager mgr;
	 
	 
	 @Test
	    public void testMember() throws Exception {
	      mgr.create("Heiner","Braun");
	       List<Member> listMember=mgr.findAll();
	       assertTrue(listMember.size()>0);
	       assertTrue(true);
	       
	    }
	 
}
