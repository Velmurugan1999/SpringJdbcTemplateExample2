package com.jdbc;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.jdbc.dao.BankDao;

@Configuration
@ComponentScan("com.jdbc")
public class JdbcConfig {
	@Bean
	public DataSource getDataSource() {
		return new DriverManagerDataSource("jdbc:mysql://localhost:3306/company","root","root");
		
	}
	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplate()
	{
		return new NamedParameterJdbcTemplate(getDataSource());
	}
	@Bean
	public BankDao getDao()
	{
		BankDao dao=new BankDao();
		dao.setTemplate(getJdbcTemplate());
		return dao;
	}
	
}
