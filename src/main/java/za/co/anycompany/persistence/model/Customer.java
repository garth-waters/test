package za.co.anycompany.persistence.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Customers")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Name is mandatory")
    @Size(min = 3, max = 60, message = "The customer name '${validatedValue}' must be between {min} and {max} characters long")
    private String name;

    @NotEmpty(message = "Country is mandatory")
    @Size(min = 1, max = 20, message = "The country '${validatedValue}' must be between {min} and {max} characters lon")
    private String country;

    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Invalid Date of Birth")
    private LocalDate dateOfBirth;

    private BigDecimal VAT;

    @OneToMany(mappedBy="customerId", fetch = FetchType.EAGER)
    private Set<Order> orders;
}
