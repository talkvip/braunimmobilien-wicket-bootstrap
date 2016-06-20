package braunimmobilien.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import braunimmobilien.dao.TypeDao;
import braunimmobilien.dao.UserDao;
import braunimmobilien.service.TypeManager;
import braunimmobilien.service.TypeExistsException;
import braunimmobilien.model.Type;

/**
 * Implementation of Manager to handle Status data.
 * 
 * @author rjansen
 */
@Service("typeManager")
public class TypeManagerImpl extends GenericManagerImpl<Type, Long> implements TypeManager {

  private TypeDao typeDao;

  @Autowired
  public void setTypeDao(TypeDao typeDao) {
      this.dao = typeDao;
      this.typeDao = typeDao;
  }

  /**
   * {@inheritDoc}
   */
  public Type getType(Long typeId) {
      return typeDao.get(typeId);
  }

  /**
   * {@inheritDoc}
   */
  public List<Type> getTypees() {
      return typeDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  public Type saveType(Type type) throws TypeExistsException {

    
         


      try {
          return typeDao.saveType(type);
      } catch (DataIntegrityViolationException e) {
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new TypeExistsException("Type '" + type.getType() + "' already exists!");
      } catch (JpaSystemException e) { // needed for JPA
          //e.printStackTrace();
          log.warn(e.getMessage());
          throw new TypeExistsException("Type '" + type.getType() + "' already exists!");
      }
  }

  /**
   * {@inheritDoc}
   */
  public void removeType(Type type) {
      log.debug("removing user: " + type);
      typeDao.remove(type);
  }

  /**
   * {@inheritDoc}
   */
  public void removeType(String typeId) {
      log.debug("removing user: " + typeId);
      typeDao.remove(new Long(typeId));
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
  public List<Type> search(String searchTerm) {
      return super.search(searchTerm, Type.class);
  }

 
}
