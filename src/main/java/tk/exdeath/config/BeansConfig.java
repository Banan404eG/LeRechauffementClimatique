package tk.exdeath.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tk.exdeath.model.logic.Registration;

@Configuration
public class BeansConfig {

    @Bean
    @Scope("singleton")
    public Registration getRegistration() {
        return new Registration();
    }
}
