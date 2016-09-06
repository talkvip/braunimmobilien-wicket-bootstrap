package braunimmobilien.service.impl;

import braunimmobilien.Constants;
import braunimmobilien.dao.AngstatusDao;
import braunimmobilien.model.Angstatus;
import braunimmobilien.service.AngstatusExistsException;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.Assert.*;

public class AngstatusManagerImplTest extends BaseManagerMockTestCase {
    //~ Instance fields ========================================================
    private AngstatusManagerImpl angstatusManager = new AngstatusManagerImpl();
    private AngstatusDao angstatusDao;

    //~ Methods ================================================================
    @Before
    public void setUp() throws Exception {
        angstatusDao = context.mock(AngstatusDao.class);
        angstatusManager.setAngstatusDao(angstatusDao);
    }

    @Test
    public void testGetAngstatus() throws Exception {
        final Angstatus testData = new Angstatus("1");
       

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(angstatusDao).get(with(equal(1L)));
            will(returnValue(testData));
        }});
        
        Angstatus angstatus= angstatusManager.getAngstatus("1");
        assertTrue(angstatus != null);
        assert angstatus != null;
            }

    @Test
    public void testSaveAngstatus() throws Exception {                                           
        final Angstatus testData = new Angstatus("1");
       

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(angstatusDao).get(with(equal(1L)));
            will(returnValue(testData));
        }});
        
        final Angstatus angstatus= angstatusManager.getAngstatus("1");
      

        context.checking(new Expectations() {{
            one(angstatusDao).saveAngstatus(with(same(angstatus)));
            will(returnValue(angstatus));
        }});
        
        Angstatus returned = angstatusManager.saveAngstatus(angstatus);
        assertTrue(returned.getStatustext().equals("1"));
       
    }

    @Test
    public void testAddAndRemoveAngstatus() throws Exception {
        Angstatus angstatus= new Angstatus();

        // call populate method in super class to populate test data
        // from a properties file matching this class name
        angstatus = (Angstatus) populate(angstatus);
        
    
       

        // set expected behavior on user dao
        final Angstatus angstatus1 = angstatus;
        context.checking(new Expectations() {{
            one(angstatusDao).saveAngstatus(with(same(angstatus1)));
            will(returnValue(angstatus1));
        }});

        angstatus = angstatusManager.saveAngstatus(angstatus);
        assertTrue(angstatus.getStatustext().equals("unbestimmt"));
        

        context.checking(new Expectations() {{
            one(angstatusDao).remove(with(equal(5L)));
        }});

        angstatusManager.removeAngstatus("5");

        context.checking(new Expectations() {{
            one(angstatusDao).get(with(equal(5L)));
            will(returnValue(null));
        }});
        
        angstatus = angstatusManager.getAngstatus("5");
        assertNull(angstatus);
    }

    @Test
    public void testAngstatusExistsException() {
        // set expectations
        final Angstatus angstatus = new Angstatus("admin");
        angstatus.setStatustext("matt@raibledesigns.com");

        final Exception ex = new DataIntegrityViolationException("");

        context.checking(new Expectations() {{
            one(angstatusDao).saveAngstatus(with(same(angstatus)));
            will(throwException(ex));
        }});
        
        // run test
        try {
            angstatusManager.saveAngstatus(angstatus);
            fail("Expected UserExistsException not thrown");
        } catch (AngstatusExistsException e) {
            log.debug("expected exception: " + e.getMessage());
            assertNotNull(e);
        }
    }
}
