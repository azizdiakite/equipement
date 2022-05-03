/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.DictrictRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.District;
import org.itech.equipment.service.DistrictService;

import java.util.Collections;
import java.util.List;

/**
 * <h2>DictrictServiceimpl</h2>
 */
@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DictrictRepository repository;

	@Override
	public District create(District d) throws Exception {
		return repository.save(d);
	}

	@Override
	public District update(District d) throws Exception {
		return repository.saveAndFlush(d);
	}

	@Override
	public District createOrUpdate(District d) throws Exception {
		if (ObjectUtils.isEmpty(d.getId())) {
			return this.create(d);
		}
		return repository.saveAndFlush(d);
	}

	@Override
	public District getOne(int id) {
		District t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<District> getAll() {
		List<District> lst;

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
