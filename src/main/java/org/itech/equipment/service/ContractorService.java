/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.Contractor;

import java.util.List;
import java.util.Map;

/**
 * <h2>ContractorServiceimpl</h2>
 */
public interface ContractorService {
	Contractor create(Contractor d);

	Contractor update(Contractor d);

	Contractor createOrUpdate(Contractor d);
	
	List<Map<String, Object>> getContractorIdAndNames();

	Contractor getOne(int id);

	List<Contractor> getAll();

	long getTotal();

	boolean delete(int id);
}
