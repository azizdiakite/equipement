/*
 * Java domain class for entity "Panne" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.repository;

import java.util.List;

import org.itech.equipment.model.Panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <h2>PanneRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:25
 * <p>
 * Description: "Panne" Repository
 */

public interface PanneRepository extends JpaRepository<Panne, Integer>, JpaSpecificationExecutor<Panne> {

	@Query(value = "SELECT p FROM Panne p JOIN p.labHasEquipement le WHERE le.status = 2")
	public List<Panne> getPending();

	@Query(value = "SELECT p.* FROM panne p JOIN lab_has_equipement le ON p.lab_has_equipement_id = le.id WHERE le.status = 2 AND le.id = :id ORDER BY p.date DESC limit 1", nativeQuery = true)
	public Panne getLastForEquipement(@Param("id") Integer labEquipementId);

}
