package org.loginmodule.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

@Repository
public abstract class CrudRepository<T> {
	
	Class<T> clazz;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public abstract Class<T> getClassName();
	
	public CrudRepository() {
		this.clazz = getClassName();
	}
	
	public T create(T object) {
		entityManager.persist(object);
		return object;
	}
	
	public T read(Object id){
		return entityManager.find(clazz, id);
	}
	
	
	public T update(T object) {
		return entityManager.merge(object);
	}
	
	public void delete(Object objectId){
		entityManager.remove(entityManager.find(clazz, objectId));
	}
	
	public CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();

	}
	
	public List<T> executeQuery(CriteriaQuery<T> query) {
        return entityManager.createQuery(query).getResultList();
    }
	
	 /**
     * Returns the list of objects
     *
     * @return
     */
    public List<T> list() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(clazz);
        query.from(clazz);
        return entityManager.createQuery(query).getResultList();
    }
	
}
