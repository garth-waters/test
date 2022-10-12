package za.co.anycompany.persistence.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class CustomerDto {
    private long id;
    private String name;
    private String country;
    private LocalDate dateOfBirth;
    private BigDecimal VAT;
    private List<OrderDto> orders;
}
