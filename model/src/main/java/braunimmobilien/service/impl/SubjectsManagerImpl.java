package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.SubjectsDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.SubjectsManager;
import braunimmobilien.service.SubjectsExistsException;
import braunimmobilien.model.Subjects;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("subjectsManager")
public class SubjectsManagerImpl extends GenericManagerImpl<Subjects, Long> implements SubjectsManager {

  private SubjectsDao subjectsDao;

  @Autowired
  public void setSubjectsDao(SubjectsDao subjectsDao) {
      this.dao = subjectsDao;
      this.subjectsDao = subjectsDao;
  }

  /**
   * {@inheritDoc}
   */
  public Subjects getSubjects(String subjectsId) {
      return subjectsDao.get(new Long(subjectsId));
  }

  /**
   * {@inheritDoc}
   */
  public List<Subjects> getSubjectses() {
      return subjectsDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Subjects saveSubjects(Subjects subjects) throws SubjectsExistsException {

    
         


      try {
          return subjectsDao.saveSubjects(subjects);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new SubjectsExistsException("Subjects '" + subjects.getSubject() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new SubjectsExistsException("Subjects '" + subjects.getSubject() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeSubjects(Subjects subjects) {
      log.debug("removing user: " + subjects);
      subjectsDao.remove(subjects);
  }

  /**
   * {@inheritDoc}
   */
  public void removeSubjects(String subjectsId) {
      log.debug("removing user: " + subjectsId);
      subjectsDao.remove(new Long(subjectsId));
  }

  /**
   * {@inheritDoc}
   *
   * @param username the login name of the human
   * @return User the populated user object
   * @throws UsernameNotFoundException thrown when username not found
   */
  

  /**
   * {@inheritDoc}
   */
  public List<Subjects> search(String searchTerm) {
      return super.search(searchTerm, Subjects.class);
  }

 
}
