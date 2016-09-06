package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Objektart;


/**
 * @author rjansen
 * 
 */
public interface ObjektartDao extends GenericDao<Objektart, Long> {

	  List<Objektart> getObjektarts();

	
	  Objektart saveObjektart(Objektart eigentuemermuster);

}
