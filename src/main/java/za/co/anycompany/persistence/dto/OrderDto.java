package za.co.anycompany.persistence.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private long id;
    private String orderId;
    private BigDecimal amount;
    private BigDecimal VAT;
}
