package za.co.anycompany.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.co.anycompany.persistence.dto.CustomerDto;
import za.co.anycompany.persistence.dto.OrderDto;
import za.co.anycompany.persistence.model.Customer;
import za.co.anycompany.persistence.model.Order;
import za.co.anycompany.persistence.repository.CustomerRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Transactional
    public CustomerDto addCustomer (@Valid final Customer customer) {
        try {
            return convertToDto(customerRepository.save(customer));
        } catch (Exception ex) {
            logger.error("Error adding a new Customer: ", ex);
            throw new RuntimeException(ex);
        }
    }

    public CustomerDto findCustomer (final long customerId) {
        try {
            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                logger.info("Orders " + customer.getOrders());
                return convertToDto(customer);
            } else {
                throw new RuntimeException("Customer does not exist");
            }
        } catch (Exception ex) {
            logger.error("Error finding the Customer: ", ex);
            throw new RuntimeException(ex);
        }
    }

    private CustomerDto convertToDto(final Customer customer) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        if (customer.getOrders() != null) {
            List<OrderDto> orders = customer.getOrders().stream().map(this::convertOrderToDto).toList();
            customerDto.setOrders(orders);
        }
        return customerDto;
    }

    private OrderDto convertOrderToDto(final Order order) {
        return  modelMapper.map(order, OrderDto.class);
    }
}
