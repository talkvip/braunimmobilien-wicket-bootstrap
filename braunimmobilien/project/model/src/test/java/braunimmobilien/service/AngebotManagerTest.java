package braunimmobilien.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import braunimmobilien.Constants;
import braunimmobilien.model.Angebot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AngebotManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(AngebotManagerTest.class);
    @Autowired
    private AngebotManager mgr;
 
    private Angebot angebot;

   
    @Test
    public void testAngebotSearch() throws Exception {
        // reindex all the data
        mgr.reindex();

        List<Angebot> found = mgr.search("Mehrfamilienhaus");
        assertEquals(1, found.size());
        Angebot angebot= found.get(0);
        assertEquals("Hannover", angebot.getAnglagebeschreibung());

        // test mirroring
        angebot = mgr.get("RH998");
        angebot.setAnglagebeschreibung("Berlin");
        mgr.saveAngebot(angebot);
       mgr.reindex();
        // now verify it is reflected in the index
        found = mgr.search("Berlin");
        assertEquals(1, found.size());
       angebot = found.get(0);
        assertEquals("Berlin", angebot.getAnglagebeschreibung());
    }
 
   
   
}
