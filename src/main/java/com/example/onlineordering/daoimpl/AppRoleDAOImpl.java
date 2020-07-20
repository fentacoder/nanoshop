package com.example.onlineordering.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.onlineordering.dao.AppRoleDAO;


@Repository
@Transactional
public class AppRoleDAOImpl extends JdbcDaoSupport implements AppRoleDAO {

	
    @Autowired
    public AppRoleDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
	@Override
	public List<String> getRoleNames(Long userId) {
		String sql = "Select r.Role_Name " //
                + " from User_Role ur, App_Role r " //
                + " where ur.Role_Id = r.Role_Id and ur.User_Id = ? ";
 
        Object[] params = new Object[] { userId };
 
        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);
 
        return roles;
	}

}
