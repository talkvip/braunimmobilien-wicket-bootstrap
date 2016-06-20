package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Objperszuord;


/**
 * @author rjansen
 * 
 */
public interface ObjperszuordDao extends GenericDao<Objperszuord, Long> {

	  List<Objperszuord> getObjperszuordes();

	
	    Objperszuord saveObjperszuord(Objperszuord objperszuord);

}