package braunimmobilien.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import braunimmobilien.Constants;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Mitarbeiter;
import braunimmobilien.model.Personen;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class KundeManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(KundeManagerTest.class);
    @Autowired
    private KundeManager mgr;
    @Autowired
    private PersonManager personmgr;
  
   
   
   
   
    @Test
    public void testSearchKunde() throws Exception {
    personmgr.reindex();
    	List<Personen> personen=personmgr.getAll();
   	 assertEquals(2, personen.size());
   	 Personen person= personen.get(0);
     assertEquals("h.h.braun@ich-ueberall.de", person.getEigtEmail());

   	  	personen=personmgr.search("\"h.h.braun@ich-ueberall.de\"");
   	
   	  	assertEquals(1, personen.size());
   	 mgr.reindex();
    	 	List<Kunde> kunden=mgr.search("\"h.h.braun@ich-ueberall.de\"");
       assertEquals(1, kunden.size());
       
    }
  
}
