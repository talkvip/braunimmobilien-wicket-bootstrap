package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Angebot;

/**
 * @author rjansen
 * 
 */
public interface AngebotDao extends GenericDao<Angebot, String> {

	  List<Angebot> getAngebote();


	    Angebot saveAngebot(Angebot angebot);

}