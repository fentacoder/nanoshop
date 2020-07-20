package com.example.onlineordering.daoimpl;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.onlineordering.dao.PaypalOrderDAO;
import com.example.onlineordering.model.Order;

@Repository
@Transactional
public class PaypalOrderDAOImpl extends JdbcDaoSupport implements PaypalOrderDAO{

	@Autowired
    public PaypalOrderDAOImpl(DataSource dataSource) {
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
			    "INSERT INTO orders (item_id,user_id,order_date) VALUES (?, ?,?)",
			   order.getItemId(),order.getUserId(), order.getOrderDate());
	}

	@Override
	public void delete(int id) {
		String query = "delete from orders where id = ?";
	    Object[] params = new Object[] {id};
	    int[] types = {Types.BIGINT};
	    this.getJdbcTemplate().update(query, params,types);
	}

	@Override
	public Order getOrder(int id) {
		String sql = " select * from orders where id=?";
	    try {
	    	return (Order) this.getJdbcTemplate().queryForObject(
	    			sql,
	    			new Object[]{id},
	    			new BeanPropertyRowMapper(Order.class));
        } 
        catch (EmptyResultDataAccessException e) {
            return null;
        }
	}  


	
}
