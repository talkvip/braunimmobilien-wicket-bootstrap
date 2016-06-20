package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Eigtstatus;


/**
 * @author rjansen
 * 
 */
public interface EigtstatusDao extends GenericDao<Eigtstatus, Long> {

	  List<Eigtstatus> getEigtstatuss();

	
	  Eigtstatus saveEigtstatus(Eigtstatus eigentuemermuster);

}
