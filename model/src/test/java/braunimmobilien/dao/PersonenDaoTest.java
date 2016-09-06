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

import braunimmobilien.model.Personen;

public class PersonenDaoTest extends BaseDaoTestCase {
    @Autowired
    private PersonDao dao;
    @Autowired
    private EigtstatusDao eigtstatusdao;
    @Autowired
    private StrassenDao strassendao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetPersonInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(10L);
    }

    @Test
    public void testGetPerson() throws Exception {
       Personen person = dao.get(1L);

        assertNotNull(person);
     
    }

   
         @Test
  // @ExpectedException(DataIntegrityViolationException.class)
    public void testUpdatePersonen() throws Exception {
        Personen person = dao.get(1L);
        	person.setEigtName("Blödsinn");
      
        dao.save(person);
       flush();

        person = dao.get(1L);
        assertEquals("Blödsinn", person.getEigtName());
        
         Personen person1=new Personen();
         person1.setEigtAnschrift("hier 1");
         person1.setEigtHausnummer("hier2");
         person1.setEigtBriefanrede("hier 3");
         person1.setStrasse(strassendao.get(1L));
      //   person1.setEigtstatus(1L);
         person1.setEigtstatus(eigtstatusdao.get(1L));
    	person1.setEigtName("Blödsinn");
    	
      dao.save(person1); 
      Personen person2= person = dao.get(1L);
   /*   assertTrue(person2.getEigtstatus().longValue()==1);
      person2.setEigtstatus(2L);
      assertTrue(person2.getEigtstatus().longValue()==2);*/
       assertTrue(person2.getEigtstatus().getId().longValue()==1);
      person2.setEigtstatus(eigtstatusdao.get(2L));
      assertTrue(person2.getEigtstatus().getId().longValue()==2);
      dao.save(person2);
      Personen person3= dao.get(1L);
     // assertTrue(person3.getEigtstatus().longValue()==2);
      assertTrue(person3.getEigtstatus().getId().longValue()==2); 
      
      
    }
       @Test
    public void testMitarbeiterExists() throws Exception {
        boolean b = dao.exists(1L);
        assertTrue(b);
    }

    @Test
    public void testMitarbeiterNotExists() throws Exception {
        boolean b = dao.exists(111L);
        assertFalse(b);
    }
    @Test
    public void testAddPerson() throws Exception {
	  Personen person1=new Personen();
      person1.setEigtAnschrift("hier 1");
      person1.setEigtHausnummer("hier2");
      person1.setEigtBriefanrede("hier 3");
      person1.setStrasse(strassendao.get(1L));
    //  person1.setEigtstatus(1L);
      person1.setEigtstatus(eigtstatusdao.get(1L));
      person1.setEigtName("Blödsinn");
        dao.save(person1);
        
        flush();
       
        person1 = dao.get(person1.getId());
        assertNotNull(person1.getId());
        assertNotNull(person1.getEigtstatus());
      
    }
   
  @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemovePerson() throws Exception {
	  Personen person1=new Personen();
      person1.setEigtAnschrift("hier 1");
      person1.setEigtHausnummer("hier2");
      person1.setEigtBriefanrede("hier 3");
      person1.setStrasse(strassendao.get(1L));
   //   person1.setEigtstatus(1L);
      person1.setEigtstatus(eigtstatusdao.get(1L));
      person1.setEigtName("Blödsinn");
        dao.save(person1);
        
        flush();
       
        person1 = dao.get(person1.getId());
        assertNotNull(person1.getId());
        assertNotNull(person1.getEigtstatus());
        dao.remove(person1);
        flush();

        // should throw DataAccessException
        dao.get(person1.getId());
    }
      @Test
    public void testPersonSearch() throws Exception {
        // reindex all the data
    	  flush();
          flushSearchIndexes();
    	  dao.reindex();
    	  Personen person=null;
        List<Personen> found = dao.search("Braun");
    assertEquals(1, found.size());
           person= found.get(0);
        assertEquals("Fritz Braun", person.getEigtName());

        // test mirroring
        person = dao.get(1L);
        person.setEigtName("Fridolin");
        dao.save(person);
     //   flush();
     //   flushSearchIndexes();
       dao.reindex();
        // now verify it is reflected in the index
        found = dao.search("Fridolin");
       assertEquals(1, found.size());
        person = found.get(0);
        assertEquals("Fridolin", person.getEigtName());
    }
   
}

