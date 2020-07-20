package com.example.onlineordering.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.onlineordering.dao.StripeOrderDAO;
import com.example.onlineordering.model.Order;

@Repository
@Transactional
public class StripeOrderDAOImpl extends JdbcDaoSupport implements StripeOrderDAO{

	@Autowired
    public StripeOrderDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

	@Override
	public List<Order> getAllOrders() {
		String sql = " select * from orders";
		 
	    List<Order> orders = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Order>(Order.class));
	    
		return orders;
	}

	@Override
	public void save(Order order) {
		this.getJdbcTemplate().update(
			    "INSERT INTO stripes (item_id,user_id,order_date) VALUES (?, ?,?)",
			   order.getItemId(),order.getUserId(), order.getOrderDate());
	}

	@Override
	public void delete(int id) {
		this.getJdbcTemplate().update(
			    "Delete from stripes where id=?",
			    id);
	}

	@Override
	public Order getOrder(int id) {
		String sql = " select * from stripes where id=?";
	Object[] params = new Object[] { id };
    
	try {
		Order order = this.getJdbcTemplate().queryForObject(sql,params,Order.class);
		return order;
    } 
    catch (EmptyResultDataAccessException e) {
        return null;
    }
	}

	
}
