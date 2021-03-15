package org.example.dto;

import lombok.*;
import org.example.persist.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class OrderDto {

    private Long id;
    private UserDto user;
    private BigDecimal sum;
    private String address;
//    private List<OrderDetails> details;
    private OrderStatus status;
}
