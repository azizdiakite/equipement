/*
 * Java domain class for entity "Partner" 
 * Created on 2022-03-29 ( Date ISO 2022-03-29 - Time 08:26:25 )
 * @author Pascal
 */
package org.itech.equipment.repository;
import org.itech.equipment.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <h2>PartnerRepository</h2>
 *
 * createdAt : 2022-03-29 - Time 08:26:25
 * <p>
 * Description: "Partner" Repository
 */


public interface PartnerRepository  extends JpaRepository<Partner, Integer> , JpaSpecificationExecutor<Partner> {

}
