package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.Subjects;


/**
 * @author rjansen
 * 
 */
public interface SubjectsDao extends GenericDao<Subjects, Long> {

	  List<Subjects> getSubjectses();

	
	    Subjects saveSubjects(Subjects Subjects);

}
