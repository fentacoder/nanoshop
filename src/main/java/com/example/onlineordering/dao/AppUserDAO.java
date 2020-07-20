package com.example.onlineordering.dao;

import java.util.List;

import com.example.onlineordering.model.AppUser;

public interface AppUserDAO {
	AppUser findUserAccount(String userName);
	AppUser findUserAccountforReg(String userName);
	List<AppUser> getAllUsers();
	public void save(AppUser theUser);
	public void update(AppUser theUser);
	public void disableUser(int id);
}
