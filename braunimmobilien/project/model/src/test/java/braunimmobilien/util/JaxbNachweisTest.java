package braunimmobilien.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.OutputStream;
import java.io.FileOutputStream;
import braunimmobilien.Constants;
import braunimmobilien.model.Nachweise;

import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.BaseManagerTestCase;
import javax.xml.bind.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class JaxbNachweisTest extends BaseManagerTestCase {
	 private Log log = LogFactory.getLog(JaxbNachweisTest.class);
	    @Autowired
	    private NachweiseManager mgr;
	 
	    private Nachweise nachweis;

	   

	   
	    @Test
	    public void testShowNachweisXML() throws Exception {
	      nachweis=mgr.get(1L);
	      nachweis.getAngebot().setAngobjzuords(null);
   	   nachweis.getAngebot().setNachweise(null);
   	   nachweis.getAngebot().setNachweise1(null);
   	   nachweis.getAngebot().setNachweise2(null);
   	    nachweis.setKunde(null);
   	   //   nachweis.getKunde().setPerson(null);
   	    //  nachweis.getKunde().setNachweise(null);;
   	    nachweis.setMitarbeiter(null);
   	    nachweis.setObjekt(null);
   	    nachweis.setPerson(null);
	      JAXBContext context = JAXBContext.newInstance( Nachweise.class );
	      Marshaller m = context.createMarshaller();
	      m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
	      OutputStream os = new FileOutputStream( "nosferatu.xml" );
	      
	      m.marshal( nachweis, os );
	//        assertEquals("unbestimmt", angstatus.getStatustext());
	       

	    
	       
	    }
}
