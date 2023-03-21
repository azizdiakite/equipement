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
import org.itech.equipment.model.Maintenance;
import org.itech.equipment.model.Panne;
import org.itech.equipment.service.PanneService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * <h2>PanneServiceimpl</h2>
 */
@Service
@Transactional
public class PanneServiceImpl implements PanneService {

	@Autowired
	private PanneRepository repository;

	@PersistenceContext
	private EntityManager em;

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

	@Override
	public List<Maintenance> getAllPannes() {
		List<Maintenance> lst;

		try {
			lst = repository.getAllPannes();

		} catch (Exception ex) {
			return Collections.emptyList();
		}
		return lst;
	}

	@Override
	public List<Map<String, Object>> getPannes(boolean all) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT s.name labo, eq.equipement_name equipement,le.serial_number, "
				+ " p.type,p.description,p.date, p.report_date, p.reporter_name,"
				+ " p.contractor_inform_date,m.start_date, m.end_date, c.name contractor,c.rep_name,"
				+ " le.status,p.comment,p.id panneId, le.id labEquipementId "
				+ " FROM panne p LEFT JOIN maintenance m ON m.panne_id = p.id "
				+ " JOIN lab_has_equipement le ON le.id = p.lab_has_equipement_id "
				+ " JOIN equipement eq ON eq.id = le.equipement_id "
				+ " LEFT JOIN contractor c ON c.id = le.contractor_id JOIN lab l ON l.id = le.lab_id "
				+ " JOIN site s ON s.id = l.site_id WHERE 1 ");

		if (!all) {
			sql.append(" AND le.status = 2");
		}

		// System.out.println(sql);
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("labName", o[0]);
				map.put("equipementName", o[1]);
				map.put("serialNumber", o[2]);
				map.put("type", o[3]);
				map.put("description", o[4]);
				map.put("panneDate", o[5]);
				map.put("panneReportedDate", o[6]);
				map.put("panneReporterName", o[7]);
				map.put("contractorInformDate", o[8]);
				map.put("maintenanceStartDate", o[9]);
				map.put("maintenanceEndDate", o[10]);
				map.put("contractorName", o[11]);
				map.put("contractorRepName", o[12]);
				map.put("status", o[13]);
				map.put("comment", o[14]);
				map.put("panneId", o[15]);
				map.put("labEquipementId", o[16]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
