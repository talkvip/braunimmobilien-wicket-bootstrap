package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Angobjzuord;


/**
 * @author rjansen
 * 
 */
public interface AngobjzuordDao extends GenericDao<Angobjzuord, Long> {

	  List<Angobjzuord> getAngobjzuordes(String objid);

	
	    Angobjzuord saveAngobjzuord(Angobjzuord angobjzuord);

}