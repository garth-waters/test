package za.co.anycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AnycompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnycompanyApplication.class, args);
	}

}
