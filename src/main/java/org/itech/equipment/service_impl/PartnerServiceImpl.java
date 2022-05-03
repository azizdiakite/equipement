/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.PartnerRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Partner;
import org.itech.equipment.service.PartnerService;

import java.util.Collections;
import java.util.List;

/**
 * <h2>PartnerServiceimpl</h2>
 */
@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {

	@Autowired
	private PartnerRepository repository;

	@Override
	public Partner create(Partner d) {

		Partner entity;

		try {
			entity = repository.save(d);

		} catch (Exception ex) {
			return null;
		}
		return entity;
	}

	@Override
	public Partner update(Partner d) {
		Partner c;

		try {
			c = repository.saveAndFlush(d);

		} catch (Exception ex) {
			return null;
		}
		return c;
	}

	@Override
	public Partner createOrUpdate(Partner d) {
		if (ObjectUtils.isEmpty(d.getId())) {
			return this.create(d);
		}
		return repository.saveAndFlush(d);
	}

	@Override
	public Partner getOne(int id) {
		Partner t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<Partner> getAll() {
		List<Partner> lst;

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
