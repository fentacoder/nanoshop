package com.example.onlineordering.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.onlineordering.dao.ItemDAO;
import com.example.onlineordering.model.Item;

@Repository
@Transactional
public class ItemDAOImpl extends JdbcDaoSupport implements ItemDAO{

	@Autowired
    public ItemDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	
	@Override
	public List<Item> getAllItems() {
		
		String sql = " select * from items";
 
        List<Item> items = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Item>(Item.class));
        
		return items;
	}


	@Override
	public void save(Item item) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Item item) {
		// TODO Auto-generated method stub
		
	}
	

}
