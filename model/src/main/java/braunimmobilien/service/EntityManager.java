package braunimmobilien.service;
import braunimmobilien.model.IEntity;

import java.util.List;


public interface EntityManager<EntityClass extends IEntity> {

List<EntityClass> list(long offset, long count);

List<EntityClass> findAll();

EntityClass findById(Long id);

EntityClass findByProperty(String propertyName, Object propertyValue);

void remove(EntityClass entity);

void persist(EntityClass entity);

long count();
}