package fr.ensimag.ack.eggsale.db.jpa;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ensimag.ack.eggsale.db.entity.BaseEntity;

@ApplicationScoped
public class GenericDao {
	@Inject
    private EntityManager entityManager;

    public void persist(BaseEntity entity) {
        getEntityManager().persist(entity);
    }

    public void merge(BaseEntity entity) {
        getEntityManager().merge(entity);
    }

    public void remove(BaseEntity entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public BaseEntity find(Object id, Class<? extends BaseEntity> entityClass) {
        BaseEntity entity = getEntityManager().find(entityClass, id);
        return entity;
    }
    
    @SuppressWarnings("unchecked")
    public List<? extends BaseEntity> findAll(Class<? extends BaseEntity> entityClass) {
        CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        List<? extends BaseEntity> entities;

        cq.select(cq.from(entityClass));

        Query q = getEntityManager().createQuery(cq);
        entities = q.getResultList();

        return entities;
    }

    @SuppressWarnings("unchecked")
    public List<? extends BaseEntity> findRange(int[] range, Class<? extends BaseEntity> entityClass) {
        CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        List<? extends BaseEntity> entities;

        cq.select(cq.from(entityClass));

        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        entities = q.getResultList();

        return entities;
    }

    public int count(Class<? extends BaseEntity> entityClass) {
        CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<? extends BaseEntity> rt = cq.from(entityClass);

        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);

        return ((Long) q.getSingleResult()).intValue();
    }

    public void flush() {
        this.entityManager.flush();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
