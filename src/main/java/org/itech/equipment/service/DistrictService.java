/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.District;

import java.util.List;

/**
 * <h2>DictrictServiceimpl</h2>
 */
public interface DistrictService {
	District create(District d) throws Exception;

	District update(District d) throws Exception;

	District createOrUpdate(District d) throws Exception;

	District getOne(int id);

	List<District> getAll();

	long getTotal();

	boolean delete(int id);
}
