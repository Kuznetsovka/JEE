package org.example.persist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Entities {
    private static final String SEQ_NAME = "order_seq";

    private Long id;
    private LocalDateTime created;
    private LocalDateTime changed;
    private User user;
    private BigDecimal sum;
    private String address;
    private List<OrderDetails> details;
    private OrderStatus status;
}
