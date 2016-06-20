package braunimmobilien.dao;
import braunimmobilien.Constants;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Angstatus;
import braunimmobilien.model.Kondition;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Land;
import braunimmobilien.model.Objektsuch;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Objektart;
import braunimmobilien.model.Objperszuord;
import java.util.List;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.NotTransactional;
import java.util.Iterator;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.net.URL;
import static org.junit.Assert.*;

public class AngebotDaoTest extends BaseDaoTestCase {

	@Autowired
	private AngebotDao angebotdao;
	@Autowired
	private AngstatusDao angstatusdao;
	@Autowired
	private KonditionDao konditiondao;
	 @Test
	    @ExpectedException(DataAccessException.class)
	    public void testGetAngebotInvalid() throws Exception {
	        // should throw DataAccessException
	        angebotdao.get("RH999");
	    }
	 
	 

	 @Test
	    public void testGetAngebot() throws Exception {
	        Angebot angebot = angebotdao.get("RH998");
	       
	        assertNotNull(angebot);
	     
	    }

	   
/*	    @Test
	    @ExpectedException(DataIntegrityViolationException.class)
	    public void testUpdateAngebot() throws Exception {
	    	Angebot angebot = angebotdao.get("RH998");
	        Angebot angebot1= new Angebot();
	    	angebot1.setAngkurzbeschreibung(angebot.getAngkurzbeschreibung());
	    	angebot1.setId("RH999");
	    	angebot1.setAnglagebeschreibung(angebot.getAnglagebeschreibung());
	    
	    	angebot1.setAngstatus(angebot.getAngstatus());
	    	angebot1.setAnganz(angebot.getAnganz());
	    	angebot1.setKondition(angebot.getKondition());
	    	angebot1.setAngaufdatum(angebot.getAngaufdatum());
	        angebotdao.saveAngebot(angebot1);

	        
	    }*/
	    @Test
	    public void testAngebotExists() throws Exception {
	        boolean b = angebotdao.exists("RH998");
	        assertTrue(b);
	    }

	    @Test
	    public void testAngebotNotExists() throws Exception {
	        boolean b = angebotdao.exists("RH999");
	        assertFalse(b);
	    }

	   
	    @Test
	    @ExpectedException(DataAccessException.class)
	    public void testAddAndRemoveAngebot() throws Exception {
	        Angebot angebot = new Angebot();
	        angebot.setId("RH999");
  		angebot.setAnglagebeschreibung("Berlin");
  		angebot.setAngkurzbeschreibung("Mehrfamilienhaus");
  	 	angebot.setAngstatus(angstatusdao.get(new Long(1)));;
    	angebot.setAnganz(1);
    	angebot.setKondition(konditiondao.get("kon"));
    	java.util.Date today = new java.util.Date();
    	angebot.setAngaufdatum(new java.sql.Date(today.getTime()));
  		
	        angebotdao.save(angebot);
	        

	        assertNotNull(angebot.getId());
	        angebot = angebotdao.get(angebot.getId());
	        angebotdao.remove(angebot);
	       

	        // should throw DataAccessException
	        angebotdao.get(angebot.getId());
	    }
	    
	    
	    @Test
	    @ExpectedException(DataAccessException.class)
	    public void testAddAngobjzuord() throws Exception {
	        Angebot angebot=angebotdao.get("RH996");
	        assertNotNull(angebot);
	        assertNotNull(angebot.getId());
assertTrue(angebot.getAngobjzuords().size()>0);
Iterator iterator=angebot.getAngobjzuords().iterator();
int i=0;
int j=0;
	while(iterator.hasNext()){
		i=1;
		Angobjzuord angobjzuord=(Angobjzuord)iterator.next();
		System.err.println("ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ I have it "+angobjzuord.getObjekte().getObjektart().getObjartname());
		assertTrue(angobjzuord.getObjekte().getObjperszuords().size()>0);
		Iterator iterator1=angobjzuord.getObjekte().getObjperszuords().iterator();
		while(iterator1.hasNext()){
			j=1;
			Objperszuord objperszuord=(Objperszuord)iterator1.next();
			System.err.println("ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ I have it "+objperszuord.getPersonen().getEigtName());
			
		}
	
	}
	assertTrue(i==1);
	assertTrue(j==1);
	        angebot = new Angebot();
	        angebot.setId("RH997");
  		angebot.setAnglagebeschreibung("Frankfurt");
  		angebot.setAngkurzbeschreibung("Gewerbehaus");
  	 	angebot.setAngstatus(angstatusdao.get(new Long(1)));;
    	angebot.setAnganz(1);
    	angebot.setKondition(konditiondao.get("kon"));
    	java.util.Date today = new java.util.Date();
    	angebot.setAngaufdatum(new java.sql.Date(today.getTime()));
    	 Objekte objekt= new Objekte();
    	 Objektart objektart=new Objektart();
    	 objektart.setObjartkurz("WGH");
    	 objektart.setObjartname("Wohn-und-Geschäftshaus");
    	 objektart.setObjarttyp(new Boolean(true));
    	 objektart.setDoppelbelegung(new Boolean(false));
    	 objekt.setObjektart(objektart);
    	 objekt.setObjletztkontakt(new java.sql.Date(today.getTime()));
    	 Land land=new Land();
    	 land.setLandname("Nirgendwo");
    	 land.setKennzeichen("N");
    	 Orte ort=new Orte();
    	 ort.setOrtname("Irgendwo");
    	 ort.setOrtplz("99999");
    	 ort.setLand(land);
    	 land.addOrt(ort);
    	 Strassen strasse=new Strassen();
    	 strasse.setMerkmal(new Boolean(true));
    	 strasse.setOrt(ort);
    	 strasse.setPlanquadrat("xyz");
    	 strasse.setStrhausbereich("1-20");
    	 strasse.setStrplz("99999");
    	 strasse.setStrname("Ich weiß nicht wo");
    	 ort.addStrassen(strasse);
    	 objekt.setStrasse(strasse);
    	 Objektsuch objektsuch=new Objektsuch();
    	 objektsuch.setSuchtext("weiß nicht was er will");
    	 
    	 objekt.setObjektsuch(objektsuch);
    	 objekt.setObjaufnahme(new java.sql.Date(today.getTime()));
    	 objekt.setObjhausnummer("Ich weiß nicht wo 3");
    	 objekt.setObjinfo("Info");
    	 objekt.setObjvorlage(new java.sql.Date(today.getTime()));
	     Angobjzuord angobjzuord=new Angobjzuord();
	     angobjzuord.setAktuell(new Boolean(true));
	     angobjzuord.setAngebot(angebot);
	     angobjzuord.setObjekte(objekt);
	     angebot.addAngobjzuord(angobjzuord);
    	 angebotdao.save(angebot);
	        
    	 angebot=null;
	        assertNotNull(angebot.getId());
	        angebot = angebotdao.get(angebot.getId());
	        assertTrue(angebot.getAngobjzuords().size()>0);
//	        angebotdao.remove(angebot);
	        iterator=angebot.getAngobjzuords().iterator();
	        while(iterator.hasNext()){
	    		angobjzuord=(Angobjzuord)iterator.next();
	    		assertTrue(angobjzuord.getObjekte().getObjektart().getObjartname().equals("Wohn-und-Geschäftshaus"));
	    		assertTrue(angobjzuord.getObjekte().getStrasse().getOrt().getLand().getLandname().equals("Nirgendwo"));
	    	
	    	}
	        // should throw DataAccessException
//	        angebotdao.get(angebot.getId());
	    }
	    
	    
	    @Test
	    public void testAngebotSearch() throws Exception {
	        // reindex all the data
	        angebotdao.reindex();

	        List<Angebot> found = angebotdao.search("Mehrfamilienhaus");
	        assertEquals(1, found.size());
	        Angebot angebot= found.get(0);
	        assertEquals("Hannover", angebot.getAnglagebeschreibung());

	        // test mirroring
	        angebot = angebotdao.get("RH998");
	        angebot.setAnglagebeschreibung("Berlin");
	        angebotdao.saveAngebot(angebot);
	        flush();
	        flushSearchIndexes();

	        // now verify it is reflected in the index
	        found = angebotdao.search("Berlin");
	        assertEquals(1, found.size());
	        angebot = found.get(0);
	        assertEquals("Berlin", angebot.getAnglagebeschreibung());
	    }
	 
	 
}
