/*
 * Created on 2021-07-09 ( Date ISO 2021-07-09 - Time 06:18:50 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.AppUser;
import org.itech.equipment.repository.AppUserRepository;
import org.itech.equipment.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <h2>AppUserServiceimpl</h2>
 */
@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserRepository repository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PersistenceContext
	private EntityManager em;

	@Override
	public AppUser create(AppUser d, boolean mustCryptPassword) {

		AppUser entity;

		try {
			if (mustCryptPassword)
				d.setPassword(bCryptPasswordEncoder.encode(d.getPassword()));
			entity = repository.save(d);

		} catch (Exception ex) {
			return null;
		}
		return entity;
	}

	@Override
	public AppUser update(AppUser d, boolean mustCryptPassword) {
		AppUser c;

		try {
			if (mustCryptPassword)
				d.setPassword(bCryptPasswordEncoder.encode(d.getPassword()));
			c = repository.saveAndFlush(d);

		} catch (Exception ex) {
			throw ex;
		}
		return c;
	}

	@Override
	public AppUser getOne(Integer id) {
		AppUser t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<AppUser> getAll() {
		List<AppUser> lst;

		try {
			lst = repository.findAll();

		} catch (Exception ex) {
			return Collections.emptyList();
		}
		return lst;
	}

	@Override
	public long getTotal() {
		long total;

		try {
			total = repository.count();
		} catch (Exception ex) {
			return 0;
		}
		return total;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			repository.deleteById(id);
			return true;

		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<AppUser> findUsers() {
		return repository.findAll();
	}

	@Override
	public List<AppUser> findActiveUser() {
		return repository.findByActive("Y");
	}

	@Override
	public List<AppUser> findInactiveUser() {
		return repository.findByActive("N");
	}

	@Override
	public List<AppUser> findLockedUser() {
		return repository.findByLocked("Y");
	}

	@Override
	public List<AppUser> findUnLockedUser() {
		return repository.findByLocked("N");
	}

	@Override
	public void updateLastLogin(String userName) {
		AppUser u = this.findUserByEmail(userName);
		if (ObjectUtils.isNotEmpty(u)) {
			u.setLastLogin(new Date());
			this.create(u, false);
		}
	}

	@Override
	public List<AppUser> findUsersIdName() {
		return repository.findUsersIdName();
	}

	@Override
	public AppUser findUserByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Optional<AppUser> findUserById(int id) {
		return repository.findById(id);
	}

}
