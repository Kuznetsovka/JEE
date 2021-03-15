package org.example.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    List<ProductDto> findAll();

    ProductDto findById(Long id);

    Long countAll();

    List<ProductDto> productsByCategory(Long id);

    void saveOrUpdate(ProductDto product);

    void deleteById(Long id);
}
