/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.MaintenanceRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Maintenance;
import org.itech.equipment.service.MaintenanceService;

import java.util.Collections;
import java.util.List;

/**
 * <h2>MaintenanceServiceimpl</h2>
 */
@Service
@Transactional
public class MaintenanceServiceImpl implements MaintenanceService {

	@Autowired
	private MaintenanceRepository repository;

	@Override
	public Maintenance create(Maintenance d) {

		Maintenance entity;

		try {
			entity = repository.save(d);

		} catch (Exception ex) {
			return null;
		}
		return entity;
	}

	@Override
	public Maintenance update(Maintenance d) {
		Maintenance c;

		try {
			c = repository.saveAndFlush(d);

		} catch (Exception ex) {
			return null;
		}
		return c;
	}

	@Override
	public Maintenance createOrUpdate(Maintenance d) {
		try {
			if (ObjectUtils.isEmpty(d.getId())) {
				return this.create(d);
			} else
				return repository.saveAndFlush(d);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Maintenance getOne(int id) {
		Maintenance t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<Maintenance> getAll() {
		List<Maintenance> lst;

		try {
			lst = repository.findAll();

		} catch (Exception ex) {
			return Collections.emptyList();
		}
		return lst;
	}

	@Override
	public List<Maintenance> getPending() {
		List<Maintenance> lst;

		try {
			lst = repository.getPending();

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
	public boolean delete(int id) {
		try {
			repository.deleteById(id);
			return true;

		} catch (Exception ex) {
			return false;
		}
	}

}
