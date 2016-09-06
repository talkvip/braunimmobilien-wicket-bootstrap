package braunimmobilien.webapp.person;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.jibx.runtime.JiBXException;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
public class JibxTest extends TestCase {
	
	 public void testUnmarshall() throws Exception {
		  IBindingFactory bfact = 
			        BindingDirectory.getFactory(TelefoneModel.class);
			   IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			   String eigttel="<tns:telefoneModel xmlns:tns=\"http://braunimmobilien/webapp/person\">";
 eigttel=eigttel+"<tns:telefon>";
 eigttel=eigttel+"<tns:art>mobile</tns:art>";
		 eigttel=eigttel+" <tns:telefon>017661905727</tns:telefon>";
				 eigttel=eigttel+"</tns:telefon>";
						 eigttel=eigttel+"<tns:telefon>";
								 eigttel=eigttel+"<tns:art>home</tns:art>";
										 eigttel=eigttel+"<tns:telefon>04711821139</tns:telefon>";
												 eigttel=eigttel+"</tns:telefon>";
												 eigttel=eigttel+"</tns:telefoneModel>";
			   TelefoneModel	  telefonemodel = (TelefoneModel)uctx.unmarshalDocument(new StringReader(eigttel), null);
			List<TelefoneModel.Telefon> list=   telefonemodel.getTelefonList() ;  
			assertTrue(list.size()==2);
	 
	 }
	 public void testMarshall() throws Exception {
		 IBindingFactory bfact = 
			        BindingDirectory.getFactory(TelefoneModel.class);
		 IMarshallingContext mctx = bfact.createMarshallingContext();
		 mctx.setIndent(2);
		  StringWriter buffer=new StringWriter();
		  mctx.setOutput(buffer);
		  TelefoneModel.Telefon telefon=new TelefoneModel.Telefon();
		  telefon.setArt("mobile");
		  telefon.setTelefon("017661905727");
		  TelefoneModel telefonemodel=new TelefoneModel();
		  telefonemodel.getTelefonList().add(telefon);
		  mctx.marshalDocument(telefonemodel);
		  
		  String eigttel="<tns:telefoneModel xmlns:tns=\"http://braunimmobilien/webapp/person\">";
		  eigttel=eigttel+"<tns:telefon>";
		  eigttel=eigttel+"<tns:art>mobile</tns:art>";
		 		 eigttel=eigttel+"<tns:telefon>017661905727</tns:telefon>";
		 				 eigttel=eigttel+"</tns:telefon>";
		 												 eigttel=eigttel+"</tns:telefoneModel>";
		 												 assertEquals(buffer.toString().replaceAll("  ", "").replaceAll("\n", ""),eigttel);
	 }
	
	 
}
