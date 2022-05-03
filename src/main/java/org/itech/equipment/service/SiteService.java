/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.Site;

import java.util.List;
import java.util.Map;

/**
 * <h2>SiteServiceimpl</h2>
 */
public interface SiteService {
	Site create(Site d);

	Site update(Site d);

	Site createOrUpdate(Site d);

	Site getOne(int id);

	List<Site> getAll();
	
	List<Map<String, Object>> getSiteIdAndNames();

	long getTotal();

	boolean delete(int id);
}
