/*
 * Java domain class for entity "Lab" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
 */
package org.itech.equipment.repository;
import org.itech.equipment.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <h2>LabRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:24
 * <p>
 * Description: "Lab" Repository
 */


public interface LabRepository  extends JpaRepository<Lab, Integer> , JpaSpecificationExecutor<Lab> {

}
