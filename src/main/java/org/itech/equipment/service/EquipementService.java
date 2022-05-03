/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.Equipement;
import java.util.List;

/**
 * <h2>EquipementServiceimpl</h2>
 */
public interface EquipementService {
	Equipement create(Equipement d);

	Equipement update(Equipement d);

	Equipement createOrUpdate(Equipement d);

	Equipement getOne(int id);

	List<Equipement> getAll();

	long getTotal();

	boolean delete(int id);
}
