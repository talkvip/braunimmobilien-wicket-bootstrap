package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Kondition;


/**
 * @author rjansen
 * 
 */
public interface KonditionDao extends GenericDao<Kondition, String> {

	  List<Kondition> getKonditionen();

	
	    Kondition saveKondition(Kondition kondition);

}

