package braunimmobilien.webapp.person;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
public class TelefonListModel implements Serializable{
	TelefoneModel telefonemodel;
	static final long serialVersionUID=3832626162173359411L;
public TelefoneModel getTelefonemodel() {
		return telefonemodel;
	}

public TelefonListModel(String eigttel) throws JiBXException{
		   IBindingFactory bfact = 
			        BindingDirectory.getFactory(TelefoneModel.class);
			   IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			   if(eigttel==null) eigttel="";
		  telefonemodel = (TelefoneModel)uctx.unmarshalDocument(new StringReader(eigttel), null);
		  }

public TelefonListModel(){
	telefonemodel = new TelefoneModel();
	List<TelefoneModel.Telefon> telefonlist=new ArrayList<TelefoneModel.Telefon>();
	telefonemodel.setTelefonList(telefonlist);
		  }
public String getTelXml()  throws JiBXException{
	 IBindingFactory bfact = 
		        BindingDirectory.getFactory(TelefoneModel.class);
	 IMarshallingContext mctx = bfact.createMarshallingContext();
	 mctx.setIndent(2);
	  StringWriter buffer=new StringWriter();
	  mctx.setOutput(buffer);
	  mctx.marshalDocument(telefonemodel);
	  return buffer.toString();}
}


