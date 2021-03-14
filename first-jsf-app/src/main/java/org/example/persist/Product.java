package org.example.persist;

import lombok.*;
import org.example.service.ProductDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name="products")
@NamedQueries({
        @NamedQuery(name = "productsByCategory", query = "select p from Product p join p.category pc where pc.id=:id")
})
public class Product implements Entities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(length = 1024)
    private String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column
    private BigDecimal price;

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Product(ProductDto productDto, Category category) {
        this(productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getPrice());
        this.category = category;
    }
    public Product(Long id) {
        this.id = id;
    }

}
