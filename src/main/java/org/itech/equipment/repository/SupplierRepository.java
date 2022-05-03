/*
 * Java domain class for entity "Supplier" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.repository;
import org.itech.equipment.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <h2>SupplierRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:25
 * <p>
 * Description: "Supplier" Repository
 */


public interface SupplierRepository  extends JpaRepository<Supplier, Integer> , JpaSpecificationExecutor<Supplier> {

}
