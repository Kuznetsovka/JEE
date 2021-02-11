package org.example.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Repository<T extends Entities> {
    private final Map<Long, T> entities = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public List<T> findAll() {
        return new ArrayList<>(entities.values());
    }

    public T findById(Long id) {
        return entities.get(id);
    }

    public void saveOrUpdate(T entity) {
        if (entity.getId() == null) {
            Long id = identity.incrementAndGet();
            entity.setId(id);
        }
        entities.put(entity.getId(), entity);
    }

    public void deleteById(Long id) {
        entities.remove(id);
    }
}
