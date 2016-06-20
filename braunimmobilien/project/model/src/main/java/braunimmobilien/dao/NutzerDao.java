package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Nutzer;


/**
 * @author rjansen
 * 
 */
public interface NutzerDao extends GenericDao<Nutzer, Long> {

	  List<Nutzer> getNutzer();

	
	    Nutzer saveNutzer(Nutzer nutzer);

}
