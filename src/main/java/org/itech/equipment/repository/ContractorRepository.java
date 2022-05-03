/*
 * Java domain class for entity "Contractor" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
 */
package org.itech.equipment.repository;
import org.itech.equipment.model.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <h2>ContractorRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:24
 * <p>
 * Description: "Contractor" Repository
 */


public interface ContractorRepository  extends JpaRepository<Contractor, Integer> , JpaSpecificationExecutor<Contractor> {

}
