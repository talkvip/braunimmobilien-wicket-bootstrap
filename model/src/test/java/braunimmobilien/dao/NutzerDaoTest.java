package braunimmobilien.dao;
import braunimmobilien.Constants;

import braunimmobilien.model.Nutzer;


import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.NotTransactional;
import static org.junit.Assert.*;

public class NutzerDaoTest extends BaseDaoTestCase {
    @Autowired
    private NutzerDao dao;
    

    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetNutzerInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(10L);
    }

    @Test
    public void testGetNutzer() throws Exception {
        Nutzer nutzer = dao.get(1L);

        assertNotNull(nutzer);
     
    }

   
    @Test
    @ExpectedException(DataIntegrityViolationException.class)
    public void testUpdateNutzer() throws Exception {
        Nutzer nutzer = dao.get(1L);
        		nutzer.setUsername("Blödsinn");
      
        dao.save(nutzer);
       flush();

        nutzer = dao.get(1L);
        assertEquals("Blödsinn", nutzer.getUsername());
        
        Nutzer nutzer1=new Nutzer();
    	nutzer1.setUsername("Blödsinn");
    	nutzer1.setDescription("Blödsinn");
    	nutzer1.setPassword("Wahnsinn");
    	nutzer1.setAdmin(true);
        dao.saveNutzer(nutzer1); 
        
    }
    @Test
    public void testNutzerExists() throws Exception {
        boolean b = dao.exists(1L);
        assertTrue(b);
    }

    @Test
    public void testNutzerNotExists() throws Exception {
        boolean b = dao.exists(111L);
        assertFalse(b);
    }

   
    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveNutzer() throws Exception {
        Nutzer nutzer = new Nutzer();
        nutzer.setUsername("Blödsinn");
        nutzer.setPassword("Braun");
        nutzer.setAdmin(true);
        dao.save(nutzer);
        flush();
        assertNotNull(nutzer.getId());
        nutzer = dao.get(nutzer.getId());
        dao.remove(nutzer);
        flush();

        // should throw DataAccessException
        dao.get(nutzer.getId());
    }
    @Test
    public void testAngstatusSearch() throws Exception {
        // reindex all the data
        dao.reindex();

        List<Nutzer> found = dao.search("root");
        assertEquals(1, found.size());
        Nutzer nutzer= found.get(0);
        assertEquals("root", nutzer.getUsername());

        // test mirroring
        nutzer = dao.get(2L);
        nutzer.setUsername("aktivX");
        dao.saveNutzer(nutzer);
        flush();
        flushSearchIndexes();

        // now verify it is reflected in the index
        found = dao.search("aktivX");
        assertEquals(1, found.size());
        nutzer = found.get(0);
        assertEquals("aktivX", nutzer.getUsername());
    }
   
}
