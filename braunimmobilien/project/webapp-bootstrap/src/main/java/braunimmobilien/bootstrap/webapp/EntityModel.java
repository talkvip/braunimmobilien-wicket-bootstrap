package braunimmobilien.bootstrap.webapp;

import java.io.Serializable;

import org.apache.wicket.spring.injection.annot.SpringBean;
import javax.persistence.EntityNotFoundException;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;
import braunimmobilien.model.AbstractEntity;
import braunimmobilien.service.EntityLoader;
import braunimmobilien.util.ScoutUtil;
import braunimmobilien.model.Identifiable;
import braunimmobilien.model.BaseObject;
import java.lang.reflect.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public  class EntityModel<T extends Identifiable< ? >>
implements IModel<T>{

/**
*
*/
private static final long serialVersionUID = -1926446968284768121L;
static Logger logger = LoggerFactory.getLogger(EntityModel.class);
@SpringBean
public EntityLoader entityLoader;

public Serializable id;

public T entity;



private final Class clazz;


public T getEntity() {
	logger.debug("getEntity "+entity+" id "+id);
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
//	else{this.entity = null;
//	this.id=null;}
	logger.debug("setEntity "+clazz+" "+entity+" id "+id);
}

public EntityModel(T entity) {
if (entity!=null) this.clazz = entity.getClass();
else this.clazz=null;
id=null;
this.entity = entity;
Injector.get().inject(this);
logger.debug("EntityModel(entity) "+entity+" id "+id);
}

public EntityModel(Class< ? extends T> clazz, Serializable id)
{
	
this.clazz = clazz;
this.id = id;
Injector.get().inject(this);
logger.debug("EntityModel(class,id) "+entity+" id "+id);
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
	if(id==null){
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
		f.set(entity,entityLoader.load(clazz,((Long) field.get(f.get(entity)))));}
	 	  }
				  }
			  }
		    } 	 
	 }
}

	}
	    	 catch(Exception e){System.err.println("Big error");
	    	 throw new EntityNotFoundException("Entity of type " + clazz
	    			 + " with id " + id + " could not be loaded :"+e); 	 
	    	 }
	logger.debug("getObject "+entity+" id "+id);
return entity;
}

public void detach() {
if (entity != null) {
if (entity.getId() != null) {
id = entity.getId();
entity = null;
}
}
logger.debug("detach "+entity+" id "+id);
}

protected T load(Serializable id) {
return (T) entityLoader.load(clazz, id);
}

public void setObject(T object) {
//	this.id=object.getId();
//	this.entity=object;
	logger.debug("setObject "+entity+" id "+id);
throw new UnsupportedOperationException(getClass()
+ " does not support #setObject(T entity)");
}
public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[EntityModel id = ")
		.append(this.id)
		.append(", Entity = ")
		.append(this.entity)
		.append("]");
	return b.toString();
}
}