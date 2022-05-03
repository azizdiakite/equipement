/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.PanneRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Panne;
import org.itech.equipment.service.PanneService;

import java.util.Collections;
import java.util.List;

/**
 * <h2>PanneServiceimpl</h2>
 */
@Service
@Transactional
public class PanneServiceImpl implements PanneService {

	@Autowired
	private PanneRepository repository;

	@Override
	public Panne create(Panne d) {

		Panne entity;

		try {
			entity = repository.save(d);

		} catch (Exception ex) {
			return null;
		}
		return entity;
	}

	@Override
	public Panne update(Panne d) {
		Panne c;

		try {
			c = repository.saveAndFlush(d);

		} catch (Exception ex) {
			return null;
		}
		return c;
	}

	@Override
	public Panne createOrUpdate(Panne d) {
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
	public Panne getOne(int id) {
		Panne t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<Panne> getAll() {
		List<Panne> lst;

		try {
			lst = repository.findAll();

		} catch (Exception ex) {
			return Collections.emptyList();
		}
		return lst;
	}

	@Override
	public List<Panne> getPending() {
		List<Panne> lst;

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
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public Panne getLastForEquipement(Integer labEquipementId) {
		try {
			return repository.getLastForEquipement(labEquipementId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
