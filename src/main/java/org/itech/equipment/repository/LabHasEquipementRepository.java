/*
 * Java domain class for entity "LabHasEquipement" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.repository;

import java.util.List;

import org.itech.equipment.model.LabHasEquipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <h2>LabHasEquipementRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:25
 * <p>
 * Description: "LabHasEquipement" Repository
 */

public interface LabHasEquipementRepository
		extends JpaRepository<LabHasEquipement, Integer>, JpaSpecificationExecutor<LabHasEquipement> {

	public List<LabHasEquipement> findByLabId(Integer labId);
}
