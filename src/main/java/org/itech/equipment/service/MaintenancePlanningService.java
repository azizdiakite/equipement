/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.MaintenancePlanning;

import java.util.List;

/**
 * <h2>MaintenancePlanningServiceimpl</h2>
 */
public interface MaintenancePlanningService {
	MaintenancePlanning create(MaintenancePlanning d);

	MaintenancePlanning update(MaintenancePlanning d);

	MaintenancePlanning getOne(int id);

	MaintenancePlanning createOrUpdate(MaintenancePlanning d);

	List<MaintenancePlanning> getAll();

	long getTotal();

	boolean delete(int id);
}
