package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Angstatus;


/**
 * @author rjansen
 * 
 */
public interface AngstatusDao extends GenericDao<Angstatus, Long> {

	  List<Angstatus> getAngstatuses();

	
	    Angstatus saveAngstatus(Angstatus angstatus);

}
