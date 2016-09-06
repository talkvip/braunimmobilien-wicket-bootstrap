package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Strassen;


/**
 * @author rjansen
 * 
 */
public interface StrassenDao extends GenericDao<Strassen, Long> {

	  List<Strassen> getStrassens(String ortid);

	
	  Strassen saveStrassen(Strassen eigentuemermuster);

}
