/* 
 * Created on 2021-07-09 ( Date ISO 2021-07-09 - Time 06:18:50 )
 * @author Pascal
 */
package org.itech.equipment.service;

import java.util.List;
import java.util.Optional;

import org.itech.equipment.model.AppUser;

/**
 * <h2>AppUserServiceimpl</h2>
 */
public interface AppUserService {
	AppUser create(AppUser d, boolean isPasswordCrypted);

	AppUser update(AppUser d, boolean isPasswordCrypted);

	AppUser getOne(Integer id);

	List<AppUser> getAll();

	public void updateLastLogin(String userName);

	public AppUser findUserByEmail(String email);

	public Optional<AppUser> findUserById(int id);

	public List<AppUser> findActiveUser();

	public List<AppUser> findUsersIdName();

	public List<AppUser> findInactiveUser();

	public List<AppUser> findLockedUser();

	public List<AppUser> findUnLockedUser();

	public List<AppUser> findUsers();

	long getTotal();

	boolean delete(Integer id);

}
