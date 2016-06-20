package braunimmobilien.service;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import braunimmobilien.Constants;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Land;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Objektart;
import braunimmobilien.model.Objektsuch;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ObjektManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(ObjektManagerTest.class);
    @Autowired
    private ObjektManager mgr;
    @Autowired
    private LandManager landmgr;
    @Autowired
    private ObjektartManager objektartmgr;
    @Autowired
    private ObjektsuchManager objektsuchmgr;
    @Autowired
    private PersonManager personmgr;
   
    @Autowired
    private EigentuemertypManager eigentuemertypmgr;
    private Objekte objekt;
    private Land land;
    private Orte ort;
    private Strassen strasse;
   
    @Test
    public void testAddAndRemovePerson() throws Exception {
        objekt = new Objekte();
        land = new Land();
        land = (Land)populate(land);
        assertEquals(land.getLandname(),"Deutschland");
        assertEquals(land.getKennzeichen(),"D");
        land.setOrte(new ArrayList<Orte>());
        Orte ort=new Orte();
        ort.setOrtname("wo bist Du");
        ort.setOrtplz("7777");
        ort.setLand(land);
        land.addOrt(ort);
        Strassen strasse=new Strassen();
        strasse.setStrname("find ich nicht");
        strasse.setStrplz("666777");
        strasse.setOrt(ort);
        strasse.setPlanquadrat("xxz");
        strasse.setStrhausbereich("a-c");
        ort.addStrassen(strasse);
       land=landmgr.save(land);
       assertNotNull(land.getId());
        // call populate method in super class to populate test data
        // from a properties file matching this class name
     Objektart objektart=objektartmgr.get(1L);
     Objektsuch objektsuch=objektsuchmgr.get(1L);
     objekt.setObjektart(objektart);
     objekt.setObjektsuch(objektsuch);
     objekt.setObjhausnummer("wo aber");
      objekt.setStrasse(land.getOrte().get(0).getStrassen().get(0));
 land.getOrte().get(0).getStrassen().get(0).addObjekt(objekt);
      Personen person=personmgr.get(1L);
      Objperszuord objperszuord = new Objperszuord();
      objperszuord.setPersonen(person);
      objperszuord.setObjekt(objekt);
      objperszuord.setEigentuemertyp(eigentuemertypmgr.get(1L));
      objekt.addObjperszuord(objperszuord);
      person.addObjperszuord(objperszuord);
      land = landmgr.save(land);
      assertNotNull( land.getOrte().get(0).getStrassen().get(0).getObjekte().get(0).getId());
      assertEquals( "Fritz Braun",land.getOrte().get(0).getStrassen().get(0).getObjekte().get(0).getObjperszuords().get(0).getPersonen().getEigtName());
      
        log.debug("removing user...");

  //      mgr.removePerson(person.getId());

       
    }
}
