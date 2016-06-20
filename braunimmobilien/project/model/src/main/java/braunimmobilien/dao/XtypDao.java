package braunimmobilien.dao;

import java.util.List;
import braunimmobilien.model.Xtyp;

	public interface XtypDao extends GenericDao<Xtyp,Long> {

		  List<Xtyp> getXtyps();
		  Xtyp saveXtyp(Xtyp xtyp);

	}
