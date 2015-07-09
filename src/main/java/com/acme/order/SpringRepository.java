package com.acme.order;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.acme")
public class SpringRepository {
	
	
	
	
	
	@Bean
	public BasicDataSource createDataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/pizza-tutorial");
		ds.setUsername("dbuser");
		ds.setPassword("dbpass");
		return ds;	
	}
	
	
	
	
}
