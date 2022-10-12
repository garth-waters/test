package za.co.anycompany.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.anycompany.persistence.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
