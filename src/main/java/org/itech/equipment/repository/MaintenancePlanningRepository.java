/*
 * Java domain class for entity "MaintenancePlanning" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.repository;
import org.itech.equipment.model.MaintenancePlanning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <h2>MaintenancePlanningRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:25
 * <p>
 * Description: "MaintenancePlanning" Repository
 */


public interface MaintenancePlanningRepository  extends JpaRepository<MaintenancePlanning, Integer> , JpaSpecificationExecutor<MaintenancePlanning> {

}
