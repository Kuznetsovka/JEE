package org.example.dto;

import lombok.*;
import org.example.persist.Category;
import org.example.persist.Product;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private CategoryDto category;
    private Long categoryId;
    private String categoryName;
    private BigDecimal price;

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        Category category = product.getCategory();
        categoryId = category != null ? category.getId() : null;
        categoryName = category != null ? category.getTitle() : null;
    }

}
