package example.core.config;

import java.net.URI;

import javax.sql.DataSource;

import org.hibernate.dialect.PostgreSQL82Dialect;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Expects DATABASE_URL to be set (used by the Heroku Postgres Add-on). When
 * accessing the Heroku Postgres database server from a developer machine,
 * DATABASE_SSL must also be set to true.
 * 
 * @see <a
 *      href="https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java">Heroku
 *      Postgres Config</a>
 * 
 * @author Nick Spacek
 * 
 */
@Configuration
@Profile("heroku")
public class HerokuDatabaseConfig implements DatabaseConfig {
	@Autowired
	private Environment env;

	@Override
	@Bean
	public DataSource dataSource() {
		URI postgresUri = env.getProperty("DATABASE_URL", URI.class);

		PGPoolingDataSource dataSource = new PGPoolingDataSource();
		dataSource.setServerName(postgresUri.getHost());
		dataSource.setDatabaseName(postgresUri.getPath().substring(1));
		dataSource.setPortNumber(postgresUri.getPort());

		String[] userInfoParts = postgresUri.getUserInfo().split(":");
		dataSource.setUser(userInfoParts[0]);
		dataSource.setPassword(userInfoParts[1]);

		if (env.getProperty("DATABASE_SSL") != null) {
			dataSource.setSsl(true);
			dataSource.setSslfactory("org.postgresql.ssl.NonValidatingFactory");
		}

		return dataSource;
	}

	@Override
	@Bean
	public HibernateJpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setDatabasePlatform(PostgreSQL82Dialect.class.getName());
		return vendorAdapter;
	}
}
