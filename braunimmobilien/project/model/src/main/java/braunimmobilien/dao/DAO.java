package braunimmobilien.dao;

import java.util.List;

import braunimmobilien.model.IEntity;

public interface DAO<EntityClass extends IEntity> {

List<EntityClass> list(long offset, long count);

List<EntityClass> findAll();

EntityClass findById(Long id);

EntityClass findByProperty(String propertyName, Object propertyValue);

void remove(EntityClass entity);

void persist(EntityClass entity);

long count();

Class<EntityClass> getClazz();

}
