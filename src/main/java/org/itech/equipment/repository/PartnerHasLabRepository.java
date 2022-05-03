/*
 * Java domain class for entity "PartnerHasLab" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.repository;
import org.itech.equipment.model.PartnerHasLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <h2>PartnerHasLabRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:25
 * <p>
 * Description: "PartnerHasLab" Repository
 */


public interface PartnerHasLabRepository  extends JpaRepository<PartnerHasLab, Integer> , JpaSpecificationExecutor<PartnerHasLab> {

}
