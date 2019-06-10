package com.gabi.backend.bikeparkend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration for Spring Data/JPA using annotations.
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Configuration
@EnableTransactionManagement
@EntityScan("com.gabi.backend.bikeparkend.model")
@EnableJpaRepositories(basePackages = "com.gabi.backend.bikeparkend.repository")
public class SpringDataConfig {
    @Autowired
    private Environment env;

    /*@Bean
    EntityManagerFactory entityManagerFactory() throws NamingException, PropertyVetoException {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(false);

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        //factory.setJpaProperties(vendorAdapter);
        //factory.setJpaProperties(hibernateProperties());
        factory.setPackagesToScan("com.recommendation.model");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("db.driver"));
        ds.setUrl(env.getProperty("db.url"));

        //DatabasePopulatorUtils.execute(dbPopulator(), ds);

        return new ConnectionPoolDataSource(ds);
    }*/

    /*private DatabasePopulator dbPopulator() {
        final ResourceDatabasePopulator dp = new ResourceDatabasePopulator();
        dp.addScript(new ClassPathResource("META-INF/data/sql/ddl.sql"));
        dp.addScript(new ClassPathResource("META-INF/data/sql/init.sql"));

        return dp;
    }

    @Bean
    Properties hibernateProperties() {
        final Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.dbcp.initialSize", "5");
        props.setProperty("hibernate.dbcp.maxActive", "20");
        props.setProperty("hibernate.dbcp.maxIdle", "20");
        props.setProperty("hibernate.dbcp.minIdle", "0");

        return props;
    }

    @Bean
    PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    *//**
     * @return persistence exception translator
     *//*
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }*/
}

