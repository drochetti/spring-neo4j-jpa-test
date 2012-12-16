package com.github.drochetti.springneo4j.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDriver;
import org.hibernate.dialect.DerbyDialect;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class JpaConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setDriverClass(EmbeddedDriver.class.getName());
		ds.setJdbcUrl("jdbc:derby:data/spring_neo4j_jpa_db;create=true");
		ds.setUsername("spring");
		ds.setPassword("neo4j_jpa");
		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
	    entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan("com.github.drochetti.springneo4j.model");
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactory.setPersistenceProvider(new HibernatePersistence());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", DerbyDialect.class.getName());
		jpaProperties.put("hibernate.show_sql", Boolean.TRUE.toString());
		jpaProperties.put("hibernate.query.jpaql_strict_compliance", Boolean.FALSE.toString());
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		entityManagerFactory.setJpaProperties(jpaProperties);
		
	    return entityManagerFactory;
	}

//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		return new JpaTransactionManager(entityManagerFactory().getObject());
//	}

}
