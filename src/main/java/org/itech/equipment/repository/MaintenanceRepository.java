/*
 * Java domain class for entity "Maintenance" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.repository;

import java.util.List;

import org.itech.equipment.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * <h2>MaintenanceRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:25
 * <p>
 * Description: "Maintenance" Repository
 */

public interface MaintenanceRepository
		extends JpaRepository<Maintenance, Integer>, JpaSpecificationExecutor<Maintenance> {

	@Query(value = "SELECT p FROM Maintenance p JOIN p.labHasEquipement le WHERE le.status = 3")
	public List<Maintenance> getPending();
}
