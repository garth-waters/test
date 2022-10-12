package za.co.anycompany;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.anycompany.persistence.dto.CustomerDto;
import za.co.anycompany.persistence.dto.OrderDto;
import za.co.anycompany.persistence.model.Customer;
import za.co.anycompany.persistence.model.Order;
import za.co.anycompany.service.CustomerService;
import za.co.anycompany.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringRunner.class)
class AnycompanyApplicationTests {
	@Autowired
	OrderService orderService;

	@Autowired
	CustomerService customerService;

	@Test
	void addCustomer() {
		LocalDate dob = LocalDate.of(1992, 2, 20);  // For public facing a pattern should be applied for date format e.g. dd/MM/yyyy
		Customer customer = Customer.builder()
				.VAT(new BigDecimal(15.0))
				.name("ABC International Limited")
				.country("UK")
				.dateOfBirth(dob)
				.build();

		CustomerDto newCustomer = customerService.addCustomer(customer);
		System.out.println("New Customer: " + newCustomer);
		addOrder(newCustomer.getId());
		getCustomer(newCustomer.getId());
		// errors can be caught and parsed for validation error
	}

	void addOrder(final long customerId) {
		Order order = Order.builder()
				.customerId(customerId)
				.amount(new BigDecimal(12.33))
				.orderId("20")
				.build();
				OrderDto orderDto = orderService.placeOrder(order, customerId);
		System.out.printf("New Order: " + orderDto);
		// errors can be caught and parsed for validation error
	}

	void getCustomer (final long customerId) {
		CustomerDto customerDto = customerService.findCustomer (customerId);
		System.out.println("Search customer: " + customerDto);
	}

}
