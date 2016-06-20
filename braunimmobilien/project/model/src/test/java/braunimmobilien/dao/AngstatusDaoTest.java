package braunimmobilien.dao;

import braunimmobilien.Constants;

import braunimmobilien.model.Angstatus;
import braunimmobilien.model.User;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.NotTransactional;
import static org.junit.Assert.*;

public class AngstatusDaoTest extends BaseDaoTestCase {
    @Autowired
    private AngstatusDao dao;
    

    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetAngstatusInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(10L);
    }

    @Test
    public void testGetAngstatus() throws Exception {
        Angstatus angstatus = dao.get(1L);

        assertNotNull(angstatus);
     
    }

   
    @Test
    @ExpectedException(DataIntegrityViolationException.class)
    public void testUpdateAngstatus() throws Exception {
        Angstatus angstatus = dao.get(1L);
        		angstatus.setStatustext("Blödsinn");
      
        dao.save(angstatus);
       flush();

        angstatus = dao.get(1L);
        assertEquals("Blödsinn", angstatus.getStatustext());
        
        Angstatus angstatus1= new Angstatus();
    	angstatus1.setStatustext("Blödsinn");
      
        dao.saveAngstatus(angstatus1); 
        
    }
    @Test
    public void testAngstatusExists() throws Exception {
        boolean b = dao.exists(1L);
        assertTrue(b);
    }

    @Test
    public void testAngstatusNotExists() throws Exception {
        boolean b = dao.exists(111L);
        assertFalse(b);
    }

   
    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveAngstatus() throws Exception {
        Angstatus angstatus = new Angstatus("teststatus");
        

        dao.save(angstatus);
        flush();

        assertNotNull(angstatus.getId());
        angstatus = dao.get(angstatus.getId());
        dao.remove(angstatus);
        flush();

        // should throw DataAccessException
        dao.get(angstatus.getId());
    }
    @Test
    public void testAngstatusSearch() throws Exception {
        // reindex all the data
        dao.reindex();

        List<Angstatus> found = dao.search("nicht");
        assertEquals(1, found.size());
        Angstatus angstatus= found.get(0);
        assertEquals("nicht aktiv", angstatus.getStatustext());

        // test mirroring
        angstatus = dao.get(2L);
        angstatus.setStatustext("aktivX");
        dao.saveAngstatus(angstatus);
        flush();
        flushSearchIndexes();

        // now verify it is reflected in the index
        found = dao.search("aktivX");
        assertEquals(1, found.size());
        angstatus = found.get(0);
        assertEquals("aktivX", angstatus.getStatustext());
    }
   
}
