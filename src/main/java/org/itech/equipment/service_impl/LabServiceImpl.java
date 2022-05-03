/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.LabRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Lab;
import org.itech.equipment.service.LabService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * <h2>LabServiceimpl</h2>
 */
@Service
@Transactional
public class LabServiceImpl implements LabService {

	@Autowired
	private LabRepository repository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Lab create(Lab d) {

		Lab entity;

		try {
			entity = repository.save(d);

		} catch (Exception ex) {
			return null;
		}
		return entity;
	}

	@Override
	public Lab update(Lab d) {
		Lab c;

		try {
			c = repository.saveAndFlush(d);

		} catch (Exception ex) {
			return null;
		}
		return c;
	}

	@Override
	public Lab createOrUpdate(Lab d) {
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
	public Lab getOne(int id) {
		Lab t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<Lab> getAll() {
		List<Lab> lst;

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

	@Override
	public List<Map<String, Object>> getLabsIdAndNames() {
		String sql = "SELECT lab.id id,name FROM lab LEFT JOIN site s ON s.id = lab.site_id  ORDER BY name ";
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

}
