package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Objarttyp;


/**
 * @author rjansen
 * 
 */
public interface ObjarttypDao extends GenericDao<Objarttyp, Long> {

	  List<Objarttyp> getObjarttypes();

	
	    Objarttyp saveObjarttyp(Objarttyp Objarttyp);

}
