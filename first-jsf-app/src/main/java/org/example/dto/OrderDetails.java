//package org.example.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class OrderDetails {
//
//    private Long id;
//    private OrderDto order;
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private ProductDto product;
//    @Column
//    private BigDecimal amount;
//    @Column
//    private BigDecimal price;
//
//    public OrderDetails(OrderDto order, ProductDto product, Long amount) {
//        this.order = order;
//        this.product = product;
//        this.amount = new BigDecimal(amount);
//        this.price = new BigDecimal(String.valueOf(product.getPrice()));
//    }
//}
