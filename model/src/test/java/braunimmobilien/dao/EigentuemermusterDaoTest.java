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

import braunimmobilien.model.Eigentuemermuster;

public class EigentuemermusterDaoTest extends BaseDaoTestCase {
    @Autowired
    private EigentuemermusterDao dao;
    @Autowired
    private TypeDao typedao;
    @Autowired
    private PersonDao persondao;
    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetEigentuemermusterInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(10L);
    }

    @Test
    public void testGetEigentuemermuster() throws Exception {
        Eigentuemermuster eigentuemermuster = dao.get(1L);

        assertNotNull(eigentuemermuster);
     
    }

   
   /* @Test
    @ExpectedException(DataIntegrityViolationException.class)
    public void testUpdateEigentuemermuster() throws Exception {
        Eigentuemermuster eigentuemermuster = dao.get(1L);
        		eigentuemermuster.setEigentuemermuster("Blödsinn");
      
        dao.save(eigentuemermuster);
       flush();

        eigentuemermuster = dao.get(1L);
        assertEquals("Blödsinn", eigentuemermuster.getEigentuemermuster());
        
        Eigentuemermuster eigentuemermuster1= new Eigentuemermuster();
    	eigentuemermuster1.setEigentuemermuster("Blödsinn");
    	eigentuemermuster1.setType(typedao.get(new Long(2)));
    	eigentuemermuster1.setPerson(persondao.get(new Long(1)));
    	
        dao.saveEigentuemermuster(eigentuemermuster1); 
        
    }*/
    
    @Test
    public void testChangePersonEigentuemermuster() throws Exception {
        Eigentuemermuster eigentuemermuster = dao.get(1L);
    	eigentuemermuster.setPerson(persondao.get(new Long(2)));
        dao.saveEigentuemermuster(eigentuemermuster); 
        eigentuemermuster = null;
        eigentuemermuster = dao.get(1L);
        assertEquals("Fritz Leone", eigentuemermuster.getPerson().getEigtName());
    }
    
    
    @Test
    public void testEigentuemermusterExists() throws Exception {
        boolean b = dao.exists(1L);
        assertTrue(b);
    }

    @Test
    public void testEigentuemermusterNotExists() throws Exception {
        boolean b = dao.exists(111L);
        assertFalse(b);
    }

   
    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveEigentuemermuster() throws Exception {
        Eigentuemermuster eigentuemermuster = new Eigentuemermuster("teststatus");
        eigentuemermuster.setType(typedao.get(new Long(2)));
    	eigentuemermuster.setPerson(persondao.get(new Long(12345)));

        dao.save(eigentuemermuster);
        flush();

        assertNotNull(eigentuemermuster.getId());
        eigentuemermuster = dao.get(eigentuemermuster.getId());
        dao.remove(eigentuemermuster);
        flush();

        // should throw DataAccessException
        dao.get(eigentuemermuster.getId());
    }
    @Test
    public void testEigentuemermusterSearch() throws Exception {
        // reindex all the data
        dao.reindex();

        List<Eigentuemermuster> found = dao.search("%Braun%");
        assertEquals(1, found.size());
        Eigentuemermuster eigentuemermuster= found.get(0);
        assertEquals("%Braun%", eigentuemermuster.getEigentuemermuster());

        // test mirroring
        eigentuemermuster = dao.get(1L);
        eigentuemermuster.setEigentuemermuster("%aktiv%");
        dao.saveEigentuemermuster(eigentuemermuster);
        flush();
        flushSearchIndexes();

        // now verify it is reflected in the index
        found = dao.search("%aktiv%");
        assertEquals(1, found.size());
        eigentuemermuster = found.get(0);
        assertEquals("%aktiv%", eigentuemermuster.getEigentuemermuster());
    }
   
}

