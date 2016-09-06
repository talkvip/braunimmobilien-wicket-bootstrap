package braunimmobilien.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import braunimmobilien.Constants;
import braunimmobilien.model.Eigentuemermuster;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class EigentuemermusterManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(AngstatusManagerTest.class);
    @Autowired
    private EigentuemermusterManager mgr;
    @Autowired
    private PersonManager personmgr;
    private Eigentuemermuster eigentuemermuster;

    
    @Test
    public void testChangePersonEigentuemermuster() throws Exception {
        eigentuemermuster = mgr.get(1L);
        assertEquals("Fritz Braun", eigentuemermuster.getPerson().getEigtName());
    	eigentuemermuster.setPerson(personmgr.get(new Long(2)));
        mgr.saveEigentuemermuster(eigentuemermuster); 
        eigentuemermuster = null;
        eigentuemermuster = mgr.get(1L);
        assertEquals("Fritz Leone", eigentuemermuster.getPerson().getEigtName());
    }
    
    
  
}

