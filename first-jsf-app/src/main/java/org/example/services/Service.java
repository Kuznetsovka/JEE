package org.example.services;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Service <T> {

    List<T> findAll();

    T findById(Long id);

    Long countAll();

    void saveOrUpdate(T entity);

    void deleteById(Long id);
}
