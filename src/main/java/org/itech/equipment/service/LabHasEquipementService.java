/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.LabHasEquipement;

import java.util.List;
import java.util.Map;

/**
 * <h2>LabHasEquipementServiceimpl</h2>
 */
public interface LabHasEquipementService {
	LabHasEquipement create(LabHasEquipement d);

	LabHasEquipement update(LabHasEquipement d);

	LabHasEquipement createOrUpdate(LabHasEquipement d);

	LabHasEquipement getOne(int id);

	List<LabHasEquipement> getAll();

	List<LabHasEquipement> findByLabId(Integer labId);

	long getTotal();

	boolean delete(int id);

	List<Map<String, Object>> getEquipementList(Integer labId);

	List<Map<String, Object>> getEquipementList(Integer labId, int status);

}
