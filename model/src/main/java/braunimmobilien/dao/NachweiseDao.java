package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Objekte;


/**
 * @author rjansen
 * 
 */
public interface NachweiseDao extends GenericDao<Nachweise, Long> {

	  List<Nachweise> getNachweisees();

	  List<Nachweise> getNachweiseNachObjekte (Long objektid);
	   
	  Nachweise saveNachweise(Nachweise nachweise);

}
