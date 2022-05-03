/*
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
*/
package org.itech.equipment.service_impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.itech.equipment.repository.LabHasEquipementRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.model.Equipement;
import org.itech.equipment.model.LabHasEquipement;
import org.itech.equipment.service.LabHasEquipementService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * <h2>LabHasEquipementServiceimpl</h2>
 */
@Service
@Transactional
public class LabHasEquipementServiceImpl implements LabHasEquipementService {

	@Autowired
	private LabHasEquipementRepository repository;
	@PersistenceContext
	private EntityManager em;

	@Override
	public LabHasEquipement create(LabHasEquipement d) {

		LabHasEquipement entity;

		try {
			entity = repository.save(d);

		} catch (Exception ex) {
			return null;
		}
		return entity;
	}

	@Override
	public LabHasEquipement update(LabHasEquipement d) {
		LabHasEquipement c;

		try {
			c = repository.saveAndFlush(d);

		} catch (Exception ex) {
			return null;
		}
		return c;
	}

	@Override
	public LabHasEquipement createOrUpdate(LabHasEquipement d) {
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
	public LabHasEquipement getOne(int id) {
		LabHasEquipement t;

		try {
			t = repository.findById(id).orElse(null);

		} catch (Exception ex) {
			return null;
		}
		return t;
	}

	@Override
	public List<LabHasEquipement> getAll() {
		List<LabHasEquipement> lst;

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
	public List<Map<String, Object>> getEquipementList(Integer labId) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT le.id id, e.equipement_name, le.brand brand, le.serial_number "
				+ " FROM lab_has_equipement le JOIN equipement e ON e.id = le.equipement_id WHERE 1 AND le.lab_id = :lid");

		// System.out.println(sql);
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("lid", labId);
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", o[0]);
				map.put("equipementName", o[1]);
				map.put("brand", o[2]);
				map.put("serialNumber", o[3]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getEquipementList(Integer labId, int status) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT le.id id, e.equipement_name, le.brand brand, le.serial_number "
				+ " FROM lab_has_equipement le JOIN equipement e ON e.id = le.equipement_id WHERE 1 "
				+ " AND le.lab_id = :lid AND le.status = :status");

		// System.out.println(sql);
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("lid", labId);
			query.setParameter("status", status);
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", o[0]);
				map.put("equipementName", o[1]);
				map.put("brand", o[2]);
				map.put("serialNumber", o[3]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<LabHasEquipement> findByLabId(Integer labId) {
		return repository.findByLabId(labId);
	}

	
	
}
