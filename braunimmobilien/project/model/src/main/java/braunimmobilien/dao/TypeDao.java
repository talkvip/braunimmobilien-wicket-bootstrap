package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Type;


/**
 * @author rjansen
 * 
 */
public interface TypeDao extends GenericDao<Type, Long> {

	  List<Type> getTypees();

	
	    Type saveType(Type Type);

}
