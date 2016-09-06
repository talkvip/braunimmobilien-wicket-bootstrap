package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Mitarbeiter;


/**
 * @author rjansen
 * 
 */
public interface MitarbeiterDao extends GenericDao<Mitarbeiter, Long> {

	  List<Mitarbeiter> getMitarbeiteres();

	
	    Mitarbeiter saveMitarbeiter(Mitarbeiter mitarbeiter);

}
