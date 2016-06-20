package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Personen;


/**
 * @author rjansen
 * 
 */
public interface PersonDao extends GenericDao<Personen, Long> {

	  List<Personen> getPersonen(String strid);

	
	  Personen savePerson(Personen eigentuemermuster);

}

