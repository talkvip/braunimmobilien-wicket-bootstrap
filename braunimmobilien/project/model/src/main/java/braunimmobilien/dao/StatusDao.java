package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Status;


/**
 * @author rjansen
 * 
 */
public interface StatusDao extends GenericDao<Status, Long> {

	  List<Status> getStatuss();

	
	  Status saveStatus(Status eigentuemermuster);

}
