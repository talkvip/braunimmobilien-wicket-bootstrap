package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Eigentuemertyp;


/**
 * @author rjansen
 * 
 */
public interface EigentuemertypDao extends GenericDao<Eigentuemertyp, Long> {

	  List<Eigentuemertyp> getEigentuemertyps();

	
	  Eigentuemertyp saveEigentuemertyp(Eigentuemertyp eigentuemermuster);

}

