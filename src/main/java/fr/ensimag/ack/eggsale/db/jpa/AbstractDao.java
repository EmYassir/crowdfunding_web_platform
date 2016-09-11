package fr.ensimag.ack.eggsale.db.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.ensimag.ack.eggsale.db.entity.BaseEntity;
import fr.ensimag.ack.eggsale.db.facade.BaseFacade;

public abstract class AbstractDao<T extends BaseEntity> implements BaseFacade<T> {
	@Inject
	private GenericDao gDao;

	private Class<T> entityClass;

	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public void persist(T entity) {
		this.gDao.persist(entity);
	}

	public void merge(T entity) {
		this.gDao.merge(entity);
	}

	@Override
	public void drop(T entity) {
		this.gDao.remove(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		return (T) this.gDao.find(id, getEntityClass());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return (List<T>) this.gDao.findAll(getEntityClass());
	}

	@SuppressWarnings("unchecked")
	public List<T> findRange(int[] range) {
		return (List<T>) this.gDao.findRange(range, getEntityClass());
	}

	public int count() {
		return this.gDao.count(getEntityClass());
	}

	public void flush() {
		this.gDao.flush();
	}

	public EntityManager getEntityManager() {
		return this.gDao.getEntityManager();
	}

	public Class<T> getEntityClass() {
		return this.entityClass;
	}
}
