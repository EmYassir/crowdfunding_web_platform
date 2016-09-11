package fr.ensimag.ack.eggsale.db.facade;

import java.util.Collection;

public interface BaseFacade<T> {
	public T findById(Long id);
        public void persist(T obj);
        public Collection<T> findAll();
        public void drop(T obj);
}
