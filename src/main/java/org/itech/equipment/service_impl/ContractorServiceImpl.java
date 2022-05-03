/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.ContractorRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Contractor;
import org.itech.equipment.service.ContractorService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * <h2>ContractorServiceimpl</h2>
 */
@Service
@Transactional
public class ContractorServiceImpl implements ContractorService {

	@Autowired
	private ContractorRepository repository;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Contractor create(Contractor d) {

		Contractor entity;

		try {
			entity = repository.save(d);

		} catch (Exception ex) {
			return null;
		}
		return entity;
	}

	@Override
	public Contractor update(Contractor d) {
		Contractor c;

		try {
			c = repository.saveAndFlush(d);

		} catch (Exception ex) {
			return null;
		}
		return c;
	}

	@Override
	public Contractor createOrUpdate(Contractor d) {
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
	public List<Map<String, Object>> getContractorIdAndNames() {
		String sql = "SELECT id,name FROM contractor ORDER BY name ";
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql);
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", o[0]);
				map.put("name", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Contractor getOne(int id) {
		Contractor t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<Contractor> getAll() {
		List<Contractor> lst;

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
