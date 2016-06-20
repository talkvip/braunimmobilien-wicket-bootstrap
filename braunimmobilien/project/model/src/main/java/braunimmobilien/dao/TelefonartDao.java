package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Telefonart;


/**
 * @author rjansen
 * 
 */
public interface TelefonartDao extends GenericDao<Telefonart, Long> {

	  List<Telefonart> getTelefonartes();

	
	    Telefonart saveTelefonart(Telefonart telefonart);

}
