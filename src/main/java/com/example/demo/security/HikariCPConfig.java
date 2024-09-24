package com.example.demo.security;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
class HikariCPConfig {

   @Value("${spring.datasource.url}")
   private String dataSourceUrl;

   @Value("${spring.datasource.username}")
   private String user;

   @Value("${spring.datasource.password}")
   private String password;

   @Value("${spring.datasource.dataSourceClassName}")
   private String dataSourceClassName;

   @Value("${spring.datasource.poolName}")
   private String poolName;

   @Value("${spring.datasource.connectionTimeout}")
   private int connectionTimeout;

   @Value("${spring.datasource.maxLifetime}")
   private int maxLifetime;

   @Value("${spring.datasource.maximumPoolSize}")
   private int maximumPoolSize;

   @Value("${spring.datasource.minimumIdle}")
   private int minimumIdle;

   @Value("${spring.datasource.idleTimeout}")
   private int idleTimeout;

   // @Value("${spring.datasource.allowMultiQueries}")
   // private boolean allowMultiQueries;
   // @Value("${spring.datasource.type}")
   // private int dsType;

   @Value("${spring.jpa.properties.hibernate.dialect}")
   private String HIBERNATE_DIALECT;

   @Value("${spring.jpa.show-sql}")
   private String HIBERNATE_SHOW_SQL;

   @Value("${spring.jpa.hibernate.ddl-auto}")
   private String HIBERNATE_HBM2DDL_AUTO;

   @Value("${entitymanager.packagesToScan}")
   private String ENTITYMANAGER_PACKAGES_TO_SCAN;

   @Value("${hibernate.jdbc.lob.non_contextual_creation}")
   private String NON_CONTEXTUAL_CREATION;

   @Autowired
   private Environment env;

   @Bean(name = "primaryDataSourceHCPEODB")
   public synchronized HikariDataSource primaryDataSource() {
      Properties dsProps = new Properties();
      dsProps.put("url", dataSourceUrl);
      dsProps.put("user", user);
      dsProps.put("password", password);
      /*
       * dsProps.put("prepStmtCacheSize",250);
       * dsProps.put("prepStmtCacheSqlLimit",2048);
       * dsProps.put("cachePrepStmts",Boolean.TRUE);
       * dsProps.put("useServerPrepStmts",Boolean.TRUE);
       */

      Properties configProps = new Properties();
      configProps.put("dataSourceClassName", dataSourceClassName);
      configProps.put("poolName", poolName);
      configProps.put("maximumPoolSize", maximumPoolSize);
      configProps.put("minimumIdle", minimumIdle);
      configProps.put("connectionTimeout", connectionTimeout);
      configProps.put("idleTimeout", idleTimeout);
      configProps.put("leakDetectionThreshold", 60000);
      configProps.put("dataSourceProperties", dsProps);
      // configProps.put("allowMultiQueries", allowMultiQueries);

      HikariConfig hc = new HikariConfig(configProps);
      HikariDataSource ds = new HikariDataSource(hc);
      return ds;
   }

   @Bean(name = "entityManagerFactory")
   public LocalSessionFactoryBean sessionFactory() {
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(primaryDataSource());
      sessionFactory.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
      Properties hibernateProperties = new Properties();
      hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
      hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
      hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
      hibernateProperties.put("hibernate.jdbc.lob.non_contextual_creation", NON_CONTEXTUAL_CREATION);
      sessionFactory.setHibernateProperties(hibernateProperties);
      return sessionFactory;
   }

   /*
    * @Bean public HibernateTransactionManager transactionManager() {
    * HibernateTransactionManager txManager = new HibernateTransactionManager();
    * txManager.setSessionFactory(sessionFactory().getObject()); return txManager;
    * }
    */

   /**
    * Declare the transaction manager.
    */

   /**
    * PersistenceExceptionTranslationPostProcessor is a bean post processor
    * which adds an advisor to any bean annotated with Repository so that any
    * platform-specific exceptions are caught and then rethrown as one
    * Spring's unchecked data access exceptions (i.e. a subclass of
    * DataAccessException).
    */
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   }

}
