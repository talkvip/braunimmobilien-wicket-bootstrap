package braunimmobilien.service;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import braunimmobilien.Constants;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Personen;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ScoutManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(ScoutManagerTest.class);
    @Autowired
    private PersonManager mgr;
    @Autowired
    private ScoutManager scoutmgr;
    @Autowired
    private EigentuemertypManager eigentuemertypmgr;
    @Autowired
    private LandManager landmgr;
  
   
    @Test
    public void testChangeScout() throws Exception {
       
        Scout scout=scoutmgr.get(66538384L);
        assertEquals("Fritz Braun", scout.getPerson().getEigtName());
        scout.setPerson(mgr.get(2L));
        scoutmgr.save(scout);
        scout=scoutmgr.get(66538384L);
        assertEquals("Fritz Leone", scout.getPerson().getEigtName());
       
    }
  
}
