package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Orte;


/**
 * @author rjansen
 * 
 */
public interface OrteDao extends GenericDao<Orte, Long> {

	  List<Orte> getOrtes(String landid);

	
	  Orte saveOrte(Orte ort);

}
