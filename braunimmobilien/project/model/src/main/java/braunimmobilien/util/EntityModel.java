package braunimmobilien.util;

import java.io.Serializable;

import javax.persistence.EntityNotFoundException;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;

import braunimmobilien.model.AbstractEntity;
import braunimmobilien.service.EntityLoader;
import braunimmobilien.model.Identifiable;
import braunimmobilien.model.BaseObject;

import java.lang.reflect.Field;
public  class EntityModel<T extends Identifiable< ? >>
implements IModel<T>{

/**
*
*/
private static final long serialVersionUID = -1926446968284768121L;


public EntityLoader entityLoader;

public EntityLoader getEntityLoader() {
	return entityLoader;
}

public void setEntityLoader(EntityLoader entityLoader) {
	this.entityLoader = entityLoader;
}

public Serializable id;

public T entity;

public T getEntity() {
	return entity;
}

public void setEntity(T entity) {
	if (entity!=null){
		if (entity.getId()!=null){
			this.id=entity.getId();
			this.entity = null;
		}
		else{this.entity = null;
		this.id=null;}
	}
	else{this.entity = null;
	this.id=null;}
}

private final Class clazz;

public EntityModel(T entity) {
if (entity!=null) this.clazz = entity.getClass();
else this.clazz=null;
id=null;
this.entity = entity;

}

public EntityModel(Class< ? extends T> clazz, Serializable id)
{
this.clazz = clazz;
this.id = id;

}

public T getObject() {
	try{
if (entity == null) {
if (id != null) {
entity = load(id);

if (entity == null) {
throw new EntityNotFoundException("Entity of type " + clazz
+ " with id " + id + " could not be found.");
}
}
}
else{
	
	 for (Field f : entity.getClass().getDeclaredFields()) {
		    Class<?> clazz = f.getType();
		    f.setAccessible(true);
		  if (!clazz.isPrimitive()) {
			  if(f.get(entity) instanceof BaseObject) { 
				  if(f.get(entity)!=null){
	   	  Field field=clazz.getDeclaredField("id");
	   	 field.setAccessible(true);
	   	if(field.getType().getName().equals("java.lang.String")){ 
				f.set(entity,entityLoader.load(clazz,((String) field.get(f.get(entity)))));
			 	  }
		if(field.getType().getName().equals("java.lang.Long")){ 
		f.set(entity,entityLoader.load(clazz,((Long) field.get(f.get(entity)))));
	 	  }
				  }
			  }
		    } 	 
	 }
}
	}
	    	 catch(Exception e){System.err.println("Big error");}
	    
return entity;
}

public void detach() {
if (entity != null) {
if (entity.getId() != null) {
id = entity.getId();
entity = null;
}
}
}

protected T load(Serializable id) {
return (T) entityLoader.load(clazz, id);
}

public void setObject(T object) {
throw new UnsupportedOperationException(getClass()
+ " does not support #setObject(T entity)");
}
}