package za.co.anycompany.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.anycompany.persistence.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
