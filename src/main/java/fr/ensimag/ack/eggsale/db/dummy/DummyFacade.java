package fr.ensimag.ack.eggsale.db.dummy;

import fr.ensimag.ack.eggsale.db.entity.BaseEntity;
import fr.ensimag.ack.eggsale.db.facade.BaseFacade;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public abstract class DummyFacade<T extends BaseEntity> implements BaseFacade<T> {

    protected Map<Long, T> objs;
    private Long idCounter = 1L;

    public DummyFacade() {
        objs = new TreeMap<Long, T>();
    }

    @Override
    public T findById(Long id) {
        return objs.get(id);
    }
    
    @Override
    public void persist(T obj) {
        Iterator<T> it = objs.values().iterator();
        T tmp;
        while(it.hasNext()){
            tmp=(T)it.next();              
            if (tmp.equals(obj)) {
                tmp = obj;
                return;
            }
        }
        
        objs.put(idCounter, obj);
        obj.setId(idCounter);
        idCounter++;
    }
    
    
    @Override
    public void drop(T obj) {
        Iterator<Long> iterator = objs.keySet().iterator();
        while (iterator.hasNext()) {
            Long tmp = (Long) iterator.next();
            if (objs.get(tmp).equals(obj)) {
                objs.remove(tmp);
            }
        }
    }
   
    
    @Override
    public Collection<T> findAll() {
        return objs.values();
    }
}
