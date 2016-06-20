package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Objekte;


/**
 * @author rjansen
 * 
 */
public interface ObjektDao extends GenericDao<Objekte, Long> {

	  List<Objekte> getObjektes(String strid);
	  List<Objekte> getObjekteNachObjektsuch (Long objektsuchid);
	  List<Objekte> getObjekteNachVorlagedatum (java.util.Date beginDate,java.util.Date endDate);
		
	  Objekte saveObjekt(Objekte eigentuemermuster);

}

