package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Objektsuch;


/**
 * @author rjansen
 * 
 */
public interface ObjektsuchDao extends GenericDao<Objektsuch, Long> {

	  List<Objektsuch> getObjektsuchs();

	
	  Objektsuch saveObjektsuch(Objektsuch eigentuemermuster);

}
