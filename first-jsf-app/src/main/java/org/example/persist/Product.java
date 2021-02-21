package org.example.persist;

import lombok.*;

import javax.enterprise.context.Dependent;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Product implements Entities {

    private Long id;
    private String name;
    private String description;
    @Dependent
    private Category category;
    private BigDecimal price;

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Product(Long id) {
        this.id = id;
    }

}
