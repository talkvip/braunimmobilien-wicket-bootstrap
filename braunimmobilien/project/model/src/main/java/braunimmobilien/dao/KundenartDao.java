package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Kundenart;


/**
 * @author rjansen
 * 
 */
public interface KundenartDao extends GenericDao<Kundenart, Long> {

	  List<Kundenart> getKundenartes();

	
	    Kundenart saveKundenart(Kundenart kundenart);

}
