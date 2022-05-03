/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.Maintenance;
import java.util.List;

/**
 * <h2>MaintenanceServiceimpl</h2>
 */
public interface MaintenanceService {
	Maintenance create(Maintenance d);

	Maintenance update(Maintenance d);

	Maintenance createOrUpdate(Maintenance d);

	Maintenance getOne(int id);

	List<Maintenance> getAll();

	List<Maintenance> getPending();

	long getTotal();

	boolean delete(int id);
}
