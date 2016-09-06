package braunimmobilien.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.ExpectedException;
import braunimmobilien.model.Emailbrief;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Mitarbeiter;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Xtyp;

public class NachweiseDaoTest extends BaseDaoTestCase {
    @Autowired
    private MitarbeiterDao mitarbeiterdao;
    @Autowired
    private KundeDao kundedao;
    @Autowired
    private NachweiseDao nachweisedao;
    @Autowired
    private AngebotDao angebotdao;
    @Autowired
    private XtypDao xtypdao;
    @Autowired
    private EmailbriefDao emailbriefdao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetNachweisInvalid() throws Exception {
        // should throw DataAccessException
        nachweisedao.get(10L);
    }

    @Test
    public void testGetNachweis() throws Exception {
       Nachweise nachweis = nachweisedao.get(1L);

        assertNotNull(nachweis);
     
    }

   
      @Test
 //   @ExpectedException(DataIntegrityViolationException.class)
    public void testUpdateNachweis() throws Exception {
    	  Nachweise nachweis = nachweisedao.get(1L);
        Nachweise nachweis1=new Nachweise();
        nachweis1.setAngebot(nachweis.getAngebot());
        nachweis1.setMitarbeiter(nachweis.getMitarbeiter());
        nachweis1.setKunde(nachweis.getKunde());
        nachweis1.setXtyp(nachweis.getXtyp());
        	nachweisedao.save(nachweis1);
        	Long id=nachweis1.getId();
       flush();
       nachweis=null;
       nachweis = nachweisedao.get(id);
        assertEquals("Fritz Braun",nachweis.getMitarbeiter().getPerson().getEigtName());
        
      
    
        
    }
    @Test
    public void testNachweisExists() throws Exception {
        boolean b = nachweisedao.exists(1L);
        assertTrue(b);
    }

    @Test
    public void testNachweisNotExists() throws Exception {
        boolean b =   nachweisedao.exists(111L);
        assertFalse(b);
    }

    
    
    @Test
  //  @ExpectedException(DataAccessException.class)
    public void testAddAndChangeNachweis() throws Exception {
    	 Nachweise nachweis1=new Nachweise();
         nachweis1.setAngebot(angebotdao.get("RH998"));
        nachweis1.setMitarbeiter(mitarbeiterdao.get(1L));
         	 nachweis1.setKunde(kundedao.get(1L));
             nachweis1.setXtyp(xtypdao.get(1L));
             nachweis1.setEmailbrief(emailbriefdao.get("xnm"));
             nachweisedao.save(nachweis1);
             Long id=nachweis1.getId();
             assertNotNull(id);
             assertTrue(id.longValue()>0);
             assertNotNull(nachweis1.getEmailbrief());
        flush();
    /*    nachweis=null;
        nachweis = nachweisedao.get(1L);
         assertEquals("Fritz Leone",nachweis.getMitarbeiter().getPerson().getEigtName());*/
         
    } 
    
   
    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveNachweis() throws Exception {
        Nachweise nachweis= new Nachweise();
        nachweisedao.save(nachweis);
        flush();
        assertNotNull(nachweis.getId());
        Long id=nachweis.getId();
        nachweis=null;
        nachweis = nachweisedao.get(id);
        nachweisedao.remove(nachweis);
        flush();

        // should throw DataAccessException
        nachweisedao.get(id);
    }
   @Test
    public void testNachweisSearch() throws Exception {
        
    	nachweisedao.reindex();
    	
        List<Nachweise> found = nachweisedao.search("Mehrfamilienhaus");
       assertEquals(1, found.size());
     /*   Nachweise nachweis= found.get(0);
        assertEquals("RH998", nachweis);

      
        
        nachweis.setAngebot(angebotdao.get("RH996"));
        nachweisedao.save(nachweis);
       flush();
      flushSearchIndexes();
     
   
        found = nachweisedao.search("RH996");
        assertEquals(1, found.size());
        nachweis = found.get(0);
        assertEquals("RH996",nachweis.getAngebot().getId());*/
    }
   
}

