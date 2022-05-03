/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.Lab;

import java.util.List;
import java.util.Map;

/**
 * <h2>LabServiceimpl</h2>
 */
public interface LabService {
	Lab create(Lab d);

	Lab update(Lab d);

	Lab getOne(int id);

	Lab createOrUpdate(Lab d);
	
	List<Map<String, Object>> getLabsIdAndNames();

	List<Lab> getAll();

	long getTotal();

	boolean delete(int id);
}
