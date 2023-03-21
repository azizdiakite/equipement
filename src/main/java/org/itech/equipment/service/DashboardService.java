/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.itech.equipment.model.LabHasEquipement;

public interface DashboardService {
	Map<String, Object> getEquipementCount(Integer labId, Integer equipementId);

	List<Map<String, Object>> getEquipementCountByEquipement(Integer labId, Integer equipementId);

	List<Map<String, Object>> getEquipementCountByLabType(Integer labId, Integer equipementId);

	/**
	 * Get Warrant or Contrat
	 * 
	 * @param labId
	 * @param equipementId
	 * @return
	 */
	Map<String, Object> getEquipementWarrant(Integer labId, Integer equipementId);

	List<Map<String, Object>> getMaintenanceByType(Integer labId, Integer equipementId, Date start, Date end);

	List<Map<String, Object>> getPanneByType(Integer labId, Integer equipementId, Date start, Date end);

	List<Map<String, Object>> getPreventiveMaintenanceDone(Integer labId, Date start, Date end);

	List<Map<String, Object>> getMaintenancePlanning(Integer labId, Date start, Date end);

	List<Map<String, Object>> getCurativeMaintenance(Integer labId, Date start, Date end);

//	List<Map<String, Object>> getLabWithMostEquipement();
//
//	List<Map<String, Object>> getLabWithLeastEquipement();

	List<Map<String, Object>> getTotalBreakdownTime(Integer labId, Date start, Date end);

	List<Map<String, Object>> getTotalRepairTime(Integer labId, Date start, Date end);

	List<Map<String, Object>> getMeanTimeBetweenFailure(Integer labId, Date start, Date end);

	List<Map<String, Object>> getMeanTimeToRepair(Integer labId, Date start, Date end);

	List<Map<String, Object>> getReparationMeanTime(Integer labId, Date start, Date end);

	List<Map<String, Object>> getAvailability(Integer labId, Date start, Date end);

}
