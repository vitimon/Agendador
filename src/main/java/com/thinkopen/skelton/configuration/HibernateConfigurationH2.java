package com.thinkopen.skelton.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySource({"classpath:/configuration.properties"})
@EnableTransactionManagement
@Configuration
public class HibernateConfigurationH2 {
	
	  @Value("${jdbc.driverClassName}")
	  private String driverClassName;
	  
	  @Value("${jdbc.url}")
	  private String jdbcUrl;
	  
	  @Value("${jdbc.username}")
	  private String jdbcUserName;
	  
	  @Value("${jdbc.password}")
	  private String jdbcPassword;
		
	  @Value("${app.entitiesPackage}")
	  private String entitiesPackage;
	  
	  @Value("${hibernate.dialect}")
	  private String hibernateDialect;
	  
	  @Value("${hibernate.show_sql}")
	  private String  hibernateShowSql;
	  
	  @Value("${hibernate.hbm2ddl.auto}")
	  private String hibernateGenerateDB;
	  
		 
	
	 @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan(entitiesPackage);
	        sessionFactory.setHibernateProperties(hibernateProperties());
	        return sessionFactory;
	    }

	    @Bean
	    public DataSource dataSource() {
	        BasicDataSource dataSource = new BasicDataSource();
	        dataSource.setDriverClassName(driverClassName);
	        dataSource.setUrl(jdbcUrl);
	        dataSource.setUsername(jdbcUserName);
	        dataSource.setPassword(jdbcPassword);	        
	        return dataSource;
	    }

	    @Bean
	    public PlatformTransactionManager hibernateTransactionManager() {
	        HibernateTransactionManager transactionManager
	          = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());
	        return transactionManager;
	    }

	    private final Properties hibernateProperties() {
	        Properties hibernateProperties = new Properties();	        
	        hibernateProperties.setProperty(
	          "hibernate.dialect", hibernateDialect);	        
	        hibernateProperties.setProperty(
	  	          "hibernate.show_sql", hibernateShowSql);
	        hibernateProperties.setProperty(
		  	          "hibernate.hbm2ddl.auto", hibernateGenerateDB);	        
	        return hibernateProperties;
	    }

		public String getDriverClassName() {
			return driverClassName;
		}

		public void setDriverClassName(String driverClassName) {
			this.driverClassName = driverClassName;
		}

		public String getJdbcUrl() {
			return jdbcUrl;
		}

		public void setJdbcUrl(String jdbcUrl) {
			this.jdbcUrl = jdbcUrl;
		}

		public String getJdbcUserName() {
			return jdbcUserName;
		}

		public void setJdbcUserName(String jdbcUserName) {
			this.jdbcUserName = jdbcUserName;
		}

		public String getJdbcPassword() {
			return jdbcPassword;
		}

		public void setJdbcPassword(String jdbcPassword) {
			this.jdbcPassword = jdbcPassword;
		}

		public String getEntitiesPackage() {
			return entitiesPackage;
		}

		public void setEntitiesPackage(String entitiesPackage) {
			this.entitiesPackage = entitiesPackage;
		}

		public String getHibernateDialect() {
			return hibernateDialect;
		}

		public void setHibernateDialect(String hibernateDialect) {
			this.hibernateDialect = hibernateDialect;
		}

		public String getHibernateShowSql() {
			return hibernateShowSql;
		}

		public void setHibernateShowSql(String hibernateShowSql) {
			this.hibernateShowSql = hibernateShowSql;
		}

		public String getHibernateGenerateDB() {
			return hibernateGenerateDB;
		}

		public void setHibernateGenerateDB(String hibernateGenerateDB) {
			this.hibernateGenerateDB = hibernateGenerateDB;
		}    
		
	    
}