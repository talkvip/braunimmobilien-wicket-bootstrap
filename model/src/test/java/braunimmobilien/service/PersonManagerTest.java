package braunimmobilien.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import braunimmobilien.Constants;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Personen;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PersonManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(PersonManagerTest.class);
    @Autowired
    private PersonManager mgr;
    @Autowired
    private ObjektManager objektmgr;
    @Autowired
    private EigtstatusManager eigtstatusmgr;
    @Autowired
    private EigentuemertypManager eigentuemertypmgr;
    @Autowired
    private LandManager landmgr;
    private Personen person;
    private Land land;
    private Orte ort;
    private Strassen strasse;
   
    @Test
    public void testAddAndRemovePerson() throws Exception {
        List<Personen> list= mgr.getAll();
        assertTrue(list.size()==2);
    	person = new Personen();
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
      person = (Personen) populate(person);
   // person.setEigtstatus(2L);
      person.setEigtstatus(eigtstatusmgr.get(2L));
      person.setStrasse(land.getOrte().get(0).getStrassen().get(0));
      strasse.addPerson(person);
       Objekte objekt=objektmgr.get(1L);
        Objperszuord objperszuord = new Objperszuord();
        objperszuord.setPersonen(person);
        objperszuord.setObjekt(objekt);
        objperszuord.setEigentuemertyp(eigentuemertypmgr.get(1L));
        person.addObjperszuord(objperszuord);
        objekt.addObjperszuord(objperszuord);
        land=landmgr.save(land);
        assertEquals("Deutschland", person.getStrasse().getOrt().getLand().getLandname());
        assertNotNull(person.getId()); 
        assertTrue(person.getId().longValue()>0);
        assertTrue(person.getObjperszuords().size()>0);
        assertEquals( "Ich weiß nicht wo 3",land.getOrte().get(0).getStrassen().get(0).getPersonen().get(0).getObjperszuords().get(0).getObjekt().getObjhausnummer());
       list= mgr.getAll();
       assertTrue(list.size()==3);
       Iterator it=list.iterator();
       Personen person2=null;
       while(it.hasNext()){
    	   Personen person1=(Personen)it.next();
    	   if(person1.getId().longValue()>2){
    		   person2=person1;
    	   }   
       }
      
     /*  assertTrue(person2.getEigtstatus().longValue()==2);   
       person2.setEigtstatus(1L);*/
       assertTrue(person2.getEigtstatus().getId().longValue()==2);   
       person2.setEigtstatus(eigtstatusmgr.get(1L));
       land=landmgr.save(land);
       Personen person3=mgr.get(person2.getId());
    //   assertTrue(person3.getEigtstatus().longValue()==1);
       assertTrue(person3.getEigtstatus().getId().longValue()==1);
        log.debug("removing user...");

  //      mgr.removePerson(person.getId());

       
    }
  
}
