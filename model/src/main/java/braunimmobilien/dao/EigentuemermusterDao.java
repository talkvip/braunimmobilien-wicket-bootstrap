package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Eigentuemermuster;


/**
 * @author rjansen
 * 
 */
public interface EigentuemermusterDao extends GenericDao<Eigentuemermuster, Long> {

	  List<Eigentuemermuster> getEigentuemermusters();

	
	  Eigentuemermuster saveEigentuemermuster(Eigentuemermuster eigentuemermuster);

}
