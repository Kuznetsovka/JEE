package org.example.service;

import org.example.persist.Category;
import org.example.persist.Product;
import org.example.repository.CategoryRepository;
import org.example.repository.ProductRepository;
import org.example.rest.ProductServiceRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.*;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductServiceRest {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(this::buildProductDto)
                .collect(Collectors.toList());
    }

    private ProductDto buildProductDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        Category category = product.getCategory();
        dto.setCategoryId(category != null ? category.getId() : null);
        dto.setCategoryName(category != null ? category.getTitle() : null);
        return dto;
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return buildProductDto(product);
        }
        return null;
    }

    @TransactionAttribute
    @Override
    public ProductDto findByName(String name) {
        Product product = productRepository.productByName(name);
        if (product != null) {
            return buildProductDto(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    public void insert(ProductDto product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

    @Override
    public void update(ProductDto product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
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
    @Override
    public List<ProductDto> productsByCategory(Long id) {
        return productRepository.productsByCategory(id).stream()
                .map(this::buildProductDto)
                .collect(Collectors.toList());
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
