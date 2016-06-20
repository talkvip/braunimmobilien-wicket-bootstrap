package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Links;


/**
 * @author rjansen
 * 
 */
public interface LinksDao extends GenericDao<Links, Long> {

	  List<Links> getLinkses();

	
	    Links saveLinks(Links angstatus);

}
