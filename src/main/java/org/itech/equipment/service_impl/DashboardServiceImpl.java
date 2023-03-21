package org.itech.equipment.service_impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.ObjectUtils;
import org.itech.equipment.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Map<String, Object> getEquipementCount(Integer labId, Integer equipementId) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT COUNT(*) 'all', COUNT(IF(labEquip.status = 1,labEquip.id,null)) 'fonctional', "
				+ " COUNT(IF(labEquip.status <> 1 AND labEquip.status <> 4 ,labEquip.id,null)) 'stop', "
				+ " COUNT(IF(labEquip.status = 3,labEquip.id,null)) 'current' "
				+ " FROM lab_has_equipement labEquip JOIN lab l ON l.id = labEquip.lab_id JOIN equipement e ON e.id = labEquip.equipement_id WHERE 1");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND l.id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(equipementId)) {
			sql.append(" AND e.id = ").append(equipementId);
		}

		// System.out.println(sql);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			int all = 0;
			int fonctional = 0;
			int stop = 0;
			int current = 0;
			for (Object[] o : results) {
				all += Integer.parseInt(o[0] + "");
				fonctional += Integer.parseInt(o[1] + "");
				stop += Integer.parseInt(o[2] + "");
				current += Integer.parseInt(o[3] + "");
			}
			response.put("all", all);
			response.put("fonctional", fonctional);
			response.put("stop", stop);
			response.put("current", current);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getEquipementCountByEquipement(Integer labId, Integer equipementId) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT e.equipement_name  name,COUNT(labEquip.id) n "
				+ " FROM lab_has_equipement labEquip JOIN lab l ON l.id = labEquip.lab_id RIGHT JOIN "
				+ " equipement e ON e.id = labEquip.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND l.id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(equipementId)) {
			sql.append(" AND e.id = ").append(equipementId);
		}
		sql.append(" GROUP BY name");

		// System.out.println(sql);
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getEquipementCountByLabType(Integer labId, Integer equipementId) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT l.lab_type type, count(labEquip.id) n"
				+ " FROM lab_has_equipement labEquip JOIN lab l ON l.id = labEquip.lab_id JOIN "
				+ " equipement e ON e.id = labEquip.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND l.id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(equipementId)) {
			sql.append(" AND e.id = ").append(equipementId);
		}
		sql.append(" GROUP BY type");

		// System.out.println(sql);
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Map<String, Object> getEquipementWarrant(Integer labId, Integer equipementId) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT COUNT(*) 'total', "
				+ " COUNT(IF((le.warranty_start_date IS NOT NULL AND le.warranty_end_date IS NOT NULL) AND le.warranty_end_date > CURDATE(),le.id,NULL)) 'warrant', "
				+ " COUNT(IF(c.id IS NOT NULL,le.id,NULL)) 'contrat' "
				+ " FROM lab_has_equipement le JOIN lab l ON l.id = le.lab_id "
				+ " LEFT JOIN contractor c ON le.id = c.id "
				+ " JOIN equipement e ON e.id = le.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND l.id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(equipementId)) {
			sql.append(" AND e.id = ").append(equipementId);
		}

		// System.out.println(sql);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			int all = 0;
			int warrant = 0;
			int contract = 0;
			for (Object[] o : results) {
				all += Integer.parseInt(o[0] + "");
				warrant += Integer.parseInt(o[1] + "");
				contract += Integer.parseInt(o[2] + "");
			}
			response.put("all", all);
			response.put("warrant", warrant);
			response.put("contract", contract);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getMaintenanceByType(Integer labId, Integer equipementId, Date start, Date end) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT m.type, COUNT(*) FROM lab_has_equipement le JOIN lab l ON l.id = le.lab_id "
				+ " JOIN maintenance m ON le.id = m.id JOIN equipement e ON e.id = le.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND l.id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(equipementId)) {
			sql.append(" AND e.id = ").append(equipementId);
		}
		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND m.start_date >= ").append(start);
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND m.end_date <= ").append(end);
		}
		sql.append(" GROUP BY m.type");

		// System.out.println(sql);
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getPanneByType(Integer labId, Integer equipementId, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT  p.type,COUNT(p.id) FROM  panne p "
				+ " JOIN lab_has_equipement le ON p.lab_has_equipement_id = le.id "
				+ " JOIN equipement eq ON eq.id = le.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND le.lab_id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(equipementId)) {
			sql.append(" AND eq.id = ").append(equipementId);
		}
		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND p.date >= '").append(sdf.format(start) + "'");
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND p.date <= '").append(sdf.format(end) + "'");
		}
		sql.append(" GROUP BY p.type ORDER BY p.type");

		//System.out.println(sql);
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getMaintenancePlanning(Integer labId, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT eq.equipement_name,COUNT(mp.id) FROM maintenance_planning mp JOIN "
				+ " lab_has_equipement le ON mp.lab_has_equipement_id = le.id "
				+ " RIGHT JOIN equipement eq ON eq.id = le.equipement_id ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND le.lab_id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND mp.date >= '").append(sdf.format(start) + "'");
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND mp.date <= '").append(sdf.format(end) + "'");
		}
		sql.append(" GROUP BY eq.equipement_name ORDER BY eq.equipement_name");

		//System.out.println(sql);
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getPreventiveMaintenanceDone(Integer labId, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT eq.equipement_name,COUNT(m.id) FROM "
				+ " (SELECT * FROM maintenance WHERE type='PREVENTIVE') m RIGHT JOIN "
				+ " lab_has_equipement le ON m.lab_has_equipement_id = le.id "
				+ " RIGHT JOIN equipement eq ON eq.id = le.equipement_id ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND le.lab_id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND m.start_date >= '").append(sdf.format(start) + "'");
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND m.end_date <= '").append(sdf.format(end) + "'");
		}

		sql.append(" GROUP BY eq.equipement_name ORDER BY eq.equipement_name");

		// System.out.println(sql);
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getCurativeMaintenance(Integer labId, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer("");
		sql.append("  SELECT eq.equipement_name,COUNT(m.id) FROM "
				+ " (SELECT * FROM maintenance WHERE type='CURATIVE') m JOIN "
				+ " lab_has_equipement le ON m.lab_has_equipement_id = le.id "
				+ " JOIN equipement eq ON eq.id = le.equipement_id ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND le.lab_id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND m.start_date >= '").append(sdf.format(start) + "'");
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND m.end_date <= '").append(sdf.format(end) + "'");
		}
		sql.append(" GROUP BY eq.equipement_name ORDER BY eq.equipement_name");

		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getTotalBreakdownTime(Integer labId, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT eq.equipement_name, "
				+ " SUM(TIMESTAMPDIFF(DAY,IF(p.date IS NULL,CURDATE(),p.date), IF(m.end_date IS NULL,CURDATE(),m.end_date))) 'break_days' "
				+ " FROM  panne p JOIN lab_has_equipement le ON p.lab_has_equipement_id = le.id "
				+ " LEFT JOIN (SELECT * FROM maintenance WHERE type='CURATIVE') m ON m.lab_has_equipement_id = le.id "
				+ " RIGHT JOIN equipement eq ON eq.id = le.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND le.lab_id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND p.date >= '").append(sdf.format(start) + "'");
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND p.date <= '").append(sdf.format(end) + "'");
		}

		sql.append(" GROUP BY eq.equipement_name ORDER BY 'break_days' DESC");

		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getTotalRepairTime(Integer labId, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT  eq.equipement_name,"
				+ " SUM(TIMESTAMPDIFF(DAY,IF(p.contractor_inform_date IS NULL,p.report_date,p.contractor_inform_date), IF(m.end_date IS NULL,CURDATE(),m.end_date))) 'maintenance_days'"
				+ " FROM  panne p JOIN lab_has_equipement le ON p.lab_has_equipement_id = le.id "
				+ " LEFT JOIN (SELECT * FROM maintenance WHERE type='CURATIVE') m ON m.lab_has_equipement_id = le.id "
				+ " RIGHT JOIN equipement eq ON eq.id = le.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND le.lab_id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND p.date >= '").append(sdf.format(start) + "'");
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND p.date <= '").append(sdf.format(end) + "'");
		}

		sql.append(" GROUP BY eq.equipement_name ORDER BY 'maintenance_days' DESC");

		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getMeanTimeBetweenFailure(Integer labId, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT eq.equipement_name, "
				+ " ROUND((SUM(TIMESTAMPDIFF(DAY,IF(le.installation_date IS NULL,CURDATE(),le.installation_date), CURDATE())) - SUM(TIMESTAMPDIFF(DAY,IF(p.date IS NULL,CURDATE(),p.date), IF(m.end_date IS NULL,CURDATE(),m.end_date)))) / COUNT(p.id),0) 'days'"
				+ " FROM  panne p JOIN lab_has_equipement le ON p.lab_has_equipement_id = le.id "
				+ " LEFT JOIN (SELECT * FROM maintenance WHERE type='CURATIVE') m ON m.lab_has_equipement_id = le.id "
				+ " RIGHT JOIN equipement eq ON eq.id = le.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND le.lab_id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND p.date >= '").append(sdf.format(start) + "'");
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND p.date <= '").append(sdf.format(end) + "'");
		}

		sql.append(" GROUP BY eq.equipement_name ORDER BY 'days' DESC");

		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getMeanTimeToRepair(Integer labId, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT eq.equipement_name, "
				+ " ROUND((SUM(TIMESTAMPDIFF(DAY,IF(p.contractor_inform_date IS NULL,p.report_date,p.contractor_inform_date), IF( m.end_date IS NULL,CURDATE(), m.end_date)))) / COUNT(m.id),0) 'days'"
				+ " FROM (SELECT * FROM maintenance WHERE type='CURATIVE') m  "
				+ " JOIN lab_has_equipement le ON m.lab_has_equipement_id = le.id "
				+ " JOIN panne p ON p.lab_has_equipement_id = le.id "
				+ " RIGHT JOIN equipement eq ON eq.id = le.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND le.lab_id = ").append(labId);
		}

		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND p.date >= '").append(sdf.format(start) + "'");
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND p.date <= '").append(sdf.format(end) + "'");
		}

		sql.append(" GROUP BY eq.equipement_name ORDER BY 'days' DESC");

		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getReparationMeanTime(Integer labId, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer("");
		sql.append("  SELECT eq.equipement_name, "
				+ " ROUND((SUM(TIMESTAMPDIFF(DAY,IF(m.start_date IS NULL,CURDATE(),m.start_date), IF( m.end_date IS NULL,CURDATE(), m.end_date)))) / COUNT(m.id),0) 'days' "
				+ " FROM (SELECT * FROM maintenance WHERE type='CURATIVE') m  "
				+ " JOIN lab_has_equipement le ON m.lab_has_equipement_id = le.id "
				+ " JOIN panne p ON p.lab_has_equipement_id = le.id "
				+ " RIGHT JOIN equipement eq ON eq.id = le.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND le.lab_id = ").append(labId);
		}

		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND p.date >= '").append(sdf.format(start) + "'");
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND p.date <= '").append(sdf.format(start) + "'");
		}

		sql.append(" GROUP BY eq.equipement_name ORDER BY 'days' DESC ");

		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Map<String, Object>> getAvailability(Integer labId, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT   equipement_name, ROUND((mtbf / (mttr + mtbf)) * 100,1) 'availability' FROM "
				+ "  ( SELECT eq.equipement_name, "
				+ "   ROUND((SUM(TIMESTAMPDIFF(DAY,IF(p.contractor_inform_date IS NULL,p.report_date,p.contractor_inform_date), IF( m.end_date IS NULL,CURDATE(), m.end_date)))) / COUNT(m.id),0) 'mttr', "
				+ " ROUND((SUM(TIMESTAMPDIFF(DAY,IF(le.installation_date IS NULL,CURDATE(),le.installation_date), CURDATE())) - SUM(TIMESTAMPDIFF(DAY,IF(p.date IS NULL,CURDATE(),p.date), IF(m.end_date IS NULL,CURDATE(),m.end_date)))) / COUNT(p.id),0) 'mtbf' "
				+ " FROM  panne p JOIN lab_has_equipement le ON p.lab_has_equipement_id = le.id "
				+ " LEFT JOIN (SELECT * FROM maintenance WHERE type='CURATIVE') m ON m.lab_has_equipement_id = le.id "
				+ " RIGHT JOIN equipement eq ON eq.id = le.equipement_id WHERE 1 ");

		if (ObjectUtils.isNotEmpty(labId)) {
			sql.append(" AND le.lab_id = ").append(labId);
		}
		if (ObjectUtils.isNotEmpty(start)) {
			sql.append(" AND p.date >= '").append(sdf.format(start) + "'");
		}

		if (ObjectUtils.isNotEmpty(end)) {
			sql.append(" AND p.date <= '").append(sdf.format(end) + "'");
		}
		sql.append(" GROUP BY eq.equipement_name) r ORDER BY availability DESC ");

		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		try {
			Query query = em.createNativeQuery(sql.toString());
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", o[0]);
				map.put("y", o[1]);
				response.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
