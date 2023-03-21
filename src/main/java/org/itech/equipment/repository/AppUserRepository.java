/*
 * Java domain class for entity "AppUser" 
 * Created on 2021-07-09 ( Date ISO 2021-07-09 - Time 06:18:50 )
 * @author Pascal
 */
package org.itech.equipment.repository;

import java.util.List;

import org.itech.equipment.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * <h2>AppUserRepository</h2>
 *
 * createdAt : 2021-07-09 - Time 06:18:50
 * <p>
 * Description: "AppUser" Repository
 */

public interface AppUserRepository extends JpaRepository<AppUser, Integer>, JpaSpecificationExecutor<AppUser> {

	public AppUser findByEmail(String email);

	@Query(value = "select u from AppUser u where u.locked = 'N' and u.active = 'Y' ")
	public List<AppUser> findUsersIdName();

	public List<AppUser> findByRole(String role);

	public List<AppUser> findByActive(String active);

	public List<AppUser> findByLocked(String locked);

	public AppUser findByPhoneContact(String contact);

}
