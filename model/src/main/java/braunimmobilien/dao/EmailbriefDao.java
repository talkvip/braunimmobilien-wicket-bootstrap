package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Emailbrief;


/**
 * @author rjansen
 * 
 */
public interface EmailbriefDao extends GenericDao<Emailbrief, String> {

	  List<Emailbrief> getEmailbriefes();

	
	    Emailbrief saveEmailbrief(Emailbrief emailbrief);

}
