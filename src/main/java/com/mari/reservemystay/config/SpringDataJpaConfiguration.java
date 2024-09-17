package com.mari.reservemystay.config;

import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableJpaRepositories("com.mari")
@EnableTransactionManagement
public class SpringDataJpaConfiguration {

    private final ResourceLoader resourceLoader;

    public SpringDataJpaConfiguration(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public LocalSessionFactoryBean entityManagerFactory(DataSource dataSource) {
        var factoryBean = new LocalSessionFactoryBean();
        factoryBean.setResourceLoader(resourceLoader);
        factoryBean.setDataSource(dataSource);
        factoryBean.setMappingLocations(loadResources());
        factoryBean.setPackagesToScan("com.mari");
        return factoryBean;
    }

    @Bean
    @ConditionalOnMissingBean(PlatformTransactionManager.class)
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @SneakyThrows
    private Resource[] loadResources() {
        return ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources("classpath*:/com/mari/**/*.hbm.xml");
    }
}