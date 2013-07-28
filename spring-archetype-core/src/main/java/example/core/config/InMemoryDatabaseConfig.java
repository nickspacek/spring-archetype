package example.core.config;

import javax.sql.DataSource;

import org.hibernate.dialect.HSQLDialect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile("development")
public class InMemoryDatabaseConfig implements DatabaseConfig {
	@Override
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().build();
	}

	@Override
	public HibernateJpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.HSQL);
		vendorAdapter.setDatabasePlatform(HSQLDialect.class.getName());
		return vendorAdapter;
	}
}
