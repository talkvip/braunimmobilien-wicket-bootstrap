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

import braunimmobilien.model.Mitarbeiter;

public class MitarbeiterDaoTest extends BaseDaoTestCase {
    @Autowired
    private MitarbeiterDao dao;
    @Autowired
    private PersonDao persondao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetMitarbeiterInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(10L);
    }

    @Test
    public void testGetMitarbeiter() throws Exception {
       Mitarbeiter mitarbeiter = dao.get(1L);

        assertNotNull(mitarbeiter);
     
    }

   
      @Test
    @ExpectedException(DataIntegrityViolationException.class)
    public void testUpdateMitarbeiter() throws Exception {
        Mitarbeiter mitarbeiter = dao.get(1L);
        	mitarbeiter.getPerson().setEigtName("Blödsinn");
      
        dao.save(mitarbeiter);
       flush();

        mitarbeiter = dao.get(1L);
        assertEquals("Blödsinn", mitarbeiter.getPerson().getEigtName());
        
       Mitarbeiter mitarbeiter1= new Mitarbeiter();
    	mitarbeiter1.setPerson(mitarbeiter.getPerson());
    	
        dao.saveMitarbeiter(mitarbeiter1); 
        
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
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveMitarbeiter() throws Exception {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setMitstatus(new Boolean(true));

        dao.save(mitarbeiter);
        flush();

        assertNotNull(mitarbeiter.getId());
        mitarbeiter = dao.get(mitarbeiter.getId());
        dao.remove(mitarbeiter);
        flush();

        // should throw DataAccessException
        dao.get(mitarbeiter.getId());
    }
    @Test
    public void testMitarbeiterSearch() throws Exception {
        // reindex all the data
        dao.reindex();

        List<Mitarbeiter> found = dao.search("nicht");
        assertEquals(1, found.size());
        Mitarbeiter mitarbeiter= found.get(0);
        assertEquals("Fritz Braun", mitarbeiter.getPerson().getEigtName());

        // test mirroring
        mitarbeiter = dao.get(1L);
        mitarbeiter.getPerson().setEigtName("Fridolin");
        dao.saveMitarbeiter(mitarbeiter);
       flush();
      flushSearchIndexes();
     
     //  dao.reindexAll(true);
        // now verify it is reflected in the index
        found = dao.search("Fridolin");
        assertEquals(1, found.size());
        mitarbeiter = found.get(0);
        assertEquals("Fridolin", mitarbeiter.getPerson().getEigtName());
    }
   
}

