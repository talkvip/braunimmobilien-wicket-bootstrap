package braunimmobilien.dao;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import braunimmobilien.model.Land;


/**
 * @author rjansen
 * 
 */
public interface LandDao extends GenericDao<Land, Long> {

	  List<Land> getLands();

	
	  Land saveLand(Land eigentuemermuster);

}
