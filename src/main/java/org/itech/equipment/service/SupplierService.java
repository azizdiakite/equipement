/* 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.service;

import org.itech.equipment.model.Supplier;

import java.util.List;

/**
 * <h2>SupplierServiceimpl</h2>
 */
public interface SupplierService {
	Supplier create(Supplier d);

	Supplier update(Supplier d);

	Supplier createOrUpdate(Supplier d);

	Supplier getOne(int id);

	List<Supplier> getAll();

	long getTotal();

	boolean delete(int id);
}
