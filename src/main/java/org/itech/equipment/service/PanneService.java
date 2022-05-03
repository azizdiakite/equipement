/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.Panne;

import java.util.List;

/**
 * <h2>PanneServiceimpl</h2>
 */
public interface PanneService {
	Panne create(Panne d);

	Panne update(Panne d);

	Panne createOrUpdate(Panne d);

	Panne getOne(int id);

	List<Panne> getAll();
	
	List<Panne> getPending();
	
	Panne getLastForEquipement(Integer labEquipementId);

	long getTotal();

	boolean delete(int id);
}
