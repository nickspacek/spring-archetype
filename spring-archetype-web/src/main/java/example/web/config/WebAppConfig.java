package example.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.core.config.DomainConfig;

@Configuration
@Import(DomainConfig.class)
public class WebAppConfig {

}
