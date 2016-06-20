package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Mitarbeiter;


/**
 * @author rjansen
 * 
 */
public interface MitrumpfDao extends GenericDao<Mitarbeiter, Long> {

	  List<Mitarbeiter> getMitrumpfes();

	
	    Mitarbeiter saveMitrumpf(Mitarbeiter mitrumpf);

}
