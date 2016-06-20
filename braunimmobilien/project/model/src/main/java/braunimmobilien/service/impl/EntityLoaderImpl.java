package braunimmobilien.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import braunimmobilien.dao.EntityLoaderDao;
import braunimmobilien.service.EntityLoader;
@Service(value = "entityLoader")
@Transactional(rollbackFor = Exception.class)
public class EntityLoaderImpl implements EntityLoader {

	@Autowired
	private EntityLoaderDao mEntityLoader;

	public void setmEntityLoader(EntityLoaderDao mEntityLoader) {
		this.mEntityLoader = mEntityLoader;
	}

	@Override
	public <T> T load(Class<T> clazz, Serializable id) {
		return mEntityLoader.load(clazz, id);
	}

}