/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.SupplierRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Supplier;
import org.itech.equipment.service.SupplierService;

import java.util.Collections;
import java.util.List;

/**
 * <h2>SupplierServiceimpl</h2>
 */
@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository repository;

	@Override
	public Supplier create(Supplier d) {

		Supplier entity;

		try {
			entity = repository.save(d);

		} catch (Exception ex) {
			return null;
		}
		return entity;
	}

	@Override
	public Supplier update(Supplier d) {
		Supplier c;

		try {
			c = repository.saveAndFlush(d);

		} catch (Exception ex) {
			return null;
		}
		return c;
	}

	@Override
	public Supplier createOrUpdate(Supplier d) {
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
	public Supplier getOne(int id) {
		Supplier t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<Supplier> getAll() {
		List<Supplier> lst;

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
	public boolean delete(int id) {
		try {
			repository.deleteById(id);
			return true;

		} catch (Exception ex) {
			return false;
		}
	}

}
