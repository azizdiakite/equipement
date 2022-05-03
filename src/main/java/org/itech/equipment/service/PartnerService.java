/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.Partner;

import java.util.List;

/**
 * <h2>PartnerServiceimpl</h2>
 */
public interface PartnerService {
	Partner create(Partner d);

	Partner update(Partner d);

	Partner createOrUpdate(Partner d);

	Partner getOne(int id);

	List<Partner> getAll();

	long getTotal();

	boolean delete(int id);
}
