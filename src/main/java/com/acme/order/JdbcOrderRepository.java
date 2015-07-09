package com.acme.order;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.acme.order.pizza.PizzaOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Configuration
@ComponentScan(basePackages = "com.acme")
public class JdbcOrderRepository implements OrderRepository {

	@Autowired
	private BasicDataSource dataSource;

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public String save(PizzaOrder order) {
		
		return this.jdbcTemplate.queryForObject("insert into order_t values(?, ?, ?, ? ,?)", String.class,
				order.getId(),
				order.getState() ,
				order.getPizzaType() ,
				order.getEstimatedTime() ,
				order.getFinishTime());
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub

	}

	@Override
	public PizzaOrder get(String pizzaOrderId) {
		return this.jdbcTemplate.queryForObject("select * from order_t where order_t.id = ?", new Object[]{pizzaOrderId} , PizzaOrder.class);
	}

	@Override
	public List<PizzaOrder> findAll() {
		List<PizzaOrder> list = this.jdbcTemplate.queryForList("Select * from order_t", PizzaOrder.class);
		return list;
	}

	@Override
	public List<PizzaOrder> findByOrderStatus(OrderStatus orderStatus) {
		return this.jdbcTemplate.queryForList("select * from order_t where order_t.status = ?", PizzaOrder.class , orderStatus);
	}

	@Bean
	public BasicDataSource createDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/pizza-tutorial");
		ds.setUsername("dbuser");
		ds.setPassword("dbpass");
		return ds;
	}
	
	

}
