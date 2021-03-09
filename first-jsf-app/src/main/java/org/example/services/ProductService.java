package org.example.services;

import org.example.dto.ProductDto;
import org.example.persist.Category;
import org.example.persist.Product;
import org.example.repository.CategoryRepository;
import org.example.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class ProductService implements Service<ProductDto> {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return new ProductDto(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductDto product) {
        logger.info("Saving product with id {}" , product.getId());
        Category category = null;
        if (product.getCategoryId() != null) {
            category = categoryRepository.getReference(product.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }
    @TransactionAttribute
    public List<ProductDto> productsByCategory(Long id) {
        return productRepository.productsByCategory(id).stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
