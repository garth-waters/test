package za.co.anycompany.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.co.anycompany.persistence.dto.OrderDto;
import za.co.anycompany.persistence.model.Customer;
import za.co.anycompany.persistence.model.Order;
import za.co.anycompany.persistence.repository.CustomerRepository;
import za.co.anycompany.persistence.repository.OrderRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public OrderDto placeOrder(@Valid final Order order, final long customerId) {
        try {
            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                order.setVAT(customer.getVAT());
                return convertToDto(orderRepository.save(order));
            } else {
                throw new RuntimeException("Customer does not exist");
            }
        } catch (Exception ex){
            logger.error("Error adding a new Customer Order: ", ex);
            throw new RuntimeException (ex);
        }
    }

    private OrderDto convertToDto(final Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
