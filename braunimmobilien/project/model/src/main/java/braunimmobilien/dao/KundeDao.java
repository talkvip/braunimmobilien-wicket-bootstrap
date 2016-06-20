package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Kunde;


/**
 * @author rjansen
 * 
 */
public interface KundeDao extends GenericDao<Kunde, Long> {

	  List<Kunde> getKundees(String kundeigtnr);

	
	    Kunde saveKunde(Kunde kunde);

}
