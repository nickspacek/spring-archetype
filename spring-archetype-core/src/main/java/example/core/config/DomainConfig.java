package example.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("example.core.repository")
@Import(HibernateConfig.class)
public class DomainConfig {

}
