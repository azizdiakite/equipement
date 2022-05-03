/*
 * Java domain class for entity "Equipement" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:24 )
 * @author Pascal
 */
package org.itech.equipment.repository;
import org.itech.equipment.model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <h2>EquipementRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:24
 * <p>
 * Description: "Equipement" Repository
 */


public interface EquipementRepository  extends JpaRepository<Equipement, Integer> , JpaSpecificationExecutor<Equipement> {

}
