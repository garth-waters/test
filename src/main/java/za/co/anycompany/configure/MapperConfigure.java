package za.co.anycompany.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigure {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
