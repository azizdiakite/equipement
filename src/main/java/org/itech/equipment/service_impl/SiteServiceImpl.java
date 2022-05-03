/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.SiteRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Site;
import org.itech.equipment.service.SiteService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * <h2>SiteServiceimpl</h2>
 */
@Service
@Transactional
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteRepository repository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Site create(Site d) {

		Site entity;

		try {
			entity = repository.save(d);

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return entity;
	}

	@Override
	public Site update(Site d) {
		Site c;

		try {
			c = repository.saveAndFlush(d);

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return c;
	}

	@Override
	public Site createOrUpdate(Site d) {
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
	public Site getOne(int id) {
		Site t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<Map<String, Object>> getSiteIdAndNames() {
		String sql = "SELECT id,name FROM site ORDER BY name ";
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
	public List<Site> getAll() {
		List<Site> lst;

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
