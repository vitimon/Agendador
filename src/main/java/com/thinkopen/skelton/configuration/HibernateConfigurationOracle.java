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

//@PropertySource({"classpath:/configuration.properties"})
//@EnableTransactionManagement
//@Configuration
public class HibernateConfigurationOracle {
	
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
	  
	  /***dbcp2 confgiuration***/
	  @Value("${dbcp2.validationQuery}")
	  private String  validationQuery;
	  
	  @Value("${dbcp2.validationQueryTimeoutSeconds}")
	  private String  validationQueryTimeoutSeconds;
	  
	  @Value("${dbcp2.testOnBorrow}")
	  private String  testOnBorrow;
			  
	  @Value("${dbcp2.initialSize}")
	  private String  initialSize;
	  
	  @Value("${dbcp2.maxTotal}")
	  private String  maxTotal;
			  
	  @Value("${dbcp2.maxIdle}")
	  private String  maxIdle;
					  
	  @Value("${dbcp2.maxWaitMillis}")
	  private Long  maxWaitMillis;	  

	  @Value("${dbcp2.timeBetweenEvictionRunsMillis}")
	  private String  timeBetweenEvictionRunsMillis;
	  
	  @Value("${dbcp2.minEvictableIdleTimeMillis}")
	  private String  minEvictableIdleTimeMillis;
	  
	  @Value("${dbcp2.maxConnLifetimeMillis}")
	  private String  maxConnLifetimeMillis;	  
			  

	  
	 
	
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
	        dataSource.setValidationQuery(validationQuery);
	        dataSource.setValidationQueryTimeout(Integer.parseInt(validationQueryTimeoutSeconds));
	        dataSource.setTestOnBorrow(Integer.parseInt(testOnBorrow) == 1 ? true : false);
	        dataSource.setInitialSize(Integer.parseInt(initialSize));
	        dataSource.setMaxTotal(Integer.parseInt(maxTotal));
	        dataSource.setMaxIdle(Integer.parseInt(maxIdle));
	        dataSource.setMaxWaitMillis(maxWaitMillis);
	        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(minEvictableIdleTimeMillis));	        
	        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(timeBetweenEvictionRunsMillis));
	        dataSource.setMaxConnLifetimeMillis(Long.parseLong(maxConnLifetimeMillis));
	        
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

		public String getValidationQuery() {
			return validationQuery;
		}

		public void setValidationQuery(String validationQuery) {
			this.validationQuery = validationQuery;
		}

		public String getValidationQueryTimeoutSeconds() {
			return validationQueryTimeoutSeconds;
		}

		public void setValidationQueryTimeoutSeconds(String validationQueryTimeoutSeconds) {
			this.validationQueryTimeoutSeconds = validationQueryTimeoutSeconds;
		}

		public String getTestOnBorrow() {
			return testOnBorrow;
		}

		public void setTestOnBorrow(String testOnBorrow) {
			this.testOnBorrow = testOnBorrow;
		}

		public String getInitialSize() {
			return initialSize;
		}

		public void setInitialSize(String initialSize) {
			this.initialSize = initialSize;
		}

		public String getMaxTotal() {
			return maxTotal;
		}

		public void setMaxTotal(String maxTotal) {
			this.maxTotal = maxTotal;
		}

		public String getMaxIdle() {
			return maxIdle;
		}

		public void setMaxIdle(String maxIdle) {
			this.maxIdle = maxIdle;
		}

		public Long getMaxWaitMillis() {
			return maxWaitMillis;
		}

		public void setMaxWaitMillis(Long maxWaitMillis) {
			this.maxWaitMillis = maxWaitMillis;
		}

		public String getTimeBetweenEvictionRunsMillis() {
			return timeBetweenEvictionRunsMillis;
		}

		public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
			this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
		}

		public String getMinEvictableIdleTimeMillis() {
			return minEvictableIdleTimeMillis;
		}

		public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
			this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
		}

		public String getMaxConnLifetimeMillis() {
			return maxConnLifetimeMillis;
		}

		public void setMaxConnLifetimeMillis(String maxConnLifetimeMillis) {
			this.maxConnLifetimeMillis = maxConnLifetimeMillis;
		}
	    
	    
	    
}