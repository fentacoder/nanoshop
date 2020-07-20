package com.example.onlineordering.daoimpl;
import java.util.List;

import javax.sql.DataSource;

import com.example.onlineordering.dao.AppUserDAO;
import com.example.onlineordering.mapper.AppUserMapper;
import com.example.onlineordering.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
@Transactional
public class AppUserDAOImpl extends JdbcDaoSupport implements AppUserDAO{
 
    @Autowired
    public AppUserDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
	@Override
	public AppUser findUserAccount(String userName) {
		        // Select .. from App_User u Where u.User_Name = ?
		        String sql = AppUserMapper.BASE_SQL + " where u.User_Name = ? and u.enabled=1";
		 
		        Object[] params = new Object[] { userName };
		        AppUserMapper mapper = new AppUserMapper();
		        try {
		            AppUser userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		            return userInfo;
		        } 
		        catch (EmptyResultDataAccessException e) {
		            return null;
		        }
	}

	@Override
	public List<AppUser> getAllUsers() {
		String sql = AppUserMapper.BASE_SQL;
		 
        try {
            List<AppUser> list= this.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<AppUser>(AppUser.class));
            return list;
        } 
        catch (EmptyResultDataAccessException e) {
            return null;
        }
	}

	@Override
	public void save(AppUser theUser) {
		String encrypted_password=passwordEncoder.encode(theUser.getEncrytedPassword());
		// save in user table
		this.getJdbcTemplate().update(
			    "INSERT INTO app_user (user_name, encryted_password, email, enabled) VALUES (?, ?,?,?)",
			    theUser.getUserName(),encrypted_password, theUser.getEmail(), 1);
		
		// now give new user to user role
		AppUser newUser =findUserAccountforReg(theUser.getUserName());
		
		// and save record in user_role table
		this.getJdbcTemplate().update(
			    "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)",
			    newUser.getUserId(), 2);
	}

	@Override
	public AppUser findUserAccountforReg(String userName) {
		 String sql = AppUserMapper.BASE_SQL + " where u.User_Name = ?";
		 
	        Object[] params = new Object[] { userName };
	        AppUserMapper mapper = new AppUserMapper();
	        try {
	            AppUser userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
	            return userInfo;
	        } 
	        catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	}

	@Override
	public void update(AppUser theUser) {
		
		String encrypted_password=passwordEncoder.encode(theUser.getEncrytedPassword());
		
		this.getJdbcTemplate().update(
			    "UPDATE app_user SET user_name=?, encryted_password=?, email=? where user_id=?",
			    theUser.getUserName(),encrypted_password, theUser.getEmail(), theUser.getUserId());
		
	}

	@Override
	public void disableUser(int id) {
		try {
		this.getJdbcTemplate().update(
			    "UPDATE app_user SET enabled=0 where user_id=?",id);
		}
		catch(Exception e){
			
		}
	}
}