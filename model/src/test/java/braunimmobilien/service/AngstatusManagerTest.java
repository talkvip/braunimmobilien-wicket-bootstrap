package braunimmobilien.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import braunimmobilien.Constants;
import braunimmobilien.model.Angstatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AngstatusManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(AngstatusManagerTest.class);
    @Autowired
    private AngstatusManager mgr;
 
    private Angstatus angstatus;

   

   
    @Test
    public void testAddAndRemoveUser() throws Exception {
        angstatus = new Angstatus();

        // call populate method in super class to populate test data
        // from a properties file matching this class name
        angstatus = (Angstatus) populate(angstatus);

        angstatus = mgr.saveAngstatus(angstatus);
        assertEquals("unbestimmt", angstatus.getStatustext());
       

        log.debug("removing user...");

        mgr.removeAngstatus(angstatus.getId().toString());

       
    }
}
