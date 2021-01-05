package de.kw.example.springhibernateenvers.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityMangerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(getDataSource());
        emf.setPackagesToScan("de.kw.example.springhibernateenvers.entity");
        emf.setJpaProperties(hibernateProperties());
        emf.setJpaVendorAdapter(vendorAdapter);

        return emf;
    }

    @Bean
    public DataSource getDataSource() {
//        return getH2Datasource();
        return getPostgresDatasource();
    }

    private BasicDataSource getH2Datasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    private BasicDataSource getPostgresDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/envers");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(@Autowired LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }

    private Properties hibernateProperties() {
        Properties props = new Properties();
//        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        props.setProperty("hibernate.default_schema", "envers");
        // envers properties
        props.setProperty("org.hibernate.envers.audit_strategy", "org.hibernate.envers.strategy.internal.ValidityAuditStrategy");

        return props;
    }
}
