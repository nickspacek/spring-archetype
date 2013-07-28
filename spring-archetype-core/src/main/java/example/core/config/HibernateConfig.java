package example.core.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("example.core.repository")
@EnableTransactionManagement
@Import({ InMemoryDatabaseConfig.class, HerokuDatabaseConfig.class })
public class HibernateConfig {
	@Autowired
	private Environment env;
	
	@Autowired
	private DatabaseConfig databaseConfig;

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = databaseConfig.vendorAdapter();

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(databaseConfig.dataSource());
		entityManagerFactory.setPackagesToScan("fiddlehead.core.domain");
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());

		Properties jpaProperties = new Properties();
		if (env.getProperty("hibernate.hbm2ddl.auto") != null) {
			jpaProperties.setProperty("hibernate.hbm2ddl.auto",
					env.getProperty("hibernate.hbm2ddl.auto"));
		}
		entityManagerFactory.setJpaProperties(jpaProperties);

		entityManagerFactory.afterPropertiesSet();
		return entityManagerFactory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
}
