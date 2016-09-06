package braunimmobilien.dao;

import braunimmobilien.Constants;

import braunimmobilien.model.Land;
import braunimmobilien.model.User;
import braunimmobilien.model.Orte;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.NotTransactional;
import static org.junit.Assert.*;

public class LandDaoTest extends BaseDaoTestCase {
    @Autowired
    private LandDao dao;
    

    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetLandInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(10L);
    }

    @Test
    public void testGetLand() throws Exception {
        Land land = dao.get(1L);

        assertNotNull(land);
        assertEquals("Nirgendwo", land.getLandname());
        assertEquals(1, land.getOrte().size());
     Orte ort=(Orte)land.getOrte().get(0);
     assertEquals("Irgendwo", ort.getOrtname());
    }

    @Test
    public void testAddOrt() throws Exception {
        Land land = dao.get(1L);

        assertNotNull(land);
        assertEquals("Nirgendwo", land.getLandname());
        assertEquals(1, land.getOrte().size());
     Orte ort=(Orte)land.getOrte().get(0);
     assertEquals("Irgendwo", ort.getOrtname());
     ort=new Orte();
     ort.setOrtplz("12345");
     ort.setLand(land);
     ort.setOrtname("Faulweiler");
     land.addOrt(ort);
     dao.save(land);
     assertEquals(2, land.getOrte().size());
     
    }
    @Test
    @ExpectedException(DataIntegrityViolationException.class)
    public void testUpdateLand() throws Exception {
        Land land = dao.get(1L);
        		land.setLandname("Blödsinn");
      
        dao.save(land);
       flush();

        land = dao.get(1L);
        assertEquals("Blödsinn", land.getLandname());
        assertEquals(1, land.getOrte().size());
        Land land1= new Land();
    	land1.setLandname("Blödsinn");
    	land1.setKennzeichen("D");
        dao.saveLand(land1); 
        
    }
    @Test
    public void testLandExists() throws Exception {
        boolean b = dao.exists(1L);
        assertTrue(b);
    }

    @Test
    public void testLandNotExists() throws Exception {
        boolean b = dao.exists(111L);
        assertFalse(b);
    }

   
    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveLand() throws Exception {
        Land land= new Land();
        land.setLandname("Blödsinn");
    	land.setKennzeichen("D");

        dao.save(land);
        flush();

        assertNotNull(land.getId());
        land = dao.get(land.getId());
        dao.remove(land);
        flush();

        // should throw DataAccessException
        dao.get(land.getId());
    }
    @Test
    public void testLandSearch() throws Exception {
    	 assertEquals(1, dao.getLands().size());
    	 Land land=dao.getLands().get(0);
    	 assertEquals("Nirgendwo", land.getLandname());
    	land= new Land();
         land.setLandname("Blödsinn");
     	land.setKennzeichen("D");
     	 dao.saveLand(land);
        // reindex all the data
        dao.reindex();
        assertEquals(2, dao.getLands().size());
        List<Land> found = dao.search("Blödsinn");
        assertEquals(1, found.size());
        land= found.get(0);
        assertEquals("Blödsinn", land.getLandname());
        found = dao.search("Nirgendwo");
        assertEquals(1, found.size());
        // test mirroring
        land = dao.get(1L);
        land.setLandname("aktivX");
        dao.saveLand(land);
        flush();
        flushSearchIndexes();

        // now verify it is reflected in the index
        found = dao.search("aktivX");
        assertEquals(1, found.size());
        land = found.get(0);
        assertEquals("aktivX", land.getLandname());
    }
   
}
