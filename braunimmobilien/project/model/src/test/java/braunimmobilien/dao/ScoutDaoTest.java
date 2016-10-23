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
import braunimmobilien.model.Scout;
public class ScoutDaoTest extends BaseDaoTestCase {
    @Autowired
    private PersonDao dao;
    @Autowired
    private EigtstatusDao eigtstatusdao;
    @Autowired
    private StrassenDao strassendao;
    @Autowired
    private ScoutDao scoutdao;
    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetScoutInvalid() throws Exception {
        // should throw DataAccessException
        scoutdao.get(66538383L);
    }

    @Test
    public void testGetScout() throws Exception {
       Scout scout = scoutdao.get(66538384L);

        assertNotNull(scout);
     
    }

   
       @Test
    public void testUpdateScout() throws Exception {
        Scout scout = scoutdao.get(66538384L);
        	scout.setPerson(dao.get(2L));
      
        scoutdao.save(scout);
       flush();

        scout = scoutdao.get(66538384L);
        assertEquals("Fritz Leone", scout.getPerson().getEigtName());
       
        
    }

       @Test
    public void testSearchScout() throws Exception {
    	   flush();
           flushSearchIndexes();
     	  scoutdao.reindex();
     	
         List<Scout> found = scoutdao.search("66538384");
     assertEquals(1, found.size());
        
    }    
}

