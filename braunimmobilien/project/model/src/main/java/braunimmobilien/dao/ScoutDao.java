package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Scout;


/**
 * @author rjansen
 * 
 */
public interface ScoutDao extends GenericDao<Scout, Long> {

	  List<Scout> getScoutes();

	
	    Scout saveScout(Scout Scout);

}
