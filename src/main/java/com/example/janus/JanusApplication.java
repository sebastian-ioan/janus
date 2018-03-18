package com.example.janus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class JanusApplication {

    public static void main(String[] args) {
        SpringApplication.run(JanusApplication.class, args);
    }


    @Configuration
    @EnableTransactionManagement
    class PremailerConfig {


//        @Bean(name = "entityManagerFactory")
//        public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
//            LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//            emf.setDataSource(dataSource);
//            emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//            emf.setPackagesToScan("com.example.janus.model");
//            emf.setPersistenceUnitName("mascara");
//            Properties jpaProperties = new Properties();
//            jpaProperties.put("hibernate.current_session_context_class","org.springframework.orm.hibernate5.SpringSessionContext");
//            emf.setJpaProperties(jpaProperties);
//            emf.afterPropertiesSet();
//            return emf.getObject();
//        }

//        @Bean
//        public SessionFactory sessionFactory(EntityManagerFactory emf) {
//            return emf.unwrap(SessionFactory.class);
//        }


        @Bean
        public PlatformTransactionManager geJpaTransactionManager(EntityManagerFactory entityManagerFactory) {
            HibernateTransactionManager transactionManager = new HibernateTransactionManager();
            transactionManager.setSessionFactory(entityManagerFactory.unwrap(SessionFactory.class));
            return transactionManager;
        }

        @Bean
        public ObjectMapper objectMapper() {

            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper;

        }

    }
}
