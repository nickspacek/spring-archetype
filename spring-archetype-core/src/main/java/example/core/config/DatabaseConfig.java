package example.core.config;

import javax.sql.DataSource;

import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public interface DatabaseConfig {
	DataSource dataSource();
	HibernateJpaVendorAdapter vendorAdapter();
}
