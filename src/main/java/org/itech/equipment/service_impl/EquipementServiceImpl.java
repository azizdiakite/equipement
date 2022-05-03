/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.EquipementRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Equipement;
import org.itech.equipment.service.EquipementService;

import java.util.Collections;
import java.util.List;

/**
 * <h2>EquipementServiceimpl</h2>
 */
@Service
@Transactional
public class EquipementServiceImpl implements EquipementService {

	@Autowired
	private EquipementRepository repository;

	@Override
	public Equipement create(Equipement d) {

		Equipement entity;

		try {
			entity = repository.save(d);

		} catch (Exception ex) {
			return null;
		}
		return entity;
	}

	@Override
	public Equipement update(Equipement d) {
		Equipement c;

		try {
			c = repository.saveAndFlush(d);

		} catch (Exception ex) {
			return null;
		}
		return c;
	}

	@Override
	public Equipement createOrUpdate(Equipement d) {
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
	public Equipement getOne(int id) {
		Equipement t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<Equipement> getAll() {
		List<Equipement> lst;

		try {
			lst = repository.findAll();

		} catch (Exception ex) {
			ex.printStackTrace();
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
