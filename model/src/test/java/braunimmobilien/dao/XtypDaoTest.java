package braunimmobilien.dao;

import braunimmobilien.Constants;

import braunimmobilien.model.Xtyp;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.NotTransactional;
import static org.junit.Assert.*;

public class XtypDaoTest extends BaseDaoTestCase {
    @Autowired
    private XtypDao dao;
    

    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetXtypInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(10L);
    }

    @Test
    public void testGetXtyp() throws Exception {
        Xtyp xtyp = dao.get(1L);

        assertNotNull(xtyp);
     
    }

   
    @Test
    @ExpectedException(DataIntegrityViolationException.class)
    public void testUpdateXtyp() throws Exception {
        Xtyp xtyp = dao.get(1L);
        		xtyp.setXtypkuerzel("Blöd");
      
        dao.save(xtyp);
       flush();

        xtyp = dao.get(1L);
        assertEquals("Blöd", xtyp.getXtypkuerzel());
        
        Xtyp xtyp1=new Xtyp();
    	xtyp1.setXtypkuerzel("Blöd");
      
        dao.saveXtyp(xtyp1); 
        
    }
    @Test
    public void testXtypExists() throws Exception {
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
    public void testAddAndRemoveXtyp() throws Exception {
        Xtyp xtyp = new Xtyp("testxtyp");
        

        dao.save(xtyp);
        flush();

        assertNotNull(xtyp.getId());
        xtyp = dao.get(xtyp.getId());
        dao.remove(xtyp);
        flush();

        // should throw DataAccessException
        dao.get(xtyp.getId());
    }
    @Test
    public void testXtypSearch() throws Exception {
        // reindex all the data
        dao.reindex();

        List<Xtyp> found = dao.search("XNM");
        assertEquals(1, found.size());
        Xtyp xtyp= found.get(0);
        assertEquals("XNM", xtyp.getXtypkuerzel());

        // test mirroring
        xtyp = dao.get(1L);
        xtyp.setXtypkuerzel("XXX XYZ");
        dao.saveXtyp(xtyp);
        flush();
        flushSearchIndexes();

        // now verify it is reflected in the index
        found = dao.search("XXX");
        assertEquals(1, found.size());
        xtyp = found.get(0);
        assertEquals("XXX XYZ", xtyp.getXtypkuerzel());
    }
   
}
