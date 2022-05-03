/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.Region;

import java.util.List;

/**
 * <h2>RegionServiceimpl</h2>
 */
public interface RegionService {
	Region create(Region d) throws Exception;

	Region update(Region d) throws Exception;

	Region createOrUpdate(Region d) throws Exception;

	Region getOne(int id);

	List<Region> getAll();

	long getTotal();

	boolean delete(int id);
}
